package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    @Test
    void ballReset() {
        Wall wall = new Wall(new Rectangle(0,0,0,0),new Point(0,0));
        wall.setBallXSpeed(2);
        wall.setBallYSpeed(2);
        wall.setBallLost(true);
        wall.ballReset();
        assertEquals(4, wall.getBallSpeedX());
        assertEquals(-4, wall.getBallSpeedY());
        assertFalse(wall.isBallLost());
    }
}