package tsp;

import graph.*;
import java.util.Arrays;

public class Ant {

  private final int nestNode;
  private final double alpha;
  private final double beta;
  private final Graph maze;

  private int currNode;
  private int nodeCount;
  private int[] visited;

  Ant(int nest, double a, double b, int nNodes, Graph m){

    beta = b;
    alpha = a;

    maze = m;
    nestNode = nest;
    currNode = nest;

    visited = new int[nNodes];
    Arrays.fill(visited, -1);
    visited[nest] = 0;
    nodeCount = 1;
  }

  public int[] doPath() {
    
    int[] path = new int[visited.length];
    Arrays.fill(path, -1);
    for(int i = 0; i < visited.length; i++){

      if(visited[i] == -1 || visited[i] > nodeCount)
        continue;

      path[visited[i]] = i;
    }

    return path;
  }

  public void move(int nextNode){ 

    if(visited[nextNode] > nodeCount || visited[nextNode] == -1){

      visited[nextNode] = nodeCount++;
      currNode = nextNode; 

    } else {

      nodeCount = visited[nextNode];
      currNode = nextNode;
    }

  }
    
  public int getNest(){
    return nestNode;
  }
  
  public int getNode(){
    return currNode;
  }

  @Override
  public String toString() {

    String str = "visited nodes:" + Arrays.toString(visited) + "\n";
    int[] path = doPath();
    str += "path: " + path[0];
    for(int i = 1; i < path.length && path[i] != -1; i++){
      str += " → " + path[i];
    }

    str += "\n Counter: " + nodeCount;
    return str;
  }
} 