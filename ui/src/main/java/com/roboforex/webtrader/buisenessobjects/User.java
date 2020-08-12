package com.roboforex.webtrader.buisenessobjects;

import java.util.Objects;
import lombok.Getter;

public class User extends AbstractBusinessObject {

  @Getter
  private final String email;
  @Getter
  private final String password;
  @Getter
  private final String firstName;
  @Getter
  private final String lastName;
  @Getter
  private final Boolean isAgeConfirmed;
  @Getter
  private final Boolean isTermsAgreed;
  @Getter
  private final String phoneCode;
  @Getter
  private final String phoneNumber;

  public User(final Builder builder) {
    this.email = builder.email;
    this.password = builder.password;
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.isAgeConfirmed = Objects.requireNonNullElse(builder.isAgeConfirmed, true);
    this.isTermsAgreed = Objects.requireNonNullElse(builder.isTermsAgreed, true);
    this.phoneCode = builder.phoneCode;
    this.phoneNumber = builder.phoneNumber;
  }

  public static class Builder {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isAgeConfirmed;
    private Boolean isTermsAgreed;
    private String phoneCode;
    private String phoneNumber;

    public Builder setEmail(String email) {
      this.email = email;
      return this;
    }

    public Builder setPassword(String password) {
      this.password = password;
      return this;
    }

    public Builder setFirstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder setLastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder setAgeConfirmed(Boolean ageConfirmed) {
      isAgeConfirmed = ageConfirmed;
      return this;
    }

    public Builder setTermsAgreed(Boolean termsAgreed) {
      isTermsAgreed = termsAgreed;
      return this;
    }

    public Builder setPhoneCode(String phoneCode) {
      this.phoneCode = phoneCode;
      return this;
    }

    public Builder setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public User build() {
      return new User(this);
    }

    public Builder using(final User user) {
      this.email = user.email;
      this.password = user.password;
      this.firstName = user.firstName;
      this.lastName = user.lastName;
      this.isAgeConfirmed = user.isAgeConfirmed;
      this.isTermsAgreed = user.isTermsAgreed;
      this.phoneCode = user.phoneCode;
      this.phoneNumber = user.phoneNumber;
      return this;
    }
  }
}
