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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener{

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "START";
    private static final String MENU_TEXT = "EXIT";
    private static final String HIGH_SCORE_TEXT = "HIGH SCORE";
    private static final String INSTRUCTION_TEXT = "INSTRUCTION";

    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Color CLICKED_BUTTON_COLOR = Color.RED;
    private static final Color CLICKED_TEXT = Color.RED;

    private Rectangle menuFace;
    private Rectangle startButton;
    private Rectangle menuButton;
    private Rectangle instructionButton;
    private Rectangle highScoreButton;


    private BasicStroke borderStoke;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private static GameFrame owner;

    private boolean startClicked;
    private boolean menuClicked;
    private boolean instructionClicked;
    private boolean highScoreClicked;

    private BufferedImage image;
    private BufferedImage ballImage;
    private BufferedImage brickImage;
    private BufferedImage hourglassImage;

    /**
     * Home Menu Constructor that will show home menu
     * @param owner Game Frame owner that will set up the home menu screen
     * @param area the screen of home menu, how big is it
     */
    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        setOwner(owner);

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension((int) (area.width / 2.4), area.height / 10);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);
        instructionButton = new Rectangle(btnDim);
        highScoreButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(2);

        greetingsFont = new Font("Serif",Font.PLAIN,30);
        gameTitleFont = new Font("Serif",Font.BOLD,45);
        creditsFont = new Font("Serif",Font.PLAIN,15);
        buttonFont = new Font("Helvetica",Font.PLAIN,23);
    }

    /**
     * Paint the screen, shows text, button, images and background images
     * @param g graphics parameter that shows the screen stuffs
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    /**
     * Draw the screen, images, text and buttons
     * @param g2d graphics2d parameter
     */
    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        try {
            ballImage = ImageIO.read(getClass().getResource("/Ball.jfif"));
            brickImage = ImageIO.read(getClass().getResource("/Brick.jfif"));
            hourglassImage = ImageIO.read(getClass().getResource("/Hourglass.jpg"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        g2d.drawImage(ballImage, 10, 50, 40, 45, this);
        g2d.drawImage(brickImage, 40, 140, 40, 40, this);
        g2d.drawImage(hourglassImage, 375, 160, 40, 40, this);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * Draw the background image
     * @param g2d graphics2d parameter
     */
    private void drawContainer(Graphics2D g2d){
        try {
            image = ImageIO.read(getClass().getResource("/BackGroundImage.jpg"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        g2d.drawImage(image, 0, 0, 450, 300, this);
    }

    /**
     * Draw the text, like greetings, title
     * @param g2d graphics2d parameter
     */
    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);


        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);

        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);
    }

    /**
     * Draw the button, like start, exit, instruction and high score
     * @param g2d graphics2d parameter
     */
    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);
        Rectangle2D iTxtRect = buttonFont.getStringBounds(INSTRUCTION_TEXT,frc);
        Rectangle2D hSTxtRect = buttonFont.getStringBounds(HIGH_SCORE_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.8);

        x *= 0.2;

        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);

        g2d.setColor(Color.WHITE);
        g2d.fill(startButton);
        g2d.setStroke(borderStoke);
        g2d.setColor(Color.BLACK);

        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.setColor(Color.BLACK);
            g2d.drawString(START_TEXT,x,y);
        }

        x = (menuFace.width - startButton.width) / 2;
        y = startButton.y;

        x *= 1.8;

        menuButton.setLocation(x,y);

        x = (int)(menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y + (startButton.height * 0.9);

        g2d.setColor(Color.WHITE);
        g2d.fill(menuButton);
        g2d.setStroke(borderStoke);
        g2d.setColor(Color.BLACK);

        if(menuClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(menuButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(menuButton);
            g2d.setColor(Color.BLACK);
            g2d.drawString(MENU_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.2;

        instructionButton.setLocation(x,y);

        x = (int)(instructionButton.getWidth() - iTxtRect.getWidth()) / 2;
        y = (int)(instructionButton.getHeight() - iTxtRect.getHeight()) / 2;

        x += instructionButton.x;
        y += instructionButton.y + (startButton.height * 0.9);

        g2d.setColor(Color.WHITE);
        g2d.fill(instructionButton);
        g2d.setStroke(borderStoke);
        g2d.setColor(Color.BLACK);

        if(instructionClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(instructionButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INSTRUCTION_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(instructionButton);
            g2d.setColor(Color.BLACK);
            g2d.drawString(INSTRUCTION_TEXT,x,y);
        }

        x = menuButton.x;
        y = menuButton.y;

        y *= 1.2;

        highScoreButton.setLocation(x,y);

        x = (int)(highScoreButton.getWidth() - hSTxtRect.getWidth()) / 2;
        y = (int)(highScoreButton.getHeight() - hSTxtRect.getHeight()) / 2;

        x += highScoreButton.x;
        y += highScoreButton.y + (startButton.height * 0.9);

        g2d.setColor(Color.WHITE);
        g2d.fill(highScoreButton);
        g2d.setStroke(borderStoke);
        g2d.setColor(Color.BLACK);

        if(highScoreClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(highScoreButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(HIGH_SCORE_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(highScoreButton);
            g2d.setColor(Color.BLACK);
            g2d.drawString(HIGH_SCORE_TEXT,x,y);
        }

    }

    /**
     * When start is clicked, start the game
     * When exit is clicked, exit the game
     * When instruction is clicked, show the instruction page
     * When high score is clicked, show the high score page
     * @param mouseEvent user's mouse when clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            owner.enableGameBoard();

        }
        else if(menuButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(instructionButton.contains(p)){
            owner.enableInstructionPage();

        }
        else if(highScoreButton.contains(p)){
            owner.enableHighScorePage();
        }
    }

    /**
     * When buttons are pressed put the button text and border to a different colour
     * @param mouseEvent user's mouse when pressed
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(menuButton.contains(p)){
            menuClicked = true;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
        else if(instructionButton.contains(p)){
            instructionClicked = true;
            repaint(instructionButton.x,instructionButton.y,instructionButton.width+1,instructionButton.height+1);
        }
        else if(highScoreButton.contains(p)){
            highScoreClicked = true;
            repaint(highScoreButton.x,highScoreButton.y,highScoreButton.width+1,highScoreButton.height+1);
        }
    }

    /**
     * When buttons are released put the button text and border to the original colour
     * @param mouseEvent user's mouse when released
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(menuClicked){
            menuClicked = false;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
        else if(instructionClicked){
            instructionClicked = false;
            repaint(instructionButton.x,instructionButton.y,instructionButton.width+1,instructionButton.height+1);
        }
        else if(highScoreClicked){
            highScoreClicked = false;
            repaint(highScoreButton.x,highScoreButton.y,highScoreButton.width+1,highScoreButton.height+1);
        }
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
     * When user cursor is moved to the buttons, change the cursor to a hand cursor
     * @param mouseEvent user's mouse move
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p) || menuButton.contains(p) || instructionButton.contains(p) || highScoreButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }

    /**
     * get method for owner, encapsulating
     * @return the owner of class
     */
    public static GameFrame getOwner(){
        return owner;
    }

    /**
     * set method for owner, encapsulating
     * @param owner the owner you want to set for the class
     */
    public static void setOwner(GameFrame owner){
        HomeMenu.owner = owner;
    }
}