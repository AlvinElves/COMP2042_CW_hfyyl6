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

    /**
     * Crack Constructor to make the brick has crack if they have strength of 2 and has been hit
     * @param brick Brick class
     * @param crackDepth crack depth of brick
     * @param steps steps of brick
     */
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

    /**
     * Draw the crack of the brick
     * @return brick cracking
     */
    public GeneralPath draw() {
        return crack;
    }

    /**
     * Reset the cracking
     */
    public void reset() {
        crack.reset();
    }

    /**
     * Make the crack of the brick based on the ball hitting the brick
     * @param point point of brick that is being hit
     * @param direction direction of brick that is being hit
     */
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

    /**
     * Draw the crack of the brick
     * @param start start point of the crack brick
     * @param end end point of the crack of brick
     */
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

    /** Jump method
     * Instead of using variable, use a method to call it to enhance maintainability (REFACTORING)
     */
    public int jump (){
        return crackDepth * 5;
    }

    /**
     * Randomise number
     * @param bound bound of brick
     * @return random number of brick
     */
    private int randomInBounds(int bound) {
        int n = (bound * 2) + 1;
        return Brick.getRnd().nextInt(n) - bound;
    }

    /**
     * Middle of brick
     * @param i integer checking
     * @param steps steps of brick
     * @param divisions division of brick
     * @return the integer checking, true or false
     */
    private boolean inMiddle(int i, int steps, int divisions) {
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return (i > low) && (i < up);
    }

    /**
     * If smaller than probability then do nothing
     * @param bound bound of brick
     * @param probability probability of brick, depends on method
     * @return the method randomInBounds
     */
    private int jumps(int bound, double probability) {

        if (Brick.getRnd().nextDouble() > probability)
            return randomInBounds(bound);
        return 0;

    }

    /**
     * Make random output
     * @param from start point
     * @param to end point
     * @param direction direction of point, horizontal or vertical
     * @return output location
     */
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

    /**
     * get method for left, encapsulating
     * @return left cracking
     */
    public static int getLEFT() {
        return LEFT;
    }

    /**
     * set method for left, encapsulating
     * @param LEFT left of the crack
     */
    public static void setLEFT(int LEFT) {
        Crack.LEFT = LEFT;
    }

    /**
     * get method for right, encapsulating
     * @return right cracking
     */
    public static int getRIGHT() {
        return RIGHT;
    }

    /**
     * set method for right, encapsulating
     * @param RIGHT right of the crack
     */
    public static void setRIGHT(int RIGHT) {
        Crack.RIGHT = RIGHT;
    }

    /**
     * get method for up, encapsulating
     * @return up cracking
     */
    public static int getUP() {
        return UP;
    }

    /**
     * set method for up, encapsulating
     * @param UP up of the crack
     */
    public static void setUP(int UP) {
        Crack.UP = UP;
    }

    /**
     * get method for down, encapsulating
     * @return down cracking
     */
    public static int getDOWN() {
        return DOWN;
    }

    /**
     * set method for down, encapsulating
     * @param DOWN down of the crack
     */
    public static void setDOWN(int DOWN) {
        Crack.DOWN = DOWN;
    }

    /**
     * get method for vertical, encapsulating
     * @return vertical crack
     */
    public static int getVERTICAL() {
        return VERTICAL;
    }

    /**
     * set method for vertical, encapsulating
     * @param VERTICAL vertical crack
     */
    public static void setVERTICAL(int VERTICAL) {
        Crack.VERTICAL = VERTICAL;
    }

    /**
     * get method for horizontal, encapsulating
     * @return horizontal crack
     */
    public static int getHORIZONTAL() {
        return HORIZONTAL;
    }

    /**
     * set method for horizontal, encapsulating
     * @param HORIZONTAL horizontal crack
     */
    public static void setHORIZONTAL(int HORIZONTAL) {
        Crack.HORIZONTAL = HORIZONTAL;
    }

}
