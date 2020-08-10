package com.roboforex.webtrader.registration;

import com.roboforex.webtrader.BaseTest;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

  @Test(dataProvider = "positiveDataProvider")
  public static void positiveTest(final String testCase,
                                  final String email,
                                  final String firstName,
                                  final String lastName,
                                  final boolean isAgeConfirmed,
                                  final boolean isTermsAgreed,
                                  final String phoneCode,
                                  final String phoneNumber) {
    LOGIN_STEP.openRegisterPage();
    REGISTER_STEP.fillUpAndSubmitRegFormAssumingSuccess(email, firstName, lastName, isAgeConfirmed, isTermsAgreed, phoneCode, phoneNumber);
    setClientPassword(ACCOUNT_DETAILS_STEP.checkIfRegistrationSucceeded(email, firstName, lastName));
    TOUR_STEP.closeTourWindowIfPresent();
    ACCOUNT_STEP.logOut();
    LOGIN_STEP.logIn(email, getClientPassword());
    ACCOUNT_STEP.checkIfLoginSucceeded();
  }

  @Test(dataProvider = "negativeDataProvider")
  public static void negativeTest(final String testCase,
                                  final String email,
                                  final String firstName,
                                  final String lastName,
                                  final boolean isAgeConfirmed,
                                  final boolean isTermsAgreed,
                                  final String phoneCode,
                                  final String phoneNumber) {
    LOGIN_STEP.openRegisterPage();
    REGISTER_STEP.fillUpAndSubmitRegFormAssumingFail(
        testCase,
        email,
        firstName,
        lastName,
        isAgeConfirmed,
        isTermsAgreed,
        phoneCode,
        phoneNumber);
  }
}
