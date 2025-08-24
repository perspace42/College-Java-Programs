/*
Author: Scott Field
Name: RegularPolygon
Version: 1.0
Purpose: Create a Regular Polygon Class then test is with 3 Regular Polygon Objects using
the no - arg constructor, and then 2 more using the other constructors.
*/

public class RegularPolygon{
    int n;
    double side;
    double x;
    double y;

    //no argument constructor
    public RegularPolygon(){
        this.n = 3; //number of sides
        this.side = 1; //length of the side
        this.x = 0; //x coordinate of polygon center
        this.y = 0; //y coordinate of polygon center
    }

    //constructor with specified sides and length of side centered at (0,0)
    public RegularPolygon(int n, double side){
        this.n = n; //number of sides
        this.side = side; //length of the side
        this.x = 0; //x coordinate of polygon center
        this.y = 0; //y coordinate of polygon cente
    }

    //constructor with specified sides, lenth of side and x and y coordinates
    public RegularPolygon(int n, double side, double x, double y){
        this.n = n;
        this.side = side;
        this.x = x;
        this.y = y;
    }

    //Getter & Setter Methods
    public int getN(){
        return n;
    }

    public void setN(int numSides){
        this.n = numSides;
    }

    double getSide(){
        return side;
    }

    public void setSide(double sideLength){
        this.side = sideLength;
    }

    double getX(){
        return x;
    }

    public void setX(double x){
        this.x = x;
    }

    double getY(){
        return y;
    }

    public void setY(double y){
        this.y = y;
    }

    //get perimeter function
    public double getPerimeter(){
        //Get Perimeter By Multiplying Side Length And Number Of Sides
        return(side * n);
    }
    //get area function
    public double getArea(){
        //divide the polygon into congruent triangles each with base s and height h, outputting the combined area of each one
        return( (n * (side * side)) / (4 * Math.tan((Math.PI/n))) );
    }

    //Print The Perimeter And Area Of A Given Polygon
    public static void printPolygon(RegularPolygon polygon){
        System.out.println("The perimeter of the polygon with: " + polygon.getN() + " sides each with length: " + polygon.getSide() + " is " + polygon.getPerimeter());
        System.out.println("The area of the polygon with: " + polygon.getN() + " sides each with length: " + polygon.getSide() + " is " + polygon.getArea() + "\n");
    }

    //Test The Polygons
    public static void main (String[] args){
        //No Argument Polygons
        RegularPolygon emptyPolygonOne = new RegularPolygon();
        RegularPolygon emptyPolygonTwo = new RegularPolygon();
        RegularPolygon emptyPolygonThree = new RegularPolygon();
        
        //Argument Polygons
        RegularPolygon twoArgPolygon = new RegularPolygon(6,4); //Hexagon Side Length 4
        RegularPolygon threeArgPolygon = new RegularPolygon(10,4,5.6,7.8);

        //Output Perimeter And Area For Each RegularPolygon
        printPolygon(emptyPolygonOne);
        printPolygon(emptyPolygonTwo);
        printPolygon(emptyPolygonThree);
        printPolygon(twoArgPolygon);
        printPolygon(threeArgPolygon);
    }

}

