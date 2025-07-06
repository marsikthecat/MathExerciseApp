package com.example.kopfrechnen.model;

import java.util.Objects;
import java.util.Random;

/**
 * Task Objekt which consists of random numbers n1 and n2 with an operator between them.
 */

public class Task {

  private final int limit;
  private int n1;
  private int n2;
  private final String operator;
  private final int answer;

  /**
   * Constructor for the task.
   */
  public Task(int limit, String operator) {
    this.limit = limit;
    this.operator = operator;
    this.answer = calculateAnswer();
  }

  private int calculateAnswer() {
    Random random = new Random();
    if (Objects.equals(operator, "/")) {
      int resultForDivision;
      while (true) {
        int n1 = random.nextInt(limit);
        int n2 = random.nextInt(limit);
        if (n2 != 0 && n1 % n2 == 0) {
          resultForDivision = n1 / n2;
          this.n1 = n1;
          this.n2 = n2;
          break;
        }
      }
      return resultForDivision;
    }
    int n1 = random.nextInt(limit);
    int n2 = random.nextInt(limit);
    int result;
    this.n1 = n1;
    this.n2 = n2;
    switch (operator) {
      case "+" -> result = n1 + n2;
      case "-" -> result = n1 - n2;
      case "*" -> result = n1 * n2;
      default -> result = 0;
    }
    return result;
  }

  public int getAnswer() {
    return answer;
  }

  /**
   * Returns the task as a string.
   */
  public String showTask() {
    return n1 + " " + operator + " " + n2;
  }
}