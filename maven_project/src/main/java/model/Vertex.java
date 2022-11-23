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
    this.type = type;
  }

  public String toString() {
    return String.format("\u001b[31m[\u001b[0m%d\u001b[31m]\u001b[0m", this.getId());
  }

  public String displayNeighbors() {
    return String.format("[%d]: %s", this.getId(), this.getNeighbors().toString());
  }
}
