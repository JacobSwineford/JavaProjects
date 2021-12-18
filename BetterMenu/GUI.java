package Misc.CustomClasses.BetterMenu;

import Misc.CustomClasses.BetterMenu.Grid.Item;
import Misc.CustomClasses.BetterMenu.Linked.LinkedItem;
import Misc.CustomClasses.BetterMenu.Linked.LinkedMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

import static Misc.CustomClasses.BetterMenu.Linked.LinkedMenu.*;

/**
 * GUI for the "Miitopia Conundrum".
 *
 * @author Jacob Swineford
 */
public class GUI extends Application {

    private Section selected;

    // rooms
    private Room topLeft;
    private Room topRight;
    private Room bottomLeft;
    private Room middle;
    private Room bottomRight;

    @Override
    public void start(Stage primaryStage) {

        Pane root = new Pane();
        final int SCENE_WIDTH = 1200;
        final int SCENE_HEIGHT = 800;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT, Color.TAN);
        LinkedMenu menu = new LinkedMenu();

        double roomWidth = 200;
        double roomHeight = 200;
        Pane inn = new Pane();
        inn.setTranslateY(100);
        inn.setTranslateX(100);

        topLeft = new Room(roomWidth, roomHeight, false);
        topRight = new Room(roomWidth, roomHeight, false);
        bottomLeft = new Room(roomWidth, roomHeight, false);
        middle = new Room(roomWidth, roomHeight * 2, true);
        bottomRight = new Room(roomWidth, roomHeight, false);

        topRight.setTranslateX(roomWidth * 2);
        bottomLeft.setTranslateY(roomHeight);
        middle.setTranslateX(roomWidth);
        bottomRight.setTranslateX(roomWidth * 2);
        bottomRight.setTranslateY(roomHeight);

        Section[] top = topLeft.getSections();
        top[0].putMii(new Mii(30, 30));
        top[1].putMii(new Mii(30, 30));
        defineMenuFlow();

        menu.select(top[0]);
        inn.getChildren().addAll(topLeft, topRight, bottomLeft, bottomRight, middle);
        root.getChildren().add(inn);

