package com.example.todolist.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.jar.Attributes;

@Entity
public class taskModel implements Serializable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "task_id")
    private int id;
    @ColumnInfo(name = "task_name")
    private String taskName;
    @ColumnInfo(name = "priority")
    private int priority;
    @ColumnInfo(name = "date_year")
    private int dateYear;
    @ColumnInfo(name = "date_month")
    private int dateMonth;
    @ColumnInfo(name = "date_day")
    private int dateDay;
    @ColumnInfo(name = "time_houre")
    private int timeHoure;
    @ColumnInfo(name = "time_min")
    private int timeMin;

    public taskModel(String taskName, int priority, int dateYear, int dateMonth, int dateDay, int timeHoure, int timeMin) {
        this.taskName = taskName;
        this.priority = priority;
        this.dateYear = dateYear;
        this.dateMonth = dateMonth;
        this.dateDay = dateDay;
        this.timeHoure = timeHoure;
        this.timeMin = timeMin;
    }

    @Ignore
    public taskModel(int id, String taskName, int priority, int dateYear, int dateMonth, int dateDay, int timeHoure, int timeMin) {
        this.id = id;
        this.taskName = taskName;
        this.priority = priority;
        this.dateYear = dateYear;
        this.dateMonth = dateMonth;
        this.dateDay = dateDay;
        this.timeHoure = timeHoure;
        this.timeMin = timeMin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDateYear() {
        return dateYear;
    }

    public void setDateYear(int dateYear) {
        this.dateYear = dateYear;
    }

    public int getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(int dateMonth) {
        this.dateMonth = dateMonth;
    }

    public int getDateDay() {
        return dateDay;
    }

    public void setDateDay(int dateDay) {
        this.dateDay = dateDay;
    }

    public int getTimeHoure() {
        return timeHoure;
    }

    public void setTimeHoure(int timeHoure) {
        this.timeHoure = timeHoure;
    }

    public int getTimeMin() {
        return timeMin;
    }

    public void setTimeMin(int timeMin) {
        this.timeMin = timeMin;
    }
}

