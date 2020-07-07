package com.zhenzhen.model;

public class ScoreModel {
    public int score;

    /**
     * 计算得分
     *
     * @param lines 消行数
     */
    public void addScore(int lines) {
        if (lines == 0) return;

        score += lines + (lines - 1);
    }
}
