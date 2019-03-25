package com.example.nofar.finalProject.GUI.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nofar.finalProject.LOGIC.Interfaces.AddChoicesListener;
import com.example.nofar.finalProject.R;

public class AddFragment extends Fragment
{
    public AddChoicesListener listener;
    ImageView imageClass;
    ImageView imageExam;
    ImageView imageHW;
    ImageView imageStudyGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.add, container, false);
        imageClass = view.findViewById(R.id.addClassImage);
        imageExam = view.findViewById(R.id.addExamImage);
        imageHW = view.findViewById(R.id.addHWImage);
        imageStudyGroup = view.findViewById(R.id.addStudyGroupImage);

        imageClass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.MoveToAddClass();
            }
        });

        imageExam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.MoveToAddExam();
            }
        });

        imageHW.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.MoveToAddHomeWork();
            }
        });

        imageStudyGroup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.MoveToAddStudyGroup();
            }
        });
        return view;
    }

}
