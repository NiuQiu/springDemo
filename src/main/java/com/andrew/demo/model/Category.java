package com.andrew.demo.model;

public enum Category {
    // enum with field
    FICTION (1, "fiction"),
    TECHNOLOGY(2, "technology"),
    ROMMANCE(3, "romance"),
    FREE_GYAN(4, "free_gyan"),
    NONE(5, "none");

    private final int level;
    private final String type;

    Category(int level, String type){
        this.level = level;
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }
}
