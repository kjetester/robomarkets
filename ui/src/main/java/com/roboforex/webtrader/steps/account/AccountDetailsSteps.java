package com.roboforex.webtrader.steps.account;

import static com.roboforex.webtrader.helpers.DriverHelper.getDriver;
import static com.roboforex.webtrader.pages.account.AccountDetailsPage.ACCOUNT_DETAILS_WINDOW_LOCATOR;

import com.roboforex.webtrader.pages.account.AccountDetailsPage;
import com.roboforex.webtrader.steps.BaseSteps;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.support.PageFactory;

public class AccountDetailsSteps extends AccountSteps {

  private static final Logger LOGGER = LogManager.getLogger(BaseSteps.class);

  public String checkIfRegistrationSucceeded(final String email,
                                             final String firstName,
                                             final String lastName) {
    final var accountDetailsPage = PageFactory.initElements(getDriver(), AccountDetailsPage.class);
    final var softly = new SoftAssertions();
    String clientPassword;
    LOGGER.info("Staring account details window presence check.");
    softly.assertThat(isElementPresent(ACCOUNT_DETAILS_WINDOW_LOCATOR))
        .as("An account details window isn't present")
        .isTrue();
    LOGGER.info("Staring created account details checks.");
    softly.assertThat(accountDetailsPage.getSuccessMessage().getText())
        .as("Email in success message isn't match")
        .contains(email);
    softly.assertThat(accountDetailsPage.getFullNameField().getText())
        .as("Email isn't match")
        .isEqualTo(firstName + " " + lastName);
    softly.assertThat(accountDetailsPage.getEmailField().getText())
        .as("Email isn't match")
        .isEqualTo(email);
    softly.assertThat(clientPassword = accountDetailsPage.getPasswordField().getText())
        .as("Password wasn't provided")
        .isNotNull()
        .isNotEmpty();
    softly.assertThat(accountDetailsPage.getAccountTypeField().getText())
        .as("The account's type isn't a demo-type")
        .containsIgnoringCase("demo");
    softly.assertThat(accountDetailsPage.getAccountNumberField().getText())
        .as("The account's number wasn't provided")
        .isNotNull()
        .isNotEmpty();
    softly.assertThat(accountDetailsPage.getAccountPasswordField().getText())
        .as("The account's password wasn't provided")
        .isNotNull()
        .isNotEmpty();
    softly.assertThat(accountDetailsPage.getLeverageField().getText())
        .as("The leverage isn't correct")
        .isEqualTo("1:500");
    softly.assertThat(accountDetailsPage.getCurrencyField().getText())
        .as("The account's currency isn't correct")
        .isEqualTo("USD");
    softly.assertThat(Integer.parseInt(accountDetailsPage.getDepositField().getText()))
        .as("The account's deposit wasn't correct")
        .isGreaterThanOrEqualTo(10000);
    softly.assertAll();
    closeAccountDetailsWindow();
    return clientPassword;
  }

  private void closeAccountDetailsWindow() {
    PageFactory.initElements(getDriver(), AccountDetailsPage.class).getCloseButton().click();
  }
}
