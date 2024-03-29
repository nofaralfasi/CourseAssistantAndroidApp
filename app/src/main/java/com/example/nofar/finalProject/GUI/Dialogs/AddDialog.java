package com.example.nofar.finalProject.GUI.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nofar.finalProject.GUI.Fragments.AddClassFirstFragment;
import com.example.nofar.finalProject.GUI.Fragments.AddClassSecondFragment;
import com.example.nofar.finalProject.GUI.Fragments.AddExamFragment;
import com.example.nofar.finalProject.GUI.Fragments.AddFragment;
import com.example.nofar.finalProject.GUI.Fragments.AddHWFragment;
import com.example.nofar.finalProject.GUI.Fragments.AddStudyGroupFragment;
import com.example.nofar.finalProject.LOGIC.Interfaces.AddChoicesListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.DismissListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.FirstAddClassListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.RefreshDataSetListener;
import com.example.nofar.finalProject.R;


public class AddDialog extends DialogFragment implements AddChoicesListener, FirstAddClassListener, DismissListener
{
    private RefreshDataSetListener refreshDataSetListener;

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
    }

    public void setRefreshDataSetListener(@NonNull RefreshDataSetListener refreshDataSetListener)
    {
        this.refreshDataSetListener = refreshDataSetListener;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog d = getDialog();
        if (d != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            d.getWindow().setLayout(width, height);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_add, container, false);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        AddFragment fragment = new AddFragment();
        fragment.listener = this;
        fragmentTransaction.replace(R.id.addDialogFrame, fragment);
        fragmentTransaction.commitAllowingStateLoss();
        return view;
    }

    @Override
    public void MoveToAddClass()
    {
        AddClassFirstFragment addClassFirstFragment = new AddClassFirstFragment();
        addClassFirstFragment.setListener(this);
        Bundle bundle = new Bundle();
        bundle.putString("opt", "new");
        addClassFirstFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.addDialogFrame, addClassFirstFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void MoveToAddHomeWork()
    {
        AddHWFragment addHWFragment = new AddHWFragment();
        Bundle bundle = new Bundle();
        bundle.putString("opt", "new");
        addHWFragment.setArguments(bundle);
        addHWFragment.setDialogDismissCallback(this);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.addDialogFrame, addHWFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void MoveToSecondAddClass(String courseName, float coursePoints, long startDate, long endDate, int color, boolean isExists, int index)
    {
        Bundle bundle = new Bundle();
        bundle.putString("courseName", courseName);
        bundle.putFloat("coursePoints", coursePoints);
        bundle.putLong("startDate", startDate);
        bundle.putLong("endDate", endDate);
        bundle.putInt("color", color);
        bundle.putBoolean("isExists", isExists);
        bundle.putInt("index", index);
        AddClassSecondFragment addClassSecondFragment = new AddClassSecondFragment();
        addClassSecondFragment.setDialogDismissCallback(this);
        addClassSecondFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.addDialogFrame, addClassSecondFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void MoveToAddExam()
    {
        AddExamFragment addExamFragment = new AddExamFragment();
        Bundle bundle = new Bundle();
        bundle.putString("opt", "new");
        addExamFragment.setArguments(bundle);
        addExamFragment.setDialogDismissCallback(this);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.addDialogFrame, addExamFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void MoveToAddStudyGroup()
    {
        AddStudyGroupFragment addStudyGroupFragment = new AddStudyGroupFragment();
        Bundle bundle = new Bundle();
        bundle.putString("opt", "new");
        addStudyGroupFragment.setArguments(bundle);
        addStudyGroupFragment.setDialogDismissCallback(this);
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.addDialogFrame, addStudyGroupFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void Dismiss()
    {
        refreshDataSetListener.RefreshDataSet();
        this.dismiss();
    }
}
