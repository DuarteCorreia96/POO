package tsp;

import graph.*;
import java.util.LinkedList;

public class Main {

  public static void main(String[] args) {

    TSPGraph g = new TSPGraph(5);

    System.out.println("Nest Node :" + 1);

    g.addEdge(1, 2, 1);
    g.addEdge(2, 3, 3);
    g.addEdge(3, 4, 3);
    g.addEdge(4, 5, 3);
    g.addEdge(5, 1, 1);
    g.addEdge(2, 5, 6);
    g.addEdge(1, 3, 2);
    g.addEdge(4, 1, 1);

    Ant a = new Ant(1, 1, 2, g);
    Event ant_move = new AntEvent(0.0, 1.5, a);
    
    LinkedList<Event> events = ant_move.doEvent();
    while(!a.isHamiltonian()){

      ant_move = events.poll();
      events = ant_move.doEvent();
    }

    //System.out.println("EEvents done: " + Event.getEEvents());
    //System.out.println("MEvents done: " + Event.getMEvents());

    //System.out.println("EEvents done: " + Event.getEEvents());
    //System.out.println("MEvents done: " + Event.getMEvents());

    System.out.println(a);
    
  }

}