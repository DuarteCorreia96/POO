package tsp;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
import xml.*;

/**
 * Used to give the priority order on the PEC (implemented as a
 * {@code PriorityQueue}) based on the times of event.
 * 
 * @see Comparator
 * @see PriorityQueue
 * @see Double#compare(double, double)
 */
class The_Comparator implements Comparator<Event> {
    public int compare(Event event1, Event event2) {
        return Double.compare(event1.getEventTime(), event2.getEventTime());    
    }
}

/**
 * The main method tries to reach a solution from a TSP problem specified in
 * {@code args[0]}. It implements a discrete stochastic simulation, that is,
 * based on a pending event container. PEC is implemented as
 * {@code PriorityQueue} that sorts events based on their time of execution.
 * Events are only added to the PEC if they are to be executed before the final
 * time of simulation.
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * 
 * @see Ant
 * @see AntEvent
 * @see ObservationEvent
 * @see TSPGraph
 */
public class Main {

    public static void main(String[] args) {

        if( !Xml.validateFile(args[0]) || !Xml.readValues(args[0]))
            System.exit(1);

        double tau = InitialValues.getFinalinst();
        int nAnts  = InitialValues.getAntcolsize();
        int nNodes = InitialValues.getNbnodes();

        int nestNode = InitialValues.getNestnode();
        double alpha = InitialValues.getAlpha();
        double beta  = InitialValues.getBeta();
        double gamma = InitialValues.getPlevel();
        double delta = InitialValues.getDelta();
        double rho   = InitialValues.getRho();
        double eta   = InitialValues.getEta();

        ArrayList<int[]> info = InitialValues.getGraphInfo();

        TSPGraph maze = new TSPGraph(nNodes , info, rho, eta); 
        
        PriorityQueue<Event> PEC = new PriorityQueue<Event>(new The_Comparator());

        Ant[] colony = new Ant[nAnts];
        
        for (int i = 0; i < nAnts; i++) {
            colony[i] = new Ant(nestNode, alpha, beta, gamma, maze);
            PEC.add(new AntEvent(0.0, delta, colony[i]));
        }
        
        PEC.add(new ObservationEvent(tau));
    
        LinkedList<Event> events; 
        while (PEC.peek() != null) {
            
            events = PEC.poll().doEvent(); 

            while (events.peek() != null && events.peek().getEventTime() <= tau) {
                PEC.add(events.poll());
            }
        }
    }
}