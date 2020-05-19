package application;


public class XY {
	double x;
	double y;
	
	XY(double d,double e){
		this.x=d;
		this.y=e;
	}
	XY(){
		this.x=-1;
		this.y=-1;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public XY getRectDimoinstioin(XY secend) {//to get the hight and the width frome the mine XY and the XY paramter 
		return new XY(Math.abs(secend.x-x),Math.abs(secend.y-y));
	}
	public String toString() {
		
		return x+" "+y;
	}

}
