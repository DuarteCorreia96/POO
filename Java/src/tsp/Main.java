package tsp;

import graph.*;
import java.util.LinkedList;

public class Main {

  public static void main(String[] args) {

    Graph<Integer, String> g = new Graph<Integer, String>(3, 4);

    g.addConnection(1, 2, 3);
    g.addConnection(1, 3, 3);
    g.addConnection(2, 4, 5);
    g.addConnection(3, 4, 1);

    Ant a = new Ant(0, 1, 3, 10, g);
    AntEvent ant_move = new AntEvent(1.5, a);
    
    ant_move.doEvent();
    ant_move.doEvent();
    ant_move.doEvent(); 

    System.out.println("EEvents done: " + ant_move.getEEvents());
    System.out.println("MEvents done: " + ant_move.getMEvents());

    ant_move = null;
    Event ant_m = new AntEvent(2, a);
    ant_m.doEvent();

    System.out.println("EEvents done: " + ant_m.getEEvents());
    System.out.println("MEvents done: " + ant_m.getMEvents());

    a.move(3);
    a.move(2);
    a.move(5);
    a.move(6);
    a.move(1);
    a.move(4);
    a.move(7);
    a.move(8);
    a.move(9);
    a.move(4);

    System.out.println(a);
    
  }

}