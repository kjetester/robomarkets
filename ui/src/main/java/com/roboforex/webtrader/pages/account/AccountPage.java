package com.roboforex.webtrader.pages.account;

import com.roboforex.webtrader.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

  public static final By INTERACTIVE_AREA_SELECTOR = By.cssSelector(".interactive-area");
  public static final By MESSAGE_TO_UNREGISTERED_USER_SELECTOR = By.cssSelector(".important");

  @FindBy(css = ".user-info__logout")
  @Getter
  private WebElement logoutButton;
}
