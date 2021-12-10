package test;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * SpecialBrick class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class SpecialBrick extends Brick{

    private static final String NAME = "Special Brick";
    private static final Color DEF_INNER = Color.BLUE;
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;

    /**
     * Special Brick Constructor to make the Special Brick
     * @param point Coordinate X and Y of brick
     * @param size size of the brick
     */
    public SpecialBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * make the special brick, rectangle
     * @param pos Coordinate X and Y of brick
     * @param size size of the brick
     * @return special brick structure
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * the face of the clay brick like inner and border color
     * @return the face of clay brick
     */
    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }

    /**
     * when brick is impacted with ball if not broken then impact and reverse the user input, a to d, d to a
     * @param point Coordinate of X and Y of the brick
     * @param dir direction of brick that is impacted
     * @return when the brick is broken, return false so it wont be hit
     */
    @Override
    public boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        else {
            impact();
            if (Player.getBarReversed()) {
                Player.setBarReversed(false);
                Player.setInnerColor(Color.GREEN);
            }
            else {
                Player.setBarReversed(true);
                Player.setInnerColor(Color.RED);
            }
        }
        return super.isBroken();
    }
}
