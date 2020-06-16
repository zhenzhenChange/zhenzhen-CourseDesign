package com.zhenzhen.apps;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class activity_app_07_02_Button extends Button {

    public activity_app_07_02_Button(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            System.out.println("Button中的事件处理触发~");
        }
        return false;
    }
}