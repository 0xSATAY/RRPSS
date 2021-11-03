package com.cz2002g5.Controller;

/** The interface the Menu edit controller.
 *  Purpose of this interface is such that any class that implements this must have the following:
 *  <ul>
 *      <li>The ability to process user actions</li>
 *      <li>The method of creating a new menu item</li>
 *      <li>The method of updating an existing menu item</li>
 *      <li>The method of removing an existing menu item</li>
 *  </ul>
 *
 *
 * */
public interface MenuEditController {

  /**
   * This method is used to process user actions.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  void selectAction(RRPSS pos);

  /**
   * This method is used to create a new menu item
   *
   * @param pos Reference to the instance of RRPSS.
   */
  void createItem(RRPSS pos);

  /**
   * This method is used to update an existing menu item.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  void updateItem(RRPSS pos);

  /**
   * This method is used to remove an existing menu item.
   *
   * @param pos Reference to the instance of RRPSS.
   */
  void removeItem(RRPSS pos);
}
