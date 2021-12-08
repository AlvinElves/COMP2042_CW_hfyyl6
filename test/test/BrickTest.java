package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class BrickTest {

    @Test
    void repair() {
        Brick brick = new Brick("Test", new Point(), new Dimension(), Color.WHITE, Color.WHITE, 2) {
            @Override
            protected Shape makeBrickFace(Point pos, Dimension size) {
                return null;
            }

            @Override
            public Shape getBrick() {
                return null;
            }
        };

        brick.impact();
        brick.repair();
        assertEquals(2, brick.getStrength());
    }

    @Test
    void impact() {
        Brick brick = new Brick("Test", new Point(), new Dimension(), Color.WHITE, Color.WHITE, 2) {
            @Override
            protected Shape makeBrickFace(Point pos, Dimension size) {
                return null;
            }

            @Override
            public Shape getBrick() {
                return null;
            }
        };

        brick.impact();
        assertEquals(1, brick.getStrength());
    }

    @Test
    void isBroken() {
        Brick brick = new Brick("Test", new Point(), new Dimension(), Color.WHITE, Color.WHITE, 2) {
            @Override
            protected Shape makeBrickFace(Point pos, Dimension size) {
                return null;
            }

            @Override
            public Shape getBrick() {
                return null;
            }
        };

        brick.impact();
        assertFalse(brick.isBroken());
        brick.impact();
        assertTrue(brick.isBroken());
    }
}