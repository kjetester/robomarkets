package com.roboforex.webtrader.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasePage {

  public static final By COOKIES_BANNER_FRAME = By.id("AllowCookies_Main");
  public static final By ERROR_LOCATOR = By.cssSelector(".tooltip_show_true > .err");
  public static final By ERROR_MESSAGES_LOCATOR =
      By.xpath("//div[@class = 'err__txt'][contains(text(), ' ')]");

  @FindBy(id = "AllowCookies_Allow_ViewButton")
  @Getter
  private WebElement allowCookiesButton;
}
