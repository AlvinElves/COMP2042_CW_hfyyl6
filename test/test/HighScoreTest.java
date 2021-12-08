package test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreTest {

    @Test
    void sortingAfterGame() {
        HighScore.setScore(new int[8][3]);
        int i, j;
        for (i = 0; i < 7; i++) {
            for (j = 0; j < 3; j++) {
                HighScore.getScore()[i][j] = 2; //Set every score variable to 2
            }
        }
        Wall.setTotalBrickBroken(7);
        GameTimer.setSeconds(10);
        GameTimer.setMinutes(3);
        HighScore.sortingAfterGame(); //Do sorting

        assertEquals(7, HighScore.getScore()[0][0]);
        assertEquals(3, HighScore.getScore()[0][1]);
        assertEquals(10, HighScore.getScore()[0][2]);
    }

    @Test
    void getScore() {
        HighScore.setScore(new int[8][3]);
        int i, j;
        for (i = 0; i < 7; i++) {
            for (j = 0; j < 3; j++) {
                assertEquals(0, HighScore.getScore()[i][j]);
            }
        }
    }
}