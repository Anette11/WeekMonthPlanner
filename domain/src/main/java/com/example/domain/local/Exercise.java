package com.example.domain.local;

public class Exercise {

    public int id;
    public boolean isCompleted;
    public String name;
    public long modifiedAt;

    public Exercise(
            int id,
            boolean isCompleted,
            String name,
            long modifiedAt
    ) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.name = name;
        this.modifiedAt = modifiedAt;
    }
}