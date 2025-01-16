package com.example.kopfrechnen;

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
 * Start window Object.
 * <p> </p>
 */

public class StartWindow extends Stage {

  private Configuration config;

  /**
   * Constructor.
   */

  public StartWindow() {
    Scene scene = getContent();
    this.setTitle("Hey");
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
    Button btn = new Button("Starten");
    btn.setOnAction(e -> startMain(operator, limit, numTask));
    Button stop = new Button("Spiel beenden");
    stop.setOnAction(e -> System.exit(0));
    VBox content = new VBox(10);
    content.setAlignment(Pos.CENTER);
    content.getChildren().addAll(new Label("Operator"), operator, new Label("Limit: "), limit,
            new Label("Anzahl Aufgaben"), numTask, btn, stop);
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
    this.config = new Configuration(
      operator.getValue(), limit.getValue(), numberOfTask.getValue());
    this.close();
  }

  public Configuration getConfig() {
    return config;
  }
}