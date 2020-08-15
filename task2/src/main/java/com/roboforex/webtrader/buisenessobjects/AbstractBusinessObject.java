package com.roboforex.webtrader.buisenessobjects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.TestNGException;

public abstract class AbstractBusinessObject {

  /**
   * POJO to pretty JSON.
   *
   * @param o object
   * @return json as string
   */
  public static synchronized String describeBusinessObject(final Object o) {
    try {
      return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(o);
    } catch (JsonProcessingException e) {
      throw new TestNGException(e.getMessage());
    }
  }
}
