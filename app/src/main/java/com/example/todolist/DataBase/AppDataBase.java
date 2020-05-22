package com.example.todolist.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todolist.Models.taskModel;

@Database(entities = taskModel.class,version = 1)
public abstract class AppDataBase extends RoomDatabase
{
    public abstract taskDao taskDao();
}
