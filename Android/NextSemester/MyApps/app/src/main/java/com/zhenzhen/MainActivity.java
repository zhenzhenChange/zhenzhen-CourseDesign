package com.zhenzhen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.zhenzhen.controller.GameController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    View view;                         // 游戏区域控件
    Button btnPause;                   // 暂停按钮
    Button btnStart;                   // 开始/重新开始按钮

    GameController gameController;     // 游戏控制器

    // 消息处理程序
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            // 重绘视图
            view.invalidate();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 隐藏系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        // 获取暂停按钮
        btnPause = findViewById(R.id.btn_pause);
        btnStart = findViewById(R.id.btn_start);

        // 实例化游戏控制器
        gameController = new GameController(handler, btnStart);

        // 初始化数据
        gameController.initData(this);

        initView();
        initListeners();
    }

    /**
     * 初始化视图
     */
    public void initView() {
        FrameLayout layoutGame = findViewById(R.id.layoutGame); // 获取父容器

        // 实例化游戏区域
        view = new View(this) {
            // 重写游戏区域（重新绘制）
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                // 设置暂停按钮文本
                btnPause.setText(gameController.isPause ? "继续" : "暂停");

                // 控制绘制
                gameController.draw(canvas);
            }
        };

        view.setLayoutParams(new FrameLayout.LayoutParams(Config.GAME_WIDTH, Config.GAME_HEIGHT));  // 设置游戏区域大小
        view.setBackgroundColor(Color.rgb(255, 255, 255));                           // 设置游戏区域背景颜色
        layoutGame.addView(view);                                                                   // 追加至父容器内
    }

    /**
     * 初始化监听器
     */
    public void initListeners() {
        findViewById(R.id.btn_left).setOnClickListener(this);
        findViewById(R.id.btn_rotate).setOnClickListener(this);
        findViewById(R.id.btn_right).setOnClickListener(this);
        findViewById(R.id.btn_bottom).setOnClickListener(this);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_pause).setOnClickListener(this);
    }

    /**
     * 实现接口方法
     * 捕捉单击事件
     *
     * @param btn 触发事件的按钮
     */
    @Override
    public void onClick(View btn) {
        // 控制单击事件
        gameController.onClick(btn.getId());

        // 操作完毕需重绘视图
        view.invalidate();
    }
}