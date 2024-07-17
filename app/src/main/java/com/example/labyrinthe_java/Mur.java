package com.example.labyrinthe_java;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Mur {

    private int startX, startY, endX, endY;
    private Paint paint;

    public Mur(int startX, int startY, int endX, int endY, Paint paint) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.paint = paint; // Initialiser la variable paint
    }

    public void onDrawMur(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.GRAY); // Couleur du mur (noir dans cet exemple)
        paint.setStrokeWidth(12); // Épaisseur du mur (5 pixels dans cet exemple)
        canvas.drawLine(startX, startY, endX, endY, paint);
    }
    public boolean collisionBalle_Mur(Balle balle) {
        // Coordonnées du centre de la balle
        float balleX = balle.getX();
        float balleY = balle.getY();

        // Calculer le point le plus proche sur le mur à partir du centre de la balle
        float closestX = clamp(balleX, startX, endX);
        float closestY = clamp(balleY, startY, endY);

        // Calculer la distance entre le point le plus proche et le centre de la balle
        float distanceX = balleX - closestX;
        float distanceY = balleY - closestY;
        float distanceSquared = distanceX * distanceX + distanceY * distanceY;

        // Vérifier si la distance est inférieure ou égale au rayon de la balle
        return distanceSquared <= balle.getRadius() * balle.getRadius();
    }

    // Méthode utilitaire pour limiter une valeur à un intervalle
    private float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }



    public int getX() {
        return startX;
    }

    public int getY() {
        return startY;
    }

    public int getWidth() {
        return Math.abs(endX - startX);
    }

    public int getHeight() {
        return Math.abs(endY - startY);
    }




}
