package com.iotwebplanet.learn.hostel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by developer on 07/07/17.
 */

public class HintAdapter extends ArrayAdapter<Objects> {


    public HintAdapter(@NonNull Context context, int resource, @NonNull List<Objects> objects) {
        super(context, resource, objects);
    }


    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}