package com.example.kopfrechnen.model;

import java.util.ArrayDeque;

/**
 * Contains Tasks that are pulled out every time after the user enters the answer.
 */

public class TaskSet {

  private final ArrayDeque<Task> tasks;

  /**
   * Adds Tasks in the Queue that are specified using the configuration that the user sets.
   */

  public TaskSet(Configuration configuration) {
    if (configuration == null) {
      throw new NullPointerException("configuration cannot be null reference");
    }
    this.tasks = new ArrayDeque<>();
    for (int i = 0; i < configuration.numberOfTask(); i++) {
      tasks.addFirst(new Task(configuration.limit(), configuration.operator()));
    }
  }

  public Task getnextTask() {
    return tasks.pollFirst();
  }
}