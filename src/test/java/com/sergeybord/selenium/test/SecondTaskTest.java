package com.sergeybord.selenium.test;


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import java.util.Random;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SecondTaskTest {


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
    public void randomOrder() throws InterruptedException {
        driver.get("http://prestashop.qatestlab.com.ua/en/");

        /* generate random number with range [1...8] */
        int min = 1, max = 8;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + min) + min;
        System.out.println("()randomNum is: " + randomNum);

        /* click at random item */
        driver.findElement(By.xpath(".//*[@id=\"homefeatured\"]/li[" + randomNum + "]/div/div[1]/div/a[1]")).click();

        /* clear input field and type randomNum into quantity field */
        driver.findElement(By.xpath(".//*[@id=\"quantity_wanted\"]")).clear();
        driver.findElement(By.xpath(".//*[@id=\"quantity_wanted\"]")).sendKeys(String.valueOf(randomNum));

        /* add to cart */
        driver.findElement(By.xpath(".//p[@id='add_to_cart']/button")).click();

        /* wait 1 sec and refresh page to update cart count */
        TimeUnit.SECONDS.sleep(1);
        driver.navigate().refresh();

        /* get item count in cart */
        String cartCount = driver.findElement(By.xpath(".//span[@class='ajax_cart_quantity']")).getText();

        /* if cart is empty */
        if (cartCount.equals("")){
            Assert.fail("()Cart is empty!" + ", expected: " + randomNum);
        }

        /* if cart has incorrect item count */
        if (!Integer.toString(randomNum).equals(cartCount)){
            Assert.fail("()" + cartCount + " items in cart, expected: " + randomNum);
        }

    }
}


