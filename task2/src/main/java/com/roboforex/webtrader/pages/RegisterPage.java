package com.roboforex.webtrader.pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

  public final static String AGE_CONFIRMATION_CHECKBOX_SELECTOR = "[class$=atleast18] .check__label";
  public final static String TERMS_AGREEMENT_CHECK_BOX_SELECTOR = "[class$=termsofuse] .check__label";
  public final static By SELECTED_PHONE_CODE_SELECTOR = By.cssSelector(".selected-item");
  public final static By PHONE_CODES_SELECTOR = By.cssSelector(".drop-down__item");

  @FindBy(css = "[type='email']")
  @Getter
  private WebElement emailInput;
  @FindBy(css = "[placeholder^='First/Given/Middle']")
  @Getter
  private WebElement firstNameInput;
  @FindBy(css = "[placeholder^='Last/Family/Surname']")
  @Getter
  private WebElement lastNameInput;
  @FindBy(css = ".quick-demo-acc-form__country .expandable")
  @Getter
  private WebElement phoneCodeDropdown;
  @FindBy(css = ".quick-demo-acc-form__phone .input__field")
  @Getter
  private WebElement phoneInput;
  @FindBy(css = AGE_CONFIRMATION_CHECKBOX_SELECTOR)
  @Getter
  private WebElement ageConfirmationCheckbox;
  @FindBy(css = TERMS_AGREEMENT_CHECK_BOX_SELECTOR)
  @Getter
  private WebElement termsAgreementCheckbox;
  @FindBy(css = ".button_type_brand")
  @Getter
  private WebElement submitButton;
}
