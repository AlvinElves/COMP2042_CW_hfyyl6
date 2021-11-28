package test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.io.IOException;

public class InstructionView implements ImageObserver {

    public InstructionView(Graphics g){
        paint(g);
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

        double x = Instruction.getMenuFace().getX();
        double y = Instruction.getMenuFace().getY();

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


        Instruction.setColor(Color.BLUE);
        Instruction.setTextFont(new Font("Serif",Font.BOLD,50));
        Instruction.setText("INSTRUCTIONS");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 0),
                Instruction.getText());

        Instruction.setColor(Color.BLACK);
        Instruction.setTextFont(new Font("Serif",Font.PLAIN,15));
        Instruction.setText("Welcome to Brick Destroyer!");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 1.1),
                Instruction.getText());

        Instruction.setText("The player's goal is to break all the bricks while not using all the balls.");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 2.1),
                Instruction.getText());

        Instruction.setText("There are 5 levels. 31 Bricks per level. Each impact will deal 1 strength.");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 3.1),
                Instruction.getText());

        Instruction.setText("ClayBrick has a strength of 1, CementBrick has a strength of 2,");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 4.1),
                Instruction.getText());

        Instruction.setText("SteelBrick has a strength of 1 and 0.4 chance of breaking,");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 5.1),
                Instruction.getText());

        Instruction.setText("ConcreteBrick has a strength of 2 and 0.7 chance of breaking, and lastly");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 6.1),
                Instruction.getText());

        Instruction.setText("a SpecialBrick, where when impacted it will invert the movement.");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 7.1),
                Instruction.getText());

        Instruction.setText("Press A to move LEFT. Press D to move RIGHT.");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 8.1),
                Instruction.getText());

        Instruction.setText("Press Esc or Space to PAUSE the game.");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 9.1),
                Instruction.getText());

        Instruction.setText("ENJOY THE GAME!!");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));

        drawTextPlace(g2d, Instruction.getColor(), Instruction.getTextFont(),
                drawTextCoordX(Instruction.getRect()), drawTextCoordY(Instruction.getRect(), 10.1),
                Instruction.getText());

    }

    public int drawTextCoordX(Rectangle2D rect){
        return (int)(Instruction.getMenuFace().getWidth() - rect.getWidth()) / 2;
    }

    public int drawTextCoordY(Rectangle2D rect, double spacingY){
        return (int) ((int) Instruction.getMenuFace().getHeight() / 5 + rect.getHeight() * spacingY);
    }

    private void drawTextPlace(Graphics2D g2d, Color color, Font font, int x, int y, String text){

        g2d.setColor(color);
        g2d.setFont(font);
        g2d.drawString(text,x,y);
    }

    private void drawInstructionButton(Graphics2D g2d){

        Instruction.setTextFont(new Font("Helvetica",Font.PLAIN,20));

        g2d.setFont(Instruction.getTextFont());

        int x = (Instruction.getMenuFace().width - Instruction.getButton().width) / 2;
        int y =(int) ((Instruction.getMenuFace().height - Instruction.getButton().height) * 0.8);

        x *= 0.2;
        y *= 1.2;

        Instruction.getButton().setLocation(x,y);

        Instruction.setText("⮘ BACK");
        Instruction.setRect(Instruction.getTextFont().getStringBounds(Instruction.getText(),g2d.getFontRenderContext()));


        x = (int)(Instruction.getButton().getWidth() - Instruction.getRect().getWidth()) / 2;
        y = (int)(Instruction.getButton().getHeight() - Instruction.getRect().getHeight()) / 2;

        x += Instruction.getButton().x;
        y += Instruction.getButton().y + (Instruction.getButton().height * 0.9);

        g2d.setColor(Color.WHITE);
        g2d.fill(Instruction.getButton());
        g2d.setStroke(Instruction.getBorderStoke());
        g2d.setColor(Color.BLACK);

        if(Instruction.getButtonClicked()){
            Color tmp = g2d.getColor();
            g2d.setColor(Color.RED);
            g2d.draw(Instruction.getButton());
            g2d.setColor(Color.RED);
            g2d.drawString(Instruction.getText(),x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(Instruction.getButton());
            g2d.setColor(Color.BLACK);
            g2d.drawString(Instruction.getText(),x,y);
        }

    }

    private void drawInstructionContainer(Graphics2D g2d){
        try {
            Instruction.setImage(ImageIO.read(getClass().getResource("/Instruction.jpg")));
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        g2d.drawImage(Instruction.getImage(), 0, 0, 450, 300, this);
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }
}
