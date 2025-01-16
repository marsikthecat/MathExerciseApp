package com.example.kopfrechnen;

import java.util.Objects;
import java.util.Random;

/**
 * Task Objekt which consists of a random number n1 and n2 with an operator between them.
 * <p> </p>
 */

public class Task {

  private final int limit;
  private int n1;
  private int n2;
  private final String operator;
  private final int answer;

  /** Constructor for the task.
   *
   *  @param limit the limit of the generated two numbers.
   *  @param operator the operator between the two numbers.
   */

  public Task(int limit, String operator) {
    this.limit = limit;
    this.operator = operator;
    this.answer = calculateAnswer();
  }

  private int calculateAnswer() {
    if (Objects.equals(operator, "/")) {
      int resultForDivision;
      while (true) {
        Random random = new Random();
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
    Random random = new Random();
    int n1 = random.nextInt(limit);
    int n2 = random.nextInt(limit);
    int result = 0;
    this.n1 = n1;
    this.n2 = n2;
    switch (operator) {
      case "+" -> result = n1 + n2;
      case "-" -> result = n1 - n2;
      case "*" -> result = n1 * n2;
    }
    return result;
  }

  public int getAnswer() {
    return answer;
  }

  public String showTask() {
    return n1 + " " + operator + " " + n2;
  }
}