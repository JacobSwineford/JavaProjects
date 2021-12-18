package Misc.CustomClasses.GatlingGun;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * @author Jacob Swineford
 */
class Gun {

    private Pane bullets;
    private Rectangle[] bulletsArr;
    private Scene scene;

    class Bullet {

        private Rectangle bullet;
        private Timeline t;

        Bullet(double width, double height, double angle) {
            bulletsArr = new Rectangle[50];
            if (!makeBullet(width, height, angle)) return;
            initBulletTimeline(angle);
        }

        private void shoot() {if(t == null) return; t.play();}

        private boolean makeBullet(double width, double height, double angle) {
            bullet = new Rectangle();
            if (!putInNextAvailableSpot(bullet, bulletsArr)) return false;
            bullet.setFill(Color.BLACK);
            bullet.setWidth(width);
            bullet.setHeight(height);
            bullet.setRotate(Math.toDegrees(angle));
            bullet.setX(gun.getTranslateX());
            bullet.setY(gun.getTranslateY());
            bullets.getChildren().add(bullet);
            return true;
        }

        private void initBulletTimeline(double angle) {
            KeyFrame kf = new KeyFrame(Duration.millis(1), event -> {
                double x = Math.sin(Math.toRadians(angle));
                double y = Math.cos(Math.toRadians(angle));
                bullet.setX(bullet.getX() + x);
                bullet.setY(bullet.getY() + y);
            });
            t = new Timeline(kf);
            t.setCycleCount(2000);
            t.setOnFinished(event -> {
                bullets.getChildren().remove(bullet);
                findAndRemove(bullet, bulletsArr);
            });
        }
    }

    private Pane gun;

    private double mouseX;
    private double mouseY;
    private double currentAngle;

    private Timeline gatling;

    Gun(double radius, double barrelLength, Scene scene) {
        this.scene = scene;
        bullets = new Pane();
        Rectangle bg = new Rectangle(scene.getWidth(), scene.getHeight());
        bg.setFill(Color.TRANSPARENT);
        bullets.setMouseTransparent(true);
        bullets.getChildren().add(bg);
        gun = new Pane();
        assembleGun(radius, barrelLength);
        setMouseEvents(scene);
        setGatlingTimeline();

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case G: toggleGatling();
            }
        });
    }

    private void assembleGun(double radius, double barrelLength) {
        Circle origin = new Circle();
        origin.setRadius(radius);
        origin.setFill(Color.BLACK);
        origin.setStroke(Color.RED);
        origin.setStrokeWidth(1);

        Rectangle arm = new Rectangle();
        arm.setWidth(barrelLength);
        arm.setHeight(radius);
        arm.setFill(Color.BLACK);
        arm.setStroke(Color.RED);
        arm.setStrokeWidth(1);
        arm.setX(-barrelLength / 2);
        arm.setY(-radius / 2);

        initRotationThread(arm);
        gun.getChildren().addAll(origin, arm);
    }

    private void initRotationThread(Rectangle arm) {
        class RotationThread extends Thread {
            public void run() {
                while (true) {
                    try {
                        sleep(1);
                        currentAngle = Math.toDegrees(Math.atan(mouseX / mouseY));
                        double rotation = 90 - currentAngle;
                        arm.setRotate(rotation);
                    } catch (Exception e) {
                        break;
                    }
                }
            }
        }
        Thread rt = new RotationThread();
        rt.start();
    }

    private void  setMouseEvents(Scene scene) {
        // move and shoot
        scene.setOnMouseMoved(event -> {
            mouseX = event.getX();
            mouseY = event.getY();
        });
        scene.setOnMouseClicked(event -> shoot());
    }

    private void setGatlingTimeline() {
        // initially off
        KeyFrame gat = new KeyFrame(Duration.millis(100), event -> shoot());
        gatling = new Timeline(gat);
        gatling.setCycleCount(-1);
    }

    private void shoot() {
        Bullet b = new Bullet(20, 20, currentAngle);
        b.shoot();
    }

    private boolean putInNextAvailableSpot(Rectangle rec, Rectangle[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                arr[i] = rec;
                return true;
            }
        }
        return false;
    }

    private void findAndRemove(Rectangle rec, Rectangle[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == rec) {arr[i] = null; return;}
        }
    }

    private int quadrant() {
        double midX = scene.getWidth() / 2;
        double midY = scene.getHeight() / 2;
        if (mouseX > midX && mouseY > midY) return 1;
        if (mouseX < midX && mouseY > midY) return 2;
        if (mouseX < midX && mouseY < midY) return 3;
        return 4;
    }

    Pane getGun() {return gun;}

    Pane getBullets() {return bullets;}

    private int gat = -1;
    void toggleGatling() {
        if (gat == -1) gatling.play();
        else gatling.pause();
        gat = -gat;
        System.out.println("toggled");
    }
}
