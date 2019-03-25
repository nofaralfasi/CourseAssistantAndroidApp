package com.example.nofar.finalProject.GUI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class ViewHolder extends RecyclerView.ViewHolder
{
    public ViewHolder(View itemView)
    {
        super(itemView);
    }

    public abstract View getForeground();

    public abstract View getBackground();


}
