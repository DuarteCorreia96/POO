package tsp;

import graph.*;
import java.util.Arrays;
import java.util.Random;

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
     * @since 1.0
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
  
    public boolean isVisited(int node) {

        /* Force nestNode after final
        if( nodeCount == visited.length - 1 && node == nestNode)
        return false;
        */
        
        if ( visited[node] != -1 || node == nestNode)
            return true;
        
        return false;
    }

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

    public double getNextNodeWeigth(int nextNode) {

        double weigth = 1;
        Edge[] edgeList = maze.getAllEdges(currNode);
        for (Edge edge : edgeList){
            if (edge.getFinishVertex().getId() == nextNode){
                weigth = edge.getWeight();
                break;
            }
        }

        return weigth;
    }

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