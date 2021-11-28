package test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

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

    public void paint(Graphics g){
        new InstructionView(g);
    }

    public static Color getColor(){
        return color;
    }

    public static void setColor(Color color){
        InstructionModel.color = color;
    }

    public static Font getTextFont(){
        return textFont;
    }

    public static void setTextFont(Font textFont){
        InstructionModel.textFont = textFont;
    }

    public static BufferedImage getImage(){
        return image;
    }

    public static void setImage(BufferedImage image){
        InstructionModel.image = image;
    }

    public static Rectangle getMenuFace(){
        return menuFace;
    }

    public static void setMenuFace(Rectangle menuFace){
        InstructionModel.menuFace = menuFace;
    }

    public static String getText(){
        return text;
    }

    public static void setText(String text){
        InstructionModel.text = text;
    }

    public static Rectangle getButton(){
        return button;
    }

    public static void setButton(Rectangle button){
        InstructionModel.button = button;
    }

    public static Rectangle2D getRect(){
        return rect;
    }

    public static void setRect(Rectangle2D rect){
        InstructionModel.rect = rect;
    }

    public static boolean getButtonClicked(){
        return buttonClicked;
    }

    public static void setButtonClicked(boolean buttonClicked){
        InstructionModel.buttonClicked = buttonClicked;
    }

    public static GameFrame getOwner(){
        return owner;
    }

    public static void setOwner(GameFrame owner){
        InstructionModel.owner = owner;
    }

    public static BasicStroke getBorderStoke(){
        return borderStoke;
    }

    public static void setBorderStoke(BasicStroke borderStoke){
        InstructionModel.borderStoke = borderStoke;
    }
}
