package tsp;

// import java.util.Arrays;
import java.util.LinkedList;

public class AntEvent extends Event {

    private static int mevents = 0;
    private final double delta;
    private final int nextNode;
    public final Ant ant;

    AntEvent(double currTime, double delta, Ant ant){
        
        this.ant = ant;
        this.delta = delta;
        this.nextNode = ant.getNextNode();
        
        setEventTime( currTime + expRandom(delta * ant.getNextNodeWeight(nextNode)) );
    }

    AntEvent(AntEvent antEvent){

        this.ant = antEvent.getAnt();
        this.delta = antEvent.getDelta();
        this.nextNode = ant.getNextNode();
        
        setEventTime( antEvent.getEventTime() + expRandom(delta * ant.getNextNodeWeight(nextNode)) );
    }

    public Ant getAnt() {
        return ant;
    }

    public double getDelta() {
        return delta;
    }

    @Override
    public void incEvent(){
        mevents++;
    }

    public static int getEventNo() {
        return mevents;
    }

    @Override
    public LinkedList<Event> doEvent(){

        ant.move(nextNode);
        incEvent();

        LinkedList<Event> newEvents = new LinkedList<Event>();
        
        if (ant.isHamiltonian()) {

            TSPGraph maze = ant.getMaze(); 
            int[] path = ant.doPath();
            int pathCost = maze.getPathCost(path);

            if (pathCost < Ant.getBestCost()){
                Ant.setBestCycle(path);
                Ant.setBestCost(pathCost);
            }

            maze.layPheromones(path, ant.getGamma() * maze.getW() / pathCost );

            for (int i = 0; i < path.length - 1; i++){

                if ( maze.getEdgePheromones(i, i + 1) == 0)
                    newEvents.push(new EvaporationEvent(getEventTime(), path[i], path[i+1], maze));
            }
            
            ant.reset();
        }

        newEvents.push(new AntEvent(this));
        return newEvents;
    }

}

