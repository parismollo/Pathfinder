package model;

import model.Vertex.Type;

public class Graph {
  static final int MAX_ROWS = 10;
  static final int MAX_COLS = 10;

  private final int rows, cols;
  private Vertex[][] vertices;

  public Graph(int rows, int cols) {
    if(rows > 0 && rows <= Graph.MAX_ROWS && cols > 0 && cols <= Graph.MAX_COLS) {
      this.rows = rows;
      this.cols = cols;
      createVertices();
    }
    else {
      throw new IllegalArgumentException("Rows or/and Cols input not valid.\n (0<rows,cols<=999)");
    }

  }


  private void createVertices() {
    // BUG: bug - not all neighbors work.
    // 1. Create 2D array
    this.vertices = new Vertex[this.getNbRows()][this.getNbCols()];
    for(int i = 0; i < this.getNbRows(); i++) {
      for(int j = 0; j < this.getNbCols(); j++) {
        vertices[i][j] = new Vertex();
      }
    }
    
    // 2. Update neighbors
    for (int i = 0; i < this.getNbRows(); i++) {
      for(int j = 0; j< this.getNbCols(); j++) {
        // TODO: Simplify code above if possible
        // 2.1 check left, right, top, bottom
        
        // left:
        int left_i = i;
        int left_j = j - 1;
        if(checkCoord(left_i, left_j)) {
          // Add neighbor:
          vertices[i][j].addNeighbor(vertices[left_i][left_j]);
        }
        
        // right:
        int right_i = i;
        int right_j = j + 1;
        if(checkCoord(right_i, right_j)) {
          // Add neighbor:
          vertices[i][j].addNeighbor(vertices[right_i][right_j]);
        };
        
        // top:
        int top_i = i - 1;
        int top_j = j;
        if(checkCoord(top_i, top_j)) {
          // Add neighbor:
          vertices[i][j].addNeighbor(vertices[top_i][top_j]);
        }
        
        // bottom
        int bottom_i = i + 1;
        int bottom_j = j;
        if(checkCoord(bottom_i, bottom_j)){
          // Add neighbor:
          vertices[i][j].addNeighbor(vertices[bottom_i][bottom_j]);
        }
      }
    }
  }

  private boolean checkCoord(int i, int j) {
    return (i >=0 && i < this.getNbRows() && j >= 0 && j < this.getNbCols());
  }

  public Vertex getVertex(int i, int j) {
    if(checkCoord(i, j))
      return this.vertices[i][j];
    return null;
  }

  public int getNbRows() {
    return this.rows;
  }

  public int getNbCols() {
    return this.cols;
  }

  public Vertex[][] getVertices() {
    return vertices;
  }

  public String showNeighbors() {
    String s = "";
    for (Vertex[] row : this.vertices) {
      for (Vertex vertex : row) {
        s+=vertex.displayNeighbors()+"\n";
      }
    }
    return s;
  }

  public String getSpaces(Vertex v) {
    String s = "";
    int numberOfDigits = v.getIcon().length();
    switch (numberOfDigits) {
      case 1:
        s += "  "; // 2 Spaces
        break;
      case 2:
        s += " "; // 1 Space
        break;
      case 3:
        s+= ""; // no space
    }
    return s;
  }

  public String toString() {
    String s = "";
    for(int i = 0; i < this.getNbRows(); i++) {
      for(int j = 0; j < this.getNbCols(); j++) {
        s+=this.getVertices()[i][j]+getSpaces(this.getVertices()[i][j]);
      }
      s+="\n";
    }
    return s;
    
  }

  public static void main(String[] args) {
    // 1. Create Graph
    Graph graph = new Graph(4, 4);
    
    // 2. Test features
    graph.getVertex(0, 0).setType(Type.START);
    graph.getVertex(2, 2).setType(Type.END);
    graph.getVertex(1, 1).setType(Type.WALL);
    
    // 3. Display Vertices
    System.out.println(graph);
    System.out.println(graph.showNeighbors());
  }
}
