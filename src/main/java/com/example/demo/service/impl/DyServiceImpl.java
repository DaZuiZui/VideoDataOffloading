package com.example.demo.service.impl;

import com.example.demo.domain.R;
import com.example.demo.service.TempleteService;
import com.example.demo.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DyServiceImpl implements TempleteService {

    @Override
    public R login(Integer id) {
        WebDriver driver = WebDriverUtils.get(id);

        //获取当前会话的句柄
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
        return R.ok("scan this code");
    }



    /**
     * 发布视频，
     *      但是目前有一个问题，就是遇见风控了，需要再次扫码，但是目前我还没有写检测这个风控的逻辑，暂时先放一下。
     *      而且抖音有防机器人检测，如果被检测为机器人那么就会被返回home页面，
     *          所以需要编写一些随机事件
     *          或者视频够短足够快，在他没有检测出来就发布（目前采用此方案，但是不可靠，时间紧以后再说）
     * @param id
     * @return
     */
    @Override
    public R publishAVideo(Integer id) {
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
            String filePath = "/Users/yangyida/Documents/testima.mp4";
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
    public R getPopularVideos(){
        // 获取 WebDriver 实例
        HashMap<String, Object> map = WebDriverUtils.create();
        WebDriver webDriver = (WebDriver) map.get("obj");
        try {
            // 打开目标页面
            webDriver.get("https://www.iesdouyin.com/share/billboard/?id=0&utm_source=copy&utm_campaign=client_share&utm_medium=android&app=aweme");

            // 等待页面加载完成
            WebDriverWait wait = new WebDriverWait(webDriver, 30); // Selenium 3 版本
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("list-container")));

            // 存储热点信息的列表
            List<Map<String, String>> hotList = new ArrayList<>();

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
            }

            // 输出调试信息
            for (Map<String, String> hotMap : hotList) {
                System.out.println("Title: " + hotMap.get("title") + ", Hot Number: " + hotMap.get("hotnumber"));
            }

            // 返回成功结果
            return R.ok(hotList);

        } catch (Exception e) {
            e.printStackTrace();
            return R.ok("获取热门视频失败：" + e.getMessage());
        }
    }


    /**
     * 获取抖音热门话题 通关 当前登入的用户
     * 在未来这个可能需要改版，但是目前来说，我需要快点把这个项目做完，暂时这么处理
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
}
