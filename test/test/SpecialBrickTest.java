package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SpecialBrickTest {

    SpecialBrick specialBrick = new SpecialBrick(new Point(), new Dimension(1,1));

    @Test
    void impactSpecialBrick() {
        specialBrick.impact();
        assertEquals(0, specialBrick.getStrength());
    }

    @Test
    void isSpecialBrickBroken() {
        specialBrick.impact();
        assertTrue(specialBrick.isBroken());
    }

    @Test
    void repairSpecialBrick() {
        specialBrick.impact();
        specialBrick.repair();
        assertEquals(1, specialBrick.getStrength());
    }

    @Test
    void setImpact() {
        specialBrick.repair();
        Player.setBarReversed(false);
        specialBrick.setImpact(new Point(), 1);
        assertTrue(Player.getBarReversed());
        specialBrick.repair();
        specialBrick.setImpact(new Point(), 1);
        assertFalse(Player.getBarReversed());
    }
}