package com.roboforex.webtrader.registration;

import com.roboforex.webtrader.BaseTest;
import com.roboforex.webtrader.buisenessobjects.User;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

  @Test(dataProvider = "positiveDataProvider")
  public static void positiveTest(final String testCase,
                                  final User user) {
    LOGIN_STEP.openRegisterPage();
    REGISTER_STEP.fillUpAndSubmitRegFormAssumingSuccess(user);
    final var registeredUser =
        ACCOUNT_DETAILS_STEP.checkIfRegistrationSucceeded(user);
    TOUR_STEP.closeTourWindowIfPresent();
    ACCOUNT_STEP.logOut();
    LOGIN_STEP.logIn(registeredUser);
    ACCOUNT_STEP.checkIfLoginSucceeded();
  }

  @Test(dataProvider = "negativeDataProvider", priority = 1)
  public static void negativeTest(final String testCase,
                                  final User user) {
    LOGIN_STEP.openRegisterPage();
    REGISTER_STEP.fillUpAndSubmitRegFormAssumingFail(testCase, user);
  }
}
