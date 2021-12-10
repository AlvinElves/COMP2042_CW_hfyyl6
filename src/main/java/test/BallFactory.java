package test;

import java.awt.geom.Point2D;

/**
 * BallFactory class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class BallFactory {

    /**
     * Get the type of ball that is being called
     * @param ballType the type of ball
     * @param center the centre of the ball
     * @return the constructor of the type of ball that is called
     */
    public Ball getBallType(String ballType, Point2D center){
        if (ballType == null)
            return null;
        if(ballType.equalsIgnoreCase("RUBBER"))
            return new RubberBall(center);

        return null;
    }
}
