package com.example.kopfrechnen.viewmodel;

import com.example.kopfrechnen.model.Task;
import com.example.kopfrechnen.model.TaskSet;
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
  private final TaskSet taskSet;
  private final ObjectProperty<Task> currentTask = new SimpleObjectProperty<>();
  private final StringProperty currentTaskString = new SimpleStringProperty();
  private final IntegerProperty correctAnswers = new SimpleIntegerProperty();
  private final StringProperty userInput = new SimpleStringProperty();
  private final ObjectProperty<String> colorProperty = new SimpleObjectProperty<>();
  private final BooleanProperty gameFinished = new SimpleBooleanProperty();

  /**
   * Constructor that sets everything to a starting point.
   */
  public ViewModel(TaskSet taskSet) {
    this.taskSet = taskSet;
    currentTask.set(taskSet.getnextTask());
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
   * processed the Answer send by the user and sets up the next task if it exists.
   */

  public void checkAnswer() {
    int userAnswer = Integer.parseInt(userInput.get());
    if (userAnswer == currentTask.get().getAnswer()) {
      correctAnswers.set(correctAnswers.get() + 1);
      colorProperty.set("-fx-background-color: green");
    } else {
      colorProperty.set("-fx-background-color: red");
    }
    Task nextTask = taskSet.getnextTask();
    if (nextTask != null) {
      currentTask.set(nextTask);
      currentTaskString.set(nextTask.showTask());
      userInput.set("");
    } else {
      gameFinished.set(true);
    }
  }
}