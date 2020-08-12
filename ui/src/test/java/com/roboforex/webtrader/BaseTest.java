package com.roboforex.webtrader;

import static com.roboforex.webtrader.helpers.DriverHelper.killDriver;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import com.roboforex.webtrader.buisenessobjects.User.Builder;
import com.roboforex.webtrader.helpers.TestFailureListener;
import com.roboforex.webtrader.steps.BaseSteps;
import com.roboforex.webtrader.steps.LoginSteps;
import com.roboforex.webtrader.steps.RegisterSteps;
import com.roboforex.webtrader.steps.account.AccountDetailsSteps;
import com.roboforex.webtrader.steps.account.AccountSteps;
import com.roboforex.webtrader.steps.account.AccountTourSteps;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners({TestFailureListener.class})
public class BaseTest {

  private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

  public static final String BASE_URL = "https://webtrader.roboforex.com/";
  public static final BaseSteps BASE_STEP = new BaseSteps();
  public static final LoginSteps LOGIN_STEP = new LoginSteps();
  public static final RegisterSteps REGISTER_STEP = new RegisterSteps();
  public static final AccountDetailsSteps ACCOUNT_DETAILS_STEP = new AccountDetailsSteps();
  public static final AccountTourSteps TOUR_STEP = new AccountTourSteps();
  public static final AccountSteps ACCOUNT_STEP = new AccountSteps();

  @BeforeMethod
  public static void openMainPage() {
    BASE_STEP.goTo(BASE_URL);
    BASE_STEP.closeCookiesBannerIfPresent();
  }

  @AfterMethod
  public static void afterMethod() {
    killDriver();
  }

  @DataProvider
  public static Object[][] positiveDataProvider() {
    return new Object[][]{
        {
            "Max values",
            new Builder()
                .setEmail(randomAlphanumeric(20) + "@" + randomAlphanumeric(20) + "."
                    + randomAlphabetic(3))
                .setFirstName(randomAlphabetic(20))
                .setLastName(randomAlphabetic(20))
                .setPhoneCode("+7")
                .setPhoneNumber(randomNumeric(10))
                .build()
        },
        {
            "Min values",
            new Builder()
                .setEmail(randomAlphanumeric(2) + "@" + randomAlphanumeric(2) + "."
                    + randomAlphabetic(2))
                .setFirstName(randomAlphabetic(2))
                .setLastName(randomAlphabetic(2))
                .setPhoneCode("+5993")
                .setPhoneNumber(randomNumeric(4))
                .build()
        },
        {
            "Knowingly incorrect test data",
            new Builder()
                .setEmail(randomAlphanumeric(10))
                .setFirstName(randomAlphabetic(2))
                .setLastName(randomAlphabetic(2))
                .setPhoneCode("+7")
                .setPhoneNumber(randomNumeric(4))
                .build()
        }
    };
  }

  @DataProvider
  public static Object[][] negativeDataProvider() {
    return new Object[][]{
        {
            "Empty fields",
            new Builder()
                .setAgeConfirmed(false)
                .setTermsAgreed(false)
                .build()
        },
        {
            "Already registered user",
            new Builder()
                .setEmail("qwe@qwe.qwe")
                .setFirstName(randomAlphabetic(2))
                .setLastName(randomAlphabetic(2))
                .setPhoneCode("+7")
                .setPhoneNumber(randomNumeric(4))
                .build()
        }
    };
  }
}
