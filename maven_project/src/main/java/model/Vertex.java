package model;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class Vertex {

  // Private enum type:
  public enum Type {
    WALL("Wall"), PATH("Path"), START("Start"), END("End"), SHTPATH("Shorter_Path"); 
    
    private String custom;
    
    private Type(String str) {
      this.custom = str;
    }

    public String toString() {
      return this.custom;
    }
  }

  // Vertex Attributes
  private static int counter = 0;
  private final int id;
  private Color color = Color.WHITE;
  private ArrayList<Vertex> neighbors;
  private Type type = Type.PATH;
  private int cost = 1; // Default cost
  private int x, y; // Coordinates in the grid

  public Vertex() {
    this.id = increaseCounter();
    this.neighbors = new ArrayList<>();
  }

  public Vertex(int x, int y) {
    this();
    this.x = x;
    this.y = y;
  }

  private int increaseCounter() {
    int current_id = counter; 
    Vertex.counter+=1;
    return current_id;
  }

  public int getId() {
    return id;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public int getCost() {
    return cost;
  }

  public ArrayList<Vertex> getNeighbors(boolean all) {
    if(all) {
      return new ArrayList<>(this.neighbors);
    }else {
      // Java 16:
      // List<Vertex> l = this.neighbors.stream().filter(n -> n.getType() != Type.WALL).toList();
      // Java 8:
      List<Vertex> l = this.neighbors.stream().filter(n -> n.getType() != Type.WALL).collect(Collectors.toList());
      return new ArrayList<>(l);
    }
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }


  public boolean addNeighbor(Vertex neighbor) {
    return this.neighbors.add(neighbor);
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    switch (type) {
      case END:
        this.setColor(Color.BLUE);
        break;
      case START:
        this.setColor(Color.GREEN);
        break;
      case WALL:
        this.setColor(Color.YELLOW);
        break;
      case SHTPATH:
        this.setColor(Color.ORANGE);
        break;
      default:
        this.setColor(Color.WHITE);
        break;
    }
    this.type = type;
  }

  public String getIcon() {
    switch (this.getType()) {
      case WALL:
        return "X";
      case START:
        return "S";
      case END:
        return "E"; 
      case SHTPATH:
        return "*";
      default:
        return String.valueOf(this.getId());
    }
  }

  public String toString() {
    return String.format("\u001b[31m["+getStrColor(1)+"%s"+getStrColor(0)+"\u001b[31m"+"]"+getStrColor(0), this.getIcon());
  }

  public String displayNeighbors() {
    return String.format("[%s]: %s", this.getId(), this.getNeighbors(true).toString());
  }

  private String getStrColor(int x) {
    if(x == 1) { // Update Vertex Color based on Type
      switch (this.getType()) {
        case WALL:
          return "\u001b[33m"; // Yellow
        case START:
          return "\u001b[32m"; // Green
        case END:
          return "\u001b[34m"; // Blue
        case SHTPATH:
          return "\u001b[36m";
        default:
          return "\u001b[37m"; // White
      }
    }
    if(x == 0) {
      return "\u001b[0m"; // Reset Color
    }

    return null;
  }
  
  public boolean isWall() {
    return type == Type.WALL;
  }

  public boolean isStart() {
    return type == Type.START;
  }

  public boolean isEnd() {
    return type == Type.END;
  }

  public boolean isPath() {
    return type == Type.PATH;
  }

  public boolean isShorterPath() {
    return type == Type.SHTPATH;
  }

}
