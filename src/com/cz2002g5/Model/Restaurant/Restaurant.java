package com.cz2002g5.Model.Restaurant;

import java.util.ArrayList;

public class Restaurant {
    public static final Integer TOTAL_TABLES;

    static {
        TOTAL_TABLES = 20;
    }

    private final ArrayList<Table> tables;

    {
        tables = new ArrayList<>();
    }

    public Restaurant() {
        for (int i = 0; i< TOTAL_TABLES; i++) {
            this.tables.add(new Table(i,((i+4)/4)*2,false));
        }
    }

    public ArrayList<Table> getTables() {
        return this.tables;
    }
}

