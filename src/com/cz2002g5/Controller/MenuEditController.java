package com.cz2002g5.Controller;

import java.io.IOException;

/**
 * This is an interface to be implemented by menu item controller classes.
 */
public interface MenuEditController {
    /**
     * This method will be used when users select an action.
     *
     * @param pos The instance of the main RRPSS controller
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
void selectAction(RRPSS pos) throws IOException;
    /**
     * This method will be used when a new menu item is created.
     *
     * @param pos The instance of the main RRPSS controller
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
void createItem(RRPSS pos) throws IOException;
    /**
     * This method will be used when a menu item is updated.
     *
     * @param pos The instance of the main RRPSS controller
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
void updateItem(RRPSS pos) throws IOException;
    /**
     * This method will be used when a menu item is removed.
     *
     * @param pos The instance of the main RRPSS controller
     * @throws IOException Thrown if file is not found when calling CSVFileUtil class methods io exception
     */
void removeItem(RRPSS pos) throws IOException;
}
