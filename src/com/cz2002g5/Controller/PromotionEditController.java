package com.cz2002g5.Controller;

import com.cz2002g5.View.PromotionEditorView;

import java.io.IOException;
import java.util.Scanner;

public class PromotionEditController implements MenuEditController {

    @Override
    public void selectAction(RRPSS pos) throws IOException {
        while (true) {
            Scanner sc = new Scanner(System.in);
            RRPSS.updateView(pos, new PromotionEditorView());
            RRPSS.showView(pos);
            while (!sc.hasNextInt()) {
                System.out.println("You have inputted a non-numerical value!\nSelect your action:");
                sc.next();
            }
            int action = sc.nextInt();
            switch (action) {
                case 1:
                    this.createItem(pos);
                    return;
                case 2:
                    this.updateItem(pos);
                    return;
                case 3:
                    this.removeItem(pos);
                    return;
                case 4:
                    return;
                default:
                    System.out.println("You have selected an invalid action!");
            }
        }
    }

    @Override
    public void createItem(RRPSS pos) {

    }

    @Override
    public void updateItem(RRPSS pos) {

    }

    @Override
    public void removeItem(RRPSS pos) {

    }
}
