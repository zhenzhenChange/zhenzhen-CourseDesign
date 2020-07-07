package com.zhenzhen.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Button;

import com.zhenzhen.Config;
import com.zhenzhen.R;
import com.zhenzhen.model.BoxModel;
import com.zhenzhen.model.MapModel;
import com.zhenzhen.model.ScoreModel;

public class GameController {
    BoxModel boxModel;             // 方块模型
    MapModel mapModel;             // 地图模型

    public ScoreModel scoreModel;  // 分数模型

    Handler handler;   // 消息处理程序
    Button btnStart;   // 暂停按钮

    public Thread autoFallThread;      // 自动下落线程

    public boolean isOver = false;     // 结束标志位 true -> 结束 Or false -> 未结束
    public boolean isPause = false;    // 暂停标志位 true -> 暂停 Or false -> 未暂停

    public GameController(Handler handler, Button btnStart) {
        this.handler = handler;
        this.btnStart = btnStart;
    }

    /**
     * 绘制游戏
     *
     * @param canvas canvas
     */
    public void drawGame(Canvas canvas) {
        // 绘制地图
        mapModel.drawMaps(canvas);

        // 绘制方块
        boxModel.drawBoxs(canvas);

        // 绘制地图辅助线
        mapModel.drawLines(canvas);

        // 绘制游戏暂停状态文本
        if (isPause) mapModel.drawPauseState(canvas);

        // 绘制游戏结束状态文本
        if (isOver) mapModel.drawOverState(canvas);
    }

    /**
     * 绘制预览
     *
     * @param canvas canvas
     */
    public void drawPreview(Canvas canvas, int width) {
        boxModel.drawPreview(canvas, width);
    }

    /**
     * 初始化数据
     */
    public void initData(Context context) {
        int width = getScreenWidth(context);                // 获取屏幕宽度
        Config.GAME_WIDTH = width * 2 / 3;                  // 设置游戏区域宽度 = 屏幕宽度 * 2 / 3
        Config.GAME_HEIGHT = Config.GAME_WIDTH * 2;         // 设置游戏区域高度 = 游戏区域宽度 * 2

        int boxSize = Config.GAME_WIDTH / Config.MAP_COLS;  // 初始化方块大小 = 游戏区域宽度 / 列数（10）

        // 实例化地图
        mapModel = new MapModel(Config.GAME_WIDTH, Config.GAME_HEIGHT, boxSize);

        // 实例化方块
        boxModel = new BoxModel(boxSize);

        // 实例化分数
        scoreModel = new ScoreModel();
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
        // 清除地图
        mapModel.cleanMaps();

        // 创建新方块
        boxModel.createBoxs();

        // 重新开始设置状态
        isOver = false;
        isPause = false;
        scoreModel.score = 0;
    }

    /**
     * 方块下落方法
     *
     * @return true -> 下落成功 Or false -> 下落失败
     */
    public boolean fallBox() {
        // 移动成功，不做任何处理
        if (boxModel.moveBox(0, 1, mapModel)) return true;

        // 移动失败（说明到边界了），进行堆积 | 在地图上标识为 true ，表明该地图方块被占用
        for (Point box : boxModel.boxs) mapModel.maps[box.x][box.y] = true;

        // 消行
        int linse = mapModel.passLines();

        // 加分
        scoreModel.addScore(linse);

        // 生成新方块
        boxModel.createBoxs();

        // 判断游戏是否结束
        isOver = isOverGame();

        // 下落完毕
        return false;
    }

    /**
     * 游戏结束判断
     *
     * @return true -> 游戏结束 Or false -> 游戏未结束
     */
    public boolean isOverGame() {
        // 地图上的方块重合
        for (Point box : boxModel.boxs) if (mapModel.maps[box.x][box.y]) return true;

        return false;
    }

    /**
     * 控制单击事件
     *
     * @param id button 的 id
     */
    public void onClick(int id) {
        switch (id) {
            // 左移：x 方向上 -1
            case R.id.btn_left:
                // 暂停 / 结束 状态，不进行任何操作
                if (isPause || isOver) return;

                boxModel.moveBox(-1, 0, mapModel);
                break;

            // 旋转
            case R.id.btn_rotate:
                if (isPause || isOver) return;

                boxModel.rotateBox(mapModel);
                break;

            // 右移：x 方向上 +1
            case R.id.btn_right:
                if (isPause || isOver) return;

                boxModel.moveBox(1, 0, mapModel);
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

                // 重新设置按钮的文本
                btnStart.setText("重新开始");
                break;

            // 暂停
            case R.id.btn_pause:
                if (isOver) return;

                isPause = !isPause;
                break;
        }
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
