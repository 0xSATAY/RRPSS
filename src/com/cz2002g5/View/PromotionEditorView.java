package com.cz2002g5.View;

public class PromotionEditorView implements View{

    @Override
    public void show() {
        System.out.println("---Editing Promotion---\n1. Create promotion\n2. Update promotion\n3. Remove promotion\n4. Cancel\nSelect your action:");
    }

    @Override
    public void show(String s) {

    }
}
