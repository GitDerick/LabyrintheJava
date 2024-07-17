package com.example.labyrinthe_java;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Trou {
    private float x, y;
    private float radius;
    private Paint paint;
    public Trou(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        paint = new Paint();
    }

    public void onDrawTrou(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, radius, paint);
    }


    public boolean collisionBalle_Trou(Balle balle) {
        float distanceX = x - balle.getX();
        float distanceY = y - balle.getY();
        float distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);
        return distance < radius + balle.getRadius();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getRadius() {
        return radius;
    }
}
