package com.example.demo.service.impl;

import com.example.demo.domain.R;
import com.example.demo.service.TempleteService;
import com.example.demo.util.WebDriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Service;

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
            // 等待页面加载完成（根据页面特性调整等待时间）
            Thread.sleep(5000);

            // 找到文件上传组件
            WebElement uploadInput = driver.findElement(By.xpath("//input[@type='file']"));
            System.out.println("找到上传文件组件");

            // 使用 JavaScript 确保文件上传组件可见（如果被隐藏）
            ((JavascriptExecutor) driver).executeScript("arguments[0].style.display = 'block';", uploadInput);

            // 设置文件路径，模拟文件选择
            String filePath = "/Users/yangyida/Downloads/06从失败中学习，建立韧性.mp4";
            uploadInput.sendKeys(filePath);
            System.out.println("文件路径已设置：" + filePath);

            // 检查文件是否上传完成（根据上传完成的标识元素调整）
            Thread.sleep(10000); // 假定上传需要 10 秒，可根据网络状况调整

            System.out.println("文件上传完成");

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
