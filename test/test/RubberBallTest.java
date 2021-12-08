package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RubberBallTest {
    RubberBall rubberBall = new RubberBall(new Point());

    @Test
    void setRubberBallSpeedX() {
        rubberBall.setXSpeed(3);
        assertEquals(3, rubberBall.getSpeedX());
    }

    @Test
    void setRubberBallSpeedY() {
        rubberBall.setYSpeed(1);
        assertEquals(1, rubberBall.getSpeedY());
    }

    @Test
    void reverseRubberBallSpeedX() {
        rubberBall.setXSpeed(5);
        rubberBall.reverseX();
        assertEquals(-5, rubberBall.getSpeedX());
        rubberBall.reverseX();
        assertEquals(5, rubberBall.getSpeedX());
    }

    @Test
    void reverseRubberBallSpeedY() {
        rubberBall.setYSpeed(2);
        rubberBall.reverseY();
        assertEquals(-2, rubberBall.getSpeedY());
        rubberBall.reverseY();
        assertEquals(2, rubberBall.getSpeedY());
    }
}