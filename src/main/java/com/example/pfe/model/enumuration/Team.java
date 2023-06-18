package com.example.pfe.model.enumuration;

public enum Team {
     SHIFT1,
     SHIFT2,
     SHIFT3;
    private static final Team[] ENUMS = Team.values();


    public static Team of(int alias) {
        return ENUMS[alias - 1];
    }
}
