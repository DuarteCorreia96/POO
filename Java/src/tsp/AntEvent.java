package tsp;

import java.util.LinkedList;

public class AntEvent extends Event {

  private final double gamma;
  private final int nextNode;
  public final Ant ant;

  AntEvent(double g, Ant a){
    
    ant = a;
    double time = 0;
    gamma = g;

    nextNode = 1;

    setEventTime(time);
  }

  public void incEvent(){
    incEEvent();
  }

  @Override
  public LinkedList<Event> doEvent(){

    incEvent();
    if(nextNode == 1 && gamma == 0){
      return null;
    }

    return null;
  }

}

