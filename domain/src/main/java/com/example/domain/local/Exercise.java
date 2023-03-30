package com.example.domain.local;

public class Exercise {

    public int id;
    public boolean isCompleted;
    public String name;

    public Exercise(
            int id,
            boolean isCompleted,
            String name
    ) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.name = name;
    }
}
