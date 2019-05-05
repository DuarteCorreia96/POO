package tsp;

import graph.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;

class The_Comparator implements Comparator<Event> {
  public int compare(Event event1, Event event2) {

    return Double.compare(event1.getEventTime(), event2.getEventTime());    
  }
}

public class Main {

  public static void main(String[] args) {

    TSPGraph maze = new TSPGraph(5);
    double tau = 1000;
    int nAnts = 5;

    int nestNode = 5;
    double alpha = 1;
    double beta = 1;
    double gamma = 1;
    
    PriorityQueue<Event> PEC = new PriorityQueue<Event>(new The_Comparator());

    System.out.println("Nest Node :" + 1);

    maze.addEdge(1, 2, 1);
    maze.addEdge(2, 3, 3);
    maze.addEdge(3, 4, 3);
    maze.addEdge(4, 5, 3);
    maze.addEdge(5, 1, 1);
    maze.addEdge(2, 5, 6);
    maze.addEdge(1, 3, 2);
    maze.addEdge(4, 1, 1);

    Ant[] colony = new Ant[nAnts];
    
    for(int i = 0; i < nAnts; i++){
      colony[i] = new Ant(nestNode, alpha, beta, maze);
      PEC.add(new AntEvent(0.0, gamma, colony[i]));
    }

    LinkedList<Event> events;
    while(PEC.peek() != null){
      
      events = PEC.poll().doEvent();

      while(events != null && events.peek() != null && events.peek().getEventTime() < tau){
        PEC.add(events.poll());
      }
    }

    System.out.println("EEvents done: " + Event.getEEvents());
    System.out.println("MEvents done: " + Event.getMEvents());

    for (int i = 0; i < nAnts; i++) {
      System.out.println(colony[i]);
    } 
  }

}