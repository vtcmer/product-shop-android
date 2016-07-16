package com.product.shop.productshop.productCart.ui;

import android.gesture.Gesture;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by vtcmer on 27/06/2016.
 */
public class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    private SwiteGestureListener listener;


    public SwipeGestureDetector(SwiteGestureListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX <= 0) {
                        listener.onDismiss();
                    }
                }
            }
            result = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
