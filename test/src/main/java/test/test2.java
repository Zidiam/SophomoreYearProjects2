package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.sql.Time;
import java.time.Duration;

public class test2 {
	 public static void main(String[] args) {
		 	//java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(java.util.logging.Level.OFF);
		   // java.util.logging.Logger.getLogger("org.apache.http").setLevel(java.util.logging.Level.OFF);
		 
	        WebDriver driver = new HtmlUnitDriver();
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).toSeconds());
	        
	    
	        try {
	            driver.get("https://www.google.com/");
	            driver.findElement(By.name("q")).sendKeys("Hello");
	            //driver.findElement(By.name("Password")).sendKeys("216269Jm!");// + Keys.ENTER
	            
	            System.out.println("test");
	            driver.findElement(By.name("btnK")).click();
	            System.out.println("test2");
	            
	            WebElement firstResult = wait.until(presenceOfElementLocated(By.className("mw")));
	            System.out.println("test3");
	            System.out.println("Test: " + driver.getTitle());
	            String str = driver.findElement(By.tagName("body")).getText();
	            System.out.println(str);
	        } finally {
	            driver.quit();
	        }
	    }
}
