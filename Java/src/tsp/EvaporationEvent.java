package tsp;

// import java.util.Arrays;
import java.util.LinkedList;

public class EvaporationEvent extends Event {

    private static int eevents = 0;
    private final int node1;
    private final int node2;
    private final TSPGraph maze;

    EvaporationEvent(double currTime, int node1, int node2, TSPGraph maze){
    
        this.maze = maze;
        this.node1 = node1;
        this.node2 = node2;

        setEventTime( currTime + expRandom(maze.getEta()) );
    }

    EvaporationEvent(EvaporationEvent oldEvent) {

        this.maze = oldEvent.getMaze();
        this.node1 = oldEvent.getNodeStart();
        this.node2 = oldEvent.getNodeFinish();

        setEventTime(this.getEventTime() + expRandom(maze.getEta()) );
    }

    @Override
    public LinkedList<Event> doEvent() {

        incEvent();

        LinkedList<Event> nextEvaporation = new LinkedList<Event>();

        if (maze.decrementEdgePheromones(node1, node2))
            nextEvaporation.push(new EvaporationEvent(this));

        return nextEvaporation;
    }

    @Override
    public void incEvent() {
        eevents++;
    }

    public static int getEventNo() {
        return eevents;
    }

    public TSPGraph getMaze() {
        return maze;
    }

    public int getNodeStart() {
        return node1;
    }

    public int getNodeFinish() {
        return node2;
    }

}

