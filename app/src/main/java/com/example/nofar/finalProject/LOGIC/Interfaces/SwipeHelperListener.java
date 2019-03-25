package com.example.nofar.finalProject.LOGIC.Interfaces;

import android.support.v7.widget.RecyclerView;

/**
 * Created by nofar on 12/03/2018.
 */

public interface SwipeHelperListener
{
    void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position);
}