        scene.setOnKeyPressed(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case D: {menu.navigate(Direction.Right); break;}
                case A: {menu.navigate(Direction.Left); break;}
                case S: {menu.navigate(Direction.Down); break;}
                case W: {menu.navigate(Direction.Up); break;}
                case SPACE: {menu.execute();}
            }
        });

        primaryStage.setTitle("Miitopia conundrum");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void defineMenuFlow() {
        LinkedList<LinkedItem> top = topSectionsWithMiis();
        LinkedList<LinkedItem> bottom = bottomSectionsWithMiis();
        linkRow(true, top);
        linkRow(true, bottom);

        Section topSection;
        Section bottomSection;
        topSection = topLeft.getSections()[0];
        bottomSection = bottomLeft.getSections()[0];
        linkIfMiiPresent(topSection, bottomSection);

        topSection = topLeft.getSections()[1];
        bottomSection = bottomLeft.getSections()[1];
        linkIfMiiPresent(topSection, bottomSection);

        topSection = topRight.getSections()[0];
        bottomSection = bottomRight.getSections()[0];
        linkIfMiiPresent(topSection, bottomSection);

        topSection = topRight.getSections()[1];
        bottomSection = bottomRight.getSections()[1];
        linkIfMiiPresent(topSection, bottomSection);

        for (LinkedItem l : top) {
            assert l instanceof Section;
            Section s = (Section) l;
            if (s.getBottom() == null && bottom.size() > 0) {
                s.setBottom(bottom.getFirst());
                s.setTop(bottom.getFirst());
            }
        }

        for (LinkedItem l : bottom) {
            assert l instanceof Section;
            Section s = (Section) l;
            if (s.getTop() == null && top.size() > 0) {
                s.setBottom(top.getFirst());
                s.setTop(top.getFirst());
            }
        }
    }

    private void linkAll() {
        Section[] top = topLeft.getSections();
        Section[] top2 = topRight.getSections();
        Section[] bottom = bottomLeft.getSections();
        Section[] bottom2 = middle.getSections();
        Section[] bottom3 = bottomRight.getSections();
        linkRow(true, top[0], top[1], top2[0], top2[1]);
        linkRow(true, bottom[0], bottom[1], bottom2[0], bottom2[1], bottom3[0], bottom3[1]);
        linkColumn(true, top[0], bottom[0]);
        linkColumn(true, top[1], bottom[1]);
        linkColumn(true, top2[0], bottom3[0]);
        linkColumn(true, top2[1], bottom3[1]);
    }

    private void delinkAll() {
        Section[] top = topLeft.getSections();
        Section[] top2 = topRight.getSections();
        Section[] bottom = bottomLeft.getSections();
        Section[] bottom2 = middle.getSections();
        Section[] bottom3 = bottomRight.getSections();
        for (Section s : top) sever(s);
        for (Section s : top2) sever(s);
        for (Section s : bottom) sever(s);
        for (Section s : bottom2) sever(s);
        for (Section s : bottom3) sever(s);
    }

    private LinkedList<LinkedItem> topSectionsWithMiis() {
        Section[] top = topLeft.getSections();
        Section[] top2 = topRight.getSections();
        LinkedList<LinkedItem> s = new LinkedList<>();
        if (top[0].mii != null) s.add(top[0]);
        if (top[1].mii != null) s.add(top[1]);
        if (top2[0].mii != null) s.add(top2[0]);
        if (top2[1].mii != null) s.add(top2[1]);
        return s;
    }

    private LinkedList<LinkedItem> bottomSectionsWithMiis() {
        Section[] bottom = bottomLeft.getSections();
        Section[] bottom2 = middle.getSections();
        Section[] bottom3 = bottomRight.getSections();
        LinkedList<LinkedItem> s = new LinkedList<>();
        if (bottom[0].mii != null) s.add(bottom[0]);
        if (bottom[1].mii != null) s.add(bottom[1]);
        if (bottom2[0].mii != null) s.add(bottom2[0]);
        if (bottom2[1].mii != null) s.add(bottom2[1]);
        if (bottom3[0].mii != null) s.add(bottom3[0]);
        if (bottom3[1].mii != null) s.add(bottom3[1]);
        return s;
    }

    private void linkIfMiiPresent(Section top, Section bottom) {
        if (top.mii != null && bottom.mii != null) linkColumn(true, top, bottom);
    }

    public static void main(String[] args) {
        launch(args);
    }

    class Box extends Pane implements Item {

        private Rectangle box;

        Box(double width, double height) {
            box = new Rectangle();
            box.setWidth(width);
            box.setHeight(height);
            box.setFill(Color.BLUE);
            box.setStroke(Color.RED);
            box.setStrokeWidth(5);
            this.getChildren().add(box);
        }

        @Override
        public void navigatedOn(Direction direction) {
            box.setStroke(Color.YELLOW);
        }

        @Override
        public void navigatedOff(Direction direction) {
            box.setStroke(Color.RED);
        }

        @Override
        public void execute() {
            class Flash extends Thread {
                @Override
                public void run() {
                    try {
                        box.setFill(Color.GREEN);
                        Thread.sleep(1000);
                        box.setFill(Color.BLUE);
                    } catch (InterruptedException e) {
                        //
                    }
                }
            }
            Thread f = new Flash();
            f.start();
        }
    }

    class Mii {

        private Pane root;
        private Rectangle box;

        Mii(double width, double height) {
            root = new Pane();
            root.setMinWidth(width);
            root.setMinHeight(height);
            box = new Rectangle();
            box.setWidth(width);
            box.setHeight(height);
            ThreadLocalRandom rand = ThreadLocalRandom.current();
            box.setFill(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            box.setStroke(Color.RED);
            box.setStrokeWidth(5);
            root.getChildren().add(box);
        }

        public Pane getRoot() {return root;}
    }

    class Arrow extends Pane {

        private Rectangle stem = new Rectangle();
        private Polygon pointer = new Polygon();

        Arrow(Color color, double width, double height,
              double stemLength, double stemHeight) {
            this.getChildren().addAll(stem, pointer);
            this.setHeight(height);
            this.setWidth(width);

            stem.setWidth(stemLength);
            stem.setHeight(stemHeight);
            stem.setY(((height - stemHeight) / 2.0));
            stem.setFill(color);

            Double[] points = new Double[] {
                    stemLength, 0.0,
                    stemLength, height,
                    width, (height / 2)
            };
            pointer.getPoints().addAll(points);
            pointer.setFill(color);
        }


    }

    class Room extends Pane {

        private Section right;
        private Section left;
        private Rectangle background;

        Room(double width, double height, boolean highRoom) {
            this.setWidth(width);
            this.setHeight(height);
            background = new Rectangle(width, height, Color.TRANSPARENT);
            background.setStroke(Color.BLACK);
            background.setStrokeWidth(5);
            this.getChildren().add(background);

            double sectionWidth = width * .3;
            double sectionHeight;
            if (highRoom)
                sectionHeight = height * .2;
            else
                sectionHeight = height * .4;

            right = new Section(sectionWidth, sectionHeight);
            left = new Section(sectionWidth, sectionHeight);
            Pane r = right.getRoot();
            Pane l = left.getRoot();
            double dx = (width - (2 * sectionWidth)) / 3;
            r.setTranslateX(dx);
            l.setTranslateX(width - dx - sectionWidth);
            r.setTranslateY(height - sectionHeight);
            l.setTranslateY(height - sectionHeight);
            this.getChildren().addAll(r, l);
        }

        Section[] getSections() {
            Section[] s = new Section[2];
            s[0] = right;
            s[1] = left;
            return s;
        }
    }

    class Section extends LinkedItem {

        private Pane root;
        private Pane pickup;
        private Rectangle background;
        private Arrow pointer;
        private Arrow pointerS;
        private Mii mii;

        Section(double width, double height) {
            root = new Pane();
            background = new Rectangle(width, height);
            pointer = new Arrow(Color.YELLOW, 50, 20, 30, 10);
            pointerS = new Arrow(Color.GREEN, 50, 20, 30, 10);
            background.setFill(Color.TRANSPARENT);
            background.setStroke(Color.BLACK);
            background.setStrokeWidth(3);
            pointer.setVisible(false);
            pointer.setTranslateY((height / 2) - (pointer.getHeight() / 2));
            pointer.setTranslateX(-1 * pointer.getWidth() - 10);
            pointerS.setVisible(false);
            pointerS.setTranslateY(-1 * pointerS.getHeight() - 30);
            pointerS.setTranslateX((width / 2) - (pointerS.getWidth() / 2));
            pointerS.setRotate(90);
            pickup = new Pane();
            double dx = (background.getWidth() - root.getMinWidth()) - 50;
            double dy = (background.getHeight() - root.getMinHeight()) - 50;
            pickup.setTranslateX(dx);
            pickup.setTranslateY(dy);
            root.getChildren().addAll(background, pointer, pointerS, pickup);
        }

        Pane getRoot() {return root;}

        void putMii(Mii mii) {
            this.mii = mii;
            Pane root = mii.getRoot();
            double dx = (background.getWidth() - root.getMinWidth()) / 2;
            double dy = (background.getHeight() - root.getMinHeight()) / 2;
            root.setTranslateY(dy);
            root.setTranslateX(dx);
            this.root.getChildren().add(mii.getRoot());
        }

        void removeMii() {
            root.getChildren().remove(mii.getRoot());
            mii = null;
        }

        void switchMii(Section section) {
            Mii thisMii = mii;
            Mii givenMii = section.mii;
            if (givenMii == null) {
                removeMii();
                section.putMii(thisMii);
            } else {
                removeMii();
                section.removeMii();
                section.putMii(thisMii);
                putMii(givenMii);
            }
        }

        void putPickup(Mii mii) {
            try {
                pickup.getChildren().add(mii.getRoot());
            } catch (Exception e) {
                //
            }
        }

        void removePickup() {
            try {
                pickup.getChildren().remove(mii.getRoot());
            } catch (Exception e) {
                //
            }
        }

        Mii getMii() {return mii;}

        @Override
        public void navigatedOn(Direction direction) {
            pointer.setVisible(true);
            if (selected != null) {
                putPickup(selected.getMii());
            }
        }

        @Override
        public void navigatedOff(Direction direction) {
            pointer.setVisible(false);
            removePickup();
        }

        @Override
        public void execute() {
            if (mii == null && selected == null) return;
            if (pointerS.isVisible()) pointerS.setVisible(false);
            else pointerS.setVisible(true);

            if (selected == this) {
                selected = null;
                return;
            }

            if (selected == null) {
                selected = this;
                root.getChildren().remove(mii.getRoot());
                putPickup(mii);
                linkAll();
            } else {
                selected.switchMii(this);
                selected.pointerS.setVisible(false);
                pointerS.setVisible(false);
                selected = null;
                delinkAll();
                defineMenuFlow();
            }
        }
    }
}
