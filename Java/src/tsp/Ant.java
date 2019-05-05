package tsp;

import graph.*;
import java.util.Arrays;
import java.util.Random;

public class Ant {

  private static Random generator = new Random();

  private final int nestNode;
  private final double alpha;
  private final double beta;
  private final TSPGraph maze;

  private int currNode;
  private int nodeCount = 1;
  private int[] visited;

  public double[][] pheromones;

  Ant(int nest, double a, double b, TSPGraph m){

    int nNodes = m.getSize();
    pheromones = new double[nNodes][nNodes];

    beta = b;
    alpha = a;
    maze = m;
    
    nestNode = nest;
    currNode = nest;

    visited = new int[nNodes + 1];
    Arrays.fill(visited, -1);
    visited[0] = nest; 
  }
  
  public boolean isVisited(int node) {

    /* Force nestNode after final
    if( nodeCount == visited.length - 1 && node == nestNode)
      return false;
    */
      
    if( (visited[node] != -1 && visited[node] < nodeCount ) || node == nestNode)
      return true;
    
    return false;
  }

  public int getNextNode() {

    int nextNode = -1;
    Edge[] edgeList = maze.getAllEdges(currNode);
    double[] probNode = new double[edgeList.length];

    for (int i = 0; i < edgeList.length; i ++) {

      int fNodeId = edgeList[i].getFinishVertex().getId();
      if( !isVisited(fNodeId) )
        probNode[i] = (alpha + pheromones[currNode - 1][fNodeId - 1])/(beta + edgeList[i].getWeight());
    }

    double sum = 0;
    for (double i : probNode) sum += i;

    for (int i = 0; i < probNode.length; i++){
      probNode[i] = sum != 0 ? probNode[i] / sum : 1.0 / probNode.length;
    }

    sum = 0;
    double select = generator.nextDouble();
    for(int i = 0; i < probNode.length; i++){
      
      sum += probNode[i];
      if(sum > select){
        nextNode = edgeList[i].getFinishVertex().getId();
        break;
      }
    }

    return nextNode;
  }

  public double getNextNodeWeigth(int nextNode) {

    double weigth = 1;
    Edge[] edgeList = maze.getAllEdges(currNode);
    for(Edge edge : edgeList){
      if(edge.getFinishVertex().getId() == nextNode){
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
    for(int i = 1; i < visited.length; i++){

      if(visited[i] == -1 || visited[i] > nodeCount)
        continue;

      path[visited[i]-1] = i;
    }

    return path;
  }

  public boolean isHamiltonian() {

    if(nodeCount == visited.length && currNode == nestNode)
      return true;

    return false;
  }

  public void cleanVisited(){

    for(int i = 1; i < visited.length; i++){
      if(i != currNode && visited[i] >= nodeCount)
        visited[i] = -1;
    }
  }

  public void move(int nextNode){ 

    if(nextNode == nestNode && nodeCount < visited.length - 1){
      nodeCount = 1;
      cleanVisited();
      return;
    }
    
    if(visited[nextNode] > nodeCount || visited[nextNode] == -1){

      visited[nextNode] = ++nodeCount;
      currNode = nextNode; 

    } else {

      nodeCount = visited[nextNode];
      currNode = nextNode;
      cleanVisited();
    }

  }
    
  public int getNest(){
    return nestNode;
  }
  
  public int getNode(){
    return currNode;
  }

  public int[] getVisited() {
    return visited;
  }

  @Override
  public String toString() {

    String str = "visited nodes:" + Arrays.toString(visited) + "\n";
    int[] path = doPath();
    str += "path: " + path[0];
    for(int i = 1; i < path.length && path[i] != -1; i++){
      str += " → " + path[i];
    }

    str += "\n Counter: " + nodeCount + "\n";
    return str;
  }
} 