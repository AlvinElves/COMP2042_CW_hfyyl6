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
import java.awt.event.*;
import java.awt.font.FontRenderContext;

/**
 * GameBoard class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class GameBoard extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Timer gameTimer;

    private GameTimer displayTimer;

    private Wall wall;

    private Level level;

    private String message;
    private String message2;

    private boolean showPauseMenu;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;

    private DebugConsole debugConsole;

    /**
     * GameBoard Constructor that will shows the game, ball and brick and player
     * @param owner JFrame owner that will set up the game board screen
     */
    public GameBoard(JFrame owner){
        super();

        strLen = 0;
        showPauseMenu = false;

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);


        this.initialize();
        message = "";
        message2 = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),new Point(300,430));
        level = new Level(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3, 3, wall);
        displayTimer = new GameTimer();
        debugConsole = new DebugConsole(owner, wall, level, displayTimer, this);
        //initialize the first level
        level.nextLevel();

        gameTimer = new Timer(10,e ->{
            wall.move();
            wall.findImpacts();
            GameTimer.setGameRunning(true);
            message = String.format("Bricks: %d Balls: %d", wall.getBrickCount(), wall.getBallCount());
            message2 = String.format("Total Bricks Broken: %d Timer: %02dm %02ds", Wall.getTotalBrickBroken(),
                    GameTimer.getMinutes(), GameTimer.getSeconds());
            if(wall.isBallLost()){
                if(wall.ballEnd()){
                    wall.wallReset();
                    GameTimer.setGameRunning(false);
                    HighScore.sortingAfterGame();
                    HomeMenu.getOwner().enableHighScorePageFromGameBoard();
                }
                GameTimer.setGameRunning(false);
                wall.ballReset();
                Player.setBarReversed(false);
                Player.setInnerColor(Color.GREEN);
                gameTimer.stop();
            }
            else if(wall.isDone()){
                if(level.hasLevel()){
                    message = "Go to Next Level";
                    GameTimer.setTempSeconds(GameTimer.getSeconds());
                    GameTimer.setTempMinutes(GameTimer.getMinutes());
                    message2 = "";
                    gameTimer.stop();
                    wall.ballReset();
                    wall.wallReset();
                    level.nextLevel();
                }
                else{
                    GameTimer.setGameRunning(false);
                    gameTimer.stop();
                    HighScore.sortingAfterGame();
                    HomeMenu.getOwner().enableHighScorePageFromGameBoard();
                }
            }

            repaint();
        });

    }

    /**
     * Initialise the game board, the screen of game
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Paint the screen, shows text, bricks, ball and player
     * @param g graphics parameter that shows the screen stuffs
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);
        g2d.drawString(message2,200,245);

        drawBall(wall.getBall(),g2d);

        for(Brick b : wall.getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayerControlBar(wall.getPlayer(),g2d);

        if(showPauseMenu)
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Clear the screen
     * @param g2d graphics2d parameter
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * Draw the brick, setting colour of bricks
     * @param brick Brick class
     * @param g2d graphics2d parameter
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /**
     * Draw the ball, setting colour of ball
     * @param ball Ball class
     * @param g2d graphics2d parameter
     */
    private void drawBall(Ball ball,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Draw the player bar, setting colour of player bar
     * @param p Player class
     * @param g2d graphics2d parameter
     */
    private void drawPlayerControlBar(Player p,Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.getInnerColor());
        g2d.fill(s);

        g2d.setColor(Player.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Draw the game and also the pause menu
     * @param g2d graphics2d parameter
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * Draw the game
     * @param g2d graphics2d parameter
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * Draw the pause menu when user press Esc key
     * @param g2d graphics2d parameter
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * when user press his keyboard, A, D, ESC, SPACE, ALT+SHIFT+F1
     * @param keyEvent user's keyboard button pressed
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                if(Player.getBarReversed())
                    wall.getPlayer().playerBarMoveRight();
                else
                    wall.getPlayer().playerBarMoveLeft();
                break;
            case KeyEvent.VK_D:
                if(Player.getBarReversed())
                    wall.getPlayer().playerBarMoveLeft();
                else
                    wall.getPlayer().playerBarMoveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                GameTimer.setGameRunning(false);
                repaint();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning()) {
                        gameTimer.stop();
                        GameTimer.setGameRunning(false);
                    }
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugConsole.setVisible(true);
            default:
                wall.getPlayer().playerBarStop();
        }
    }

    /**
     * when user stop press A or D, the player bar stops moving
     * @param keyEvent user's keyboard button pressed
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        wall.getPlayer().playerBarStop();
    }

    /**
     * When the pause menu screens open, user can click on continue, restart and exit button
     * @param mouseEvent user's mouse when clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(continueButtonRect.contains(p)){
            showPauseMenu = false;
            repaint();
        }
        else if(restartButtonRect.contains(p)){
            message = "Restarting Game...";
            message2 = "";
            wall.ballReset();
            wall.wallReset();
            GameTimer.setSeconds(GameTimer.getTempSeconds());
            GameTimer.setMinutes(GameTimer.getTempMinutes());
            Wall.setTotalBrickBroken((level.getLevel() - 1) * wall.getBrickCount());
            Player.setBarReversed(false);
            Player.setInnerColor(Color.GREEN);
            showPauseMenu = false;
            repaint();
        }
        else if(exitButtonRect.contains(p)){
            GameTimer.resetGame();
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * When user cursor is moved to the exit, continue and restart button, change the cursor to a hand cursor
     * @param mouseEvent user's mouse move
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(exitButtonRect != null && showPauseMenu) {
            if (exitButtonRect.contains(p) || continueButtonRect.contains(p) || restartButtonRect.contains(p))
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * When game screen loses focus, says focus lost and stop the game and game timer
     */
    public void onLostFocus(){
        gameTimer.stop();
        GameTimer.setGameRunning(false);
        message = "Focus Lost";
        message2 = "";
        repaint();
    }

}