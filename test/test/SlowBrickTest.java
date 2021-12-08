package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SlowBrickTest {
    SlowBrick slowBrick = new SlowBrick(new Point(), new Dimension(1,1));

    @Test
    void impactSlowBrick() {
        slowBrick.impact(); //Impact the clay brick
        assertEquals(0, slowBrick.getStrength()); //Check if the strength is 0
    }

    @Test
    void isSlowBrickBroken() {
        slowBrick.impact(); //Impact the clay brick
        assertTrue(slowBrick.isBroken()); //Check if its strength is 0
    }

    @Test
    void repairSlowBrick() {
        slowBrick.impact(); //Impact the clay brick
        slowBrick.repair(); //Repair the brick like strength
        assertEquals(1, slowBrick.getStrength()); //Check if the strength is back to 1
    }

}