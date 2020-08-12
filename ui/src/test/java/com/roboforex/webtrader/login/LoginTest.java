package com.roboforex.webtrader.login;

import com.roboforex.webtrader.BaseTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

  @Test()
  public static void positiveTest() {
    LOGIN_STEP.logInWithoutRegistration();
    ACCOUNT_STEP.checkIfLoginSucceeded();
    ACCOUNT_STEP.checkIfUserNotRegistered();
  }
}
