package by.vodemka;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumITechArt {
    public static void main(String[] args) {
        JUnitCore runner = new JUnitCore();
        Result result = runner.run(SeleniumITechArt.class);
        System.out.println("Run tests: " + result.getRunCount());
        System.out.println("Failed tests: " + result.getFailureCount());
        System.out.println("Ignored tests: " + result.getIgnoreCount());
        System.out.println("Success: " + result.wasSuccessful());
    }

    static WebDriver driver;

    @BeforeClass
    public static void beforeTesting() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("http://www.google.by");

        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("iTechArt");
        searchField.submit();
    }

    @Test
    public void shouldBeITechArtInThePageTitle(){
        Assert.assertTrue("'iTechArt' in the page title not found",driver.getTitle().contains("iTechArt"));
    }

    @Test
    public void shouldBeITechArtInTheFirstAnswerHeader(){
        WebElement firstResultHeader = driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div/div/div/div[1]/a/h3"));
        Assert.assertTrue("'iTechArt' in the header of the first answer not found",firstResultHeader.getText().contains("iTechArt"));
    }

    @Test
    public void shouldBeITechArtInTheFirstAnswerBody(){
        WebElement firstResultBody = driver.findElement(By.xpath("//*[@id='rso']/div[1]/div/div/div/div/div[2]/div/span"));
        Assert.assertTrue("'iTechArt' in the body of the first answer not found", firstResultBody.getText().contains("iTechArt"));
    }

    @AfterClass
    public static void afterTesting(){
        driver.quit();
    }
}
