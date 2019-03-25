package com.example.nofar.finalProject.GUI.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nofar.finalProject.GUI.Adapters.GradedExamAdapter;
import com.example.nofar.finalProject.GUI.Dialogs.GradeDialog;
import com.example.nofar.finalProject.GUI.SwipeHelpers.UnCompleteTwoSidesItemHelper;
import com.example.nofar.finalProject.LOGIC.Core.Exam;
import com.example.nofar.finalProject.LOGIC.Core.User;
import com.example.nofar.finalProject.LOGIC.Interfaces.OnLongGradedItemListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.RefreshDataSetListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.ShowDialogExamListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.SwipeHelperListener;
import com.example.nofar.finalProject.R;

import java.util.ArrayList;



public class GradedExamFragment extends Fragment implements RefreshDataSetListener, OnLongGradedItemListener, SwipeHelperListener, DialogInterface.OnClickListener
{

    private RecyclerView gradedRecyclerView;
    private ArrayList<Exam> gradedList;
    private GradedExamAdapter gradedExamAdapter;
    private ShowDialogExamListener dialogExamListener;
    private GradeDialog gradeDialog;
    private TextView pointsText;
    private TextView avgText;
    private int gradeAboutToChangeIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.graded_exam, container, false);
        gradedRecyclerView = view.findViewById(R.id.gradedExamList);
        pointsText = view.findViewById(R.id.gradedExamPointsText);
        avgText = view.findViewById(R.id.gradedExamAvgText);
        gradedList = User.Student.getGradedExams();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        gradedRecyclerView.setLayoutManager(mLayoutManager);
        gradedRecyclerView.setItemAnimator(new DefaultItemAnimator());
        gradedExamAdapter = new GradedExamAdapter(gradedList, this);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new UnCompleteTwoSidesItemHelper<GradedExamAdapter.GradedExamViewHolder>(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(gradedRecyclerView);
        gradedRecyclerView.setAdapter(gradedExamAdapter);
        return view;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible)
    {
        if (gradedExamAdapter != null && menuVisible)
        {
            RefreshDataSet();
        }
        super.setMenuVisibility(menuVisible);
    }

    @Override
    public void RefreshDataSet()
    {
        gradedExamAdapter.notifyDataSetChanged();
        SetAverageTexts();
    }

    private void SetAverageTexts()
    {
        float[] result = User.Student.CalculateExamsAvg();
        pointsText.setText(String.valueOf(result[1]));
        avgText.setText(String.valueOf(result[0]));
    }

    @Override
    public void OnLongGradedItem(int pos)
    {
        gradeDialog = new GradeDialog();
        gradeDialog.setOnClickListener(this);
        gradeAboutToChangeIndex = pos;
        gradeDialog.show(getFragmentManager(), "Number Picker");
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position)
    {
        if (direction == ItemTouchHelper.RIGHT)
        {
            User.Student.RemoveExam(position, true, getContext());
        }
        else
        {
            User.Student.FromGradedToUngraded(position, getContext());
        }
        gradedExamAdapter.notifyItemRemoved(position);
        SetAverageTexts();

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i)
    {

        if (i == - 1)
        {
            gradedList.get(gradeAboutToChangeIndex).setGrade(gradeDialog.getPicker().getValue());
            gradedExamAdapter.notifyItemChanged(gradeAboutToChangeIndex);
            SetAverageTexts();
        }
    }

}

