/*
 Author: Scott Field
 Version: 1.0
 Date: 09/05/2023
 Purpose:
 Rewrite the Circle class in Listing 13.2 to extend GeometricObject and implement the Comparable interface. 
 Override the equals method in the Object class. Two Circle objects are equal if their radii are the same.
 */

public class Circle extends GeometricObject implements Comparable<Double>{
    private double radius;

    public Circle() {
    }

    public Circle(double radius) {
      this.radius = radius;
    }

    public Circle(double radius,
        String color, boolean filled) {
     this.radius = radius;
      setColor(color);
      setFilled(filled);
    }

    /** Return radius */
    public double getRadius() {
      return radius;
    }
    /** Set a new radius */
    public void setRadius(double radius) {
    this.radius = radius;
    }

    /** Return area */
    public double getArea() {
      return radius * radius * Math.PI;
    }

    /** Return diameter */
    public double getDiameter() {
      return 2 * radius;
    }

    /** Return perimeter */
    public double getPerimeter() {
      return 2 * radius * Math.PI;
    }

    /*Implement The Comparable Interface*/
    public int compareTo(Double rightRadius){
        //If the Left Circle Radius is greater than the Right Circle Radius return 1 
        if (this.radius > rightRadius){
            return 1;
        //If the Left Circle Radius is less than the Right Circle Radius return -1
        }else if (this.radius < rightRadius){
            return -1;
        //Otherwise both radius are equal return 0
        }else{
            return 0;
        }
    }

    /*Override The Equals Method In The Object Class*/
    @Override
    public boolean equals (Object myObject){
        if (myObject instanceof Circle){
            return radius == ((Circle) myObject).radius;
        }else{
            return false;
        }
    }

    /** Print the circle info */
    public void printCircle() {
     System.out.println("The circle is created " + getDateCreated() +
       " and the radius is " + radius);
    }

    /* Test Loop */
    public static void main(String[] args){
        Circle lower = new Circle(5);
        Circle equal = new Circle(5);
        Circle higher = new Circle (10);
        System.out.println("Lower Radius Circle Equals Higher Radius Circle Evaluates To: " + lower.equals(higher));
        System.out.println("Equal Radius Circle Equals Equal Radius Circle Evaluates To: " + lower.equals(equal));

        System.out.println("Lower to Higher Circle Comparison: " +  lower.compareTo(higher.getRadius()));
        System.out.println("Higher to Lower Circle Comparison: " +  higher.compareTo(lower.getRadius()));
        System.out.println("Equal to Equal Circle Comparison: " +  lower.compareTo(equal.getRadius()));
    }
}