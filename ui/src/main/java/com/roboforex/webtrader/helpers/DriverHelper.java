package com.roboforex.webtrader.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverHelper {

  private static final Logger LOGGER = LogManager.getLogger(DriverHelper.class);

  private static RemoteWebDriver driver;

  /**
   * WebDriver singleton.
   *
   * @return driver
   */
  public static WebDriver getDriver() {
    if (driver == null) {
      LOGGER.debug("Starting Chrome");
      WebDriverManager.chromedriver().setup();
      ChromeOptions opts = new ChromeOptions();
      opts
          .addArguments("--headless", "--disable-gpu")
          .setAcceptInsecureCerts(true);
      driver = new ChromeDriver(opts);
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
      driver.manage().window().setSize(new Dimension(1500, 800));
      driver.manage().window().setPosition(new Point(0, 0));
    }
    return driver;
  }

  /**
   * Kill driver.
   */
  public static void killDriver() {
    if (driver != null) {
      LOGGER.debug("Closing Chrome");
      driver.close();
      driver.quit();
      driver = null;
    }
  }
}