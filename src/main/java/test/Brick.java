package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Created by filippo on 04/09/16.
 *
 */
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

    public static int getUpImpact() {
        return UP_IMPACT;
    }

    public static void setUpImpact(int upImpact) {
        UP_IMPACT = upImpact;
    }

    public static int getDownImpact() {
        return DOWN_IMPACT;
    }

    public static void setDownImpact(int downImpact) {
        DOWN_IMPACT = downImpact;
    }

    public static int getLeftImpact() {
        return LEFT_IMPACT;
    }

    public static void setLeftImpact(int leftImpact) {
        LEFT_IMPACT = leftImpact;
    }

    public static int getRightImpact() {
        return RIGHT_IMPACT;
    }

    public static void setRightImpact(int rightImpact) {
        RIGHT_IMPACT = rightImpact;
    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    public boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return broken;
    }

    public abstract Shape getBrick();

    public Color getBorderColor(){
        return  border;
    }

    public Color getInnerColor(){
        return inner;
    }


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

    public final boolean isBroken(){
        return broken;
    }

    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    public void impact(){
        strength--;
        broken = (strength == 0);
    }


    public Shape getBrickFace() {
        return brickFace;
    }

    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }

    public static Random getRnd() {
        return rnd;
    }

    public static void setRnd(Random rnd) {
        Brick.rnd = rnd;
    }

    public static int getMinCrack() {
        return MIN_CRACK;
    }

    public static int getDefCrackDepth() {
        return DEF_CRACK_DEPTH;
    }

    public static int getDefSteps() {
        return DEF_STEPS;
    }

    public static void setMinCrack(int minCrack) {
        MIN_CRACK = minCrack;
    }

    public static void setDefCrackDepth(int defCrackDepth) {
        DEF_CRACK_DEPTH = defCrackDepth;
    }

    public static void setDefSteps(int defSteps) {
        DEF_STEPS = defSteps;
    }
}





