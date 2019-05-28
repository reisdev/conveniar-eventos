package com.mthrsj.conveniareventos;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FoundationPicker extends ListView {
    ArrayAdapter foundations = new ArrayAdapter(this.getContext(), R.layout.foundation);

    FoundationPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setValues(final String[] values) {
        foundations.addAll(values);
        final ListView FoundationsList = findViewById(R.id.FoundationList);
        FoundationsList.setAdapter(foundations);
        FoundationsList.measure(0, 0);

        final int height = (FoundationsList.getMeasuredHeight() / 2);
        Log.i("LST", Integer.toString(height));


        setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        FoundationsList.post(new Runnable() {
            @Override
            public void run() {
                smoothScrollBy(height + 560, 200);
            }
        });
    }

}
