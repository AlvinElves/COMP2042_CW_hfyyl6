package test;

import java.awt.*;
import java.awt.geom.Point2D;

public class BallFactory {

    public Ball getBallType(String ballType, Point2D center){
        if (ballType == null)
            return null;
        if(ballType.equalsIgnoreCase("RUBBER"))
            return new RubberBall(center);

        return null;
    }
}
