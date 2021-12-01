package test;

import java.util.TimerTask;
import java.util.Timer;

public class GameTimer {

    private static int seconds;
    private static int minutes;
    private static int tempSeconds;
    private static int tempMinutes;
    private static boolean gameRunning = false;

    public GameTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isGameRunning()) {
                    setSeconds(getSeconds() + 1);
                    if(getSeconds() > 59) {
                        setSeconds(0);
                        setMinutes(getMinutes() + 1);
                    }
                }
            }
        }, 0, 1000);
    }

    public static void resetGame(){
        setTempSeconds(0);
        setSeconds(0);
        setMinutes(0);
        setTempMinutes(0);
        setGameRunning(false);
    }
    public static int getTempSeconds(){
        return tempSeconds;
    }

    public static void setTempSeconds(int seconds){
        GameTimer.tempSeconds = seconds;
    }
    public static int getSeconds() {
        return seconds;
    }

    public static void setSeconds(int seconds) {
        GameTimer.seconds = seconds;
    }

    public static boolean isGameRunning() {
        return gameRunning;
    }

    public static void setGameRunning(boolean gameRunning) {
        GameTimer.gameRunning = gameRunning;
    }

    public static int getMinutes() {
        return minutes;
    }

    public static void setMinutes(int minutes) {
        GameTimer.minutes = minutes;
    }

    public static int getTempMinutes() {
        return tempMinutes;
    }

    public static void setTempMinutes(int tempMinutes) {
        GameTimer.tempMinutes = tempMinutes;
    }
}