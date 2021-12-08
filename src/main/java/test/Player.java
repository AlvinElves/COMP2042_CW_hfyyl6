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


public class Player {


    private static Color BORDER_COLOR = Color.GREEN.darker().darker();
    private static Color INNER_COLOR = Color.GREEN;

    private static int DEF_MOVE_AMOUNT;

    private Rectangle playerFace;
    private Point ballPoint;
    private static int moveAmount;
    private int min;
    private int max;

    private static boolean barReversed = false;

    /**
     * Player Constructor to make the player bar for the player to move left and right
     * @param ballPoint coordinate X and Y of the ball so that the bar is just under the ball
     * @param width width of the bar
     * @param height height of the bar
     * @param container the player bar
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        setMoveAmount(0);
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;
        setDefMoveAmount(5);
    }

    /**
     * Make the player bar rectangle
     * @param width width of the bar
     * @param height height of the bar
     * @return
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * Impact of the ball and the player bar
     * @param b ball
     * @return true if impact, else false
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown()) ;
    }

    /**
     * Move the player bar to left and right and make sure it is not over the screen
     */
    public void playerBarMove(){
        double x = ballPoint.getX() + getMoveAmount();
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * Move the player bar to the left with negative moveAmount
     */
    public void playerBarMoveLeft(){
        setMoveAmount(-getDefMoveAmount());
    }

    /**
     * Move the player bar to the right with positive moveAmount
     */
    public void playerBarMoveRight(){
        setMoveAmount(getDefMoveAmount());
    }

    /**
     * Stop the player bar from moving
     */
    public void playerBarStop(){
        setMoveAmount(0);
    }

    /**
     * get method for player face, encapsulating
     * @return player face, the rectangle bar
     */
    public Shape getPlayerFace(){
        return playerFace;
    }

    /**
     * Move the player bar to a certain position
     * @param p coordinate X and Y
     */
    public void playerBarMoveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * get method for barReversed, encapsulating
     * @return check is the player movement is reversed due to special brick
     */
    public static boolean getBarReversed() {
        return barReversed;
    }

    /**
     * set method for barReserved, encapsulating
     * @param barReversed set whether the bar is going to be inverted movement
     */
    public static void setBarReversed(boolean barReversed) {
        Player.barReversed = barReversed;
    }

    /**
     * Set method for InnerColor, encapsulating
     * @param color setting the inner colour of the player bar
     */
    public static void setInnerColor(Color color){
        Player.INNER_COLOR = color;
    }

    /**
     * get method for borderColor, encapsulating
     * @return the border colour of player bar
     */
    public static Color getBorderColor() {
        return BORDER_COLOR;
    }

    /**
     * get method for innerColor, encapsulating
     * @return the inner colour of player bar
     */
    public static Color getInnerColor() {
        return INNER_COLOR;
    }

    /**
     * get method for def_move_amount, encapsulating
     * @return the player bar moving amount
     */
    public static int getDefMoveAmount() {
        return DEF_MOVE_AMOUNT;
    }

    /**
     * set method for def_move_amount, encapsulating
     * @param DEF_MOVE_AMOUNT how much the player can move
     */
    public static void setDefMoveAmount(int DEF_MOVE_AMOUNT){
        Player.DEF_MOVE_AMOUNT = DEF_MOVE_AMOUNT;
    }

    /**
     * get method for moveAmount, encapsulating
     * @return how much the player move
     */
    public static int getMoveAmount() {
        return moveAmount;
    }

    /**
     * set method for moveAmount, encapsulating
     * @param moveAmount how much the player bar can move
     */
    public static void setMoveAmount(int moveAmount) {
        Player.moveAmount = moveAmount;
    }
}