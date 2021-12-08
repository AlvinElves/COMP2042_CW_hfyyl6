package test;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * Clay Brick Constructor to make the Clay Brick
     * @param point Coordinate X and Y of the brick
     * @param size size of the brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * make the clay brick, rectangle
     * @param pos Coordinate X and Y of brick
     * @param size size of the brick
     * @return clay brick structure
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


}
