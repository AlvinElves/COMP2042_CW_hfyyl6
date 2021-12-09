package test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * InstructionController class
 * @author LimYiHong
 * @since 09/12/2021
 */

public class InstructionController implements MouseListener, MouseMotionListener {

    /**
     * When back button is clicked go back to home menu
     * @param mouseEvent user's mouse when clicked
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InstructionModel.getButton().contains(p)){
            InstructionModel.getOwner().backToHomePage();
        }
    }

    /**
     * When back button is pressed put the button text and border to a different colour
     * @param mouseEvent user's mouse when pressed
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InstructionModel.getButton().contains(p)){
            InstructionModel.setButtonClicked(true);
            GameFrame.getInstruction().repaint(InstructionModel.getButton().x, InstructionModel.getButton().y, InstructionModel.getButton().width+1, InstructionModel.getButton().height+1);
        }
    }

    /**
     * When back button is released put the button text and border to the original colour
     * @param mouseEvent user's mouse when released
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(InstructionModel.getButtonClicked()) {
            InstructionModel.setButtonClicked(false);
            GameFrame.getInstruction().repaint(InstructionModel.getButton().x, InstructionModel.getButton().y, InstructionModel.getButton().width+1, InstructionModel.getButton().height+1);
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
     * When user cursor is moved to the back button, change the cursor to a hand cursor
     * @param mouseEvent user's mouse move
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InstructionModel.getButton().contains(p))
            GameFrame.getInstruction().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            GameFrame.getInstruction().setCursor(Cursor.getDefaultCursor());
    }
}
