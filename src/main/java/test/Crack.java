package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public class Crack {

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    private static int LEFT = 10;
    private static int RIGHT = 20;
    private static int UP = 30;
    private static int DOWN = 40;
    private static int VERTICAL = 100;
    private static int HORIZONTAL = 200;

    private final Brick brick;
    private GeneralPath crack;

    private int crackDepth;
    private int steps;


    public Crack(Brick brick, int crackDepth, int steps) {
        setLEFT(10);
        setRIGHT(20);
        setUP(30);
        setDOWN(40);
        setVERTICAL(100);
        setHORIZONTAL(200);

        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }


    public GeneralPath draw() {

        return crack;
    }

    public void reset() {
        crack.reset();
    }

    protected void makeCrack(Point2D point, int direction) {
        Rectangle bounds = brick.getBrickFace().getBounds();

        Point impact = new Point((int) point.getX(), (int) point.getY());
        Point start = new Point();
        Point end = new Point();

        Point tmp;

        if (direction == getLEFT()){
            start.setLocation(bounds.x + bounds.width, bounds.y);
            end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
            tmp = makeRandomPoint(start, end, getVERTICAL());
            makeCrack(impact, tmp);
        }
        else if (direction == getRIGHT()){
            start.setLocation(bounds.getLocation());
            end.setLocation(bounds.x, bounds.y + bounds.height);
            tmp = makeRandomPoint(start, end, getVERTICAL());
            makeCrack(impact, tmp);
        }
        else if (direction == getUP()){
            start.setLocation(bounds.x, bounds.y + bounds.height);
            end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
            tmp = makeRandomPoint(start, end, getHORIZONTAL());
            makeCrack(impact, tmp);
        }
        else if (direction == getDOWN()){
            start.setLocation(bounds.getLocation());
            end.setLocation(bounds.x + bounds.width, bounds.y);
            tmp = makeRandomPoint(start, end, getHORIZONTAL());
            makeCrack(impact, tmp);
        }
    }

    protected void makeCrack(Point start, Point end) {

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x, start.y);

        double w = (end.x - start.x) / (double) steps;
        double h = (end.y - start.y) / (double) steps;


        double x, y;

        for (int i = 1; i < steps; i++) {

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(crackDepth);

            if (inMiddle(i, CRACK_SECTIONS, steps))
                y += jumps(jump(), JUMP_PROBABILITY);

            path.lineTo(x, y);

        }

        path.lineTo(end.x, end.y);
        crack.append(path, true);
    }

    /** Instead of using variable, use a method to call it to enhance maintainability (REFACTORING) **/
    public int jump (){
        return crackDepth * 5;
    }

    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return Brick.getRnd().nextInt(n) - bound;
    }

    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    private int jumps(int bound, double probability) {

        if (Brick.getRnd().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;

    }

    private Point makeRandomPoint(Point from, Point to, int direction) {

        Point out = new Point();
        int pos;

        if(direction == getHORIZONTAL()){
            pos = Brick.getRnd().nextInt(to.x - from.x) + from.x;
            out.setLocation(pos, to.y);
        }
        else if (direction == getVERTICAL()){
            pos = Brick.getRnd().nextInt(to.y - from.y) + from.y;
            out.setLocation(to.x, pos);
        }
        return out;
    }

    public static int getLEFT() {
        return LEFT;
    }

    public static void setLEFT(int LEFT) {
        Crack.LEFT = LEFT;
    }

    public static int getRIGHT() {
        return RIGHT;
    }

    public static void setRIGHT(int RIGHT) {
        Crack.RIGHT = RIGHT;
    }

    public static int getUP() {
        return UP;
    }

    public static void setUP(int UP) {
        Crack.UP = UP;
    }

    public static int getDOWN() {
        return DOWN;
    }

    public static void setDOWN(int DOWN) {
        Crack.DOWN = DOWN;
    }

    public static int getVERTICAL() {
        return VERTICAL;
    }

    public static void setVERTICAL(int VERTICAL) {
        Crack.VERTICAL = VERTICAL;
    }

    public static int getHORIZONTAL() {
        return HORIZONTAL;
    }

    public static void setHORIZONTAL(int HORIZONTAL) {
        Crack.HORIZONTAL = HORIZONTAL;
    }

}
