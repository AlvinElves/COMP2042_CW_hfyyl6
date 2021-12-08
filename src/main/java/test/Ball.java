package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * Ball Constructor to be called when new ball needs to be designed
     * @param center centre of the ball
     * @param radiusA radius of ball for X axis
     * @param radiusB radius of ball for Y axis
     * @param inner Color of the ball for the inner part
     * @param border Color of ball border
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        setUp(new Point2D.Double());
        setDown(new Point2D.Double());
        setLeft(new Point2D.Double());
        setRight(new Point2D.Double());

        getUp().setLocation(center.getX(),center.getY()-(float)(radiusB / 2));
        getDown().setLocation(center.getX(),center.getY()+(float)(radiusB / 2));

        getLeft().setLocation(center.getX()-(float)(radiusA /2),center.getY());
        getRight().setLocation(center.getX()+(float)(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * Make the ball like Rubber Ball so the game can be played
     * @param center centre of the ball
     * @param radiusA radius of ball for X axis
     * @param radiusB radius of the ball for Y axis
     * @return return the ball
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * Shows how the ball will be displayed when its moving
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * Set the speed of the Ball for going left and right
     * @param s speed of the ball, negative go left and positive go right
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * Set the speed of the Ball for going up and down
     * @param s speed of the ball, negative go down and positive go up
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * Change the ball direction from left to right or right to left
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * Change the ball direction from up to down or down to up
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * get method for BorderColor, encapsulating
     * @return color of the ball border
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * get method for InnerColor, encapsulating
     * @return color of the ball for inner part
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * get method for center, encapsulating
     * @return the center of the ball
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     *  get method for BallFace, encapsulating
     * @return how the ball will look
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Move the ball to the position p
     * @param p Coordinate x and y
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * set the point coordinate X and Y
     * @param width coordinate X of ball
     * @param height coordinate Y of ball
     */
    private void setPoints(double width,double height){
        getUp().setLocation(center.getX(),center.getY()-(height / 2));
        getDown().setLocation(center.getX(),center.getY()+(height / 2));

        getLeft().setLocation(center.getX()-(width / 2),center.getY());
        getRight().setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * get method for SpeedX, encapsulating
     * @return the speed of going left or right
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * get method for SpeedY, encapsulating
     * @return the speed of going down or up
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * get method for Up, encapsulating
     * @return the point of Up
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * set method for Up, encapsulating
     * @param up point of up
     */
    public void setUp(Point2D up) {
        this.up = up;
    }

    /**
     * get method for Down, encapsulating
     * @return the point of down
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * set method for Down, encapsulating
     * @param down point of down
     */
    public void setDown(Point2D down) {
        this.down = down;
    }

    /**
     * get method for Left, encapsulating
     * @return the point of left
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * set method for Left, encapsulating
     * @param left point of left
     */
    public void setLeft(Point2D left) {
        this.left = left;
    }

    /**
     * get method for right, encapsulating
     * @return the point of Right
     */
    public Point2D getRight() {
        return right;
    }

    /**
     * set method for Right, encapsulating
     * @param right point of right
     */
    public void setRight(Point2D right) {
        this.right = right;
    }
}
