package Misc.CustomClasses.Mancala;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Mancala GunGame to play Mancala.
 *
 * @author Jacob Swineford
 */
public class MancalaGUI extends Application {

    private Mancala m;
    private Circle[] divots;
    private Text[] values;
    private Text[] labels;
    private Circle[] hitboxes;
    private Text turn;
    private Pane root = new Pane();

    @Override
    public void start(Stage primaryStage)
    {

        final int SCENE_WIDTH = 1800;
        final int SCENE_HEIGHT = 600;
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        divots = new Circle[14];
        values = new Text[14];
        labels = new Text[14];
        hitboxes = new Circle[14];
        m = new Mancala();

        double r = 80;
        double dx = 200;
        double dy = 200;
        double ix = (SCENE_WIDTH - (5*dx + 2*r)) / 2 + r;
        double iy = (SCENE_HEIGHT - (2*r + dy)) / 2 + r;
        double strokeWidth = r/6;
        double fontSize = 50;
        double labelFontSize = fontSize/2;
        Color nsc = Color.DARKOLIVEGREEN;
        Color sc = Color.DARKORANGE.darker();
        Color stc = Color.FIREBRICK;
        Color bgC = Color.SANDYBROWN;

        // bug fix?
        Rectangle bg = new Rectangle(0, 0, SCENE_WIDTH, SCENE_HEIGHT);
        bg.setFill(bgC);
        root.getChildren().add(bg);

        turn = new Text("Turn: " + m.getTurn());
        turn.setX(SCENE_WIDTH / 2.0 - fontSize*1.5);
        turn.setY(iy-dy/1.5);
        turn.setFont(new Font(fontSize));
        root.getChildren().add(turn);

        Button b = new Button("reset");
        b.setOnAction(event -> {
            m.reset();
            updateValues();
            turn.setText("Turn: " + m.getTurn());
        });
        b.setTranslateX(SCENE_WIDTH / 2.0 - fontSize/1.2);
        b.setTranslateY(iy+1.5*dy);
        b.setFont(new Font(labelFontSize));
        root.getChildren().add(b);

        iterateDivots(ix, iy, dx, dy, r, strokeWidth, nsc, sc, stc);
        iterateValues(fontSize);
        iterateLabels(labelFontSize);
        iterateHitBoxes();
        setEvents(stc);

        primaryStage.setTitle("MancalaGUI");
        primaryStage.setAlwaysOnTop(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void iterateDivots(double ix, double iy, double dx, double dy, double r,
                               double strokeWidth, Color c, Color c2, Color c3)
    {
        divots[0] = new Circle(ix, iy + dy, r);
        divots[1] = new Circle(ix + dx, iy + dy, r);
        divots[2] = new Circle(ix + 2*dx, iy + dy, r);
        divots[3] = new Circle(ix + 3*dx, iy + dy, r);
        divots[4] = new Circle(ix + 4*dx, iy + dy, r);
        divots[5] = new Circle(ix + 5*dx, iy + dy, r);
        divots[6] = new Circle(ix + 6*dx, iy + dy/2, r);
        divots[7] = new Circle(ix + 5*dx, iy, r);
        divots[8] = new Circle(ix + 4*dx, iy, r);
        divots[9] = new Circle(ix + 3*dx, iy, r);
        divots[10] = new Circle(ix + 2*dx, iy, r);
        divots[11] = new Circle(ix + dx, iy, r);
        divots[12] = new Circle(ix, iy, r);
        divots[13] = new Circle(ix - dx, iy + dy/2, r);
        setDivotColor(c, c2);
        addDivots(strokeWidth, c3);
    }

    private void iterateValues(double fontSize)
    {
        for (int i = 0; i < 14; i++)
        {
            values[i] = new Text(Integer.toString(m.getDivots()[i]));
            values[i].setX(divots[i].getCenterX() - fontSize / 3);
            values[i].setY(divots[i].getCenterY() + fontSize / 3);
            values[i].setFont(new Font(fontSize));
            root.getChildren().add(values[i]);
        }
    }

    private void iterateLabels(double fontSize)
    {
        for (int i = 0; i < 14; i++)
        {
            double fontScale = 2;
            if(i==6)
            {
                labels[i]=new Text(divots[i].getCenterX()-fontSize/fontScale,
                        divots[i].getCenterY()-divots[i].getRadius()/2, "SA");
                labels[i].setFont(new Font(fontSize));
                root.getChildren().add(labels[i]);
                continue;
            }
            if(i==13)
            {
                labels[i]=new Text(divots[i].getCenterX()-fontSize/fontScale,
                        divots[i].getCenterY()-divots[i].getRadius()/2, "SB");
                labels[i].setFont(new Font(fontSize));
                root.getChildren().add(labels[i]);
                continue;
            }
            if (i<7)
            {
                labels[i]=new Text(divots[i].getCenterX()-fontSize/fontScale,
                        divots[i].getCenterY()-divots[i].getRadius()/2, "A" + i);
            } else
            {
                labels[i]=new Text(divots[i].getCenterX()-fontSize/fontScale,
                        divots[i].getCenterY()-divots[i].getRadius()/2, "B" + (i-7));
            }
            labels[i].setFont(new Font(fontSize));
            root.getChildren().add(labels[i]);
        }
    }

    private void iterateHitBoxes()
    {
        for (int i = 0; i < 14; i++)
        {
            hitboxes[i] = new Circle(
                    divots[i].getCenterX(),
                    divots[i].getCenterY(),
                    divots[i].getRadius());
            hitboxes[i].setFill(Color.TRANSPARENT);
            root.getChildren().add(hitboxes[i]);
        }
    }

    private void setEvents(Color strokeColor)
    {
        hitboxes[0].setOnMouseClicked(event -> distributeValues(0));
        hitboxes[1].setOnMouseClicked(event -> distributeValues(1));
        hitboxes[2].setOnMouseClicked(event -> distributeValues(2));
        hitboxes[3].setOnMouseClicked(event -> distributeValues(3));
        hitboxes[4].setOnMouseClicked(event -> distributeValues(4));
        hitboxes[5].setOnMouseClicked(event -> distributeValues(5));
        hitboxes[7].setOnMouseClicked(event -> distributeValues(7));
        hitboxes[8].setOnMouseClicked(event -> distributeValues(8));
        hitboxes[9].setOnMouseClicked(event -> distributeValues(9));
        hitboxes[10].setOnMouseClicked(event -> distributeValues(10));
        hitboxes[11].setOnMouseClicked(event -> distributeValues(11));
        hitboxes[12].setOnMouseClicked(event -> distributeValues(12));

        hitboxes[0].setOnMouseEntered(event -> divots[0].setStroke(strokeColor.brighter()));
        hitboxes[0].setOnMouseExited(event -> divots[0].setStroke(strokeColor));
        hitboxes[1].setOnMouseEntered(event -> divots[1].setStroke(strokeColor.brighter()));
        hitboxes[1].setOnMouseExited(event -> divots[1].setStroke(strokeColor));
        hitboxes[2].setOnMouseEntered(event -> divots[2].setStroke(strokeColor.brighter()));
        hitboxes[2].setOnMouseExited(event -> divots[2].setStroke(strokeColor));
        hitboxes[3].setOnMouseEntered(event -> divots[3].setStroke(strokeColor.brighter()));
        hitboxes[3].setOnMouseExited(event -> divots[3].setStroke(strokeColor));
        hitboxes[4].setOnMouseEntered(event -> divots[4].setStroke(strokeColor.brighter()));
        hitboxes[4].setOnMouseExited(event -> divots[4].setStroke(strokeColor));
        hitboxes[5].setOnMouseEntered(event -> divots[5].setStroke(strokeColor.brighter()));
        hitboxes[5].setOnMouseExited(event -> divots[5].setStroke(strokeColor));
        hitboxes[6].setOnMouseEntered(event -> divots[6].setStroke(strokeColor.brighter()));
        hitboxes[6].setOnMouseExited(event -> divots[6].setStroke(strokeColor));
        hitboxes[7].setOnMouseEntered(event -> divots[7].setStroke(strokeColor.brighter()));
        hitboxes[7].setOnMouseExited(event -> divots[7].setStroke(strokeColor));
        hitboxes[8].setOnMouseEntered(event -> divots[8].setStroke(strokeColor.brighter()));
        hitboxes[8].setOnMouseExited(event -> divots[8].setStroke(strokeColor));
        hitboxes[9].setOnMouseEntered(event -> divots[9].setStroke(strokeColor.brighter()));
        hitboxes[9].setOnMouseExited(event -> divots[9].setStroke(strokeColor));
        hitboxes[10].setOnMouseEntered(event -> divots[10].setStroke(strokeColor.brighter()));
        hitboxes[10].setOnMouseExited(event -> divots[10].setStroke(strokeColor));
        hitboxes[11].setOnMouseEntered(event -> divots[11].setStroke(strokeColor.brighter()));
        hitboxes[11].setOnMouseExited(event -> divots[11].setStroke(strokeColor));
        hitboxes[12].setOnMouseEntered(event -> divots[12].setStroke(strokeColor.brighter()));
        hitboxes[12].setOnMouseExited(event -> divots[12].setStroke(strokeColor));
        hitboxes[13].setOnMouseEntered(event -> divots[13].setStroke(strokeColor.brighter()));
        hitboxes[13].setOnMouseExited(event -> divots[13].setStroke(strokeColor));
    }

    private void distributeValues(int index)
    {
        m.distribute(index);
        turn.setText("Turn: " + m.getTurn());
        updateValues();
    }

    private void updateValues()
    {
        for (int i = 0; i < 14; i++)
        {
            values[i].setText(Integer.toString(m.getDivots()[i]));
        }
    }

    private void setDivotColor(Color c, Color c2)
    {
        for (int i = 0; i < 14; i++)
        {
            if (i == 6 || i == 13)
            {
                divots[i].setFill(c2);
            } else
            {
                divots[i].setFill(c);
            }
        }
    }

    private void addDivots(double strokeWidth, Color strokeColor)
    {
        for (Circle c : divots)
        {
            c.setStroke(strokeColor);
            c.setStrokeWidth(strokeWidth);
            root.getChildren().add(c);
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
