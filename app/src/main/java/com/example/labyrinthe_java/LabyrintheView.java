package com.example.labyrinthe_java;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LabyrintheView extends View implements SensorEventListener {


    private SensorManager sensorManager;
    private List<Mur> murs;
    private List<Trou> trous;
    private Balle balle;
    private Arrivee arrivee;

    private void init(Context context) {
        murs = new ArrayList<>();
        Random random = new Random();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(12);


        murs.add(new Mur(80, random.nextInt(300) + 50, 250, random.nextInt(300) + 50, paint));
        murs.add(new Mur(95, random.nextInt(300) + 300, 200, random.nextInt(300) + 300, paint));
        murs.add(new Mur(150, random.nextInt(500) + 350, 500, random.nextInt(500) + 350, paint));
        murs.add(new Mur(100, random.nextInt(200) + 650, 350, random.nextInt(200) + 650, paint));
        murs.add(new Mur(350, random.nextInt(300) + 1200, 580, random.nextInt(300) + 1200, paint));
        murs.add(new Mur(200, random.nextInt(150) + 1000, 350, random.nextInt(150) + 1000, paint));
        murs.add(new Mur(500, random.nextInt(300) + 50, 850, random.nextInt(300) + 50, paint));
        murs.add(new Mur(430, random.nextInt(200) + 1150, 800, random.nextInt(200) + 1150, paint));



        // Ajouter des traits verticaux
        murs.add(new Mur(random.nextInt(300) + 50, 100, random.nextInt(300) + 50, 400, paint));
        murs.add(new Mur(random.nextInt(300) + 500, 450, random.nextInt(300) + 500, 850, paint));
        murs.add(new Mur(random.nextInt(300) + 650, 300, random.nextInt(300) + 650, 500, paint));
        murs.add(new Mur(random.nextInt(300) + 440, 900, random.nextInt(300) + 440, 1050, paint));
        murs.add(new Mur(random.nextInt(300) + 550, 1450, random.nextInt(300) + 550, 1700, paint));
        murs.add(new Mur(random.nextInt(300) + 750, 900, random.nextInt(300) + 750, 1150, paint));
        murs.add(new Mur(random.nextInt(300) + 600, 800, random.nextInt(300) + 600, 1000, paint));


        trous = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            int x = random.nextInt(1500) + 100; // Valeur aléatoire pour x entre 100 et 1599
            int y = random.nextInt(1500) + 200; // Valeur aléatoire pour y entre 200 et 1699
            int radius = random.nextInt(15) + 15; // Valeur aléatoire pour le rayon entre 15 et 29
            trous.add(new Trou(x, y, radius));
        }

        //arrivee = new Arrivee(425, 50, 30);
        int xEnd = random.nextInt(900) + 50; // Valeur aléatoire pour x entre 50 et 949
        int yEnd = random.nextInt(300) + 50; // Valeur aléatoire pour y entre 50 et 349
        arrivee = new Arrivee(xEnd, yEnd, 30);


        balle = new Balle(300, 1900, 15, Color.GREEN);
        int xBall = random.nextInt(900) + 50; // Valeur aléatoire pour x entre 50 et 949
        int yBall = random.nextInt(1800) + 100; // Valeur aléatoire pour y entre 100 et 1899

        balle = new Balle(xBall, yBall, 15, Color.GREEN);

        // Initialiser le gestionnaire de capteurs
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public LabyrintheView(Context context) {
        super(context);
        init(context);
    }


    public LabyrintheView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LabyrintheView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }


    private void checkCollision() {
        for (Trou trou : trous) {
            if (trou.collisionBalle_Trou(balle)) {
                Toast.makeText(getContext(), "Game Over ! Vous avez heurté un trou !", Toast.LENGTH_SHORT).show();

                balle.Invisible();

                return;
            }
        }

        for (Mur mur : murs) {
            if (mur.collisionBalle_Mur(balle)) {
                Toast.makeText(getContext(), "Game Over ! Vous avez heurté un mur !", Toast.LENGTH_SHORT).show();

                balle.Invisible();

                return;
            }
        }

        if (arrivee.Ballecollision(balle)) {
            Toast.makeText(getContext(), "Félicitations !", Toast.LENGTH_LONG).show();

            balle.Invisible();
        }
    }


    // Dans la classe Mur
    public void repositionnerMur() {
        Random random = new Random();
        // Redéfinir les coordonnées du mur avec de nouvelles valeurs aléatoires
        int x1 = 80;
        int y1 = random.nextInt(300) + 50;
        int x2 = 250;
        int y2 = random.nextInt(300) + 50;
    }

    // Dans la classe Trou
    public void repositionnerTrou() {
        Random random = new Random();
        // Redéfinir les coordonnées du trou avec de nouvelles valeurs aléatoires
        int x = random.nextInt(1500) + 100;
        int y = random.nextInt(1500) + 200;
        int rayon = random.nextInt(15) + 15;
    }

    // Dans la classe Balle
    public void repositionnerBalle() {
        Random random = new Random();
        // Redéfinir les coordonnées de la balle avec de nouvelles valeurs aléatoires
        int x = random.nextInt(900) + 50;
        int y = random.nextInt(1800) + 100;
    }

    // Dans la classe Arrivee
    public void repositionnerArrivee() {
        Random random = new Random();
        // Redéfinir les coordonnées du point de sortie avec de nouvelles valeurs aléatoires
        int x = random.nextInt(900) + 50;
        int y = random.nextInt(300) + 50;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (murs != null) {
            for (Mur mur : murs) {
                mur.onDrawMur(canvas);
            }
        }

        if (trous != null) {
            for (Trou trou : trous) {
                trou.onDrawTrou(canvas);
            }
        }

        if (balle != null) {
            balle.onDrawBall(canvas);
        }

        if (arrivee != null) {
            arrivee.onDrawEnd(canvas);
        }

    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        // Mettre à jour la position de la balle en fonction des valeurs de l'accéléromètre
        float xAcceleration = event.values[0];
        float yAcceleration = event.values[1];
        float newX = balle.getX() + xAcceleration * 1.0f;
        float newY = balle.getY() + yAcceleration * 1.0f;

        int screenWidth = getWidth() /* obtenir la largeur de l'écran */;
        int screenHeight = getHeight()/* obtenir la hauteur de l'écran */;
        checkCollision();
        balle.deplacer(newX, newY, screenWidth, screenHeight);
        invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float xTouch = event.getX();
        float yTouch = event.getY();

        balle.moveBall(xTouch, yTouch);

        // Vérifier les collisions avec les murs et les trous
        checkCollision();

        // Redessiner la vue
        invalidate();

        return true;
    }
}
