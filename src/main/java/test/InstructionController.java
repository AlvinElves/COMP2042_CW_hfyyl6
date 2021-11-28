package test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InstructionController extends GameFrame implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(Instruction.getButton().contains(p)){
            Instruction.getOwner().backToHomePage();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(Instruction.getButton().contains(p)){
            Instruction.setButtonClicked(true);
            GameFrame.getInstruction().repaint(Instruction.getButton().x,Instruction.getButton().y,Instruction.getButton().width+1,Instruction.getButton().height+1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(Instruction.getButtonClicked()) {
            Instruction.setButtonClicked(false);
            GameFrame.getInstruction().repaint(Instruction.getButton().x,Instruction.getButton().y,Instruction.getButton().width+1,Instruction.getButton().height+1);
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
        if(Instruction.getButton().contains(p))
            GameFrame.getInstruction().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            GameFrame.getInstruction().setCursor(Cursor.getDefaultCursor());
    }
}
