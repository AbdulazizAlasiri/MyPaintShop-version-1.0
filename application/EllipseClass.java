package application;

import java.awt.Color;

public class EllipseClass extends Shape {

	private javafx.scene.paint.Color ShapeColor;
	private javafx.scene.paint.Color fillColor;//if null no fill
	private XY firstCoordinate;
	private XY secondCoordinate;

	public EllipseClass(javafx.scene.paint.Color Shapecolor,javafx.scene.paint.Color fillColor,  XY firstCoordinate, XY secondCoordinate) {
		super(Shapecolor,  fillColor, firstCoordinate, secondCoordinate);
		this.ShapeColor=Shapecolor;
		this.fillColor=fillColor;
		this.firstCoordinate=firstCoordinate;
		this.secondCoordinate=secondCoordinate;
		
	}

	public double getMinor() {

		return secondCoordinate.getY() - firstCoordinate.getY();
	}

	public double getMajor() {

		return secondCoordinate.getX() - firstCoordinate.getX();
	}

	// in case of circle
	public double getRadius() {
		if (firstCoordinate == secondCoordinate)
			return secondCoordinate.getX() - firstCoordinate.getX();
		

		return -1;
	}

	public javafx.scene.paint.Color getShapecolor() {

		return ShapeColor;
	}

	public void setShapecolor(javafx.scene.paint.Color shapecolor) {
		this.ShapeColor = shapecolor;

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

		return  "circle" + " " + fillColor + " " +  ShapeColor+ " " + firstCoordinate//hhhhhhhh
				+" "+ secondCoordinate + "\n";
	}


	public javafx.scene.paint.Color getFillColor() {
		return fillColor;
	}


	public void setFillColor(javafx.scene.paint.Color fillColor) {
		this.fillColor=fillColor;
		
	}


}
