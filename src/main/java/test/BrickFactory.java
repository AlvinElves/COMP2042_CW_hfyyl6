package test;

import java.awt.*;

/**
 * BrickFactory class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class BrickFactory {

    /**
     * Get the type of brick that is being called
     * @param brickType the type of ball
     * @param point the coordinate X and Y of the brick
     * @param size the size of the brick
     * @return the constructor of the type of ball that is called
     */
    public Brick getBrickType(String brickType, Point point, Dimension size){
        if (brickType == null)
            return null;
        if(brickType.equalsIgnoreCase("CLAY"))
            return new ClayBrick(point, size);
        else if(brickType.equalsIgnoreCase("CEMENT"))
            return new CementBrick(point, size);
        else if(brickType.equalsIgnoreCase("SLOW"))
            return new SlowBrick(point, size);
        else if(brickType.equalsIgnoreCase("SPECIAL"))
            return new SpecialBrick(point, size);
        else if(brickType.equalsIgnoreCase("STEEL"))
            return new SteelBrick(point, size);

        return null;
    }
}
