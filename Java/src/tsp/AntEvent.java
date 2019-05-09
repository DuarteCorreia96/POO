package tsp;

import java.util.LinkedList;

/**
 * AntEvent is a {@code Event} that moves a ant to the next node, the time 
 * is based on an exponential distribuiton with mean value proportional to 
 * the weight of the next node. <p>
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see Event
 */

public class AntEvent extends Event {

    private static int mevents = 0;
    private final double delta;
    private final int nextNode;
    public final Ant ant;

    /**
     * Constructor of a {@code AntEvent} which receives the {@code currTime} of events 
     * and {@code delta} which is proportional to the average of the exponential
     * distribution of the time between the {@code currTime} and the event. <p>
     * 
     * The ant is the ant to be moved by the event. <p> 
     * 
     * Next node is choosen by the ant. <p>
     * 
     * @param currTime Current time of simulation
     * @param delta porpotional to average of the exxponetial distribution of time 
     * between now and the event
     * @param ant to be moved by the event
     * 
     * @see Ant#getNextNode()
     */
    AntEvent(double currTime, double delta, Ant ant){
        
        this.ant = ant;
        this.delta = delta;
        this.nextNode = ant.getNextNode();
        
        setEventTime( currTime + expRandom(delta * ant.getNextNodeWeight(nextNode)) );
    }

    /**
     * Constructor of a {@code AntEvent} to be used by another {@code AntEvent} to construct
     * a event with the same proprieties of itself that will happen after the old one.
     * 
     * A new node is selected by the ant. <p>
     * 
     * @param oldEvent event that originates the new one
     * 
     * @see Ant#getNextNode()
     * @see AntEvent#AntEvent(double, double, Ant)
     */
    AntEvent(AntEvent oldEvent){

        this.ant = oldEvent.getAnt();
        this.delta = oldEvent.getDelta();
        this.nextNode = ant.getNextNode();
        
        setEventTime( oldEvent.getEventTime() + expRandom(delta * ant.getNextNodeWeight(nextNode)) );
    }

    /**
     * Returns the ant that the event will move
     * @return ant to be moved
     */
    public Ant getAnt() {
        return ant;
    }

    /**
     * Returns the {@code delta} of the event 
     * @return delta
     */
    public double getDelta() {
        return delta;
    }

    /**
     * Returns the number of move events realized so far by every ant
     * @return total number of move events
     */
    public static int getEventNo() {
        return mevents;
    }

    /**
     * It moves the ant to the next node and checks if it reached a <i>Hamiltonian Cycle</i>,
     * if a cycle is reached checks to see if it is the best one found by all the ants, and 
     * lays pheromones on the edges of the cycle. If those edges didn't have pheromones it starts 
     * the evaporation of them.
     * 
     * @return A {@code LinkedList<Event>} originated by the current {@code Event} with atleast a 
     * new ant move and Evaporation Events if a cycle is reached and new edges are layed with pheromones.
     */
    @Override
    public LinkedList<Event> doEvent(){

        ant.move(nextNode);
        LinkedList<Event> newEvents = new LinkedList<Event>();
        
        if (ant.isHamiltonian()) {

            TSPGraph maze = ant.getMaze(); 
            int[] path = ant.doPath();
            int pathCost = maze.getPathCost(path);
            

            if (pathCost < maze.getBestCost()){
                maze.setBestCycle(path, pathCost);
            }

            for (int i = 0; i < path.length - 1; i++){
                
                if ( !maze.checkEdgeEvaporation(path[i], path[i + 1]))
                    newEvents.push(new EvaporationEvent(getEventTime(), path[i], path[i+1], maze));
            }
            
            maze.layPheromones(path, ant.getGamma() * maze.getW() / pathCost );
            ant.reset();
        }

        newEvents.push(new AntEvent(this));

        mevents++;
        return newEvents;
    }

}

