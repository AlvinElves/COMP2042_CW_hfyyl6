package test;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InstructionController extends GameFrame implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InstructionModel.getButton().contains(p)){
            InstructionModel.getOwner().backToHomePage();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InstructionModel.getButton().contains(p)){
            InstructionModel.setButtonClicked(true);
            GameFrame.getInstruction().repaint(InstructionModel.getButton().x, InstructionModel.getButton().y, InstructionModel.getButton().width+1, InstructionModel.getButton().height+1);
        }
    }

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

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(InstructionModel.getButton().contains(p))
            GameFrame.getInstruction().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            GameFrame.getInstruction().setCursor(Cursor.getDefaultCursor());
    }
}
