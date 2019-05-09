package tsp;

import java.util.LinkedList;

/**
 * ObservationEvent is a {@code Event} that prints to the terminal the result of
 * observations of the system, showing the current time of the simulation, the
 * number of {@code AntEvents} and {@code EvaporationEvents} simulated so far
 * and the shortest <i>Hamiltonian Cycle</i> found so far by every {@code Ant}.
 * <p>
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see Event
 */
public class ObservationEvent extends Event {

    private static int observation = 1;
    private final double tau;
    private final TSPGraph maze;

    /**
     * Constructor of a {@code ObservationEvent} which receives the {@code tau}
     * which is the final time of the simulation. Creating a event to happen at 
     * a new observation time. <p>
     * 
     * @param tau Final time 
     */
    ObservationEvent(double tau, TSPGraph maze) {

        this.tau = tau;
        this.maze = maze;
        setEventTime(observation * tau / 20);
    }

    @Override
    /**
     * Prints to the terminal the result of observations of the system, showing the
     * current time of the simulation, the number of {@code AntEvents} and
     * {@code EvaporationEvents} simulated so far and the shortest
     * <i>Hamiltonian Cycle</i> found so far by every {@code Ant}.
     * 
     * @return A {@code LinkedList<Event>} originated by the current {@code Event}
     *         with a new ObservationEvent atfer {@code tau}/20 time.
     */
    public LinkedList<Event> doEvent() {
        
        System.out.println("Observation number: \t" + observation);
        System.out.println("\t Present instant:              \t" + getEventTime());
        System.out.println("\t Number of move events:        \t" + AntEvent.getEventNo());
        System.out.println("\t Number of evaporation events: \t" + EvaporationEvent.getEventNo());
        System.out.println("\t Hamiltonian cycle:            \t" + maze.bestPathString() + "\n");
        
        LinkedList<Event> nextObservation = new LinkedList<Event>();
        observation++;

        nextObservation.push(new ObservationEvent(tau, maze));
        return nextObservation;
    }

}