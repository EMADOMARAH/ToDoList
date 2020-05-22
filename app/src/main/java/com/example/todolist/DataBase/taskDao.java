package com.example.todolist.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist.Models.taskModel;

import java.util.List;

@Dao
public interface taskDao
{
    @Insert
    void insert(taskModel... t);

    @Query("SELECT * FROM taskModel WHERE priority = 1")
    List<taskModel> gethigh();

    @Query("SELECT * FROM taskModel WHERE priority = 2")
    List<taskModel> getmedium();

    @Query("SELECT * FROM taskModel WHERE priority = 3")
    List<taskModel> getlow();

    @Update
    void update(taskModel taskModel);

    @Delete
    void delete(taskModel taskModel);


}
