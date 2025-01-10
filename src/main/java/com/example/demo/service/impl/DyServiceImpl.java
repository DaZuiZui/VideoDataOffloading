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
        return R.ok("scanf this code");
    }



    /**
     * 发布视频
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
}
