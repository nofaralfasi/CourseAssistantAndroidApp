package com.example.nofar.finalProject.LOGIC.Enums;

public enum Day
{
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

    public int GetDayNumber()
    {
        return this.ordinal() + 1;
    }

    @Override
    public String toString()
    {
        switch (this)
        {
            case SUNDAY:
                return "Sunday";
            case MONDAY:
                return "Monday";
            case TUESDAY:
                return "Tuesday";
            case WEDNESDAY:
                return "Wednesday";
            case THURSDAY:
                return "Thursday";
            case FRIDAY:
                return "Friday";
            case SATURDAY:
                return "Saturday";
            default:
                return "Sunday";
        }
    }
}
