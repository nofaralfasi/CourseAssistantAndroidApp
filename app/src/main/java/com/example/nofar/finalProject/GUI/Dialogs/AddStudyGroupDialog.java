package com.example.nofar.finalProject.GUI.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nofar.finalProject.GUI.Fragments.AddStudyGroupFragment;
import com.example.nofar.finalProject.LOGIC.Interfaces.DismissListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.RefreshDataSetListener;
import com.example.nofar.finalProject.R;

public class AddStudyGroupDialog extends DialogFragment implements DismissListener
{
    RefreshDataSetListener refreshDataSetListener;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MY_DIALOG);
    }

    public void setRefreshDataSetListener(RefreshDataSetListener refreshDataSetListener)
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
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        AddStudyGroupFragment addStudyGroupFragment = new AddStudyGroupFragment();
        addStudyGroupFragment.setDialogDismissCallback(this);
        addStudyGroupFragment.setArguments(getArguments());
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.addDialogFrame, addStudyGroupFragment);
        fragmentTransaction.commitAllowingStateLoss();
        return view;
    }


    @Override
    public void Dismiss()
    {
        this.refreshDataSetListener.RefreshDataSet();
        this.dismiss();
    }
}

