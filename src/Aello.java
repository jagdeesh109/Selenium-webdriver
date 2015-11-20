// Please Make note of the following point
// 1) The test suite is genrated using selenium ide 
// 2) Selenium ide is addon/ide for firfox. This has to installed as firefox addon to create to test suite.
// 3) The genrated test suite are tested on local win7 box, Here test suite are passed.
// 4) Test suite are failed when running on the remote server 
// 5) stack of remote server (ubuntu-14, jenkins, github,java 1.8,mavan-3.3)
// 6) Test suite are build using jenkins. ehich is getting failed.
// 7)  
import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Aello {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    
	  
	  // Setup firefox binary to start in Xvfb        
      String Xport = System.getProperty(
              "lmportal.xvfb.id", ":99");
      final File firefoxPath = new File(System.getProperty(
              "lmportal.deploy.firefox.path", "/usr/bin/firefox"));
      FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
      firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);

      // Start Firefox driver
      WebDriver driver = new FirefoxDriver(firefoxBinary, null);
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.get("http://google.com/");

      // Take snapshot of browser
     /* File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(srcFile, new File("ffsnapshot.png"));*/
      driver.quit();  
	  
	  
	  
	driver = new FirefoxDriver();
    baseUrl = "https://www.google.co.in/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAello() throws Exception {
    driver.get(baseUrl + "/?gfe_rd=cr&ei=3zpEVurHAubI8Aflm4HgBQ&gws_rd=ssl");
    driver.findElement(By.id("lst-ib")).clear();
    driver.findElement(By.id("lst-ib")).sendKeys("hello");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
