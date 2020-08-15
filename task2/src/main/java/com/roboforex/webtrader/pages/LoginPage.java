package com.roboforex.webtrader.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

  @FindBy(css = "[type='email']")
  @Getter
  private WebElement emailInput;
  @FindBy(css = "[type='password']")
  @Getter
  private WebElement passwordInput;
  @FindBy(css = ".button_type_brand")
  @Getter
  private WebElement loginButton;
  @FindBy(css = ".button_type_quick-demo")
  @Getter
  private WebElement registerButton;
  @FindBy(css = "[class $= 'nonregistered'] .link__title")
  @Getter
  private WebElement logInWithoutRegistrationButton;
}
