package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {
    ClayBrick clayBrick = new ClayBrick(new Point(), new Dimension(1,1));

    @Test
    void impactClayBrick() {
        clayBrick.impact(); //Impact the clay brick
        assertEquals(0, clayBrick.getStrength()); //Check if the strength is 0
    }

    @Test
    void isClayBrickBroken() {
        clayBrick.impact(); //Impact the clay brick
        assertTrue(clayBrick.isBroken()); //Check if its strength is 0
    }

    @Test
    void repairClayBrick() {
        clayBrick.impact(); //Impact the clay brick
        clayBrick.repair(); //Repair the brick like strength
        assertEquals(1, clayBrick.getStrength()); //Check if the strength is back to 1
    }

}