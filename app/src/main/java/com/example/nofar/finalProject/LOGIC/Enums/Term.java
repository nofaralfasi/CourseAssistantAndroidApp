package com.example.nofar.finalProject.LOGIC.Enums;

public enum Term
{
    A, B, C;

    public static String[] GetOpt()
    {
        String[] strings = new String[Term.values().length];
        for (int i = 0; i < Term.values().length; i++)
        {
            strings[i] = Term.values()[i].toString();
        }
        return strings;
    }
}

