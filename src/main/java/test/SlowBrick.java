package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Timer;
import java.util.TimerTask;

public class SlowBrick extends Brick{

    private static final String NAME = "Concrete Brick";
    private static final Color DEF_INNER = Color.CYAN;
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int CONCRETE_STRENGTH = 1;

    public SlowBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CONCRETE_STRENGTH);
    }

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

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }

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
