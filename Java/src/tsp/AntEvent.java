package tsp;

import java.util.Arrays;
import java.util.LinkedList;

public class AntEvent extends Event {

  private final double gamma;
  private final int nextNode;
  public final Ant ant;

  AntEvent(double currTime, double g, Ant a){
    
    ant = a;
    nextNode = ant.getNextNode();

    gamma = g;
    setEventTime( currTime + expRandom(gamma * ant.getNextNodeWeigth(nextNode)) );
  }

  public void incEvent(){
    incEEvent();
  }

  @Override
  public LinkedList<Event> doEvent(){

    ant.move(nextNode);
    System.out.println(" → " + nextNode + " at time: " +  this.getEventTime());
    incEvent();

    LinkedList<Event> antEvents = new LinkedList<Event>();
    antEvents.push(new AntEvent(this.getEventTime(), gamma, ant));

    return antEvents;
  }

}

