package com.roboforex.webtrader.steps;

import static com.roboforex.webtrader.helpers.DriverHelper.getDriver;
import static com.roboforex.webtrader.pages.BasePage.COOKIES_BANNER_FRAME;
import static com.roboforex.webtrader.pages.BasePage.ERROR_LOCATOR;
import static com.roboforex.webtrader.pages.BasePage.ERROR_MESSAGES_LOCATOR;
import static org.assertj.core.api.Assertions.assertThat;

import com.roboforex.webtrader.pages.BasePage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.TestNGException;

public class BaseSteps {

  private static final Logger LOGGER = LogManager.getLogger(BaseSteps.class);

  public void goTo(final String baseUrl) {
    LOGGER.info(String.format("Opening '%s'", baseUrl));
    getDriver().get(baseUrl);
  }

  /**
   * Scrolling to given element.
   *
   * @param element WebElement
   * @return WebElement
   */
  protected WebElement scrollToElement(final WebElement element) {
    try {
      ((JavascriptExecutor) getDriver())
          .executeScript("arguments[0].scrollIntoView(true);", element);
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
      throw new TestNGException(e.toString());
    }
    return element;
  }

  /**
   * Scrolling to given element.
   *
   * @param webElements WebElements
   * @return WebElement
   */
  protected WebElement scrollToElement(final List<WebElement> webElements,
                                       final int i) {
    try {
      ((JavascriptExecutor) getDriver())
          .executeScript("arguments[0].scrollIntoView(true);", webElements.get(i));
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
      throw new TestNGException(e.toString());
    }
    return webElements.get(i);
  }

  protected boolean isElementPresent(final By by) {
    try {
      getDriver().findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  protected void checkForNoErrors() {
    assertThat(isElementPresent(ERROR_LOCATOR))
        .as("Errors while submitting registration form")
        .isFalse();
  }

  protected void checkForErrors(final int count) {
    assertThat(isElementPresent(ERROR_LOCATOR))
        .as("No errors while submitting registration form")
        .isTrue();
    assertThat(getDriver().findElements(ERROR_LOCATOR)
        .get(getDriver().findElements(ERROR_LOCATOR).size() - 1)
        .findElements(ERROR_MESSAGES_LOCATOR).size())
        .as("Wrong count of error messages")
        .isEqualTo(count);
  }

  public void closeCookiesBannerIfPresent() {
    if (isElementPresent(COOKIES_BANNER_FRAME)) {
      LOGGER.info("Closing the 'Cookies banner'");
      PageFactory.initElements(getDriver(), BasePage.class).getAllowCookiesButton().click();
    }
  }
}
