package com.roboforex.webtrader.steps;

import static com.roboforex.webtrader.helpers.DriverHelper.getDriver;

import com.roboforex.webtrader.pages.LoginPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class LoginSteps extends BaseSteps {

  private static final Logger LOGGER = LogManager.getLogger(LoginSteps.class);

  public void openRegisterPage() {
    LOGGER.info("Opening the Registration page");
    PageFactory.initElements(getDriver(), LoginPage.class).getRegisterButton().click();
  }

  public void logIn(final String clientEmail,
                    final String clientPassword) {
    fillUpEmailInput(clientEmail);
    fillUpPasswordInput(clientPassword);
    submitLogin();
  }

  private void fillUpEmailInput(String clientEmail) {
    LOGGER.info(String.format("Filling up the 'Email' field with value '%s'", clientEmail));
    PageFactory.initElements(getDriver(), LoginPage.class).getEmailInput().sendKeys(clientEmail);
  }

  private void fillUpPasswordInput(String clientPassword) {
    LOGGER.info(String.format("Filling up the 'Password' field with value '%s'", clientPassword));
    PageFactory.initElements(getDriver(), LoginPage.class).getPasswordInput().sendKeys(clientPassword);
  }

  private void submitLogin() {
    LOGGER.info("Submitting the Login form");
    PageFactory.initElements(getDriver(), LoginPage.class).getLoginButton().click();
    checkForNoErrors();
  }
}
