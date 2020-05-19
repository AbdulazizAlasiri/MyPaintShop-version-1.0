package application;

import java.awt.Color;



public class RightTriangleClass extends Shape {

	private javafx.scene.paint.Color shapeColor;
	private javafx.scene.paint.Color fillColor;
	private XY firstCoordinate;
	private XY secondCoordinate;
	

	public RightTriangleClass(javafx.scene.paint.Color Shapecolor, javafx.scene.paint.Color fillColor, XY firstCoordinate, XY secondCoordinate) {
		super(Shapecolor,  fillColor, firstCoordinate, secondCoordinate);
		this.shapeColor=Shapecolor;
		this.fillColor=fillColor;
		this.firstCoordinate=firstCoordinate;
		this.secondCoordinate=secondCoordinate;
		
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

		return "righttriangle" + " " + fillColor +" "+shapeColor + " " + firstCoordinate
				+" "	+ secondCoordinate +  "\n";
	}
	public javafx.scene.paint.Color getFillColor() {
		return fillColor;
	}


	public void setFillColor(javafx.scene.paint.Color fillColor) {
		this.fillColor=fillColor;
		
	}
}
