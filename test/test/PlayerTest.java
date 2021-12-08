package test;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void playerBarMoveLeft() {
        Player player = new Player((Point) new Point(300,430).clone(),150,10, new Rectangle(0,0,600,450));
        Player.setDefMoveAmount(5);
        player.playerBarMoveRight();
        player.playerBarMoveLeft();
        assertEquals(-5, Player.getMoveAmount());
    }

    @Test
    void playerBarMoveRight() {
        Player player = new Player((Point) new Point(300,430).clone(),150,10, new Rectangle(0,0,600,450));
        Player.setDefMoveAmount(3);
        player.playerBarMoveLeft();
        player.playerBarMoveRight();
        assertEquals(3, Player.getMoveAmount());
    }

    @Test
    void playerBarStop() {
        Player player = new Player((Point) new Point(300,430).clone(),150,10, new Rectangle(0,0,600,450));
        Player.setDefMoveAmount(5);
        player.playerBarMoveLeft();
        player.playerBarMoveRight();
        player.playerBarStop();
        assertEquals(0, Player.getMoveAmount());
    }
}