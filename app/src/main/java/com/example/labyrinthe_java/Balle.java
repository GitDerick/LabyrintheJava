package com.example.labyrinthe_java;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Balle {
    private float x, y;
    private float radius;
    private Paint paint;
    private boolean visible;



    public Balle(float x, float y, float radius, int color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
        visible = true;
    }

    public void onDrawBall(Canvas canvas) {
        if (visible) {
            canvas.drawCircle(x, y, radius, paint);
        }
    }


    public void moveBall(float newX, float newY) {
        x = newX;
        y = newY;
    }public void deplacer(float newX, float newY, float screenWidth, float screenHeight) {
        float leftLimit = radius;
        float rightLimit = screenWidth - radius;
        float topLimit = radius;
        float bottomLimit = screenHeight - radius;

        x = Math.min(Math.max(newX, leftLimit), rightLimit);
        y = Math.min(Math.max(newY, topLimit), bottomLimit);


    }

    public float getRadius() {
        return radius;
    }

    public boolean collisionMur(Mur mur) {
        float distanceX = Math.abs(x - mur.getX());
        float distanceY = Math.abs(y - mur.getY());

        if (distanceX <= mur.getWidth() / 2 + radius && distanceY <= mur.getHeight() / 2 + radius) {
            return true;
        } else {
            // Pas de collision
            return false;
        }
    }


    public boolean collisionAvecTrou(Trou trou) {
        float distanceX = Math.abs(x - trou.getX());
        float distanceY = Math.abs(y - trou.getY());

        if (distanceX <= trou.getRadius() && distanceY <= trou.getRadius()) {
            // Collision détecté
            return true;
        } else {
            // Pas de collision
            return false;
        }
    }


    public boolean collisionAvecArrivee(Arrivee arrivee) {
        float distanceX = Math.abs(x - arrivee.getX());
        float distanceY = Math.abs(y - arrivee.getY());

        // Vérifier si la distance horizontale ou verticale entre la balle et le trou est inférieure au rayon de la balle
        if (distanceX <= arrivee.getRadius() && distanceY <= arrivee.getRadius()) {
            // Collision détectée
            return true;
        } else {
            // Pas de collision
            return false;
        }
    }




    public void Invisible() {
        visible = false;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
