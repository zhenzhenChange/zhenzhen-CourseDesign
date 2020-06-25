package com.zhenzhen.apps;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

public class activity_app_07_01_SizeListener implements View.OnClickListener {
    private TextView textView;

    public activity_app_07_01_SizeListener(TextView view) {
        this.textView = view;
    }

    public void onClick(View view) {
        float size = textView.getTextSize();
        switch (view.getId()) {
            case R.id.bigger:
                size += 2;
                break;
            case R.id.smaller:
                size -= 2;
                break;
            default:
                break;
        }
        if (size <= 8) size = 8;
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }
}