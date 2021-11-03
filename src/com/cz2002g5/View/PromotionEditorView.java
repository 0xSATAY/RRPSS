package com.cz2002g5.View;

/**
 * The type Promotion editor view.
 */
public class PromotionEditorView implements View {

  @Override
  public void display() {
    System.out.println(
        "---Editing Promotion---\n1. Create promotion\n2. Update promotion\n3. Remove promotion\n4. Cancel\nSelect your action:");
  }

  @Override
  public void displayCustomView(String s) {
    System.out.println(s);
  }
}
