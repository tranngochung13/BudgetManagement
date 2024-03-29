package com.example.budgetmanagement.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.budgetmanagement.model.Todo;

@Database(entities = {Todo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
}