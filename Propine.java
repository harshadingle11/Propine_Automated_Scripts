import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Propine {

    static WebDriver driver = null;

    public static void main(String[] args) throws ParseException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\harshad.ingle\\IdeaProjects\\SeleniumProject\\Libs\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.navigate().to("https://vast-dawn-73245.herokuapp.com/");

        TC_01();
        TC_02();
        TC_03();

    }
    //Verify the title of web page
    @Test
    public static void TC_01() {
        String pageTitle = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertTrue(pageTitle.contains("Propine Date Parser"));
        System.out.println(pageTitle.contains("Propine Date Parser"));
    }

    //Verify that entered date and resulting date are same or not
    @Test
    public static void TC_02() throws ParseException {
        String inputDate = "12/12/2012";
        driver.findElement(By.name("date")).click();
        driver.findElement(By.name("date")).sendKeys(inputDate);

        driver.findElement(By.xpath("//input[@type = \"submit\"]")).click();
        String actualDate = driver.findElement(By.xpath("//h3//following-sibling::div")).getText();

        String expectedDate = "Wed Dec 12 2012 00:00:00 GMT+0000";

        Assert.assertTrue(actualDate.equals(expectedDate));

        System.out.println(actualDate.equals(expectedDate));

    }

    //Verify that if the user enters an invalid date then proper message is displayed or not.
    @Test
    public static void TC_03() throws ParseException {

        String invalidInputDate = "invalid_date";
        driver.findElement(By.name("date")).click();
        /*driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);*/

        driver.findElement(By.name("date")).sendKeys(invalidInputDate);
        driver.findElement(By.xpath("//input[@type = \"submit\"]")).click();
        String actualResult = driver.findElement(By.xpath("//h3//following-sibling::div")).getText();
        String expectedResult = "Invalid date";
        Assert.assertTrue(actualResult.equals(expectedResult));

        driver.quit();
    }
}