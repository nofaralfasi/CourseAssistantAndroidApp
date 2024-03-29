package com.example.nofar.finalProject.LOGIC.Secondary;

import android.support.annotation.NonNull;

import com.google.firebase.database.Exclude;
import com.example.nofar.finalProject.LOGIC.Core.Course;
import com.example.nofar.finalProject.LOGIC.Enums.TaskType;

public class CalendarHelper implements Comparable<CalendarHelper>
{
    private int hour;
    private int minute;
    private int day;
    private int month;
    private int year;
    private int endHour;
    private int endMinute;
    private String TaskName;
    private Course course;
    private TaskType taskType;
    private long eventId;
    private String courseName;

    public CalendarHelper(int hour, int minute, int day, int month, int year, String taskName, Course course, TaskType taskType)
    {
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.month = month;
        this.year = year;
        TaskName = taskName;
        this.course = course;
        this.taskType = taskType;
        this.eventId = 0;
        this.endMinute = - 1;
        this.endHour = - 1;
        this.courseName = course.getCourseName();
    }

    public CalendarHelper(int hour, int minute, int day, int month, int year, int endHour, int endMinute, String taskName, Course course, TaskType taskType)
    {
        this.hour = hour;
        this.minute = minute;
        this.day = day;
        this.month = month;
        this.year = year;
        this.endHour = endHour;
        this.endMinute = endMinute;
        TaskName = taskName;
        this.course = course;
        this.taskType = taskType;
        this.eventId = 0;
        this.courseName = course.getCourseName();
    }

    public CalendarHelper()
    {
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public int getHour()
    {
        return hour;
    }

    public void setHour(int hour)
    {
        this.hour = hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public void setMinute(int minute)
    {
        this.minute = minute;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public int getEndHour()
    {
        return endHour;
    }

    public void setEndHour(int endHour)
    {
        this.endHour = endHour;
    }

    public int getEndMinute()
    {
        return endMinute;
    }

    public void setEndMinute(int endMinute)
    {
        this.endMinute = endMinute;
    }

    public String getTaskName()
    {
        return TaskName;
    }

    public void setTaskName(String taskName)
    {
        TaskName = taskName;
    }

    @Exclude
    public void setCourse(Course course)
    {
        this.course = course;
    }

    public void setTaskType(TaskType taskType)
    {
        this.taskType = taskType;
    }

    public long getEventId()
    {
        return eventId;
    }

    public void setEventId(long eventId)
    {
        this.eventId = eventId;
    }

    public String GenerateTimeStr()
    {
        String ampm = "PM";
        String minutestr = String.valueOf(minute);
        if (minute < 10)
        {
            minutestr = "0" + minutestr;
        }
        if (hour < 12)
        {
            ampm = "AM";
        }

        return hour + ":" + minutestr + " " + ampm;
    }

    public TaskType getTaskType()
    {
        return taskType;
    }


    @Override
    public int compareTo(@NonNull CalendarHelper calendarHelper)
    {
        int result = this.getHour() - calendarHelper.getHour();
        if (result != 0)
        {
            return result;
        }
        return this.getMinute() - calendarHelper.getMinute();
    }

    @Exclude
    public Course getCourse()
    {
        return course;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj != this)
        {
            CalendarHelper tempCalendarHelper = (CalendarHelper) obj;
            return this.TaskName.equals(tempCalendarHelper.TaskName) && this.course.getCourseName().equals(tempCalendarHelper.course.getCourseName()) && this.taskType == tempCalendarHelper.taskType;
        }
        else
        {
            return true;
        }
    }
}
