package com.example.kopfrechnen;

import com.example.kopfrechnen.model.Configuration;
import com.example.kopfrechnen.model.TaskQueue;
import com.example.kopfrechnen.utils.LabeledSpinner;
import com.example.kopfrechnen.viewmodel.ViewModel;
import java.util.Objects;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Configuration: 6 lines.
 * Task: 60 lines.
 * TaskQueue: 36 lines.
 * LabeledSpinner: 30 lines.
 * ViewModel: 97 lines.
 * Main: 146 lines.
 * Total 375 lines + 79 lines css = 454 lines.
 */

public class Main extends Application {

  private StackPane root;
  private VBox startView;
  private ViewModel viewModel;
  private Configuration configuration;

  @Override
  public void start(Stage stage) {
    root = new StackPane();
    ComboBox<String> operator = new ComboBox<>();
    operator.getItems().addAll("+", "-", "*", "/");
    operator.setValue("+");
    LabeledSpinner limit = new LabeledSpinner("Limit: ", new Spinner<>(1, 100, 1));
    LabeledSpinner numTask = new LabeledSpinner("Number of tasks: ", new Spinner<>(1, 30, 1));
    Button startBtn = new Button("Start");
    startBtn.setOnAction(e -> {
      configuration = new Configuration(operator.getValue(), limit.getValue(), numTask.getValue());
      startGame();
    });
    Button stopBtn = new Button("Stop");
    stopBtn.setOnAction(e -> System.exit(0));
    HBox buttonBox = new HBox();
    buttonBox.setAlignment(Pos.CENTER);
    buttonBox.setPadding(new Insets(20, 0, 0, 0));
    Region region = new Region();
    region.setPrefWidth(20);
    buttonBox.getChildren().addAll(startBtn, region, stopBtn);
    startView = new VBox(10);
    startView.setAlignment(Pos.CENTER);
    startView.setStyle("-fx-background-color: white");
    startView.getChildren().addAll(new Label("Choose operator: "), operator,
        limit, numTask, buttonBox);

    root.getChildren().add(startView);
    Scene scene = new Scene(root, 320, 240);
    scene.getStylesheets().add(Objects.requireNonNull(
            getClass().getResource("/styles.css")).toExternalForm());
    limit.getStyleClass().add("spinner");
    numTask.getStyleClass().add("spinner");
    startBtn.getStyleClass().add("button");
    stopBtn.getStyleClass().add("button");
    operator.getStyleClass().add("combo-box");

    stage.setTitle("MathQuiz");
    stage.setScene(scene);
    stage.show();
  }

  private void startGame() {
    VBox gameView = new VBox();
    TaskQueue taskQueue = new TaskQueue(configuration);
    viewModel = new ViewModel(taskQueue);
    gameView.setAlignment(Pos.CENTER);
    gameView.styleProperty().bind(viewModel.getColorProperty());

    Label taskLabel = new Label();
    taskLabel.textProperty().bind(viewModel.getCurrentTaskString());
    taskLabel.setFont(new Font("Arial", 25));

    TextField field = new TextField();
    Platform.runLater(field::requestFocus);
    field.setFont(new Font("Arial", 24));
    field.textProperty().bindBidirectional(viewModel.getUserInputProperty());

    gameView.getChildren().addAll(taskLabel, field);

    long startTime = System.currentTimeMillis();
    field.setOnKeyPressed(e -> {
      if (e.getCode() == KeyCode.ENTER) {
        System.out.println("key");
        viewModel.checkAnswer();
        if (viewModel.getGameFinishedProperty().get()) {
          endGame(field, viewModel.getCorrectAnswersProperty().get(), startTime);
        }
      }
    });
    root.getChildren().add(gameView);
    gameView.toFront();
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
      startView.toFront();
    } else {
      System.exit(0);
    }
  }

  /**
   * .
   */
  public static void main(String[] args) {
    launch();
  }
}