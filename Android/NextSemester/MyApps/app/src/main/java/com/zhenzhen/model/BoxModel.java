package com.zhenzhen.model;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class BoxModel {
    public int boxSize;               // 方块大小
    public int boxType;               // 方块类型
    public Point[] boxs;              // 方块

    Paint boxPaint;                   // 方块画笔

    public BoxModel(int boxSize) {
        this.boxSize = boxSize;

        // 初始化默认值，防止空指针抛异常
        this.boxs = new Point[]{};

        // 方块画笔实例化
        boxPaint = new Paint();
        boxPaint.setColor(0xff000000);
        boxPaint.setAntiAlias(true);
    }

    /**
     * 创建方块、生成新方块
     */
    public void createBoxs() {
        // 随机生成方块的类型
        Random random = new Random();
        boxType = random.nextInt(7);

        switch (boxType) {
            // 田
            case 0:
                boxs = new Point[]{new Point(4, 0), new Point(5, 0), new Point(4, 1), new Point(5, 1)};
                break;
            // L
            case 1:
                boxs = new Point[]{new Point(4, 1), new Point(5, 0), new Point(3, 1), new Point(5, 1)};
                break;
            // J
            case 2:
                boxs = new Point[]{new Point(4, 1), new Point(3, 0), new Point(3, 1), new Point(5, 1)};
                break;
            // I
            case 3:
                boxs = new Point[]{new Point(4, 0), new Point(3, 0), new Point(5, 0), new Point(6, 0)};
                break;
            // S
            case 4:
                boxs = new Point[]{new Point(4, 1), new Point(4, 0), new Point(3, 1), new Point(5, 0)};
                break;
            // Z
            case 5:
                boxs = new Point[]{new Point(4, 1), new Point(3, 0), new Point(4, 0), new Point(5, 1)};
                break;
            // T
            case 6:
                boxs = new Point[]{new Point(4, 1), new Point(4, 0), new Point(3, 1), new Point(5, 1)};
                break;
        }
    }

    /**
     * 绘制方块
     *
     * @param canvas canvas
     */
    public void drawBoxs(Canvas canvas) {
        for (Point box : boxs) {
            float currXBoxSize = box.x * boxSize;
            float currYBoxSize = box.y * boxSize;

            // 从左上（x, y）开始绘制，加上一个盒子的大小到右下（x + size, y + size）结束
            canvas.drawRect(currXBoxSize, currYBoxSize, currXBoxSize + boxSize, currYBoxSize + boxSize, boxPaint);
        }
    }

    /**
     * 方块移动方法
     *
     * @param x 水平方向偏移量
     * @param y 垂直方向偏移量
     * @return true -> 移动成功 Or false -> 移动失败
     */
    public boolean moveBox(int x, int y, MapModel mapModel) {
        // 边界预处理
        for (Point box : boxs) if (checkBounddary(box.x + x, box.y + y, mapModel)) return false;

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
    public void rotateBox(MapModel mapModel) {
        // 田 字型方块，跳过
        if (boxType == 0) return;

        // 旋转边界预处理
        for (Point box : boxs) {
            int checkX = -box.y + boxs[0].y + boxs[0].x;
            int checkY = box.x - boxs[0].x + boxs[0].y;
            if (checkBounddary(checkX, checkY, mapModel)) return;
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
    public boolean checkBounddary(int x, int y, MapModel mapModel) {
        return (x < 0 || y < 0 || x >= mapModel.maps.length || y >= mapModel.maps[0].length || mapModel.maps[x][y] /* 碰到了地图上的其他方块 */);
    }
}
