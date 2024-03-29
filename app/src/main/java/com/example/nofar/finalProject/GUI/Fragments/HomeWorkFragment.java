package com.example.nofar.finalProject.GUI.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;
import com.example.nofar.finalProject.LOGIC.Core.User;
import com.example.nofar.finalProject.LOGIC.Interfaces.OnHWDialogListener;
import com.example.nofar.finalProject.LOGIC.Interfaces.RefreshDataSetListener;
import com.example.nofar.finalProject.R;

public class HomeWorkFragment extends Fragment implements RefreshDataSetListener, OnHWDialogListener
{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private OnHWDialogListener onHWDialogListener;
    private UncompletedHWFragment uncompletedHWFragment;
    private CompletedHWFragment completedHWFragment;
    private final int ITEMS = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_home_work, container, false);
        tabLayout = view.findViewById(R.id.hwTabs);
        viewPager = view.findViewById(R.id.hwViewPager);
        uncompletedHWFragment = new UncompletedHWFragment();
        uncompletedHWFragment.setOnHWDialogListener(this);
        completedHWFragment = new CompletedHWFragment();
        completedHWFragment.setOnHWDialogListener(this);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
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

    public void setOnHWDialogListener(OnHWDialogListener onHWDialogListener)
    {
        this.onHWDialogListener = onHWDialogListener;
    }

    @Override
    public void RefreshDataSet()
    {
        User.Student.SaveChanges(FirebaseDatabase.getInstance().getReference());
        uncompletedHWFragment.RefreshDataSet();
    }

    @Override
    public void OnHWDialog(String index)
    {
        this.onHWDialogListener.OnHWDialog(index);
    }

    class MyAdapter extends FragmentPagerAdapter
    {
        public MyAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch (position)
            {
                case 0:
                    return uncompletedHWFragment;
                case 1:
                    return completedHWFragment;
            }
            return null;
        }

        @Override
        public int getCount()
        {
            return ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position)
            {
                case 0:
                    return getResources().getString(R.string.uncompleteed);
                case 1:
                    return getResources().getString(R.string.completed);
            }
            return null;
        }
    }
}


