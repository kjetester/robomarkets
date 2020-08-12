package com.roboforex.webtrader.buisenessobjects;

import lombok.Getter;

public enum Currency {

  USD("USD", "US Dollar"),
  EUR("EUR", "Euro");

  @Getter
  private final String code;
  @Getter
  private final String description;

  Currency(final String code,
           final String description) {
    this.code = code;
    this.description = description;
  }
}
