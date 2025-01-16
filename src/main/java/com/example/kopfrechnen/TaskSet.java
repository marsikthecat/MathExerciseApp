package com.example.kopfrechnen;

import java.util.ArrayList;
import java.util.List;

public class TaskSet {

  private final List<Task> taskList;
  private int index = 0;

  public TaskSet(Configuration configuration) {
    if (configuration == null) {
      throw new NullPointerException("configuration cannot be null reference");
    }
    this.taskList = new ArrayList<>();
    for (int i = 0; i < configuration.numberOfTask(); i++) {
      taskList.add(new Task(configuration.limit(), configuration.operator()));
    }
  }

  public Task getnextTask() {
    Task task = taskList.get(index);
    if (containsNextTask()) {
      index++;
    }
    return task;
  }
  public boolean containsNextTask() {
    return index < taskList.size();
  }
}