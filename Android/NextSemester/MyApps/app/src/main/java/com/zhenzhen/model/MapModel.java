package com.zhenzhen.model;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.zhenzhen.Config;

import java.util.Arrays;

public class MapModel {
    Paint mapPaint;            // 地图画笔
    Paint linePaint;           // 辅助线画笔
    Paint statePaint;          // 游戏状态画笔

    public int width;          // 地图宽度
    public int height;         // 地图高度
    public int boxSize;        // 方块大小

    public boolean[][] maps;   // 地图

    public MapModel(int width, int height, int boxSize) {
        this.width = width;
        this.height = height;
        this.boxSize = boxSize;

        // 初始化地图大小
        maps = new boolean[Config.MAP_COLS][Config.MAP_ROWS];

        linePaint = new Paint();                                // 辅助线画笔实例化
        linePaint.setColor(0xffff0000);                         // 画笔颜色
        linePaint.setAntiAlias(true);                           // 开启抗锯齿

        mapPaint = new Paint();                                 // 地图画笔实例化
        mapPaint.setColor(0xff666666);
        mapPaint.setAntiAlias(true);

        statePaint = new Paint();                                // 状态画笔实例化
        statePaint.setColor(0xffff0000);
        statePaint.setAntiAlias(true);
        statePaint.setTextSize(100);
    }

    /**
     * 绘制地图
     *
     * @param canvas canvas
     */
    public void drawMaps(Canvas canvas) {
        for (int x = 0; x < maps.length; x++) {
            for (int y = 0; y < maps[x].length; y++) {
                if (maps[x][y])
                    canvas.drawRect(x * boxSize, y * boxSize, x * boxSize + boxSize, y * boxSize + boxSize, mapPaint);
            }
        }
    }

    /**
     * 绘制地图辅助线
     *
     * @param canvas canvas
     */
    public void drawLines(Canvas canvas) {
        // 绘制垂直方向辅助线
        for (int x = 0; x < maps.length + 1; x++) {
            // 垂直方向上，从 0 开始，到游戏区域的高度结束，间隔为一个盒子的大小（宽度）
            canvas.drawLine(x * boxSize, 0, x * boxSize, height, linePaint);
        }

        // 绘制水平方向辅助线
        for (int y = 0; y < maps[0].length + 1; y++) {
            // 水平方向上，从 0 开始，到游戏区域的宽度结束，间隔为一个盒子的大小（高度）
            canvas.drawLine(0, y * boxSize, width, y * boxSize, linePaint);
        }
    }

    /**
     * 绘制游戏结束状态文本
     *
     * @param canvas canvas
     */
    public void drawOverState(Canvas canvas) {
        canvas.drawText("游戏结束", width / 2 - statePaint.measureText("游戏结束") / 2, height / 2, statePaint);
    }

    /**
     * 绘制游戏暂停状态文本
     *
     * @param canvas canvas
     */
    public void drawPauseState(Canvas canvas) {
        canvas.drawText("已暂停", width / 2 - statePaint.measureText("已暂停") / 2, height / 2, statePaint);
    }

    /**
     * 消除行
     */
    public void passLines() {
        for (int y = maps[0].length - 1; y > 0; y--) {
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
        for (boolean[] map : maps) if (!map[y]) return false;

        return true;
    }

    /**
     * 删除行
     */
    public void deleteLines() {
        for (int y = maps[0].length - 1; y > 0; y--) {
            for (int x = 0; x < maps.length; x++) {
                // 将当前行消除，用上一行替代
                maps[x][y] = maps[x][y - 1];
            }
        }
    }

    /**
     * 清除地图
     */
    public void cleanMaps() {
        for (boolean[] map : maps) Arrays.fill(map, false);
    }
}
