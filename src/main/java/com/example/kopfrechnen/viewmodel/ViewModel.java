package com.example.kopfrechnen.viewmodel;

import com.example.kopfrechnen.model.Task;
import com.example.kopfrechnen.model.TaskQueue;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * ViewModel that processes interactions from the UI.
 */

public class ViewModel {
  private final TaskQueue taskQueue;
  private final ObjectProperty<Task> currentTask = new SimpleObjectProperty<>();
  private final StringProperty currentTaskString = new SimpleStringProperty();
  private final IntegerProperty correctAnswers = new SimpleIntegerProperty();
  private final StringProperty userInput = new SimpleStringProperty();
  private final ObjectProperty<String> colorProperty = new SimpleObjectProperty<>();
  private final BooleanProperty gameFinished = new SimpleBooleanProperty();

  /**
   * Constructor that sets everything to a starting point.
   */
  public ViewModel(TaskQueue taskQueue) {
    this.taskQueue = taskQueue;
    currentTask.set(taskQueue.getNextTask());
    currentTaskString.set(currentTask.get().showTask());
    colorProperty.set("-fx-background-color: white");
    userInput.set("");
    correctAnswers.set(0);
    gameFinished.set(false);
  }

  public StringProperty getCurrentTaskString() {
    return currentTaskString;
  }

  public StringProperty getUserInputProperty() {
    return userInput;
  }

  public IntegerProperty getCorrectAnswersProperty() {
    return correctAnswers;
  }

  public BooleanProperty getGameFinishedProperty() {
    return gameFinished;
  }

  public ObjectProperty<String> getColorProperty() {
    return colorProperty;
  }

  /**
   * processes the Answer by the user and pulls the next task if it exists.
   */
  public void checkAnswer() {
    if (!validate()) {
      colorProperty.set("-fx-background-color: red");
    } else {
      int userAnswer = Integer.parseInt(userInput.get());
      if (userAnswer == currentTask.get().getAnswer()) {
        correctAnswers.set(correctAnswers.get() + 1);
        colorProperty.set("-fx-background-color: green");
      } else {
        colorProperty.set("-fx-background-color: red");
      }
    }
    Task nextTask = taskQueue.getNextTask();
    if (nextTask != null) {
      currentTask.set(nextTask);
      currentTaskString.set(nextTask.showTask());
      userInput.set("");
    } else {
      gameFinished.set(true);
    }
  }

  private boolean validate() {
    String input = userInput.get();
    if (input.isEmpty() || input.isBlank()) {
      return false;
    }
    try {
      Integer.parseInt(userInput.get());
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}