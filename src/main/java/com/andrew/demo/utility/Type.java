package com.andrew.demo.utility;

public enum Type {
    FICTION("FICTION"),
    NONFICTION("NON-FICTION");

    public final String type;

    private Type(String genre) {
        this.type = genre;
    }

    public String getType() {
        return type;
    }
}
