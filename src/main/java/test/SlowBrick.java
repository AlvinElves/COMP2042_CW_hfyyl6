package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

/**
 * SlowBrick class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class SlowBrick extends Brick{

    private static final String NAME = "Concrete Brick";
    private static final Color DEF_INNER = Color.CYAN;
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int CONCRETE_STRENGTH = 1;

    /**
     * Slow Brick Constructor to make the Slow Brick
     * @param point Coordinate X and Y of the brick
     * @param size size of the brick
     */
    public SlowBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CONCRETE_STRENGTH);
    }

    /**
     * Slows the player movement from 5 to 2 for 4 seconds
     */
    public void slowPlayer(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Player.setDefMoveAmount(5);
                Player.setInnerColor(Color.GREEN);
            }
        }, 4000);

        Player.setDefMoveAmount(2);
        Player.setInnerColor(Color.BLUE);
    }

    /**
     * make the slow brick, rectangle
     * @param pos Coordinate X and Y of brick
     * @param size size of the brick
     * @return slow brick structure
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * the face of the slow brick like inner and border color
     * @return the face of slow brick
     */
    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }

    /**
     * when brick is impacted with ball if not broken then impact and slow player
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
            slowPlayer();
        }
        return super.isBroken();
    }
}
