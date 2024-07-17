package com.example.labyrinthe_java;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Arrivee {
    private float x;
    private float y;
    private float radius;
    private Paint paint;



    public Arrivee(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        paint = new Paint();
    }

    public boolean Ballecollision(Balle balle) {
        float distanceX = balle.getX() - x;
        float distanceY = balle.getY() - y;
        float distance = (float) Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        return distance <= radius;
    }

    public void onDrawEnd(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        canvas.drawCircle(x, y, radius, paint);
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
