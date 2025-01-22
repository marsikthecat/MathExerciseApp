package com.example.kopfrechnen;

import java.util.Optional;
import com.example.kopfrechnen.model.Configuration;
import com.example.kopfrechnen.model.TaskSet;
import com.example.kopfrechnen.uiutils.StartWindow;
import com.example.kopfrechnen.viewmodel.ViewModel;
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
 * Main 109 lines.
 * ViewModel 81 lines.
 * TaskSet 30 lines.
 * Configuration 13 lines.
 * StartWindow 69 lines.
 * 302 lines + 79 lines CSS.
 */

public class Main extends Application {

  private ViewModel viewModel;
  private Configuration configuration;
  private TaskSet taskSet;

  @Override
  public void start(Stage stage) {
    StartWindow startWindow = new StartWindow();
    configuration = startWindow.getConfig();
    taskSet = new TaskSet(configuration);
    viewModel = new ViewModel(taskSet);

    VBox content = new VBox();
    Label taskLabel = new Label();
    taskLabel.textProperty().bind(viewModel.getCurrentTaskString());
    taskLabel.setFont(new Font("Arial", 25));

    TextField field = new TextField();
    field.setFont(new Font("Arial", 24));
    field.textProperty().bindBidirectional(viewModel.getUserInputProperty());

    content.getChildren().addAll(taskLabel, field);
    content.setAlignment(Pos.CENTER);
    content.styleProperty().bind(viewModel.getColorProperty());

    long startTime = System.currentTimeMillis();
    setUpGame(field, startTime);

    Scene scene = new Scene(content, 320, 240);
    stage.setTitle("MathQuiz");
    stage.setScene(scene);
    stage.show();
  }

  public void setUpGame(TextField field, long startTime) {
    field.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        viewModel.checkAnswer();
        if (viewModel.getGameFinishedProperty().get()) {
          endGame(field, viewModel.getCorrectAnswersProperty().get(), startTime);
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
    this.viewModel = new ViewModel(taskSet);
  }

  private void endGame(TextField field, int right, long st) {
    field.setDisable(true);
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Result");
    long et = System.currentTimeMillis();
    String headerMessage = "You got " + right + " of " + configuration.numberOfTask()
            + " correct \n" + "needed time: " + (et - st) / 1000.0  + " seconds";
    alert.setHeaderText(headerMessage);
    alert.setContentText("restart?");
    ButtonType yes = new ButtonType("Yes");
    ButtonType no = new ButtonType("Nope");
    alert.getButtonTypes().setAll(yes, no);
    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == yes) {
      resetGame(field);
    } else {
      System.exit(0);
    }
  }

  public static void main(String[] args) {
    launch();
  }
}