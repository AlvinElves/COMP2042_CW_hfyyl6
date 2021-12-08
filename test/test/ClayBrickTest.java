package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {
    ClayBrick clayBrick = new ClayBrick(new Point(), new Dimension(1,1));

    @Test
    void impactClayBrick() {
        clayBrick.impact();
        assertEquals(0, clayBrick.getStrength());
    }

    @Test
    void isClayBrickBroken() {
        clayBrick.impact();
        assertTrue(clayBrick.isBroken());
    }

    @Test
    void repairClayBrick() {
        clayBrick.impact();
        clayBrick.repair();
        assertEquals(1, clayBrick.getStrength());
    }

}