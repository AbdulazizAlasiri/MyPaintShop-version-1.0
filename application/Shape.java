package application;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Shape {
	public javafx.scene.paint.Color shapeColor;
	public javafx.scene.paint.Color fillColor;//if null no fill
	public XY firstCoordinate;
	public XY secondCoordinate;
	ArrayList<XY> Coordinate = new ArrayList<>();
	

	public Shape(javafx.scene.paint.Color Shapecolor,javafx.scene.paint.Color fillColor, XY firstCoordinate, XY secondCoordinate) {
		this.shapeColor = Shapecolor;
		this.fillColor=fillColor;
		this.firstCoordinate = firstCoordinate;
		this.secondCoordinate = secondCoordinate;
	}
	public Shape(javafx.scene.paint.Color Shapecolor , ArrayList<XY> Coordinate) {
		this.shapeColor = Shapecolor;
		this.Coordinate=Coordinate;
	}
	public abstract javafx.scene.paint.Color getFillColor();
	
	public abstract void setFillColor(javafx.scene.paint.Color fillColor);
	
	public abstract javafx.scene.paint.Color getShapecolor();

	public abstract void setShapecolor(javafx.scene.paint.Color shapecolor);

	public abstract XY getFirstCoordinate();

	public abstract void setFirstCoordinate(XY firstCoordinate);

	public abstract XY getSecondCoordinate();

	public abstract void setSecondCoordinate(XY secondCoordinate);

	public abstract String toString();

}
