package tsp;

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

        double tau = 10;
        int nAnts = 5;

        int nestNode = 5;
        double alpha = 1;
        double beta = 1;
        double gamma = 1;
        double delta = .1;
        double rho = 0.01;
        double eta = 1;

        int[][] info = new int[8][3];
        info[0][0] = 1; info[0][1] = 3; info[0][2] = 6;
        info[1][0] = 1; info[1][1] = 5; info[1][2] = 2;
        info[2][0] = 1; info[2][1] = 2; info[2][2] = 3;
        info[3][0] = 1; info[3][1] = 4; info[3][2] = 6;
        info[4][0] = 2; info[4][1] = 3; info[4][2] = 3;
        info[5][0] = 2; info[5][1] = 5; info[5][2] = 5;
        info[6][0] = 2; info[6][1] = 4; info[6][2] = 2;
        info[7][0] = 4; info[7][1] = 5; info[7][2] = 1; 
        
        TSPGraph maze = new TSPGraph(5, info, rho, eta); 
        
        PriorityQueue<Event> PEC = new PriorityQueue<Event>(new The_Comparator());

        Ant[] colony = new Ant[nAnts];
        
        for (int i = 0; i < nAnts; i++) {
            colony[i] = new Ant(nestNode, alpha, beta, gamma, maze);
            PEC.add(new AntEvent(0.0, delta, colony[i]));
        }
    
        LinkedList<Event> events;
        Event currEvent;
        double currTime = 0;
        int observationCounter = 1;
        while (PEC.peek() != null) {
            
            currEvent = PEC.poll();
            currTime = currEvent.getEventTime();
            if ( currTime >= observationCounter * tau / 20) {

                Main.printObservation(observationCounter, currTime, Event.getMEvents(), Event.getEEvents(), Ant.bestPathString());
                observationCounter++;
            }

            events = currEvent.doEvent(); 

            while (events.peek() != null && events.peek().getEventTime() < tau) {
                PEC.add(events.poll());
            }
        }

        Main.printObservation(observationCounter, tau, Event.getMEvents(), Event.getEEvents(), Ant.bestPathString());

    }

    public static void printObservation(int observation, double currTime, int MEvents, int EEvents, String path) {

        System.out.println("Observation number: \t" + observation);
        System.out.println("\t Present instant:              \t" + currTime);
        System.out.println("\t Number of move events:        \t" + MEvents);
        System.out.println("\t Number of evaporation events: \t" + EEvents);
        System.out.println("\t Hamiltonian cycle:            \t" + path + "\n");
    }

}