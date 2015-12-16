package com.sergeybord.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

public class FirstTaskTest {



    private WebDriver driver;

    @BeforeClass
    public void beforeTest() {
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterTest(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test

    public void logoPresence(){
        driver.get("http://prestashop.qatestlab.com.ua/");
        if (driver.findElements(By.id("header_logo")).size() == 0) { Assert.fail("!Logo is absent!"); }
    }

    @Test
    public void featuredGoodsCount(){
        driver.get("http://prestashop.qatestlab.com.ua/");
        /* fill goods List with li elements*/
        List<WebElement> goods = driver.findElements(By.xpath(".//*[@id=\"homefeatured\"]/li"));

        /* check count of goods on page */
        int goodsCount = 8;
        if (goods.size() != goodsCount){
            Assert.fail("!goodsCount must be " + goodsCount + ", NOT " + goods.size() + "!");
        }
    }
}


