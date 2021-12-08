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

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class DebugPanel extends JPanel {

    private static final Color DEF_BKG = Color.WHITE;

    private final JButton skipLevel;
    private final JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private final Wall wall;
    private final Level level;
    private final GameTimer displayTimer;

    /**
     * Debug Panel Constructor that will shows button and slider that can be use and what happens after
     * @param wall Wall class
     * @param level Level class
     * @param displayTimer Game Timer class
     */
    public DebugPanel(Wall wall, Level level, GameTimer displayTimer){

        this.wall = wall;
        this.level = level;
        this.displayTimer = displayTimer;

        initialize();

        skipLevel = makeButton("Skip Level",e -> skipLevel());
        resetBalls = makeButton("Reset Balls",e -> resetBalls());

        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }

    /**
     * Skip the level for developer to check each level
     */
    private void skipLevel(){
        if (level.hasLevel()) {
            level.nextLevel();
            Wall.setTotalBrickBroken((level.getLevel() - 1) * wall.getBrickCount());
            GameTimer.setTempSeconds(GameTimer.getSeconds());
            GameTimer.setTempMinutes(GameTimer.getMinutes());
        }
        else
            System.out.println("Reached the Final level");
    }

    /**
     * Reset the ball count
     */
    private void resetBalls(){
        wall.resetBallCount();
    }

    /**
     * Initialise the debug panel, shows the screen
     */
    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * Make the button for developer to click next level or reset ball
     * @param title Name of the button
     * @param e user's mouse click
     * @return shows the button
     */
    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }

    /**
     * Make the slider for developer to slide and make the ball speeds X and Y
     * @param min minimum speed of ball, minumum slider
     * @param max maximum speed of ball, maximmum slider
     * @param e user's mouse click
     * @return shows the slider
     */
    private JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * Set the Speed X and Y of the ball
     * @param x speed X of ball, move left and right
     * @param y Speed Y of ball, move up and down
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }
}