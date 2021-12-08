package test;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


public class CementBrick extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private Crack crack;
    private Shape brickFace;

    /**
     * Cement Brick Constructor to make the Cement Brick
     * @param point Coordinate X and Y of the brick
     * @param size size of the brick
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(this, getDefCrackDepth(), getDefSteps());
        brickFace = super.getBrickFace();
    }


    /**
     * make the cement brick, rectangle
     * @param pos Coordinate X and Y of brick
     * @param size size of the brick
     * @return cement brick structure
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * when brick is impacted with the ball, if brick not broken strength decrease and brick cracked
     * @param point Coordinate of X and Y of the brick
     * @param dir direction of brick that is impacted
     * @return when the brick is broken, return false so it wont be hit
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }

    /**
     * the face of the cement brick like inner and border color
     * @return the face of cement brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * if the strength of brick is more than 1, then there will have crack when brick is impacted
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.getBrickFace(),false);
            brickFace = gp;
        }
    }

    /**
     * reset the brick, back to its strength and not broken
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }
}
