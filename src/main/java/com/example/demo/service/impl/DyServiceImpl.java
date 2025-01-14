package com.example.demo.service.impl;

import com.example.demo.domain.ApplicationId;
import com.example.demo.domain.DataCenter;
import com.example.demo.domain.FarmerInfo;
import com.example.demo.domain.R;
import com.example.demo.service.TempleteService;
import com.example.demo.util.WebDriverUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Service
public class DyServiceImpl implements TempleteService {

    private static Long userId = 1L;

    /**
     * 抖音扫码登入，但是这里有一个问题，如果是正常使用的模式不应该让用户看到机器操作的页面，但是目前扫码是通关在机器的页面扫码，
     * todo 所以在未来，我们需要改进这个方案，将登入二维码传到输客户端让用户在客户端扫码，
     * todo 如果该账户被风控但是扫码成功后会出现一个接受短信验证码，所以我们还要做风控的处理页面。
     *
     * @param id
     * @return
     */
    @Override
    public R login(Integer id) {
        WebDriver driver = WebDriverUtils.get(id);

        // 获取当前会话的句柄
        String originalHandle = driver.getWindowHandle();

        // 打开新标签页
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank', '_blank');");

        // 切换到新标签页
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // 在新标签页中打开目标页面
        driver.get("https://creator.douyin.com/");
        System.out.println("已在新标签页中打开页面：https://creator.douyin.com/");

        // 等待二维码图片加载
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement qrCodeElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.qrcode-image-QrGzx7 img")));

        // 获取二维码图片的 URL
        String qrCodeUrl = qrCodeElement.getAttribute("src");

        // 输出二维码 URL
        System.out.println("二维码的 URL: " + qrCodeUrl);

        //返回 qr
        return R.ok(qrCodeUrl);
    }


