package test;

import java.util.TimerTask;
import java.util.Timer;

public class GameTimer {

    private int seconds;
    private int minutes;
    private int tempSeconds;
    private int tempMinutes;
    private boolean gameRunning = false;

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

    public void resetGame(){
        setTempSeconds(0);
        setSeconds(0);
        setMinutes(0);
        setTempMinutes(0);
        setGameRunning(false);
    }
    public int getTempSeconds(){
        return tempSeconds;
    }

    public void setTempSeconds(int seconds){
        this.tempSeconds = seconds;
    }
    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getTempMinutes() {
        return tempMinutes;
    }

    public void setTempMinutes(int tempMinutes) {
        this.tempMinutes = tempMinutes;
    }
}
