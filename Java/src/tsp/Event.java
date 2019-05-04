package tsp;

import java.util.LinkedList;

public abstract class Event {

  private double eventTime;
  private static int eevents = 0;
  private static int mevents = 0;

  public abstract void incEvent();
  public abstract LinkedList<Event> doEvent();

  public void setEventTime(double time) {
    eventTime = time;
  }

  public double getEventTime() {
    return eventTime;
  }

  public void incEEvent() {
    eevents++;
  }

  public void incMEvent() {
    mevents++;
  }

  public int getEEvents() {
    return eevents;
  }

  public int getMEvents() {
    return mevents;
  }

}

