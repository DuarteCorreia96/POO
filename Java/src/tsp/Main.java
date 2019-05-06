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

    int[][] info = new int[8][3];
		info[0][0] = 1; info[0][1] = 3; info[0][2] = 6;
		info[1][0] = 1; info[1][1] = 5; info[1][2] = 2;
		info[2][0] = 1; info[2][1] = 2; info[2][2] = 3;
		info[3][0] = 1; info[3][1] = 4; info[3][2] = 6;
		info[4][0] = 2; info[4][1] = 3; info[4][2] = 3;
		info[5][0] = 2; info[5][1] = 5; info[5][2] = 5;
		info[6][0] = 2; info[6][1] = 4; info[6][2] = 2;
    info[7][0] = 4; info[7][1] = 5; info[7][2] = 1; 
    
    TSPGraph maze = new TSPGraph(5, info, 1, 1); 
    double tau = 10000;
    int nAnts = 5;

    int nestNode = 5;
    double alpha = 1;
    double beta = 1;
    double gamma = 1;
    double eta = 0.1;
    
    PriorityQueue<Event> PEC = new PriorityQueue<Event>(new The_Comparator());

    Ant[] colony = new Ant[nAnts];
    
    for(int i = 0; i < nAnts; i++){
      colony[i] = new Ant(nestNode, alpha, beta, gamma, maze);
      PEC.add(new AntEvent(0.0, eta, colony[i]));
    }
 
    LinkedList<Event> events;
    while(PEC.peek() != null){
      
      events = PEC.poll().doEvent(); 

      while(events.peek() != null && events.peek().getEventTime() < tau){
        PEC.add(events.poll());
      }
    }

    System.out.println("\n EEvents done: " + Event.getEEvents());
    System.out.println(" MEvents done: " + Event.getMEvents());
    System.out.println(" Cycles found: " + Ant.getHamiltonianNo() + "\n");

    for (int i = 0; i < nAnts; i++) {
      System.out.println(colony[i]);
    } 
  }

}