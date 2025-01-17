package com.example.kopfrechnen;

import java.util.ArrayDeque;

public class TaskSet {

  private final ArrayDeque<Task> tasks;

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

  public boolean isEmpty() {
    return tasks.isEmpty();
  }
}