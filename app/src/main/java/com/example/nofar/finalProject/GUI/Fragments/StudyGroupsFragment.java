package com.example.nofar.finalProject.GUI.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nofar.finalProject.GUI.Adapters.StudyGroupAdapter;
import com.example.nofar.finalProject.LOGIC.Core.User;
import com.example.nofar.finalProject.LOGIC.Interfaces.RefreshDataSetListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.ShowDialogStudyGroupListener;
import com.example.nofar.finalProject.R;
import com.google.firebase.database.FirebaseDatabase;

public class StudyGroupsFragment extends android.support.v4.app.Fragment implements RefreshDataSetListener, ShowDialogStudyGroupListener
{
    private final int ITEM = 2;
    private ShowDialogStudyGroupListener dialogStudyGroupListener;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public void setDialogStudyGroupListener(@NonNull ShowDialogStudyGroupListener dialogStudyGroupListener)
    {
        this.dialogStudyGroupListener = dialogStudyGroupListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.fragment_study_group, container, false);
        tabLayout = view.findViewById(R.id.sgTabs);
        viewPager = view.findViewById(R.id.studyGroupViewPager);
        viewPager.setAdapter(new StudyGroupAdapter().onCreateViewHolder());
        tabLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        return view;
    }

    @Override
    public void RefreshDataSet()
    {

        User.Student.SaveChanges(FirebaseDatabase.getInstance().getReference());
    }

    @Override
    public void ShowDialogStudyGroup(int pos)
    {
        this.dialogStudyGroupListener.ShowDialogStudyGroup(pos);
    }

}
