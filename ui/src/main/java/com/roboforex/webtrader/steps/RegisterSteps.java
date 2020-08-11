package com.roboforex.webtrader.steps;

import static com.roboforex.webtrader.helpers.DriverHelper.getDriver;
import static com.roboforex.webtrader.pages.RegisterPage.AGE_CONFIRMATION_CHECKBOX_SELECTOR;
import static com.roboforex.webtrader.pages.RegisterPage.PHONE_CODES_SELECTOR;
import static com.roboforex.webtrader.pages.RegisterPage.SELECTED_PHONE_CODE_SELECTOR;
import static com.roboforex.webtrader.pages.RegisterPage.TERMS_AGREEMENT_CHECK_BOX_SELECTOR;

import com.roboforex.webtrader.pages.RegisterPage;
import java.util.stream.IntStream;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.TestNGException;

public class RegisterSteps extends BaseSteps {

  private static final Logger LOGGER = LogManager.getLogger(RegisterSteps.class);
  private static final String SCRIPT_STRING = "return window.getComputedStyle(document."
      + "querySelector('%s'),'::after').getPropertyValue('content')";

  public void fillUpAndSubmitRegFormAssumingSuccess(final String email,
                                                    final String firstName,
                                                    final String lastName,
                                                    final boolean isAgeConfirmed,
                                                    final boolean isTermsAgreed,
                                                    final String phoneCode,
                                                    final String phoneNumber) {
    fillUpEmailInput(email);
    fillUpPhoneNumberInputs(phoneCode, phoneNumber);
    fullUpFirstNameInput(firstName);
    fillUpLastNameInput(lastName);
    setAgeConfirmationCheckbox(isAgeConfirmed);
    setTermsAgreementCheckbox(isTermsAgreed);
    submitRegistration();
    checkForNoErrors();
  }

  public void fillUpAndSubmitRegFormAssumingFail(final String testCase,
                                                 final String email,
                                                 final String firstName,
                                                 final String lastName,
                                                 final boolean isAgeConfirmed,
                                                 final boolean isTermsAgreed,
                                                 final String phoneCode,
                                                 final String phoneNumber) {

    switch (testCase) {
      case "Empty fields" -> {
        setAgeConfirmationCheckbox(isAgeConfirmed);
        setTermsAgreementCheckbox(isTermsAgreed);
        submitRegistration();
        checkForErrors(9);
      }
      case "Already registered user" -> {
        fillUpEmailInput(email);
        fillUpPhoneNumberInputs(phoneCode, phoneNumber);
        fullUpFirstNameInput(firstName);
        fillUpLastNameInput(lastName);
        setAgeConfirmationCheckbox(isAgeConfirmed);
        setTermsAgreementCheckbox(isTermsAgreed);
        submitRegistration();
        checkForErrors(1);
      }
      default -> throw new IllegalArgumentException("Unexpected test case value: " + testCase);
    }

  }

  private void fillUpEmailInput(final String email) {
    LOGGER.info(String.format("Filling up the 'Email' field with value '%s'", email));
    PageFactory.initElements(getDriver(), RegisterPage.class).getEmailInput().sendKeys(email);
  }

  private void fillUpPhoneNumberInputs(final String targetPhoneCode,
                                       final String phoneNumber) {
    LOGGER.info(String.format("Filling up the 'Phone number' field with value '%s'",
        targetPhoneCode + phoneNumber));
    final var page = PageFactory.initElements(getDriver(), RegisterPage.class);
    final var phoneCodeDropdown = page.getPhoneCodeDropdown();
    var selectedPhoneCode = phoneCodeDropdown.findElement(SELECTED_PHONE_CODE_SELECTOR)
        .getText().replaceAll(".* ", "");
    if (!targetPhoneCode.equals(selectedPhoneCode)) {
      phoneCodeDropdown.click();
      final var phoneCodesList = phoneCodeDropdown.findElements(PHONE_CODES_SELECTOR);
      IntStream.range(0, phoneCodesList.size()).forEach(i -> {
        final var phoneCode = phoneCodesList.get(i).getText().replaceAll(".* ", "");
        if (phoneCode.length() != 0) {
          if (targetPhoneCode.equals(phoneCode)) {
            scrollToElement(phoneCodesList, i).click();
          }
        } else {
          scrollToElement(phoneCodesList, i);
        }
        if (i == phoneCodesList.size()) {
          throw new TestNGException("Provided phone code is not listed");
        }
      });
    }
    page.getPhoneInput().sendKeys(phoneNumber);
  }

  private void fullUpFirstNameInput(final String firstName) {
    LOGGER.info(String.format("Filling up the 'First name' field with value '%s'", firstName));
    PageFactory.initElements(getDriver(), RegisterPage.class).getFirstNameInput()
        .sendKeys(firstName);
  }

  private void fillUpLastNameInput(final String lastName) {
    LOGGER.info(String.format("Filling up the 'Last name' field with value '%s'", lastName));
    PageFactory.initElements(getDriver(), RegisterPage.class).getLastNameInput()
        .sendKeys(lastName);
  }

  private void setAgeConfirmationCheckbox(final boolean isToBeSelected) {
    final var isSelected = !((JavascriptExecutor) getDriver())
        .executeScript(String.format(SCRIPT_STRING, AGE_CONFIRMATION_CHECKBOX_SELECTOR))
        .toString().equals("none");
    if (!isSelected && isToBeSelected) {
      clickAgeConfirmationCheckbox();
    } else if (isSelected && !isToBeSelected) {
      clickAgeConfirmationCheckbox();
    }
  }

  private void setTermsAgreementCheckbox(final boolean isToBeSelected) {
    final var isSelected = !((JavascriptExecutor) getDriver())
        .executeScript(String.format(SCRIPT_STRING, TERMS_AGREEMENT_CHECK_BOX_SELECTOR))
        .toString().equals("none");
    if (!isSelected && isToBeSelected) {
      clickTermsAgreementCheckbox();
    } else if (isSelected && !isToBeSelected) {
      clickTermsAgreementCheckbox();
    }
  }

  private void clickAgeConfirmationCheckbox() {
    LOGGER.info("Setting up the 'Age confirmation' checkbox into 'true'");
    PageFactory.initElements(getDriver(), RegisterPage.class).getAgeConfirmationCheckbox()
        .click();
  }

  private void clickTermsAgreementCheckbox() {
    LOGGER.info("Setting up the 'Terms agreement' checkbox into 'true'");
    final var termsAgreementCheckbox = PageFactory.initElements(getDriver(), RegisterPage.class)
        .getTermsAgreementCheckbox();
    new Actions(getDriver()).moveByOffset(
        termsAgreementCheckbox.getLocation().getX() + 5,
        termsAgreementCheckbox.getLocation().getY()
            + termsAgreementCheckbox.getSize().getHeight() / 2).click().build().perform();
  }

  private void submitRegistration() {
    LOGGER.info("Submitting the Registration form");
    PageFactory.initElements(getDriver(), RegisterPage.class).getSubmitButton().click();
  }
}
