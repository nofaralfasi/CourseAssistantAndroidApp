package com.example.nofar.finalProject.LOGIC.Core;

import android.support.annotation.NonNull;

import com.example.nofar.finalProject.LOGIC.Enums.Term;
import com.google.firebase.database.Exclude;

import java.util.Calendar;

public class StudyGroup implements Comparable<StudyGroup>
{
    private Course course;
    private Calendar studyGroupDate;
    private int notify;
    private String SGName;
    private int pushId;

    public StudyGroup(Course course, Calendar studyGroupDate, int notify, String SGName)
    {
        this.SGName=SGName;
        this.course = course;
        this.studyGroupDate = studyGroupDate;
        this.notify = notify;
    }

    public StudyGroup()
    {
        studyGroupDate = Calendar.getInstance();
    }

    public StudyGroup(StudyGroup currentStudyGroup)
    {
        this.course = currentStudyGroup.getCourse();
        this.studyGroupDate = Calendar.getInstance();
        this.studyGroupDate.setTimeInMillis(currentStudyGroup.GetStudyGroupDateAsCalendar().getTimeInMillis());
        this.notify = getNotify();
    }

    public Calendar GetStudyGroupDateAsCalendar()
    {
        return this.studyGroupDate;
    }

    public int getNotify()
    {
        return notify;
    }

    public void setNotify(int notify)
    {
        this.notify = notify;
    }

    public long getStudyGroupDate()
    {
        return studyGroupDate.getTimeInMillis();
    }

    public String getStudyGroupName()
    {
        return SGName;
    }

    public void setStudyGroupDate(long studyGroupDate)
    {
        this.studyGroupDate.setTimeInMillis(studyGroupDate);
    }

    public int getPushId()
    {
        return pushId;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    @Exclude
    public Course getCourse()
    {
        return course;
    }

    public void setPushId(int pushId)
    {
        this.pushId = pushId;
    }

    @Override
    public int compareTo(@NonNull StudyGroup studyGroup)
    {
        return this.studyGroupDate.compareTo(studyGroup.GetStudyGroupDateAsCalendar());
    }

    public Term getTerm() {
        return Term.A;
    }

    public Calendar GetToDateAsObject()
    {
        return studyGroupDate;
    }

}

