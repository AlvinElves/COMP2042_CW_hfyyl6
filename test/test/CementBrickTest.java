package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CementBrickTest {
    CementBrick cementBrick = new CementBrick(new Point(), new Dimension(1,1));

    @Test
    void impactCementBrick() {
        cementBrick.impact();
        assertEquals(1, cementBrick.getStrength());
    }

    @Test
    void isCementBrickBroken() {
        cementBrick.impact();
        assertFalse(cementBrick.isBroken());
        cementBrick.impact();
        assertTrue(cementBrick.isBroken());
    }

    @Test
    void repairCementBrick() {
        cementBrick.impact();
        cementBrick.repair();
        assertEquals(2, cementBrick.getStrength());
    }

}