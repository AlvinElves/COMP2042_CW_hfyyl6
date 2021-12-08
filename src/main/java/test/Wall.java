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

    private BallFactory ballFactory;

    private static int totalBrickBroken = 0;

    /**
     * Wall Constructor to make the bricks, ball and player bar
     * @param drawArea the rectangle of the game screen
     * @param ballPos the starting ball position
     */
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

    /**
     * Make the ball that is going to be in the game
     * @param ballPos starting ball position
     */
    private void makeBall(Point2D ballPos){
        ballFactory = new BallFactory();
        setBall(ballFactory.getBallType("RUBBER", ballPos));
    }

    /**
     * Start moving the player bar and ball
     */
    public void move(){
        getPlayer().playerBarMove();
        getBall().move();
    }

    /**
     * When ball impacts with the brick or side and top border
     */
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

    /**
     * When the ball impact the brick
     * @return if strength is 1 then break, if not crack the brick and decrease strength
     */
    private boolean impactWall(){
        for(Brick b : getBricks()){
            if(b.findImpact(getBall()) == Brick.getUpImpact()){
                getBall().reverseY();
                return b.setImpact(getBall().getDown(), Crack.getUP());
            }
            else if (b.findImpact(getBall()) == Brick.getDownImpact()) {
                getBall().reverseY();
                return b.setImpact(getBall().getUp(), Crack.getDOWN());
            }
            else if (b.findImpact(getBall()) == Brick.getLeftImpact()) {
                getBall().reverseX();
                return b.setImpact(getBall().getRight(), Crack.getRIGHT());
            }
            else if (b.findImpact(getBall()) == Brick.getRightImpact()) {
                getBall().reverseX();
                return b.setImpact(getBall().getLeft(), Crack.getLEFT());
            }
        }
        return false;
    }

    /**
     * Check if the ball has impacted the top and side border
     * @return true if impact else false
     */
    private boolean impactBorder(){
        Point2D p = getBall().getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * Reset the ball speed, move the ball and player bar back to starting position
     */
    public void ballReset(){
        getPlayer().playerBarMoveTo(startPoint);
        getBall().moveTo(startPoint);
        getBall().setXSpeed(ballSpeedX);
        getBall().setYSpeed(ballSpeedY);
        ballLost = false;
    }

    /**
     * Reset the wall, brick count and ball count
     */
    public void wallReset(){
        for(Brick b : getBricks())
            b.repair();
        brickCount = getBricks().length;
        ballCount = 3;
    }

    /**
     * check if there is anymore ball
     * @return true if there is no more ball, else false
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * check if there is anymore brick
     * @return true if there is no more brick, else false
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * get method for ballCount, encapsulating
     * @return the ball count of the game
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * get method for ball lost, encapsulating
     * @return if the ball is lost
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * set method for Speed X, encapsulating
     * @param s horizontal speed of the ball
     */
    public void setBallXSpeed(int s){
        getBall().setXSpeed(s);
    }

    /**
     * set method for Speed Y, encapsulating
     * @param s vertical speed of the ball
     */
    public void setBallYSpeed(int s){
        getBall().setYSpeed(s);
    }

    /**
     * reset the ball count, back to 3
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * get method for brick, encapsulating
     * @return the brick of the game
     */
    public Brick[] getBricks() {
        return bricks;
    }

    /**
     * set method for brick, encapsulating
     * @param bricks set the brick of wall
     */
    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    /**
     * get method for brick count, encapsulating
     * @return the brick count for the level
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * set method for brick count, encapsulating
     * @param brickCount number of brick in the game
     */
    public void setBrickCount(int brickCount){
        this.brickCount = brickCount;
    }

    /**
     * get method for ball, encapsulating
     * @return the ball of the class
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * set method for ball, encapsulating
     * @param ball set the ball to the class
     */
    public void setBall(Ball ball) {
        this.ball = ball;
    }

    /**
     * get method for player, encapsulating
     * @return the player of the class
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * set method for player, encapsulating
     * @param player set the player to the class
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * get method for total brick broken, encapsulating
     * @return the number of total brick broken throughout the entire game
     */
    public static int getTotalBrickBroken() {
        return totalBrickBroken;
    }

    /**
     *  set method for total brick broken, encapsulating
     * @param totalBrickBroken the number of total brick broken being set
     */
    public static void setTotalBrickBroken(int totalBrickBroken) {
        Wall.totalBrickBroken = totalBrickBroken;
    }
}