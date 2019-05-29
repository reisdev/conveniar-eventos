package com.mthrsj.conveniareventos;

import android.content.Context;
import android.util.AttributeSet;
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
        setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        FoundationsList.post(new Runnable() {
            @Override
            public void run() {
                smoothScrollBy((getHeight()/2)+getHeight()/3, 200);
            }
        });
    }

}
