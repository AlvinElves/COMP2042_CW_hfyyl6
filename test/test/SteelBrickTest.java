package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickTest {
    SteelBrick steelBrick = new SteelBrick(new Point(), new Dimension(1,1));

    @Test
    void impactSteelBrick() {
        steelBrick.setRandomProbability(0.4); //More than steel probability
        steelBrick.impact(); //Brick will not lose strength
        assertEquals(1, steelBrick.getStrength());
        steelBrick.setRandomProbability(0.3); //More than steel probability
        steelBrick.impact(); //Brick will lose strength
        assertEquals(0, steelBrick.getStrength());
    }

    @Test
    void isSteelBrickBroken() {
        steelBrick.setRandomProbability(0.5); //More than steel probability
        steelBrick.impact(); // Brick will be not broken
        assertFalse(steelBrick.isBroken());
        steelBrick.setRandomProbability(0.2); //Less than steel probability
        steelBrick.impact(); // Brick will be broken
        assertTrue(steelBrick.isBroken());
    }

    @Test
    void repairSteelBrick() {
        steelBrick.setRandomProbability(0.2); //Less than steel probability
        steelBrick.impact();
        steelBrick.repair();
        assertEquals(1, steelBrick.getStrength());
    }
}