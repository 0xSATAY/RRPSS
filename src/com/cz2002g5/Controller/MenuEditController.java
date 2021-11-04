package com.cz2002g5.Controller;

/**
 * The interface of the Menu edit controller. Purpose of this interface is such that any class that
 * implements this must have the following:
 *
 * <ul>
 *   <li>The ability to process user actions
 *   <li>The method of creating a new menu item
 *   <li>The method of updating an existing menu item
 *   <li>The method of removing an existing menu item
 * </ul>
 */
public interface MenuEditController {

  /**
   * process user actions.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  void selectAction(RRPSS pos);

  /**
   * create a new menu item
   *
   * @param pos Reference to the instance of RRPSS.
   */
  void createItem(RRPSS pos);

  /**
   * update an existing menu item.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  void updateItem(RRPSS pos);

  /**
   * remove an existing menu item.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  void removeItem(RRPSS pos);
}
