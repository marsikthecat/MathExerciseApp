package com.example.kopfrechnen.model;

/**
 * Configuration Objekt, that contains the configuration for making the Tasks.
 */

public record Configuration(String operator, int limit, int numberOfTask) {

  /**
   * Constructor.
   */
  public Configuration {}
}