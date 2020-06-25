package com.zhenzhen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    View view;                 // 游戏区域控件
    int gameWidth, gameHeight; // 游戏区域宽高

    Paint boxPaint;            // 方块画笔
    Paint linePaint;           // 辅助线画笔

    int boxSize;               // 方块大小
    Point[] boxs;              // 方块
    boolean[][] maps;          // 地图

    int cols;                  // 地图列数
    int rows;                  // 地图行数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 隐藏系统状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        initData();
        initView();
        initListeners();
    }

    /**
     * 初始化数据
     */
    public void initData() {
        int width = getScreenWidth(this);   // 获取屏幕宽度
        gameWidth = width * 2 / 3;                 // 设置游戏区域宽度 = 屏幕宽度 * 2 / 3
        gameHeight = gameWidth * 2;                // 设置游戏区域高度 = 游戏区域宽度 * 2
        maps = new boolean[10][20];                // 初始化地图

        cols = maps.length;                        // 记录地图列数
        rows = maps[0].length;                     // 记录地图行数

        // 初始化方块
        boxs = new Point[]{new Point(2, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1)};

        boxSize = gameWidth / maps.length;         // 初始化方块大小 = 游戏区域宽度 / 列数（10）
    }

    /**
     * 初始化视图
     */
    public void initView() {
        linePaint = new Paint();                                // 辅助线画笔实例化
        linePaint.setColor(Color.rgb(0, 0, 0));  // 画笔颜色
        linePaint.setAntiAlias(true);                           // 开启抗锯齿

        boxPaint = new Paint();                                 // 方块画笔实例化
        boxPaint.setColor(Color.rgb(0, 0, 0));
        boxPaint.setAntiAlias(true);

        FrameLayout layoutGame = findViewById(R.id.layoutGame); // 获取父容器

        // 实例化游戏区域
        view = new View(this) {
            // 重写游戏区域（重新绘制）
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                // 绘制方块
                for (Point box : boxs) {
                    float currXBoxSize = box.x * boxSize;
                    float currYBoxSize = box.y * boxSize;

                    // 从左上（x, y）开始绘制，加上一个盒子的大小到右下（x + size, y + size）结束
                    canvas.drawRect(currXBoxSize, currYBoxSize, currXBoxSize + boxSize, currYBoxSize + boxSize, boxPaint);
                }

                // 绘制垂直方向辅助线
                for (int x = 0; x < cols + 1; x++) {
                    // 垂直方向上，从 0 开始，到游戏区域的高度结束，间隔为一个盒子的大小（宽度）
                    canvas.drawLine(x * boxSize, 0, x * boxSize, view.getHeight(), linePaint);
                }

                // 绘制水平方向辅助线
                for (int y = 0; y < rows + 1; y++) {
                    // 水平方向上，从 0 开始，到游戏区域的宽度结束，间隔为一个盒子的大小（高度）
                    canvas.drawLine(0, y * boxSize, view.getWidth(), y * boxSize, linePaint);
                }
            }
        };

        view.setLayoutParams(new FrameLayout.LayoutParams(gameWidth, gameHeight));  // 设置游戏区域大小
        view.setBackgroundColor(Color.rgb(255, 255, 255));           // 设置游戏区域背景颜色
        layoutGame.addView(view);                                                   // 追加至父容器内
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
        switch (btn.getId()) {
            // 左移：x 方向上 -1
            case R.id.btn_left:
                moveBox(-1, 0);
                break;

            // 旋转
            case R.id.btn_rotate:
                rotateBox();
                break;

            // 右移：x 方向上 +1
            case R.id.btn_right:
                moveBox(1, 0);
                break;

            // 下移：y 方向上 +1
            case R.id.btn_bottom:
                moveBox(0, 1);
                break;

            // 开始
            case R.id.btn_start:
                break;

            // 暂停
            case R.id.btn_pause:
                break;
        }

        // 操作完毕需重绘视图
        view.invalidate();
    }

    /**
     * 方块移动方法
     *
     * @param x 水平方向偏移量
     * @param y 垂直方向偏移量
     */
    public void moveBox(int x, int y) {
        // 边界预处理
        for (Point box : boxs) if (checkBounddary(box.x + x, box.y + y)) return;

        // 遍历方块数组，在其基础上改变其偏移量
        for (Point box : boxs) {
            box.x += x;
            box.y += y;
        }
    }

    /**
     * 方块旋转方法
     */
    public void rotateBox() {
        // 旋转边界预处理
        for (Point box : boxs) {
            int checkX = -box.y + boxs[0].y + boxs[0].x;
            int checkY = box.x - boxs[0].x + boxs[0].y;
            if (checkBounddary(checkX, checkY)) return;
        }

        // 遍历方块数组，围绕其中心点顺时针旋转 90°
        for (Point box : boxs) {
            // 笛卡尔公式
            int checkX = -box.y + boxs[0].y + boxs[0].x;
            int checkY = box.x - boxs[0].x + boxs[0].y;

            box.x = checkX;
            box.y = checkY;
        }
    }

    /**
     * 边界判断：判断方块是否出界
     *
     * @param x 方块 x 坐标
     * @param y 方块 y 坐标
     * @return true -> 出界 Or false -> 未出界
     */
    public boolean checkBounddary(int x, int y) {
        return (x < 0 || y < 0 || x >= cols || y >= rows);
    }

    /**
     * 获取屏幕宽度
     *
     * @param context 上下文
     * @return 屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}