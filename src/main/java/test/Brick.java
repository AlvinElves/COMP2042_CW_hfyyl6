package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

abstract public class Brick  {

    private static int MIN_CRACK;
    private static int DEF_CRACK_DEPTH;
    private static int DEF_STEPS;


    private static int UP_IMPACT;
    private static int DOWN_IMPACT;
    private static int LEFT_IMPACT;
    private static int RIGHT_IMPACT;


    private static Random rnd;

    private String name;
    private Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;

    /**
     * Brick Constructor to be called when new brick needs to be designed
     * @param name name of the brick
     * @param pos coordinate X and Y of brick
     * @param size size of the brick
     * @param border color of the brick border
     * @param inner color of the brick for the inner part
     * @param strength strength of the brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        setMinCrack(1);
        setDefCrackDepth(1);
        setDefSteps(35);
        setUpImpact(100);
        setDownImpact(200);
        setLeftImpact(300);
        setRightImpact(400);

        setRnd(new Random());
        broken = false;
        this.name = name;
        setBrickFace(makeBrickFace(pos,size));
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * abstract method to make the type of brick
     * @param pos Coordinate X and Y of brick
     * @param size size of the brick
     * @return new brick that has the shape of a rectangle
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * When the brick has an impact, this method will be used
     * @param point Coordinate of X and Y of the brick
     * @param dir direction of brick that is impacted
     * @return the brick is broken
     */
    public boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return broken;
    }

    /**
     * abstract method to get the type of brick
     * @return the type of brick
     */
    public abstract Shape getBrick();

    /**
     * what happens when the ball and brick collided
     * @param b the type of ball
     * @return an integer to make the amount of force that the ball and brick hits
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(getBrickFace().contains(b.getRight()))
            out = getLeftImpact();
        else if(getBrickFace().contains(b.getLeft()))
            out = getRightImpact();
        else if(getBrickFace().contains(b.getUp()))
            out = getDownImpact();
        else if(getBrickFace().contains(b.getDown()))
            out = getUpImpact();
        return out;
    }

    /**
     * make the brick broken
     * @return the brick is broken
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * reset the brick, make it not broken and has the full strength of its brick
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * when the brick has impacted, reduce the strength of it, if the strength is 0 then make the brick is broken
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    /**
     * get method for Up_Impact, encapsulating
     * @return Up Impact of brick
     */
    public static int getUpImpact() {
        return UP_IMPACT;
    }

    /**
     * set method for Up_Impact, encapsulating
     * @param upImpact Up_Impact integer
     */
    public static void setUpImpact(int upImpact) {
        UP_IMPACT = upImpact;
    }

    /**
     * get method for Down_Impact, encapsulating
     * @return Down Impact of brick
     */
    public static int getDownImpact() {
        return DOWN_IMPACT;
    }

    /**
     * set method for Down_Impact, encapsulating
     * @param downImpact Down_Impact integer
     */
    public static void setDownImpact(int downImpact) {
        DOWN_IMPACT = downImpact;
    }

    /**
     * get method for Left_Impact, encapsulating
     * @return Left Impact of brick
     */
    public static int getLeftImpact() {
        return LEFT_IMPACT;
    }

    /**
     * set method for Left_Impact, encapsulating
     * @param leftImpact Left_Impact integer
     */
    public static void setLeftImpact(int leftImpact) {
        LEFT_IMPACT = leftImpact;
    }

    /**
     * get method for Right_Impact, encapsulating
     * @return Right Impact of brick
     */
    public static int getRightImpact() {
        return RIGHT_IMPACT;
    }

    /**
     * set method for Right_Impact, encapsulating
     * @param rightImpact Right_Impact integer
     */
    public static void setRightImpact(int rightImpact) {
        RIGHT_IMPACT = rightImpact;
    }

    /**
     * get method for Border Color, encapsulating
     * @return the border colour of brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * get method for Inner Color, encapsulating
     * @return the inner colour of the brick
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * get method for Brick Face, encapsulating
     * @return the face of the brick, like its color
     */
    public Shape getBrickFace() {
        return brickFace;
    }

    /**
     * set method for Brick Face, encapsulating
     * @param brickFace face of the brick
     */
    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }

    /**
     * get method for random, encapsulating
     * @return random number
     */
    public static Random getRnd() {
        return rnd;
    }

    /**
     * set method for random, encapsulating
     * @param rnd random number for steel brick
     */
    public static void setRnd(Random rnd) {
        Brick.rnd = rnd;
    }

    /**
     * get method for Def_Crack_Depth, encapsulating
     * @return the crack depth of the brick
     */
    public static int getDefCrackDepth() {
        return DEF_CRACK_DEPTH;
    }

    /**
     * get method for Def_Steps, encapsulating
     * @return the steps of the brick
     */
    public static int getDefSteps() {
        return DEF_STEPS;
    }

    /**
     * set method for Min_Crack, encapsulating
     * @param minCrack minimum crack of the brick
     */
    public static void setMinCrack(int minCrack) {
        MIN_CRACK = minCrack;
    }

    /**
     * set method for Def_Crack_Depth, encapsulating
     * @param defCrackDepth crack depth of the brick
     */
    public static void setDefCrackDepth(int defCrackDepth) {
        DEF_CRACK_DEPTH = defCrackDepth;
    }

    /**
     * set method for Def_Steps, encapsulating
     * @param defSteps steps of the brick
     */
    public static void setDefSteps(int defSteps) {
        DEF_STEPS = defSteps;
    }
}





