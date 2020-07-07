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
import android.widget.TextView;

import com.zhenzhen.controller.GameController;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    View gameView;                     // 游戏区域控件
    View previewView;                  // 预览区域控件

    Button btnPause;                   // 暂停按钮
    Button btnStart;                   // 开始/重新开始按钮

    TextView scoreCur;                 // 当前得分文本控件

    GameController gameController;     // 游戏控制器

    // 消息处理程序
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            // 重绘视图
            gameView.invalidate();
            previewView.invalidate();

            // 刷新得分
            scoreCur.setText(gameController.scoreModel.score + "");
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

        // 获取文本控件
        scoreCur = findViewById(R.id.scoreCur);

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
        FrameLayout layoutPreview = findViewById(R.id.layoutPreview); // 获取父容器

        // 实例化游戏区域
        gameView = new View(this) {
            // 重写游戏区域（重新绘制）
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                // 设置暂停按钮文本
                btnPause.setText(gameController.isPause ? "继续" : "暂停");

                // 控制绘制
                gameController.drawGame(canvas);
            }
        };

        gameView.setLayoutParams(new FrameLayout.LayoutParams(Config.GAME_WIDTH, Config.GAME_HEIGHT));  // 设置游戏区域大小
        gameView.setBackgroundColor(Color.rgb(255, 255, 255));                           // 设置游戏区域背景颜色
        layoutGame.addView(gameView);                                                                   // 追加至父容器内

        // 实例化预览区域
        previewView = new View(this) {
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                gameController.drawPreview(canvas, previewView.getWidth());
            }
        };

        previewView.setLayoutParams(new FrameLayout.LayoutParams(-1, 400));
        previewView.setBackgroundColor(0x20000000);
        layoutPreview.addView(previewView);
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
        gameView.invalidate();
        previewView.invalidate();
    }
}