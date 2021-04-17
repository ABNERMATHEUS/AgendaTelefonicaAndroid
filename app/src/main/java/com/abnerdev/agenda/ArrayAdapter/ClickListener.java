package com.abnerdev.agenda.ArrayAdapter;

import android.view.View;

public interface ClickListener {

    void onItemClick(int position, View view);
    boolean onItemLongClick(int position,View view);
}
