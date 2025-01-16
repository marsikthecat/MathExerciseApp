package com.example.kopfrechnen;

/**
 * Configuration Objekt, that contains the configuration for making the Tasks.
 * <p> </p>
 */

public record Configuration(String operator, int limit, int numberOfTask) {

  /**
   * Constructor.
   */
  public Configuration {
  }
}