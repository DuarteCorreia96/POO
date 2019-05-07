package tsp;

import java.util.LinkedList;
import java.util.Random;

public abstract class Event {

    private double eventTime;

    private static Random random = new Random();

    public abstract void incEvent();
    public abstract LinkedList<Event> doEvent();

    public static double expRandom(double m) {
        double next = random.nextDouble();
        return -m * Math.log(1.0 - next);
    }

    public void setEventTime(double time) {
        eventTime = time;
    }

    public double getEventTime() {
        return eventTime;
    }
}