package com.example.nofar.finalProject.GUI.Adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nofar.finalProject.GUI.Views.Oval;
import com.example.nofar.finalProject.LOGIC.Core.Exam;
import com.example.nofar.finalProject.LOGIC.Core.StudyGroup;
import com.example.nofar.finalProject.LOGIC.Interfaces.OnLongGradedItemListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.OnLongSGItemListener;
import com.example.nofar.finalProject.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class StudyGroupAdapter extends RecyclerView.Adapter<StudyGroupAdapter.StudyGroupViewHolder>
{
    private final String DATE_PATTERN = "E','dd MMM HH:mm a ";
    private ArrayList<StudyGroup> gradedList;
    private OnLongSGItemListener onLongSGItemListener;

    public StudyGroupAdapter(ArrayList<StudyGroup> gradedList, OnLongSGItemListener onLongSGItemListener)
    {
        this.onLongSGItemListener = onLongSGItemListener;
        this.gradedList = gradedList;
    }

    public StudyGroupAdapter()
    {
        this.onLongSGItemListener = new OnLongSGItemListener() {
            @Override
            public void OnLongSGItemListener(int pos) {

            }
        };
        this.gradedList = new ArrayList<>();
    }

    public PagerAdapter onCreateViewHolder() {
        return null;
    }


    public class StudyGroupViewHolder extends ViewHolder
    {
        public TextView examClassText;
        public TextView examPointsText;
        public TextView examTermText;
        public TextView examDateText;
        public View view;
        private ViewGroup Foreground;
        private ViewGroup Background;

        public StudyGroupViewHolder(View itemView)
        {
            super(itemView);
            view = itemView;
            examClassText = itemView.findViewById(R.id.examClassText);
            examPointsText = itemView.findViewById(R.id.examPointsText);
            examTermText = itemView.findViewById(R.id.examTermText);
            examDateText = itemView.findViewById(R.id.examDateText);
            Foreground = itemView.findViewById(R.id.viewForeground);
            Background = itemView.findViewById(R.id.Background);

        }

        @Override
        public View getForeground()
        {
            return Foreground;
        }

        @Override
        public View getBackground()
        {
            return Background;
        }
    }


    @Override
    public StudyGroupAdapter.StudyGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.graded_exam_item, parent, false);
        return new StudyGroupAdapter.StudyGroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final StudyGroupAdapter.StudyGroupViewHolder holder, final int position)
    {
        StudyGroup studyGroup = gradedList.get(position);
        holder.view.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                System.out.println(holder.getAdapterPosition());
                onLongSGItemListener.OnLongSGItemListener(holder.getAdapterPosition());
                return true;
            }
        });
        holder.examClassText.setText(studyGroup.getCourse().getCourseName());
        holder.examPointsText.setText(String.valueOf(studyGroup.getCourse().getPoints()));
        Date date = studyGroup.GetStudyGroupDateAsCalendar().getTime();
        SimpleDateFormat ft = new SimpleDateFormat(DATE_PATTERN);
        holder.examDateText.setText(ft.format(date));
        holder.getForeground().setBackgroundColor(studyGroup.getCourse().getCourseColor());
    }

    @Override
    public int getItemCount()
    {
        return gradedList.size();
    }
}

