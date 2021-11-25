package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

public class ConcreteBrick extends Brick{

    private static final String NAME = "Concrete Brick";
    private static final Color DEF_INNER = new Color(102, 102, 102);
    private static final Color DEF_BORDER = new Color(153, 153, 153);
    private static final int CONCRETE_STRENGTH = 2;
    private static final double CONCRETE_PROBABILITY = 0.7;

    private Random rnd;
    private Crack crack;
    private Shape brickFace;
    private boolean random;

    public ConcreteBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CONCRETE_STRENGTH);
        rnd = new Random();
        crack = new Crack(this, DEF_CRACK_DEPTH, DEF_STEPS);
        brickFace = super.getBrickFace();
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        impact(point, dir);
        if(!super.isBroken()){
            updateBrick();
            return false;
        }
        return true;
    }

    private void updateBrick(){
        if(!super.isBroken() && random){
            GeneralPath gp = crack.draw();
            gp.append(super.getBrickFace(),false);
            brickFace = gp;
        }
    }

    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }

    public void impact(Point2D point, int dir){
        if(randomImpact()){
            super.impact();
            crack.makeCrack(point,dir);
        }
    }

    public boolean randomImpact(){
        return random = rnd.nextDouble() < CONCRETE_PROBABILITY;
    }
}
