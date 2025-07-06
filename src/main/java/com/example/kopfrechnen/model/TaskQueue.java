package com.example.kopfrechnen.model;

import java.util.ArrayDeque;

/**
 * Contains Tasks that are pulled out every time after the user enters the answer.
 */

public class TaskQueue extends ArrayDeque<Task> {

  private Configuration configuration;

  /**
   * Constructor that initializes the task queue with a given configuration.
   */
  public TaskQueue(Configuration configuration) {
    if (configuration == null) {
      throw new NullPointerException("configuration cannot be null reference");
    }
    this.configuration = configuration;
    fillTasks();
  }

  /**
   * Fills the task queue with tasks based on the current configuration.
   */
  public void fillTasks() {
    for (int i = 0; i < configuration.numberOfTask(); i++) {
      addFirst(new Task(configuration.limit(), configuration.operator()));
    }
  }

  public Task getNextTask() {
    return isEmpty() ? null : pollFirst();
  }

  /**
   * Clears the task queue.
   */
  public void setConfigurationAndRestart(Configuration configuration) {
    if (configuration == null) {
      throw new NullPointerException("configuration cannot be null reference");
    }
    this.configuration = configuration;
    clear();
    fillTasks();
  }
}