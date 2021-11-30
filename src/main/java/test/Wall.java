/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class Wall {
    private Rectangle area;

    private Brick[] bricks;
    private Ball ball;
    private Player player;

    private int ballSpeedX;
    private int ballSpeedY;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    private static int totalBrickBroken;

    public Wall(Rectangle drawArea, Point ballPos){

        this.startPoint = new Point(ballPos);

        ballCount = 3;
        ballLost = false;
        ballSpeedX = 4;
        ballSpeedY = -4;

        makeBall(ballPos);
        getBall().setXSpeed(ballSpeedX);
        getBall().setYSpeed(ballSpeedY);

        setPlayer(new Player((Point) ballPos.clone(),150,10, drawArea));

        area = drawArea;


    }

    private void makeBall(Point2D ballPos){
        setBall(new RubberBall(ballPos));
    }

    public void move(){
        getPlayer().playerBarMove();
        getBall().move();
    }

    public void findImpacts(){
        if(getPlayer().impact(getBall())){
            getBall().reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            setBrickCount(getBrickCount() - 1);
            setTotalBrickBroken(getTotalBrickBroken() + 1);
        }
        else if(impactBorder()) {
            getBall().reverseX();
        }
        else if(getBall().getPosition().getY() < area.getY()){
            getBall().reverseY();
        }
        else if(getBall().getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    private boolean impactWall(){
        for(Brick b : getBricks()){
            switch(b.findImpact(getBall())) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getDown(), Crack.UP);
                case Brick.DOWN_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getUp(), Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getRight(), Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getLeft(), Crack.LEFT);
            }
        }
        return false;
    }

    private boolean impactBorder(){
        Point2D p = getBall().getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    public int getBallCount(){
        return ballCount;
    }

    public boolean isBallLost(){
        return ballLost;
    }

    public void ballReset(){
        getPlayer().playerBarMoveTo(startPoint);
        getBall().moveTo(startPoint);
        getBall().setXSpeed(ballSpeedX);
        getBall().setYSpeed(ballSpeedY);
        ballLost = false;
    }

    public void wallReset(){
        for(Brick b : getBricks())
            b.repair();
        brickCount = getBricks().length;
        ballCount = 3;
    }

    public boolean ballEnd(){
        return ballCount == 0;
    }

    public boolean isDone(){
        return brickCount == 0;
    }

    public void setBallXSpeed(int s){
        getBall().setXSpeed(s);
    }

    public void setBallYSpeed(int s){
        getBall().setYSpeed(s);
    }

    public void resetBallCount(){
        ballCount = 3;
    }

    public Brick[] getBricks() {
        return bricks;
    }

    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    public int getBrickCount(){
        return brickCount;
    }

    public void setBrickCount(int brickCount){
        this.brickCount = brickCount;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public static int getTotalBrickBroken() {
        return totalBrickBroken;
    }

    public static void setTotalBrickBroken(int totalBrickBroken) {
        Wall.totalBrickBroken = totalBrickBroken;
    }
}
