package com.roboforex.webtrader;

import static com.roboforex.webtrader.helpers.DriverHelper.killDriver;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import com.roboforex.webtrader.helpers.TestFailureListener;
import com.roboforex.webtrader.steps.BaseSteps;
import com.roboforex.webtrader.steps.LoginSteps;
import com.roboforex.webtrader.steps.RegisterSteps;
import com.roboforex.webtrader.steps.account.AccountDetailsSteps;
import com.roboforex.webtrader.steps.account.AccountSteps;
import com.roboforex.webtrader.steps.account.AccountTourSteps;
import lombok.Getter;
import lombok.Setter;
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

  @Getter
  @Setter
  private static String clientPassword;

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
            randomAlphanumeric(20) + "@" + randomAlphanumeric(20) + "." + randomAlphabetic(3),
            randomAlphabetic(20),
            randomAlphabetic(20),
            true,
            true,
            "+7",
            randomNumeric(10)
        },
        {
            "Min values",
            randomAlphanumeric(2) + "@" + randomAlphanumeric(2) + "." + randomAlphabetic(2),
            randomAlphabetic(2),
            randomAlphabetic(2),
            true,
            true,
            "+5993",
            randomNumeric(4)
        },
        {
            "Knowingly incorrect test data",
            randomAlphanumeric(10),
            randomAlphabetic(2),
            randomAlphabetic(2),
            true,
            true,
            "+7",
            randomNumeric(4)
        }
    };
  }

  @DataProvider
  public static Object[][] negativeDataProvider() {
    return new Object[][]{
        {
            "Empty fields",
            null,
            null,
            null,
            false,
            false,
            null,
            null
        },
        {
            "Already registered user",
            "qwe@qwe.qwe",
            randomAlphabetic(2),
            randomAlphabetic(2),
            true,
            true,
            "+7",
            randomNumeric(4)
        }
    };
  }
}
