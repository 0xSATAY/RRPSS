package com.cz2002g5.View;

/**
 * The interface View that is to be implemented by all other user views. Purpose of a view is to
 * simulate a model-view-controller architecture. Any class that implements this interface must have
 * the following:
 *
 * <ul>
 *   <li>The method of displaying a default view</li>
 *   <li>The method of displaying a custom view</li>
 * </ul>
 */
public interface View {

  /** Displays the generic content of a view. */
  void display();

  /**
   * Display custom view based on the input param.
   *
   * @param s the string to be displayed
   */
  void displayCustomView(String s);
}
