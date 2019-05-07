package tsp;

import java.util.LinkedList;

/**
 * EvaporationEvent is a {@code Event} that evaporates pheromones from a edge 
 * of a {@code TSPGraph} the time is based on an exponential distribuiton 
 * with mean value proportional to the {@code eta} of the {@code TSPGRaph} <p>
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see Event
 */
public class EvaporationEvent extends Event {

    private static int eevents = 0;
    private final int node1;
    private final int node2;
    private final TSPGraph maze;

    /**
     * Constructor of a {@code EvaporationEvent} which receives the {@code currTime}
     * of events, {@code maze} of the edges to be evaporated and the ids of the
     * edge's nodes.
     * <p>
     * 
     * The {@code eventTime} is defined with {@code eta} from the {@code TSPGraph}
     * which is proportional to the average of the exponential distribution of the
     * time between the {@code currTime} and the evaporation event.
     * <p>
     * 
     * 
     * @param currTime Current time of simulation
     * @param node1 origin of the edge to be evaporated
     * @param node2 finish node of the edge to be evaporated
     * @param maze {@code TSPGraph} of the edge to be evaporated
     * @see TSPGraph
     */
    EvaporationEvent(double currTime, int node1, int node2, TSPGraph maze){
    
        this.maze = maze;
        this.node1 = node1;
        this.node2 = node2;

        setEventTime( currTime + expRandom(maze.getEta()) );
    }

    /**
     * Constructor of a {@code EvaporationEvent} to be used by another 
     * {@code EvaporationEvent} to construct a event with the same proprieties 
     * of itself that will happen after the old one.
     * 
     * 
     * @param oldEvent event that originates the new one
     * 
     * @see EvaporationEvent#EvaporationEvent(double, int, int, TSPGraph)
     */
    EvaporationEvent(EvaporationEvent oldEvent) {

        this.maze = oldEvent.getMaze();
        this.node1 = oldEvent.getNodeStart();
        this.node2 = oldEvent.getNodeFinish();

        setEventTime(this.getEventTime() + expRandom(maze.getEta()) );
    }

    @Override
    /**
     * Decreases the pheromone level of the edge and creates a new event if the edge still
     * has pheromones.
     * 
     * @return A {@code LinkedList<Event>} originated by the current {@code Event}
     *         with a new Evaporation Events if the edge still has pheromones.
     */
    public LinkedList<Event> doEvent() {

        LinkedList<Event> nextEvaporation = new LinkedList<Event>();
        
        if (maze.decrementEdgePheromones(node1, node2))
            nextEvaporation.push(new EvaporationEvent(this));
        
        eevents++;
        return nextEvaporation;
    }

    /**
     * Returns the number of evaporation events realized so far by every ant
     * 
     * @return total number of evaporation events
     */
    public static int getEventNo() {
        return eevents;
    }

    /**
     * Returns the TSPGraph of the edge to be evaporated
     * @return TSPGraph that has the edge to evaporate
     */
    public TSPGraph getMaze() {
        return maze;
    }

    /**
     * Returns origin node of the edge to be evaporated
     * @return origin node id
     */
    public int getNodeStart() {
        return node1;
    }

    /**
     * Returns finish node of the edge to be evaporated
     * 
     * @return finish node id
     */
    public int getNodeFinish() {
        return node2;
    }

}

