package com.roboforex.webtrader.pages.account;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TourPage extends AccountPage {

  public final static By TOUR_WINDOW_FRAME = By.cssSelector(".tour-window__frame");

  @FindBy(css = ".tour-window__close")
  @Getter
  private WebElement closeButton;
}
