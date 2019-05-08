package tsp;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
import xml.*;

class The_Comparator implements Comparator<Event> {
    public int compare(Event event1, Event event2) {
        return Double.compare(event1.getEventTime(), event2.getEventTime());    
    }
}

public class Main {

    public static void main(String[] args) {

        //InitialValues test = new InitialValues();
        Xml.validateFile(args[0]);
        Xml.readValues(args[0]);

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

    public static void printObservation(int observation, double currTime, int MEvents, int EEvents, String path) {

        System.out.println("Observation number: \t" + observation);
        System.out.println("\t Present instant:              \t" + currTime);
        System.out.println("\t Number of move events:        \t" + MEvents);
        System.out.println("\t Number of evaporation events: \t" + EEvents);
        System.out.println("\t Hamiltonian cycle:            \t" + path + "\n");
    }

}