    /**
     * 发布视频，
     * 但是目前有一个问题，就是遇见风控了，需要再次扫码，但是目前我还没有写检测这个风控的逻辑，暂时先放一下。
     * 而且抖音有防机器人检测，如果被检测为机器人那么就会被返回home页面，
     *          todo 所以需要编写一些随机事件去避免机器人检测
     *          或者视频够短足够快，在他没有检测出来就发布（目前采用此方案，但是不可靠，时间紧以后再说）
     *
     * @param id
     * @return
     */
    @Override
    public R publishAVideo(Integer id, String path) {
        WebDriver driver = WebDriverUtils.get(id);
        String originalHandle = driver.getWindowHandle();

        // 打开新标签页
        ((JavascriptExecutor) driver).executeScript("window.open('about:blank', '_blank');");

        // 切换到新标签页
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        // 进入上传视频页面
        driver.get("https://creator.douyin.com/creator-micro/content/upload");
        System.err.println("已经切换到投稿页面");

        try {
            // 等待页面加载完成
            Thread.sleep(5000);

            // 上传文件
            WebElement uploadInput = driver.findElement(By.xpath("//input[@type='file']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", uploadInput);
            //String filePath = "/Users/yangyida/Documents/testima.mp4";
            String filePath = path;
            uploadInput.sendKeys(filePath);
            System.out.println("文件路径已设置：" + filePath);

            // 等待文件上传完成
            Thread.sleep(10000); // 根据实际情况调整等待时间

            // 在标题输入框中输入 "123"
            WebElement titleInput = driver.findElement(By.xpath("//input[@placeholder='填写作品标题，为作品获得更多流量']"));
            titleInput.sendKeys("123");
            System.out.println("标题已输入：123");

//            // 在简介框中输入 "6666"
//            WebElement descriptionInput = driver.findElement(By.xpath("//div[@class='zone-container editor-kit-container editor editor-comp-publish notranslate mozilla']"));
//            ((JavascriptExecutor) driver).executeScript("arguments[0].innerHTML = '<div><span>6666</span></div>';", descriptionInput);
//            System.out.println("作品简介已输入：6666");

//            // 等待“作品未见异常”提示
//            WebDriverWait wait = new WebDriverWait(driver, 30);
//            wait.until(ExpectedConditions.textToBePresentInElementLocated(
//                    By.xpath("//*[contains(text(),'作品未见异常')]"), "作品未见异常"));
//            System.out.println("提示：作品未见异常");

            //模拟人为操作 =》 随机点击页面 体部/页面边缘
//            Random random = new Random();
//            int randomNumber = random.nextInt(2);
//            WebElement body = null;
//            if(randomNumber == 0){
//                body = driver.findElement(By.tagName("body"));
//            } else if (randomNumber == 1) {
//                body = driver.findElement(By.tagName("html"));
//            }
//            body.click();
//            System.out.println("body / html 已点击");

            // 点击发布按钮
            WebElement publishButton = driver.findElement(By.xpath("//button[contains(text(),'发布')]"));
            publishButton.click();
            System.out.println("发布按钮已点击");

        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("上传失败：" + e.getMessage());
        } finally {
            // 返回到原来的标签页
            driver.switchTo().window(originalHandle);
        }

        return R.ok("视频发布成功");
    }

    /**
     * 获取抖音热门视频
     * 在未来这个可能需要改版，但是目前来说，我需要快点把这个项目做完，暂时这么处理
     *
     * @return
     */
    @Override
    public R getPopularVideos() {

        try {
            // 存储热点信息的列表
            List<Map<String, String>> hotList = new ArrayList<>();

            boolean cacheToken = false;

            if (DataCenter.HotData.containsKey(ApplicationId.Douyin)) {
                Date date1 = new Date();
                Date times = (Date) DataCenter.HotData.get(ApplicationId.Douyin).get("times");

                // 判断时差是否大于等于 2 分钟
                if ((date1.getTime() - times.getTime()) >= 2 * 60 * 1000) {
                    System.out.println("两个时间的时差大于或等于 2 分钟");
                } else {
                    System.out.println("两个时间的时差小于 2 分钟" + date1 + "  and  " + times + "当前时差" + (date1.getTime() - times.getTime()));
                    cacheToken = true;
                }
            }


            if (cacheToken) {
                hotList = (List<Map<String, String>>) DataCenter.HotData.get(ApplicationId.Douyin).get("list");
                System.out.println("执行了缓存");
            } else {
                // 获取 WebDriver 实例
                HashMap<String, Object> map = WebDriverUtils.create();
                WebDriver webDriver = (WebDriver) map.get("obj");


                // 打开目标页面
                webDriver.get("https://www.iesdouyin.com/share/billboard/?id=0&utm_source=copy&utm_campaign=client_share&utm_medium=android&app=aweme");

                // 等待页面加载完成
                WebDriverWait wait = new WebDriverWait(webDriver, 30); // Selenium 3 版本
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("list-container")));


                // 找到包含热点信息的主容器
                WebElement listContainer = webDriver.findElement(By.className("list-container"));

                // 获取热点标题和热点数量的元素列表
                List<WebElement> titles = listContainer.findElements(By.cssSelector("span.sentence.nowrap"));
                List<WebElement> hotValues = listContainer.findElements(By.cssSelector("div.hot-value > div.value"));

                // 确保两个列表的长度相同
                if (titles.size() != hotValues.size()) {
                    System.out.println("标题和热点数量不匹配！");
                    return R.ok("标题和热点数量不匹配！");
                }

                // 遍历标题和热点数量，将其存入 Map 并添加到 List 中
                for (int i = 0; i < titles.size(); i++) {
                    String title = titles.get(i).getText(); // 获取标题文字
                    String hotNumber = hotValues.get(i).getText(); // 获取热点数量文字

                    Map<String, String> hotMap = new HashMap<>();
                    hotMap.put("title", title);
                    hotMap.put("hotnumber", hotNumber);

                    hotList.add(hotMap);

                    HashMap<String, Object> cacheMap = new HashMap<>();
                    cacheMap.put("times", new Date());
                    cacheMap.put("list", hotList);
                    DataCenter.HotData.put(ApplicationId.Douyin, cacheMap);
                }
                webDriver.quit();
            }


            // 返回成功结果
            return R.ok(hotList);

        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("获取热门视频失败：" + e.getMessage());
        }
    }


    /**
     * todo
     * 获取抖音热门话题 通关 当前登入的用户
     * 在未来这个可能需要改版，但是目前来说，我需要快点把这个项目做完，暂时这么处理 但是这么做很麻烦，暂时搁置
     *
     * @param id
     * @return
     */
    @Override
    public R getHotTopicByDouyinId(Integer id) {
//        WebDriver webDriver = WebDriverUtils.get(id);
//        webDriver.get("https://creator.douyin.com/creator-micro/creative-guidance?discover_menu=2");
        return null;
    }


    /**
     * 获取账号信息
     * todo:存在bug，获取喜欢数和关注一样多 需要修复
     *
     * @param id
     * @return
     */
    @Override
    public R getFarmerInfo(@RequestParam("id") Integer id, @RequestParam("PCID") Integer PCID, @RequestParam("name") String name) {
        // 从 WebDriverUtils 工具类获取 WebDriver 实例
        WebDriver webDriver = WebDriverUtils.get(id);

        try {
            // 目标抖音创作者页面 URL
            String url = "https://creator.douyin.com/creator-micro/home";
            webDriver.get(url);

            // 等待页面加载完成，必要时可以改为显式等待
            Thread.sleep(3000);

            // 实例化返回的 FarmerInfo 对象
            FarmerInfo farmerInfo = new FarmerInfo();

            // user. 获取用户名
            try {
                WebElement usernameElement = webDriver.findElement(By.cssSelector("div.name-_lSSDc"));
                farmerInfo.setUsername(usernameElement.getText());
            } catch (Exception e) {
                farmerInfo.setUsername("用户名获取失败");
            }

            // 2. 获取抖音号
            try {
                WebElement douyinIdElement = webDriver.findElement(By.cssSelector("div.unique_id-EuH8eA"));
                String douyinIdText = douyinIdElement.getText().replace("抖音号：", "").trim();
                farmerInfo.setId(douyinIdText);
            } catch (Exception e) {
                farmerInfo.setId("抖音号获取失败");
            }

            // 3. 获取签名
            try {
                WebElement signatureElement = webDriver.findElement(By.cssSelector("div.signature-HLGxt7"));
                farmerInfo.setSignature(signatureElement.getText());
            } catch (Exception e) {
                farmerInfo.setSignature("签名获取失败");
            }

            // 4. 获取关注数量
            try {
                WebElement followingElement = webDriver.findElement(By.cssSelector("div#guide_home_following span.number-No6ev9"));
                farmerInfo.setFollowingCount(Integer.parseInt(followingElement.getText()));
//                WebElement likeElement = webDriver.findElement(By.xpath("//div[contains(@class, 'statics-item-MDWoNA') and contains(., '获赞')]//span[@class='number-No6ev9']"));
//                farmerInfo.setLikeCount(Integer.parseInt(likeElement.getText()));

            } catch (Exception e) {
                farmerInfo.setFollowingCount(0);
            }

            // 5. 获取粉丝数量
            try {
                WebElement fansElement = webDriver.findElement(By.cssSelector("div#guide_home_fans span.number-No6ev9"));
                farmerInfo.setFansCount(Integer.parseInt(fansElement.getText()));
            } catch (Exception e) {
                farmerInfo.setFansCount(0);
            }

            // 6. 获取获赞数量
            try {
                WebElement likeElement = webDriver.findElement(By.xpath("//div[contains(text(), '获赞')]/span[@class='number-No6ev9']"));
                farmerInfo.setLikeCount(Integer.parseInt(likeElement.getText().trim()));
            } catch (Exception e) {
                farmerInfo.setLikeCount(0);
            }

            // 7. 获取用户头像 URL
            try {
                WebElement avatarElement = webDriver.findElement(By.cssSelector("div.avatar-XoPjK6 img.img-PeynF_"));
                String avatarUrl = avatarElement.getAttribute("src");
                farmerInfo.setAvatarUrl(avatarUrl);
            } catch (Exception e) {
                farmerInfo.setAvatarUrl("头像获取失败");
            }

            farmerInfo.setPCID(PCID.toString());
            farmerInfo.setTmpname(name);
            HashMap<String, Object> res = new HashMap<>();
            res.put("farmerInfo", farmerInfo);

            //这里会确保在这里使用的时候已经进行初始化了
            ArrayList<FarmerInfo> list = (ArrayList<FarmerInfo>) DataCenter.map.get(userId).get("douyinList");
            list.add(farmerInfo);

            // 返回成功结果
            return R.ok(farmerInfo);
        } catch (Exception e) {
            // 捕获所有异常并记录日志
            e.printStackTrace();
            return R.ok("获取账号信息失败：" + e.getMessage());
        } finally {
            // 关闭 WebDriver，避免资源泄漏
//            if (webDriver != null) {
//                webDriver.quit();
//            }
        }
    }

    /**
     * 获取工作账号列表
     * todo 修复bug为null的时候会报错，这里逻辑是有问题的
     *
     * @param id
     * @return
     */
    @Override
    public R getFarmerList(Integer id) {

//        ArrayList<FarmerInfo> list = new ArrayList<>();
//
//        if(!Objects.isNull(DataCenter.map.get(userId).get("douyinList"))){
//            list = (ArrayList<FarmerInfo>) DataCenter.map.get(userId).get("douyinList");
//        }

        ArrayList<FarmerInfo> list = (ArrayList<FarmerInfo>) DataCenter.map.get(userId).get("douyinList");
        return R.ok(list);
    }

    /**
     * 处理手机验证码
     *
     * @param id
     * @return
     */
    public R phoneJugeCode(Integer id) {

        WebDriver driver = WebDriverUtils.get(id);
        WebDriverWait wait = new WebDriverWait(driver, 5);

        try {
            // 尝试使用 WebDriverWait 等待元素可点击
            WebElement smsOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='接收短信验证']/parent::div")));
            smsOption.click();

            WebElement sendCode = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='获取验证码']/parent::div")));
            sendCode.click(); // 点击获取验证码
        } catch (TimeoutException e) {
            return R.fail("获取验证码超时");
        }

        return R.ok();
    }

    /**
     * 验证验证码是否正确
     *
     * @param id
     * @param code
     * @return
     */
    public R phoneJugeTrueCode(Integer id, String code) {
        System.out.println("验证码：" + code);
        WebDriver driver = WebDriverUtils.get(id);
        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            // 1. 定位到验证码输入框并输入验证码
            WebElement codeInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='uc-ui-input_textbox']//input[@type='number']")));
            System.out.println("定位验证码输入框执行");

            // 确保输入框可用且已清空
            if (codeInput.isEnabled()) {
                codeInput.clear(); // 清空输入框
                codeInput.sendKeys(code); // 输入验证码
            } else {
                return R.fail("验证码输入框不可用");
            }

            // 2. 定位并点击提交按钮
            WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'uc-ui-verify_sms-verify_button') and contains(@class, 'primary') and not(contains(@class, 'disabled')) and contains(text(), '验证')]")));
            submitButton.click();
            System.out.println("定位并点击提交按钮: 验证");

            // 3. 等待页面或弹窗中的验证码验证结果
            // 使用 findElements() 检查元素是否存在
            List<WebElement> resultMessageList = driver.findElements(By.xpath("//p[contains(@class, 'uc-ui-verify_error')]"));

            if (!resultMessageList.isEmpty()) {
                WebElement resultMessage = wait.until(ExpectedConditions.visibilityOf(resultMessageList.get(0)));
                String resultText = resultMessage.getText();

                if ("验证码错误，请重新输入".equals(resultText)) {
                    System.out.println("错误提示信息: " + resultText);
                    return R.fail("验证码错误");
                }
                return R.ok("验证码正确");
            } else {
                // 如果元素不存在，则直接跳过等待，或者返回默认值
                return R.ok("验证码正确");
            }
        } catch (TimeoutException e) {
            // 如果没有获取到结果消息，返回错误
            System.out.println("验证码验证超时，页面或弹窗没有加载完成");
            return R.fail("验证码验证成功");
        } catch (Exception e) {
            // 捕获其他异常并记录
            System.out.println("发生错误：" + e.getMessage());
            return R.fail("验证码处理过程中发生错误");
        }
    }
}
