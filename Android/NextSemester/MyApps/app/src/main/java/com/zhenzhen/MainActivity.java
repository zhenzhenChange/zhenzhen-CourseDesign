package com.zhenzhen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    View view;                 // 游戏区域控件
    Button btnPause;           // 暂停按钮
    int gameWidth, gameHeight; // 游戏区域宽高

    Paint boxPaint;            // 方块画笔
    Paint mapPaint;            // 地图画笔
    Paint linePaint;           // 辅助线画笔

    int boxSize;               // 方块大小
    int boxType;               // 方块类型
    Point[] boxs;              // 方块
    boolean[][] maps;          // 地图

    int cols;                  // 地图列数
    int rows;                  // 地图行数

    public Thread autoFallThread;      // 自动下落线程
    public boolean isOver = false;     // 结束标志位 true -> 结束 Or false -> 未结束
    public boolean isPause = false;    // 暂停标志位 true -> 暂停 Or false -> 未暂停

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

        boxs = new Point[]{};                      // 初始化 boxs / 空指针异常 BUG

        boxSize = gameWidth / maps.length;         // 初始化方块大小 = 游戏区域宽度 / 列数（10）
    }

    /**
     * 创建方块、生成新方块
     */
    public void createBoxs() {
        // 随机生成方块的类型
        Random random = new Random();
        boxType = random.nextInt(3);

        switch (boxType) {
            case 0:
                boxs = new Point[]{new Point(4, 0), new Point(5, 0), new Point(4, 1), new Point(5, 1)};
                break;
            case 1:
                boxs = new Point[]{new Point(4, 1), new Point(5, 0), new Point(3, 1), new Point(5, 1)};
                break;
            case 2:
                boxs = new Point[]{new Point(4, 1), new Point(3, 0), new Point(3, 1), new Point(5, 1)};
                break;
        }
    }

    /**
     * 初始化视图
     */
    public void initView() {
        linePaint = new Paint();                                // 辅助线画笔实例化
        linePaint.setColor(0x50000000);                         // 画笔颜色
        linePaint.setAntiAlias(true);                           // 开启抗锯齿

        mapPaint = new Paint();                                 // 地图画笔实例化
        mapPaint.setColor(0xff666666);
        mapPaint.setAntiAlias(true);

        boxPaint = new Paint();                                 // 方块画笔实例化
        boxPaint.setColor(0xff000000);
        boxPaint.setAntiAlias(true);

        FrameLayout layoutGame = findViewById(R.id.layoutGame); // 获取父容器

        // 实例化游戏区域
        view = new View(this) {
            // 重写游戏区域（重新绘制）
            @Override
            protected void onDraw(Canvas canvas) {
                super.onDraw(canvas);

                // 绘制地图
                for (int x = 0; x < cols; x++) {
                    for (int y = 0; y < rows; y++) {
                        if (maps[x][y])
                            canvas.drawRect(x * boxSize, y * boxSize, x * boxSize + boxSize, y * boxSize + boxSize, mapPaint);
                    }
                }

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
                // 暂停 / 结束 状态，不进行任何操作
                if (isPause || isOver) return;

                moveBox(-1, 0);
                break;

            // 旋转
            case R.id.btn_rotate:
                if (isPause || isOver) return;

                rotateBox();
                break;

            // 右移：x 方向上 +1
            case R.id.btn_right:
                if (isPause || isOver) return;

                moveBox(1, 0);
                break;

            // 下落
            case R.id.btn_bottom:
                if (isPause || isOver) return;

                // 持续下落
                while (true) if (!fallBox()) break;
                break;

            // 开始
            case R.id.btn_start:
                startGame();
                break;

            // 重新开始
            case R.id.btn_restart:
                restartGame();
                break;

            // 暂停
            case R.id.btn_pause:
                if (isOver) return;

                isPause = !isPause;

                // 设置暂停按钮文本
                btnPause.setText(isPause ? "继续" : "暂停");
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
     * @return true -> 移动成功 Or false -> 移动失败
     */
    public boolean moveBox(int x, int y) {
        // 边界预处理
        for (Point box : boxs) if (checkBounddary(box.x + x, box.y + y)) return false;

        // 遍历方块数组，在其基础上改变其偏移量
        for (Point box : boxs) {
            box.x += x;
            box.y += y;
        }

        return true;
    }

    /**
     * 方块旋转方法
     */
    public void rotateBox() {
        // 田 字型方块，跳过
        if (boxType == 0) return;

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
     * 方块下落方法
     *
     * @return true -> 下落成功 Or false -> 下落失败
     */
    public boolean fallBox() {
        // 移动成功，不做任何处理
        if (moveBox(0, 1)) return true;

        // 移动失败（说明到边界了），进行堆积 | 在地图上标识为 true ，表明该地图方块被占用
        for (Point box : boxs) maps[box.x][box.y] = true;

        // 消行
        passLines();

        // 生成新方块
        createBoxs();

        // 判断游戏是否结束
        isOver = isOverGame();

        // 下落完毕
        return false;
    }

    /**
     * 消除行
     */
    public void passLines() {
        for (int y = rows - 1; y > 0; y--) {
            if (checkLines(y)) {
                deleteLines();

                // 从消去的下一行开始继续循环
                y++;
            }
        }
    }

    /**
     * 消行判断
     *
     * @param y 地图 y 坐标
     * @return true -> 可以消行 Or false -> 不能消行
     */
    public boolean checkLines(int y) {
        // 要求每一行的每一格都为 true，即有方块占用
        for (int x = 0; x < cols; x++) if (!maps[x][y]) return false;

        return true;
    }

    /**
     * 删除行
     */
    public void deleteLines() {
        for (int y = rows - 1; y > 0; y--) {
            for (int x = 0; x < cols; x++) {
                // 将当前行消除，用上一行替代
                maps[x][y] = maps[x][y - 1];
            }
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
        return (x < 0 || y < 0 || x >= cols || y >= rows || maps[x][y] /* 碰到了地图上的其他方块 */);
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

    /**
     * 开始游戏
     */
    public void startGame() {
        if (autoFallThread == null) {
            autoFallThread = new Thread() {
                @Override
                @SuppressWarnings("InfiniteLoopStatement")
                public void run() {
                    super.run();

                    // 自动下落
                    while (true) {
                        try {
                            // 休眠 500 ms
                            sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        // 暂停 / 结束 状态，跳过
                        if (isPause || isOver) continue;

                        // 执行一次下落
                        fallBox();

                        // 通知主线程重绘视图
                        handler.sendEmptyMessage(0);
                    }
                }
            };

            // 启动线程
            autoFallThread.start();
        }

        for (int x = 0; x < cols; x++) Arrays.fill(maps[x], false);

        createBoxs();
    }

    /**
     * 重新开始游戏
     */
    public void restartGame() {
        // startGame();
    }

    /**
     * 游戏结束判断
     *
     * @return true -> 游戏结束 Or false -> 游戏未结束
     */
    public boolean isOverGame() {
        // 地图上的方块重合
        for (Point box : boxs) if (maps[box.x][box.y]) return true;

        return false;
    }
}