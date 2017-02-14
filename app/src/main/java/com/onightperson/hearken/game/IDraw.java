package com.onightperson.hearken.game;

import android.graphics.Canvas;

/**
 * Created by liubaozhu on 17/1/4.
 */

interface IDraw {

    /**
     * 初始化内容
     */
    void init(Object... objects);

    /**
     * 绘制内容
     * @param canvas
     */
    void onDraw(Canvas canvas);

    /**
     * 数据更新
     */
    void updateUI();

}
