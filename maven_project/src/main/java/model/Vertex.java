package model;
import java.awt.Color;
import java.util.ArrayList;



public class Vertex {

  // Private enum type:
  public enum Type {
    WALL("Wall"), PATH("Path"), START("Start"), END("End");
    
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

  public Vertex() {
    this.id = increaseCounter();
    this.neighbors = new ArrayList<>();
  }

  private int increaseCounter() {
    return counter+=1;
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

  public ArrayList<Vertex> getNeighbors() {
    return new ArrayList<>(this.neighbors);
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
      default:
        return String.valueOf(this.getId());
    }
  }

  public String toString() {
    return String.format("\u001b[31m["+getStrColor(1)+"%s"+getStrColor(0)+"\u001b[31m"+"]"+getStrColor(0), this.getIcon());
  }

  public String displayNeighbors() {
    return String.format("[%s]: %s", this.getId(), this.getNeighbors().toString());
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
        default:
          return "\u001b[37m"; // White
      }
    }
    if(x == 0) {
      return "\u001b[0m"; // Reset Color
    }

    return null;
  }
}
