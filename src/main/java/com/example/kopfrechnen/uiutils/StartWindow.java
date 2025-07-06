package com.example.kopfrechnen.uiutils;

import com.example.kopfrechnen.model.Configuration;
import java.util.Objects;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Start window Object which asks the user for the configuration.
 */

public class StartWindow extends Stage {

  private Configuration config;

  /**
   * Constructor.
   */

  public StartWindow() {
    Scene scene = getContent();
    this.setTitle("Hey bro");
    this.setResizable(false);
    this.setScene(scene);
    this.showAndWait();
  }

  private Scene getContent() {
    ComboBox<String> operator = new ComboBox<>();
    operator.getItems().addAll("+", "-", "*", "/");
    operator.setValue("+");
    Spinner<Integer> limit = new Spinner<>(1, 100, 1);
    Spinner<Integer> numTask = new Spinner<>(1, 30, 1);
    Button btn = new Button("Start the Game");
    btn.setOnAction(e -> startMain(operator, limit, numTask));
    Button stop = new Button("Stop the Game");
    stop.setOnAction(e -> System.exit(0));
    VBox content = new VBox(10);
    content.setAlignment(Pos.CENTER);
    content.getChildren().addAll(new Label("Choose operator: "), operator,
            new Label("Limit: "), limit, new Label("Number of tasks: "), numTask, btn, stop);
    Scene scene = new Scene(content, 200, 300);
    scene.getStylesheets().add(Objects.requireNonNull(
            getClass().getResource("/styles.css")).toExternalForm());
    limit.getStyleClass().add("spinner");
    numTask.getStyleClass().add("spinner");
    btn.getStyleClass().add("button");
    stop.getStyleClass().add("button");
    operator.getStyleClass().add("combo-box");
    return scene;
  }

  private void startMain(ComboBox<String> operator, Spinner<Integer> limit, 
      Spinner<Integer> numberOfTask) {
    if (operator.getValue() == null || limit.getValue() == null
        || numberOfTask.getValue() == null || operator.getValue().isEmpty()) {
      config = new Configuration("+", 5, 5);
    } else {
      config = new Configuration(
              operator.getValue(), limit.getValue(), numberOfTask.getValue());
    }
    this.close();
  }

  public Configuration getConfig() {
    return config;
  }
}