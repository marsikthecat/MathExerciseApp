package com.example.kopfrechnen;

import java.util.Optional;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * <p> </p>
 * Kopfrechner.
 * Main 125 lines.
 * Task 68 lines.
 * TaskSet 26 lines.
 * Configuration 15 lines.
 * StartWindow 70 lines.
 * 304 lines.
 * + 79 lines CSS.
 */

public class Main extends Application {

  private int right = 0;
  private Configuration configuration;
  private TaskSet taskSet;

  @Override
  public void start(Stage stage) {
    StartWindow startWindow = new StartWindow();
    configuration = startWindow.getConfig();
    this.taskSet = new TaskSet(configuration);
    VBox content = new VBox();
    Label taskLabel = new Label();
    taskLabel.setFont(new Font("Arial", 25));
    TextField field = new TextField();
    field.setFont(new Font("Arial", 24));
    long st = System.currentTimeMillis();
    playGame(taskLabel, field, content, st);
    content.getChildren().addAll(taskLabel, field);
    content.setAlignment(Pos.CENTER);
    Scene scene = new Scene(content, 320, 240);
    stage.setTitle("Kopfrechnen");
    stage.setScene(scene);
    stage.show();
  }

  private void playGame(Label taskLabel, TextField field, VBox content, long st) {
    Task task = taskSet.getnextTask();
    taskLabel.setText(task.showTask());
    field.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        handleUserAnswer(field, content, task);
        if (!taskSet.isEmpty()) {
          field.clear();
          playGame(taskLabel, field, content, st);
        } else {
          boolean continueGame = endGame(field, right, st);
          if (continueGame) {
            resetGame(field);
            playGame(taskLabel, field, content, System.currentTimeMillis());
          } else {
            System.exit(0);
          }
        }
      }
    });
  }

  private void resetGame(TextField field) {
    StartWindow startWindow = new StartWindow();
    configuration = startWindow.getConfig();
    if (configuration == null) {
      System.exit(0);
    }
    field.setDisable(false);
    this.taskSet = new TaskSet(configuration);
    this.right = 0;
  }

  private void handleUserAnswer(TextField field, VBox content, Task task) {
    if (check(field.getText()) || Integer.parseInt(field.getText())
            == task.getAnswer()) {
      content.setStyle("-fx-background-color: green");
      right++;
    } else {
      content.setStyle("-fx-background-color: red");
    }
  }

  private boolean endGame(TextField field, int right, long st) {
    field.setDisable(true);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Ergebnis");
    long et = System.currentTimeMillis();
    String headerMessage = "Ergebnis: " + right + " von " + configuration.numberOfTask()
            + " richtig \n" + "Zeit: " + (et - st) / 1000.0  + " Sekunden";
    alert.setHeaderText(headerMessage);
    alert.setContentText("Neustart?");
    ButtonType yes = new ButtonType("Ja");
    ButtonType no = new ButtonType("Nein");
    alert.getButtonTypes().setAll(yes, no);
    Optional<ButtonType> result = alert.showAndWait();
    return result.isPresent() && result.get() == yes;
  }

  boolean check(String txt) {
    try {
      Integer.parseInt(txt);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static void main(String[] args) {
    launch();
  }
}