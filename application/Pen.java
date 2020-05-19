package application;

import java.awt.Color;
import java.util.ArrayList;

public class Pen  {
	private String penName;//can pe eraser of Pen
	private javafx.scene.paint.Color shapeColor;
	ArrayList<XY> Coordinate;

	public Pen(String penName,javafx.scene.paint.Color colorselected,ArrayList<XY> Coordinate ) {
		this.penName=penName;
		this.shapeColor=colorselected;
		this.Coordinate=Coordinate;
		
	}

	
	public javafx.scene.paint.Color getPencolor() {
		return null;
	}

	
	public void setPencolor(javafx.scene.paint.Color shapecolor) {

	}

	
	public ArrayList getCoordinates() {
		return Coordinate;
	}

	
	public void setFirstCoordinate(XY firstCoordinate) {

	}

	
	public XY getSecondCoordinate() {
		return null;
	}

	
	public void setSecondCoordinate(XY secondCoordinate) {

	}

	
	public String toString() {
//		String points = "" ;
//		for(int i=0;i<Coordinate.size();i++) {
//			points+=Coordinate.get(i).toString();
//			System.out.println(Coordinate.get(i).toString());
//		}
		return penName+" "+shapeColor+" "+Coordinate+"\n";
	}

}
