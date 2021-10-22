package com.cz2002g5.Controller;

import java.io.IOException;

public interface MenuEditController {
    void selectAction(RRPSS pos) throws IOException;
    void createItem(RRPSS pos) throws IOException;
    void updateItem(RRPSS pos) throws IOException;
    void removeItem(RRPSS pos) throws IOException;
}
