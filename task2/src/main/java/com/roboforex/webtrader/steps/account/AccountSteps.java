package com.roboforex.webtrader.steps.account;

import static com.roboforex.webtrader.helpers.DriverHelper.getDriver;
import static com.roboforex.webtrader.pages.account.AccountPage.INTERACTIVE_AREA_SELECTOR;
import static com.roboforex.webtrader.pages.account.AccountPage.MESSAGE_TO_UNREGISTERED_USER_SELECTOR;
import static org.assertj.core.api.Assertions.assertThat;

import com.roboforex.webtrader.pages.account.AccountPage;
import com.roboforex.webtrader.steps.BaseSteps;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class AccountSteps extends BaseSteps {

  private static final Logger LOGGER = LogManager.getLogger(AccountSteps.class);

  public void logOut() {
    LOGGER.info("Logging out");
    PageFactory.initElements(getDriver(), AccountPage.class).getLogoutButton().click();
  }

  public void checkIfLoginSucceeded() {
    LOGGER.info("Verifying login");
    assertThat(isElementPresent(INTERACTIVE_AREA_SELECTOR))
        .as("A Logout button isn't present")
        .isTrue();
  }

  public void checkIfUserNotRegistered() {
    LOGGER.info("Checking message to unregistered user");
    assertThat(isElementPresent(MESSAGE_TO_UNREGISTERED_USER_SELECTOR))
        .as("A Message isn't present")
        .isTrue();
    assertThat(getDriver().findElement(MESSAGE_TO_UNREGISTERED_USER_SELECTOR).getText())
        .as("A Message has incorrect text")
        .isEqualTo("Attention! You're not logged in!");

  }
}
