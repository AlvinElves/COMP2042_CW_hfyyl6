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
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

/**
 * GameFrame class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoard gameBoard;
    private HomeMenu homeMenu;
    private static InstructionModel instructionModel;
    private HighScore highScore;

    private boolean gaming;

    /**
     * Game Frame Constructor that will be like the brain of everything
     */
    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoard(this);

        homeMenu = new HomeMenu(this,new Dimension(450,300));

        setInstruction(new InstructionModel(this, new Dimension(450,300)));

        highScore = new HighScore(this,new Dimension(450,300));

        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);

    }

    /**
     * Initialise the game frame, the screen of game
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * Go back to the home menu page from instruction page
     */
    public void backToHomePage(){
        this.dispose();
        this.remove(getInstruction());
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Go back to the home menu page from High Score page
     */
    public void backToHomePageFromHighScore(){
        this.dispose();
        this.remove(highScore);
        this.add(homeMenu,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Go to the instruction page from home menu page
     */
    public void enableInstructionPage(){
        this.dispose();
        this.remove(homeMenu);
        this.add(getInstruction(),BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Go to the high score page from home menu page
     */
    public void enableHighScorePage(){
        this.dispose();
        this.remove(homeMenu);
        this.add(highScore,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Go to the high score page after the game ends, from game board
     */
    public void enableHighScorePageFromGameBoard(){
        this.dispose();
        this.remove(gameBoard);
        this.add(highScore,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Go to the game board from home menu, starts the game and start everything from scratch
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);
        Wall.setTotalBrickBroken(0);
        GameTimer.resetGame();

    }

    /**
     * Set the screen in the middle of the user's screen
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }

    /**
     * When the window screen is focused, user is able to play the game
     * @param windowEvent user's window screen
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * When the window screen lost focused, everything stops timer or ball and player movement
     * @param windowEvent user's window screen
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }

    /**
     * get method for instruction Model, encapsulating
     * @return the instruction model screen
     */
    public static InstructionModel getInstruction(){
        return instructionModel;
    }

    /**
     * set method for instruction Model, encapsulating
     * @param instructionModel set the instruction screen, like the size
     */
    public void setInstruction(InstructionModel instructionModel){
        GameFrame.instructionModel = instructionModel;
    }
}