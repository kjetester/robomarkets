package com.roboforex.webtrader.steps.account;

import static com.roboforex.webtrader.helpers.DriverHelper.getDriver;
import static com.roboforex.webtrader.pages.account.TourPage.TOUR_WINDOW_FRAME;

import com.roboforex.webtrader.pages.account.TourPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

public class AccountTourSteps extends AccountSteps {

  private static final Logger LOGGER = LogManager.getLogger(AccountTourSteps.class);

  public void closeTourWindowIfPresent() {
    if (isElementPresent(TOUR_WINDOW_FRAME)) {
      LOGGER.info("Closing the 'Tour window'");
      PageFactory.initElements(getDriver(), TourPage.class).getCloseButton().click();
    }
  }
}
