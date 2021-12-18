package Misc.CustomClasses.GatlingGun;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jacob Swineford
 */
public class GunGame extends Application {

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 800;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.HONEYDEW);

        class TargetRange {

            private Pane range; // root for targets and bullets
            private Pane targets;
            private Rectangle[] targetsArr;
            private Pane bullets;
            private Rectangle[] bulletsArr;

            private Label hitLabel;
            private int hitCount = 0;

            private String audioFile = "file:C:\\Users\\Jacob\\Music\\CB.wav";
            private java.applet.AudioClip clip;

            class Gun {

                class Bullet {

                    private Rectangle bullet;
                    private Timeline t;

                    Bullet(double width, double height, double angle) {
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
                        bullet.setX(0);
                        bullet.setY(0);
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

                Gun(double radius, double barrelLength) {
                    gun = new Pane();
                    assembleGun(radius, barrelLength);
                    setMouseEvents();
                    setGatlingTimeline();
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
                                    arm.setRotate(90 - currentAngle);
                                } catch (Exception e) {
                                    break;
                                }
                            }
                        }
                    }
                    Thread rt = new RotationThread();
                    rt.start();
                }

                private void  setMouseEvents() {
                    // move and shoot
                    scene.setOnMouseMoved(event -> {
                        mouseX = event.getX();
                        mouseY = event.getY();
                    });
                    scene.setOnMouseClicked(event -> {
                        MouseButton b = event.getButton();
                        switch (b) {
                            case SECONDARY: {
                                Rectangle target = new Rectangle(20, 20);
                                if (!putInNextAvailableSpot(target, targetsArr)) break;
                                target.setId("target");
                                target.setStroke(Color.BLACK);
                                target.setStrokeWidth(4);
                                target.setFill(Color.ORANGE);
                                target.setX(event.getX() - 10);
                                target.setY(event.getY() - 10);
                                targets.getChildren().add(target);
                            } break;
                            case PRIMARY: shoot(); break;
                        }
                    });
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

                private Pane getGun() {return gun;}

                private int gat = -1;
                void toggleGatling() {
                    if (gat == -1) gatling.play();
                    else gatling.pause();
                    gat = -gat;
                    System.out.println("toggled");
                }
            }

            private TargetRange(double width, double height) {
                double radius = 40;
                double length = radius * 5;
                int targetAmount = 1000;
                int bulletsAmount = 20;
                range = new Pane();
                bullets = new Pane();
                bulletsArr = new Rectangle[bulletsAmount];
                targets = new Pane();
                targetsArr = new Rectangle[targetAmount];
                Gun gun = new Gun(radius, length);
                hitLabel = new Label("Count: " + hitCount);
                hitLabel.setTranslateX(SCENE_WIDTH / 2.0 - 30);
                hitLabel.setMouseTransparent(true);

                try {
                    clip = java.applet.Applet.newAudioClip(
                            new java.net.URL(audioFile));
                } catch (Exception e) {e.printStackTrace();}

                expand(width, height);
                range.getChildren().addAll(bullets, targets, gun.getGun(), hitLabel);
                //initTargets(width, height, targetAmount);
                initBullsEyeTimeline();

                scene.setOnKeyPressed(event -> {
                    KeyCode key = event.getCode();
                    switch (key) {
                        case G: gun.toggleGatling(); break;
                        case R: {
                            targetsArr = new Rectangle[targetAmount];
                            initTargets(width, height, targetAmount);
                            break;}
                        case Q: {
                            bullets.getChildren().clear();
                            targets.getChildren().clear();
                            bulletsArr = new Rectangle[bulletsAmount];
                            targetsArr = new Rectangle[targetAmount];
                        } break;
                        case Z: playAudioFile(); break;
                        case X: pauseAudioFile(); break;
                    }
                });
            }

            private void expand(double width, double height) {
                Rectangle bg = new Rectangle(width, height);
                bg.setFill(Color.TRANSPARENT);
                range.getChildren().add(bg);
                range.setMouseTransparent(true);
                bullets.getChildren().add(bg);
                bullets.setMouseTransparent(true);
                targets.getChildren().add(bg);
                targets.setMouseTransparent(true);
            }

            private void initTargets(double width, double height, int amount) {
                ThreadLocalRandom rand = ThreadLocalRandom.current();
                for (int i = 0; i < amount; i++) {
                    Rectangle target = new Rectangle(50, 50);

                    target.setId("target");
                    target.setStroke(Color.BLACK);
                    target.setStrokeWidth(4);
                    target.setFill(Color.GREEN);
                    target.setX(rand.nextDouble() * width);
                    target.setY(rand.nextDouble() * height);
                    targets.getChildren().add(target);
                    putInNextAvailableSpot(target, targetsArr);
                }
            }

            private void initBullsEyeTimeline() {
                KeyFrame kf = new KeyFrame(Duration.millis(1), event -> bullsEye());
                Timeline t = new Timeline(kf);
                t.setCycleCount(-1);
                t.play();
            }

            private boolean overlapping(Rectangle r1, Rectangle r2) {
                if (r1 == null || r2 == null) return false;
                double dx = Math.abs(r1.getX() - r2.getX());
                double dy = Math.abs(r1.getY() - r2.getY());
                return dx <= r1.getWidth() && dy <= r1.getHeight();
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

            private void bullsEye() {
                for (int i = 0; i < targetsArr.length; i++) {
                    for (int j = 0; j < bulletsArr.length; j++) {
                        if (overlapping(targetsArr[i], bulletsArr[j])) {
                            hitLabel.setText("Count: " + (++hitCount));
                            targets.getChildren().remove(targetsArr[i]);
                            bullets.getChildren().remove(bulletsArr[j]);
                            targetsArr[i] = null;
                            bulletsArr[j] = null;
                            return;
                        }
                    }
                }
            }

            private void playAudioFile() {
                clip.loop();
            }

            private void pauseAudioFile() {
                clip.stop();
            }

            private Pane getRange() {return range;}
        }
        TargetRange tr = new TargetRange(SCENE_WIDTH, SCENE_HEIGHT);
        root.getChildren().addAll(tr.getRange());

//        double radius = 40;
//        double length = radius * 5;
//        Gun g = new Gun(radius, length, scene);
//        Pane gun = g.getGun();
//
//        root.getChildren().add(gun);
//        root.getChildren().add(g.getBullets());

        primaryStage.setTitle("Cannon");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
