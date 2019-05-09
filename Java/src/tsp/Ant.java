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
     * @param node to be checked
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
     * Creats the path transversed by the ant so far and 
     * returns it.
     * 
     * @return {@code path} of the so far.
     */
    public int[] doPath() {
        
        int[] path = new int[visited.length];
        Arrays.fill(path, -1);
        path[0] = visited[0];
        for (int i = 1; i < visited.length; i++){

            if (visited[i] != -1)
                path[visited[i]] = i;

        }

        return path;
    }

    /**
     * Checks if the ant has reached a <i>Hamilton Cycle</i>.
     * 
     * @return {@code true} if current path is a <i>Hamiltonian Cycle</i>, 
     * {@code false} if not.
     */
    public boolean isHamiltonian() {

        if (nodeCount == visited.length && currNode == nestNode)
            return true;

        return false;
    }

    private void cleanVisited(){

        for (int i = 1; i < visited.length; i++){
            if (i != currNode && visited[i] >= nodeCount)
                visited[i] = -1;
        }
    }

    /**
     * Moves ant for next node, updating it's path and dleting from it's path nodes
     * that will not create a cycle, e.g, if current path is 1→2→3 and next node is
     * 2 it will move to node 2 and update path to 1→2.
     * 
     * @param nextNode node that the ant will go next.
     */
    public void move(int nextNode){ 

        if (nextNode == nestNode && nodeCount < visited.length - 1){
            reset();
            return;
        }
        
        if (visited[nextNode] == -1){

            visited[nextNode] = nodeCount++;
            currNode = nextNode;

        } else {

            nodeCount = visited[nextNode];
            currNode = nextNode;
            cleanVisited();
        }

    }

    /**
     * Resets the ant to nest node and it's path.
     */
    public void reset() {

        Arrays.fill(visited, -1);
        visited[0] = nestNode;
        currNode = nestNode;
        nodeCount = 1;
    }
    
    /**
     * Returns ant's nest node.
     * 
     * @return {@code nestNode} id
     */
    public int getNest(){
        return nestNode;
    }
  
    /**
     * Returns ant's current node.
     * 
     * @return {@code currNode} id
     */
    public int getNode(){
        return currNode;
    }

    /**
     * Returns the {@code TSPGraph} that the ant is transversing.
     * 
     * @return {@code maze} 
     */
    public TSPGraph getMaze() {
        return maze;
    }

    /**
     * Returns the array that saves the path and visited nodes 
     * by the ant. Array[0] is the nest node and Array[node] is 
     * {@code -1} if node is not visited and a {@code int > 1} 
     * for visited in which {@code int} is the order of visit.
     * 
     * @return {@code visited} array which saves the path and visitied nodes.
     */
    public int[] getVisited() {
        return visited;
    }

    /**
     * Returns {@code gamma} of the ant. Gamma is the ratio of how many 
     * pheromones are left behind by the ant after fininshing a <i>Hamiltonian 
     * Cycle</i>.
     * 
     * @return {@code gamma}
     */
    public double getGamma() {
        return gamma;
    }

    @Override
    public String toString() {

        int[] path = doPath();
        String str = "path: {" + path[0];

        for (int i = 1; i < path.length - 1 && path[i] != -1; i++){
            str += ", " + path[i];
        }

        str += "} Counter: " + nodeCount;
        return str;
    }

} 