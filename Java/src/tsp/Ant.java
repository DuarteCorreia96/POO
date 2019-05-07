package tsp;

import graph.*;
import java.util.Arrays;
import java.util.Random;

/**
 * A {@code Ant} is used to transverse a {@code TSPGraph} in order to find a
 * solution to the <i>Traveling Salesman Problem</i>, that is, the
 * <i>Hamiltonian Cycle</i> wth less cost.
 * <p>
 * 
 * Each Ant has a reference to the {@code TSPGraph} that will transverse, a
 * {@code nestNode} which is the end and start of every <i>Hamiltonian Cycle</i>
 * and a {@code visited} array which saves the current current path of the
 * {@code Ant}. It also saves the constants {@code alpha}, {@code beta} and
 * {@code gamma} that define how the ant chooses the next node and how much
 * <i>pheromones</i> it leaves on her trail after finishing a <i>Hamiltonian
 * Cycle</i>.
 * <p>
 * 
 * 
 * In summary {@code Ant} provides methods for: <p>
 * <ul>
 * <li> Move the ant to the next node.
 * <li> Check if a node has alrady been visited.
 * <li> Get the ant choice for next node.
 * <li> Get the next node weight.
 * <li> Get the current path of the ant.
 * <li> Check if the current path is already a <i>Hamiltonian Cycle</i>
 * <li> Get the current best clyce and weight.
 * </ul> 
 * 
 * The class has a static variable that saves the best cycle found so far and 
 * its cost. <p>
 * 
 * @author Duarte Correia
 * @author Joao Pinto
 * @author Jose Bastos
 * @see TSPGraph
 * 
 */ 
public class Ant {

    private static Random generator = new Random();
    private static int[] bestCycle;
    private static int bestCycleCost = Integer.MAX_VALUE;

    private final int nestNode;
    private final double alpha;
    private final double beta;
    private final double gamma;
    private final TSPGraph maze;

    private int currNode;
    private int nodeCount = 1;
    private int[] visited;

    /**
     * Creates a {@code Ant} with the parameters given.
     * Also inicializes {@code visited} and {@code nodes}
     * in order to be prepared to transverse the maze 
     * 
     * @param nest  The nest node of the colony
     * @param alpha alpha value of ant
     * @param beta  beta value of ant
     * @param gamma proportion of pheromones that the ant leaves
     * @param maze  graph the ant will transverse
     * 
    */
    Ant(int nest, double alpha, double beta, double gamma, TSPGraph maze){

        this.alpha = alpha;
        this.gamma = gamma;
        this.beta = beta;
        this.maze = maze;
        
        this.nestNode = nest;
        this.currNode = nest;
        
        int nNodes = maze.getSize();
        visited = new int[nNodes + 1];
        Arrays.fill(visited, -1);
        visited[0] = nest; 
    }
  
    /**
     * Checks if a node has been visited by the ant. <p>
     * 
     * @param node
     * @return {@code true} if the ant has visited the node, {@code false} if not.
     */
    public boolean isVisited(int node) {

        /* Force nestNode after final
        if( nodeCount == visited.length - 1 && node == nestNode)
        return false;
        */
        
        if ( visited[node] != -1 || node == nestNode)
            return true;
        
        return false;
    }

    /**
     * Chooses randomly ant's next node based on variables {@code alpha}, {@code beta},
     * the weigths and the <i>pheromones level</i> of each {@code Edge} of the current
     * node and the visited status of next node possibilities.
     * 
     * @return {@code nextNode} node choosen by the ant to be next
     */
    public int getNextNode() {

        int nextNode = -1;
        Edge[] edgeList = maze.getAllEdges(currNode);
        double[] probNode = new double[edgeList.length];

        for (int i = 0; i < edgeList.length; i ++) {

            int fNodeId = edgeList[i].getFinishVertex().getId();
            if ( !isVisited(fNodeId) )
                probNode[i] = (alpha + maze.getEdgePheromones(currNode, fNodeId))/(beta + edgeList[i].getWeight());
        }

        double sum = 0;
        for (double i : probNode) sum += i;

        for (int i = 0; i < probNode.length; i++){
            probNode[i] = sum != 0 ? probNode[i] / sum : 1.0 / probNode.length;
        }

        sum = 0;
        double select = generator.nextDouble();
        for (int i = 0; i < probNode.length; i++){
        
            sum += probNode[i];
            if (sum > select){
                nextNode = edgeList[i].getFinishVertex().getId();
                break;
            }
        }

        return nextNode;
    }

    /**
     * Gets {@code nextNode} weight when going from ant's current node.
     * 
     * @param nextNode node the ant will move to.
     * @return {@code weight} the weight of the edge between current node 
     * and next.
     * 
     * @see Link#getWeight()
     */
    public double getNextNodeWeight(int nextNode) {

        double weight = 1;
        Edge[] edgeList = maze.getAllEdges(currNode);
        for (Edge edge : edgeList){
            if (edge.getFinishVertex().getId() == nextNode){
                weight = edge.getWeight();
                break;
            }
        }

        return weight;
    }

    /**
     * 
     * @return
     */
    public int[] doPath() {
        
        int[] path = new int[visited.length];
        Arrays.fill(path, -1);
        path[0] = visited[0];
        for (int i = 1; i < visited.length; i++){

            if (visited[i] == -1 || visited[i] > nodeCount)
                continue;

            path[visited[i]-1] = i;
        }

        return path;
    }

    public boolean isHamiltonian() {

        if (nodeCount == visited.length && currNode == nestNode)
            return true;

        return false;
    }

    public void cleanVisited(){

        for (int i = 1; i < visited.length; i++){
            if (i != currNode && visited[i] >= nodeCount)
                visited[i] = -1;
        }
    }

    public void move(int nextNode){ 

        if (nextNode == nestNode && nodeCount < visited.length - 1){
            reset();
            return;
        }
        
        if (visited[nextNode] >= nodeCount || visited[nextNode] == -1){

            visited[nextNode] = ++nodeCount;
            currNode = nextNode; 

        } else {

            nodeCount = visited[nextNode];
            currNode = nextNode;
            cleanVisited();
        }

    }

    public void reset() {

        Arrays.fill(visited, -1);
        visited[0] = nestNode;
        nodeCount = 1;
    }
    
    public int getNest(){
        return nestNode;
    }
  
    public int getNode(){
        return currNode;
    }

    public TSPGraph getMaze() {
        return maze;
    }

    public int[] getVisited() {
        return visited;
    }

    public double getGamma() {
        return gamma;
    }

    public static int getBestCost() {
        return bestCycleCost;
    }

    public static void setBestCost(int cost) {
        bestCycleCost = cost;
    }

    public static void setBestCycle(int[] path) {
        bestCycle = path;
    }

    public static int[] getBestCycle() {
        return bestCycle;
    }

    @Override
    public String toString() {

        int[] path = doPath();
        String str = "path: {" + path[0];

        for (int i = 1; i < path.length && path[i] != -1; i++){
            str += ", " + path[i];
        }

        str += "} Counter: " + nodeCount;
        return str;
    }

    public static String bestPathString() {

        int[] path = bestCycle;

        if(bestCycle == null)
            return "";

        String str = "{" + path[0];

        for (int i = 1; i < path.length && path[i] != -1; i++) {
            str += "," + path[i];
        }

        str += "}";
        return str;
    }
} 