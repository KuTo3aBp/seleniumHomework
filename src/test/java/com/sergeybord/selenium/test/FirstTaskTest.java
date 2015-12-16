package com.sergeybord.selenium.test;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class FirstTaskTest {


    private WebDriver driver;

    @BeforeClass
    public void beforeTest() {
        driver = new ChromeDriver();

        /* set browser size to prevent design change according to browser size */
        driver.manage().window().setSize(new Dimension(800,600));
    }

    @AfterClass
    public void afterTest(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    public void logoPresence(){
        driver.get("http://prestashop.qatestlab.com.ua/en/");

        /* if logo is absent -> fail test */
        if (driver.findElements(By.id("header_logo")).size() == 0) {
            Assert.fail("()Logo is absent");
        }
    }

    @Test
    public void featuredItemsCount(){
        driver.get("http://prestashop.qatestlab.com.ua/en/");

        /* fill goods List with li elements*/
        List<WebElement> items = driver.findElements(By.xpath(".//*[@id=\"homefeatured\"]/li"));

        /* check count of goods on page */
        int itemsCount = 8;
        if (items.size() != itemsCount){
            Assert.fail("()itemsCount must be " + itemsCount + ", NOT " + items.size());
        }
    }
}


