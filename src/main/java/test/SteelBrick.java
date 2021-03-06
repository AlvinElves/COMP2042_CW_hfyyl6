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

/**
 * SteelBrick class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private double randomProbability;
    private Random rnd;
    private Shape brickFace;

    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.getBrickFace();
    }

    /**
     * Steel Brick Constructor to make the Steel Brick
     * @param pos Coordinate X and Y of brick
     * @param size size of the brick
     * @return steel brick structure
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * the face of the steel brick like inner and border color
     * @return the face of steel brick
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * when brick is impacted with ball if not broken then impact
     * @param point Coordinate of X and Y of the brick
     * @param dir direction of brick that is impacted
     * @return when the brick is broken, return false so it wont be hit
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        setRandomProbability(rnd.nextDouble());
        impact();
        return  super.isBroken();
    }

    /**
     * the random number that is generated when brick is hit, if it is less than the probability of brick, then
     * decrease the strength by 1
     */
    public void impact(){
        if(getRandomProbability() < STEEL_PROBABILITY){
            super.impact();
        }
    }

    public double getRandomProbability() {
        return randomProbability;
    }

    public void setRandomProbability(double randomProbability) {
        this.randomProbability = randomProbability;
    }
}
