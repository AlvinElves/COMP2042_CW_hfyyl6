package test;


import static org.junit.jupiter.api.Assertions.*;

class GameTimerTest {

    @org.junit.jupiter.api.Test
    void resetGame() {
        GameTimer.setSeconds(2);
        GameTimer.setMinutes(3);
        GameTimer.setTempSeconds(5);
        GameTimer.setTempMinutes(4);
        GameTimer.setGameRunning(true);
        GameTimer.resetGame(); //Check if reset, put everything back to 0 and false
        assertEquals(0, GameTimer.getSeconds());
        assertEquals(0, GameTimer.getMinutes());
        assertEquals(0, GameTimer.getTempMinutes());
        assertEquals(0, GameTimer.getTempSeconds());
        assertFalse(GameTimer.isGameRunning());
    }

    @org.junit.jupiter.api.Test
    void increaseSecond() {
        GameTimer gameTimer = new GameTimer();
        GameTimer.resetGame(); //Make every variable back to 0 and false
        gameTimer.increaseSecond(); //Increase second by 1
        assertEquals(1, GameTimer.getSeconds()); //Check if seconds increase by 1

        GameTimer.setSeconds(59);
        gameTimer.increaseSecond(); //Increase second by 1
        assertEquals(1, GameTimer.getMinutes()); //Check if minute increase by 1 when second is more than 59
        assertEquals(0, GameTimer.getSeconds()); //Check if second is back to 0 when second is more than 59
    }

    @org.junit.jupiter.api.Test
    void getTempSeconds() {
        GameTimer.setTempSeconds(10); //Set the temp second to 10
        assertEquals(10, GameTimer.getTempSeconds()); //Check if the temp second is 10
    }

    @org.junit.jupiter.api.Test
    void setTempSeconds() {
        GameTimer.setTempSeconds(30); //Set the temp second to 30
        assertEquals(30, GameTimer.getTempSeconds()); //Check if the temp second is 30
    }

    @org.junit.jupiter.api.Test
    void getSeconds() {
        GameTimer.setSeconds(15); //Set the second to 15
        assertEquals(15, GameTimer.getSeconds()); //Check if the second is 15
    }

    @org.junit.jupiter.api.Test
    void setSeconds() {
        GameTimer.setSeconds(45); //Set the second to 45
        assertEquals(45, GameTimer.getSeconds()); //Check if the second is 45
    }

    @org.junit.jupiter.api.Test
    void isGameRunning() {
        GameTimer.setGameRunning(true);
        assertTrue(GameTimer.isGameRunning());
    }

    @org.junit.jupiter.api.Test
    void setGameRunning() {
        GameTimer.setGameRunning(false);
        assertFalse(GameTimer.isGameRunning());
    }

    @org.junit.jupiter.api.Test
    void getMinutes() {
        GameTimer.setMinutes(3);
        assertEquals(3, GameTimer.getMinutes());
    }

    @org.junit.jupiter.api.Test
    void setMinutes() {
        GameTimer.setMinutes(2);
        assertEquals(2, GameTimer.getMinutes());
    }

    @org.junit.jupiter.api.Test
    void getTempMinutes() {
        GameTimer.setTempMinutes(4);
        assertEquals(4, GameTimer.getTempMinutes());
    }

    @org.junit.jupiter.api.Test
    void setTempMinutes() {
        GameTimer.setTempMinutes(1);
        assertEquals(1, GameTimer.getTempMinutes());
    }
}