package tsp;

 import java.util.Arrays;
import java.util.LinkedList;

import graph.TSPGraph;

public class AntEvent extends Event {

  private final double delta;
  private final int nextNode;
  public final Ant ant;

  AntEvent(double currTime, double delta, Ant ant){
    
    this.ant = ant;
    this.delta = delta;
    this.nextNode = ant.getNextNode();
    
    setEventTime( currTime + expRandom(delta * ant.getNextNodeWeigth(nextNode)) );
  }

  AntEvent(AntEvent antEvent){

    this.ant = antEvent.getAnt();
    this.delta = antEvent.getDelta();
    this.nextNode = ant.getNextNode();
    
    setEventTime( antEvent.getEventTime() + expRandom(delta * ant.getNextNodeWeigth(nextNode)) );
  }

  public Ant getAnt() {
    return ant;
  }

  public double getDelta() {
    return delta;
  }

  @Override
  public void incEvent(){
    incMEvent();
  }

  @Override
  public LinkedList<Event> doEvent(){

    ant.move(nextNode);
    incEvent();

    LinkedList<Event> antEvents = new LinkedList<Event>();
    
    if(ant.isHamiltonian()) {

      Ant.incHamiltonians();
      TSPGraph maze = ant.getMaze(); 
      int[] path = ant.doPath();

      maze.layPheromones(path, ant.getGamma()*maze.getW() / maze.getCycleCost(path));
      System.out.println("Found hamiltonian cycle at t: " + this.getEventTime());
      System.out.println(ant + "\n");
      ant.reset();
    }

    antEvents.push(new AntEvent(this));
    return antEvents;
  }

}

