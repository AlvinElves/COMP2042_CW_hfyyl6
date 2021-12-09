package test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * InstructionModel class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class InstructionModel extends JComponent{

    private static String text;

    private static Color color;

    private static Rectangle menuFace;
    private static Rectangle button;

    private static Rectangle2D rect;

    private static BasicStroke borderStoke;

    private static Font textFont;

    private static GameFrame owner;

    private static boolean buttonClicked;

    private static BufferedImage image;

    /**
     * Instruction constructor that will set up the instruction screen
     * @param owner GameFrame owner that will set up the instruction screen
     * @param area the screen of the instruction, how big is it
     */
    public InstructionModel(GameFrame owner, Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(new InstructionController());
        this.addMouseMotionListener(new InstructionController());

        setOwner(owner);



        InstructionModel.setMenuFace(new Rectangle(new Point(0,0),area));

        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 13);
        setButton(new Rectangle(btnDim));

        setBorderStoke(new BasicStroke(2));

    }

    /**
     * Paint the screen, shows text, button and background images
     * @param g graphics parameter that shows the screen stuffs
     */
    public void paint(Graphics g){
        new InstructionView(g);
    }

    /**
     * get method for Colour, encapsulating
     * @return the colour
     */
    public static Color getColor(){
        return color;
    }

    /**
     * set method for Colour, encapsulating
     * @param color colour you want to use
     */
    public static void setColor(Color color){
        InstructionModel.color = color;
    }

    /**
     * get method for text font, encapsulating
     * @return text font
     */
    public static Font getTextFont(){
        return textFont;
    }

    /**
     * set method for text font, encapsulating
     * @param textFont text font you want to use
     */
    public static void setTextFont(Font textFont){
        InstructionModel.textFont = textFont;
    }

    /**
     * get method for image, encapsulating
     * @return the image
     */
    public static BufferedImage getImage(){
        return image;
    }

    /**
     * set method for image, encapsulating
     * @param image the image want to use
     */
    public static void setImage(BufferedImage image){
        InstructionModel.image = image;
    }

    /**
     * get method for menu face, encapsulating
     * @return menu face of instruction
     */
    public static Rectangle getMenuFace(){
        return menuFace;
    }

    /**
     * set method for menu face, encapsulating
     * @param menuFace menu face that want to be used
     */
    public static void setMenuFace(Rectangle menuFace){
        InstructionModel.menuFace = menuFace;
    }

    /**
     * get method for text, encapsulating
     * @return text
     */
    public static String getText(){
        return text;
    }

    /**
     * set method for text, encapsulating
     * @param text text that want to be used
     */
    public static void setText(String text){
        InstructionModel.text = text;
    }

    /**
     * get method for button, encapsulating
     * @return rectangle button
     */
    public static Rectangle getButton(){
        return button;
    }

    /**
     * set method for button, encapsulating
     * @param button how big the rectangle button is going to be
     */
    public static void setButton(Rectangle button){
        InstructionModel.button = button;
    }

    /**
     * get method for rectangle text, encapsulating
     * @return rectangle text
     */
    public static Rectangle2D getRect(){
        return rect;
    }

    /**
     * set method for rectangle text, encapsulating
     * @param rect rectangle text
     */
    public static void setRect(Rectangle2D rect){
        InstructionModel.rect = rect;
    }

    /**
     * get method for button clicked, encapsulating
     * @return true if button is clicked else false
     */
    public static boolean getButtonClicked(){
        return buttonClicked;
    }

    /**
     * set method for button clicked, encapsulating
     * @param buttonClicked whether the button is clicked
     */
    public static void setButtonClicked(boolean buttonClicked){
        InstructionModel.buttonClicked = buttonClicked;
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
        InstructionModel.owner = owner;
    }

    /**
     * get method for border stoke, encapsulating
     * @return how big the border will be for the button
     */
    public static BasicStroke getBorderStoke(){
        return borderStoke;
    }

    /**
     * set method for border stoke, encapsulating
     * @param borderStoke how big the button border will be
     */
    public static void setBorderStoke(BasicStroke borderStoke){
        InstructionModel.borderStoke = borderStoke;
    }
}
