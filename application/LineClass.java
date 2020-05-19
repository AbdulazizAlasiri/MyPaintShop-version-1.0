package application;

import java.awt.Color;

public class LineClass extends Shape {

	private javafx.scene.paint.Color shapeColor;
	private javafx.scene.paint.Color fillColor;//if null no fill
	private XY firstCoordinate;
	private XY secondCoordinate;

	public LineClass(javafx.scene.paint.Color Shapecolor, javafx.scene.paint.Color fillColor,XY firstCoordinate, XY secondCoordinate) {
		super(Shapecolor,fillColor, firstCoordinate, secondCoordinate);
		this.shapeColor=Shapecolor;
		this.fillColor=fillColor;
		this.firstCoordinate=firstCoordinate;
		this.secondCoordinate=secondCoordinate;
	}

	public int getLength() {
		return (int) Math.sqrt(((secondCoordinate.getX() - firstCoordinate.getX())
				* (secondCoordinate.getX() - firstCoordinate.getX()))
				+ ((secondCoordinate.getY() - firstCoordinate.getY())
						* (secondCoordinate.getY() - firstCoordinate.getY())));
	}

	

	
	public javafx.scene.paint.Color getShapecolor() {

		return shapeColor;
	}

	public void setShapecolor(javafx.scene.paint.Color shapecolor) {
		this.shapeColor = shapecolor;

	}

	public XY getFirstCoordinate() {

		return firstCoordinate;
	}

	public void setFirstCoordinate(XY firstCoordinate) {
		this.firstCoordinate = firstCoordinate;

	}

	public XY getSecondCoordinate() {

		return secondCoordinate;
	}

	public void setSecondCoordinate(XY secondCoordinate) {
		this.secondCoordinate = secondCoordinate;

	}

	public String toString() {

		return "line"  + " "+fillColor+" " +shapeColor  +" " + firstCoordinate+" "//hhhhhhhhhh
				+ secondCoordinate + "\n";
	}
	

	public javafx.scene.paint.Color getFillColor() {
		return fillColor;
	}


	public void setFillColor(javafx.scene.paint.Color fillColor) {
		this.fillColor=fillColor;
		
	}
}
