package com.roboforex.webtrader.pages.account;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountDetailsPage extends AccountPage {

  public static final By ACCOUNT_DETAILS_WINDOW_LOCATOR = By.cssSelector(".demo-account-details");

  @FindBy(css = ".demo-account-details__description")
  @Getter
  private WebElement successMessage;
  @FindBy(css = "[class $= '_user-name'] .text")
  @Getter
  private WebElement fullNameField;
  @FindBy(css = "[class $= '_user-email'] .text")
  @Getter
  private WebElement emailField;
  @FindBy(css = "[class $= '_user-pass'] .text")
  @Getter
  private WebElement passwordField;
  @FindBy(css = "[class $= '_acc-type'] .text")
  @Getter
  private WebElement accountTypeField;
  @FindBy(css = "[class $= '_acc-number'] .text")
  @Getter
  private WebElement accountNumberField;
  @FindBy(css = "[class $= '_acc-pass'] .text")
  @Getter
  private WebElement accountPasswordField;
  @FindBy(css = "[class $= '_acc-leverage'] .text")
  @Getter
  private WebElement leverageField;
  @FindBy(css = "[class $= '_acc-currency'] .text")
  @Getter
  private WebElement currencyField;
  @FindBy(css = "[class $= '_acc-deposit'] .text")
  @Getter
  private WebElement depositField;
  @FindBy(css = ".demo-account-details__close > div")
  @Getter
  private WebElement closeButton;

}
