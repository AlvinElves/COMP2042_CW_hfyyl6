package test;

import java.util.TimerTask;
import java.util.Timer;

public class GameTimer {

    private static int seconds;
    private static int minutes;
    private static int tempSeconds;
    private static int tempMinutes;
    private static boolean gameRunning = false;

    /**
     * Constructor Game Timer that has a timer that increase second by 1 every second.
     */
    public GameTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isGameRunning()) {
                    increaseSecond();
                }
            }
        }, 0, 1000);
    }

    /**
     * Reset the timer when needed, set everything to 0 and false
     */
    public static void resetGame(){
        setTempSeconds(0);
        setSeconds(0);
        setMinutes(0);
        setTempMinutes(0);
        setGameRunning(false);
    }

    /**
     * Increase second, when more than 59 increase minute and set second back to 0
     */
    public void increaseSecond(){
        setSeconds(getSeconds() + 1);
        if(getSeconds() > 59) {
            setSeconds(0);
            setMinutes(getMinutes() + 1);
        }
    }

    /**
     * get method for temp Seconds, encapsulating
     * @return the temp seconds that is used to store the time when user reach a new level
     */
    public static int getTempSeconds(){
        return tempSeconds;
    }

    /**
     * set method for temp Seconds, encapsulating
     * @param tempSeconds seconds of time when user reach a new level
     */
    public static void setTempSeconds(int tempSeconds){
        GameTimer.tempSeconds = tempSeconds;
    }

    /**
     * get method for Seconds, encapsulating
     * @return the seconds of the game time
     */
    public static int getSeconds() {
        return seconds;
    }

    /**
     * set method for Seconds, encapsulating
     * @param seconds the game time seconds
     */
    public static void setSeconds(int seconds) {
        GameTimer.seconds = seconds;
    }

    /**
     * get method, check if the game is running so the timer runs
     * @return true if game is running, else false
     */
    public static boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * set method for Game Running, encapsulating
     * @param gameRunning game is running or not
     */
    public static void setGameRunning(boolean gameRunning) {
        GameTimer.gameRunning = gameRunning;
    }

    /**
     * get method for Minutes, encapsulating
     * @return the minutes of the game time
     */
    public static int getMinutes() {
        return minutes;
    }

    /**
     * set method for Minutes, encapsulating
     * @param minutes the game time minutes
     */
    public static void setMinutes(int minutes) {
        GameTimer.minutes = minutes;
    }

    /**
     * get method for temp Minutes, encapsulating
     * @return the temp minutes that is used to store the time when user reach a new level
     */
    public static int getTempMinutes() {
        return tempMinutes;
    }

    /**
     * set method for temp Minutes, encapsulating
     * @param tempMinutes minutes of time when user reach a new level
     */
    public static void setTempMinutes(int tempMinutes) {
        GameTimer.tempMinutes = tempMinutes;
    }
}