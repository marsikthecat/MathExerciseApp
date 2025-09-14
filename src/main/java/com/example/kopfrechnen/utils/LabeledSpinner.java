package com.example.kopfrechnen.utils;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;

/**
 * Helper Class for a Labeled Input.
 */
public class LabeledSpinner extends HBox {

  private final Spinner<Integer> inputElement;

  /**
   * Puts the Label and Spinner in a HBox and styles it a bit.
   */
  public LabeledSpinner(String label, Spinner<Integer> spinner) {
    Label label1 = new Label(label);
    this.inputElement = spinner;
    inputElement.setPrefWidth(60);
    getChildren().addAll(label1, inputElement);
    setStyle("-fx-background-color: transparent");
    setAlignment(Pos.CENTER);
  }

  public int getValue() {
    return inputElement.getValue();
  }
}