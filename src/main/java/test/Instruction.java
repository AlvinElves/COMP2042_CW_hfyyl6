package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Instruction extends JComponent implements MouseListener, MouseMotionListener{

    private static final String GREETING1_TEXT = "INSTRUCTIONS";
    private static final String GREETING2_TEXT = "Welcome to Brick Destroyer!";
    private static final String GOAL1_TEXT = "The player's goal is to break all the bricks while not using all the balls.";
    private static final String GOAL2_TEXT = "There are 5 levels. 31 Bricks per level. Each impact will deal 1 strength.";
    private static final String BRICK1_TEXT = "Clay has a strength of 1, Cement has a strength of 2,";
    private static final String BRICK2_TEXT = "Steel has a strength of 1 and 0.4 chance of breaking, Concrete has a ";
    private static final String BRICK3_TEXT = "strength of 2 and 0.7 chance of breaking, and lastly a SpecialBrick,";
    private static final String BRICK4_TEXT = "where when impacted it will invert the movement.";
    private static final String INSTRUCTION1_TEXT = "Press A to move LEFT. Press D to move RIGHT.";
    private static final String INSTRUCTION2_TEXT = "Press Esc or Space to pause the game.";
    private static final String GREETING3_TEXT = "ENJOY THE GAME!!";
    private static final String BACK_TEXT = "⮘ BACK";

    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color CLICKED_BUTTON_COLOR = Color.RED;
    private static final Color CLICKED_TEXT = Color.RED;

    private Rectangle menuFace;
    private Rectangle backButton;


    private BasicStroke borderStoke;

    private Font buttonFont;
    private Font greetingFont;
    private Font instructionFont;

    private GameFrame owner;

    private boolean backButtonClicked;

    private BufferedImage image;


    public Instruction(GameFrame owner, Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;



        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 13);
        backButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(2);

        instructionFont = new Font("Serif",Font.PLAIN,15);
        greetingFont = new Font("Serif",Font.BOLD,50);
        buttonFont = new Font("Helvetica",Font.PLAIN,20);

    }

    public void paint(Graphics g){
        drawInstructionMenu((Graphics2D)g);
    }

    public void drawInstructionMenu(Graphics2D g2d){

        drawInstructionContainer(g2d);

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
        drawInstructionText(g2d);
        drawInstructionButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    private void drawInstructionText(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetings1Rect = greetingFont.getStringBounds(GREETING1_TEXT,frc);
        Rectangle2D greetings2Rect = instructionFont.getStringBounds(GREETING2_TEXT,frc);
        Rectangle2D goal1Rect = instructionFont.getStringBounds(GOAL1_TEXT,frc);
        Rectangle2D goal2Rect = instructionFont.getStringBounds(GOAL2_TEXT,frc);
        Rectangle2D brick1Rect = instructionFont.getStringBounds(BRICK1_TEXT,frc);
        Rectangle2D brick2Rect = instructionFont.getStringBounds(BRICK2_TEXT,frc);
        Rectangle2D brick3Rect = instructionFont.getStringBounds(BRICK3_TEXT,frc);
        Rectangle2D brick4Rect = instructionFont.getStringBounds(BRICK4_TEXT,frc);
        Rectangle2D instruction1Rect = instructionFont.getStringBounds(INSTRUCTION1_TEXT,frc);
        Rectangle2D instruction2Rect = instructionFont.getStringBounds(INSTRUCTION2_TEXT,frc);
        Rectangle2D greetings3Rect = instructionFont.getStringBounds(GREETING3_TEXT,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetings1Rect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 5);
        drawTextPlace(g2d,Color.BLUE, greetingFont,sX, sY, GREETING1_TEXT);

        sX = (int)(menuFace.getWidth() - greetings2Rect.getWidth()) / 2;
        sY +=(int) greetings2Rect.getHeight() * 1.1;
        drawTextPlace(g2d,TEXT_COLOR, instructionFont,sX, sY, GREETING2_TEXT);

        sX = (int)(menuFace.getWidth() - goal1Rect.getWidth()) / 2;
        sY += (int) goal1Rect.getHeight() * 1.1;
        drawTextPlace(g2d,TEXT_COLOR, instructionFont,sX, sY, GOAL1_TEXT);

        sX = (int)(menuFace.getWidth() - goal2Rect.getWidth()) / 2;
        sY += (int) goal2Rect.getHeight() * 1.1;
        drawTextPlace(g2d,TEXT_COLOR, instructionFont,sX, sY, GOAL2_TEXT);

        sX = (int)(menuFace.getWidth() - brick1Rect.getWidth()) / 2;
        sY += (int) brick1Rect.getHeight() * 1.1;
        drawTextPlace(g2d,TEXT_COLOR, instructionFont,sX, sY, BRICK1_TEXT);

        sX = (int)(menuFace.getWidth() - brick2Rect.getWidth()) / 2;
        sY += (int) brick2Rect.getHeight() * 1.1;
        drawTextPlace(g2d,TEXT_COLOR, instructionFont,sX, sY, BRICK2_TEXT);

        sX = (int)(menuFace.getWidth() - brick3Rect.getWidth()) / 2;
        sY += (int) brick3Rect.getHeight() * 1.1;
        drawTextPlace(g2d,TEXT_COLOR, instructionFont,sX, sY, BRICK3_TEXT);

        sX = (int)(menuFace.getWidth() - brick4Rect.getWidth()) / 2;
        sY += (int) brick4Rect.getHeight() * 1.1;
        drawTextPlace(g2d,TEXT_COLOR, instructionFont,sX, sY, BRICK4_TEXT);

        sX = (int)(menuFace.getWidth() - instruction1Rect.getWidth()) / 2;
        sY += (int) instruction1Rect.getHeight() * 1.1;
        drawTextPlace(g2d,TEXT_COLOR, instructionFont,sX, sY, INSTRUCTION1_TEXT);

        sX = (int)(menuFace.getWidth() - instruction2Rect.getWidth()) / 2;
        sY += (int) instruction2Rect.getHeight() * 1.1;
        drawTextPlace(g2d,TEXT_COLOR, instructionFont,sX, sY, INSTRUCTION2_TEXT);

        sX = (int)(menuFace.getWidth() - greetings3Rect.getWidth()) / 2;
        sY += (int) greetings3Rect.getHeight() * 1.1;
        drawTextPlace(g2d,TEXT_COLOR, instructionFont,sX, sY, GREETING3_TEXT);

    }

    private void drawTextPlace(Graphics2D g2d, Color color, Font font, int x, int y, String text){

        g2d.setColor(color);
        g2d.setFont(font);
        g2d.drawString(text,x,y);
    }

    private void drawInstructionButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D bTxtReact = buttonFont.getStringBounds(BACK_TEXT,frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - backButton.width) / 2;
        int y =(int) ((menuFace.height - backButton.height) * 0.8);

        x *= 0.2;
        y *= 1.2;

        backButton.setLocation(x,y);

        x = (int)(backButton.getWidth() - bTxtReact.getWidth()) / 2;
        y = (int)(backButton.getHeight() - bTxtReact.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.9);

        g2d.setColor(Color.WHITE);
        g2d.fill(backButton);
        g2d.setStroke(borderStoke);
        g2d.setColor(Color.BLACK);

        if(backButtonClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(backButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(backButton);
            g2d.setColor(Color.BLACK);
            g2d.drawString(BACK_TEXT,x,y);
        }

    }

    private void drawInstructionContainer(Graphics2D g2d){
        try {
            image = ImageIO.read(getClass().getResource("/Instruction.jpg"));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        g2d.drawImage(image, 0, 0, 450, 300, this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            owner.backToHomePage();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p)){
            backButtonClicked = true;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(backButtonClicked ){
            backButtonClicked = false;
            repaint(backButton.x,backButton.y,backButton.width+1,backButton.height+1);
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

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(backButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
