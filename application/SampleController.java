package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Window;

public class SampleController implements Initializable{
	private static final Window Stage = null;

	@FXML
    private Button BlackButton;

    @FXML
    private Button whiteButton;

    @FXML
    private Button grayButton;

    @FXML
    private Button redButton;

    @FXML
    private Button lightblueButton;

    @FXML
    private Button magentaButton;

    @FXML
    private Button yellowButton;

    @FXML
    private Button blueButton;

    @FXML
    private Button greenButton;

    @FXML
    private Button orangeButton;

    @FXML
    private Button lightgrayButton;

    @FXML
    private Button cyanButton;

    @FXML
    private Button pinkButton;

    @FXML
    private Button lightgreenButton;

    @FXML
    private ColorPicker ColorBox;

    @FXML
    private Button penButton;

    @FXML
    private Button eraserButton;

    @FXML
    private Button lineButton;

    @FXML
    private Button circleButton;

    @FXML
    private Circle circal;

    @FXML
    private Button rectanglebutton;

    @FXML
    private Rectangle rectangleButton;

    @FXML
    private Button righttriangleButton;

    @FXML
    private Button triangleButton;

    @FXML
    private Button fillButton;

    @FXML
    private MenuItem openMenu;

    @FXML
    private MenuItem saveMenu;

    @FXML
    private MenuItem newMenu;

    @FXML
    private MenuItem closeMenu;

    @FXML
    private MenuItem penMenu;

    @FXML
    private MenuItem eraserMenu;

    @FXML
    private MenuItem lineMenu;

    @FXML
    private MenuItem circleMenu;

    @FXML
    private MenuItem rectangleMenu;

    @FXML
    private MenuItem triangleMenu;

    @FXML
    private MenuItem righttriangleMenu;

    @FXML
    private MenuItem fillMenu;

    @FXML
    private MenuItem colorBoxMenu;

    @FXML
    private Label mouselocationLabel;

    @FXML
    private Label selectedShapeLabel;

    @FXML
    private Label selectedColorLabel;
    
    @FXML
    private Label hightlable;
    
    @FXML
    private Label wigthlabel;

    @FXML
    private Label shapelabel;

    @FXML
    private TextField setHight;

    @FXML
    private TextField setWidth;

    @FXML
    private Button moveUp;

    @FXML
    private Button moveLeft;

    @FXML
    private Button moveRight;

    @FXML
    private Button moveDown;

    @FXML
    private Button deleteButton;

    @FXML
    private Canvas canvas;

    
  
    private String toolSelected="";//name of the tool selected
    
    private javafx.scene.paint.Color colorselected=javafx.scene.paint.Color.WHITE;//defoult color is black
    	
    GraphicsContext pentool,erasertool,circaltool,linetool,rectangletool,triangletool,righttriangletool,cleartool,selecttool;//tools
    
    ArrayList<Shape> shapesarray=new ArrayList<Shape>();//this array list to stor data about Shapes name,color,fillColor,first cordenate secend cordaner
    ArrayList<Pen> penarray=new ArrayList<Pen>();//this array to stor data about pen and ereser color thicknes array of point
	 XY start;//the first point that the user input
	 XY end;
	 	
	  Boolean saved=true;
	 File selectedFile ;
	 
	 Shape selectedShape;
	 Shape selectedShapeCopy;

	public String fillString="";//added to file
	
	//event 
	
	   @FXML
	    void getShapeinfo(MouseEvent event) {//MOUSE clecked in a Shape and the fill 
	    	System.out.println(fillString);
	    	repaint(fillString);
	    	
			 XY mouseXY=new XY(event.getX(),event.getY());
		    	
		    	if(!shapesarray.isEmpty()) {
		    		int indx=shapesarray.size()-1;
		    		while(indx>=0){
		    			if(isintheShape(shapesarray.get(indx), mouseXY)){
		    			selectedShape=shapesarray.get(indx);    			
		    			break;

		    			}
		    			
		    		indx--;
		    		
		    		}
		    		
		    		
		    	if(!(selectedShape==null)){
		    		
		    		if(toolSelected.equals("fill")&&selectedShape!=null) {
		    			
		    			

		    	    	Shape newSelectedShape;
		    			newSelectedShape=selectedShape;
		    			String oldShape=selectedShape.toString();
		    	    	
		    	    	
		    			newSelectedShape.setFillColor(colorselected);
		    			changeShape(oldShape,newSelectedShape.toString());
		    			selectedShape=newSelectedShape;
		    			repaint(fillString);
		    			
		    			
		    		}else {
		    			
			    	selecttool.setLineDashes(5.0);
			    	selecttool.setStroke(javafx.scene.paint.Color.BLUE);//color of toolselecter
			    	
			    		XY[] correctedform=getCorrectForm(selectedShape.firstCoordinate, selectedShape.secondCoordinate);
			    		XY WH=correctedform[1].getRectDimoinstioin(correctedform[0]);
			    		selecttool.strokeRect(correctedform[0].x-1, correctedform[0].y-1, WH.x+1, WH.y+1);
			    	
			    	
			    	
			    	
		    		shapelabel.setText(selectedShape.getClass().getTypeName().substring(12));
		    		
		    		
		    		if(selectedShape.getClass().getName().equals("application.LineClass")) {
		    			setHight.setText(((LineClass)selectedShape).getLength()+"");
		    		  hightlable.setText("Length");
		    		  wigthlabel.setVisible(false);;
		    		  setWidth.setVisible(false);
		    		  setHight.setDisable(false);
		    		}
		    		else {
		    		wigthlabel.setVisible(true);;
		    		setWidth.setVisible(true);
		    		hightlable.setText("Hight");
		    		
		    		setHight.setText(WH.y+"");
		    		setHight.setDisable(false);
		    		setWidth.setText(WH.x+"");
		    		setWidth.setDisable(false);
		    		}
		    		moveDown.setDisable(false);
		    		moveUp.setDisable(false);
		    		
		    		moveLeft.setDisable(false);
		    		moveRight.setDisable(false);
		    		
		    		deleteButton.setDisable(false);
		    		

		    		}
		    	}
		    	else {
		    		
		    		shapelabel.setText("NO Shape has been selected");
		    		
		    		setHight.setText("0");
		    		setHight.setDisable(true);
		    		setWidth.setText("0");
		    		setWidth.setDisable(true);
		    		
		    		moveDown.setDisable(true);
		    		moveUp.setDisable(true);
		    		
		    		moveLeft.setDisable(true);
		    		moveRight.setDisable(true);
		    		
		    		selectedShapeLabel.setText("");
		    		selectedColorLabel.setText("");
		    		
		    		deleteButton.setDisable(true);
		    	}

		    	
		    	selectedShapeCopy=selectedShape;
		    	selectedShape=null;
		    	selecttool.setLineDashes(1.0);

		    	}
	    }
	
    @FXML
    void moveShape(ActionEvent event) {
    	
    	double moveSceal=10.0;
    	
    	if(((Button)event.getSource()).getText().equals("▲")) {

    		Shape newSelectedShape;
    		newSelectedShape=selectedShapeCopy;
    		String oldShape=selectedShapeCopy.toString();
        	
        	
    		newSelectedShape.getFirstCoordinate().setY(newSelectedShape.getFirstCoordinate().getY()-moveSceal);
    		newSelectedShape.getSecondCoordinate().setY(newSelectedShape.getSecondCoordinate().getY()-moveSceal);
    		changeShape(oldShape,newSelectedShape.toString());
    		selectedShapeCopy=newSelectedShape;
    		repaint(fillString);
    		
    		
    		
    	}
    	if(((Button)event.getSource()).getText().equals("▼")) {

    		Shape newSelectedShape;
    		newSelectedShape=selectedShapeCopy;
    		String oldShape=selectedShapeCopy.toString();
        	
        	
    		newSelectedShape.getFirstCoordinate().setY(newSelectedShape.getFirstCoordinate().getY()+moveSceal);
    		newSelectedShape.getSecondCoordinate().setY(newSelectedShape.getSecondCoordinate().getY()+moveSceal);
    		changeShape(oldShape,newSelectedShape.toString());
    		selectedShapeCopy=newSelectedShape;
    		repaint(fillString);
    		
    		
    		
    	}
    	
    	if(((Button)event.getSource()).getText().equals("►")) {

    		Shape newSelectedShape;
    		newSelectedShape=selectedShapeCopy;
    		String oldShape=selectedShapeCopy.toString();
        	
        	
    		newSelectedShape.getFirstCoordinate().setX(newSelectedShape.getFirstCoordinate().getX()+moveSceal);
    		newSelectedShape.getSecondCoordinate().setX(newSelectedShape.getSecondCoordinate().getX()+moveSceal);
    		changeShape(oldShape,newSelectedShape.toString());
    		selectedShapeCopy=newSelectedShape;
    		repaint(fillString);
    		
    		
    		
    	}
    	
    	if(((Button)event.getSource()).getText().equals("◄")) {

    		Shape newSelectedShape;
    		newSelectedShape=selectedShapeCopy;
    		String oldShape=selectedShapeCopy.toString();
        	
        	
    		newSelectedShape.getFirstCoordinate().setX(newSelectedShape.getFirstCoordinate().getX()-moveSceal);
    		newSelectedShape.getSecondCoordinate().setX(newSelectedShape.getSecondCoordinate().getX()-moveSceal);
    		changeShape(oldShape,newSelectedShape.toString());
    		selectedShapeCopy=newSelectedShape;
    		repaint(fillString);
    		
    		
    		
    	}
    	
    	
    	
    	
    }
    
    @FXML
    void deleteShape(ActionEvent event) {
    	deleteShape(selectedShapeCopy.toString());
    	shapesarray.remove(selectedShapeCopy);
    	repaint(fillString);
    	setUpShapeSelected();
    }
    

    @FXML
    void setShapeSize(ActionEvent event) {
    	XY selectedShapDim=selectedShapeCopy.firstCoordinate.getRectDimoinstioin(selectedShapeCopy.secondCoordinate);
    	
    	try {
    		if(selectedShapeCopy.getClass().getName().equals("application.LineClass")) {
    			if(Double.parseDouble(setHight.getText())<=0)throw new InputMismatchException();
    			
    			
    			double lengthSceal= Double.parseDouble(setHight.getText())/((LineClass)selectedShapeCopy).getLength();
    	    	double widthSceal=lengthSceal*selectedShapDim.x-selectedShapDim.x;
    	    	double hightSceal=lengthSceal*selectedShapDim.y-selectedShapDim.y;
    	    	
    	    	
    	    	Shape newSelectedShape;
    			newSelectedShape=selectedShapeCopy;
    			String oldShape=selectedShapeCopy.toString();
    	    	
    	    	
    			newSelectedShape.getSecondCoordinate().setX(newSelectedShape.getSecondCoordinate().getX()+widthSceal);
    			newSelectedShape.getSecondCoordinate().setY(newSelectedShape.getSecondCoordinate().getY()+hightSceal);
    			
    			changeShape(oldShape,newSelectedShape.toString());
    			selectedShapeCopy=newSelectedShape;
    			
    			repaint(fillString);

    			
    		}
    		
    		else {
    		if(Double.parseDouble(setWidth.getText())<=0)throw new InputMismatchException();
    		if(Double.parseDouble(setHight.getText())<=0)throw new InputMismatchException();
    		
    	double widthSceal=Double.parseDouble(setWidth.getText())-selectedShapDim.x;
    	double hightSceal=Double.parseDouble(setHight.getText())-selectedShapDim.y;

    	
    	Shape newSelectedShape;
		newSelectedShape=selectedShapeCopy;
		String oldShape=selectedShapeCopy.toString();
    	
    	
		newSelectedShape.getSecondCoordinate().setX(newSelectedShape.getSecondCoordinate().getX()+widthSceal);
		newSelectedShape.getSecondCoordinate().setY(newSelectedShape.getSecondCoordinate().getY()+hightSceal);
		
		changeShape(oldShape,newSelectedShape.toString());
		selectedShapeCopy=newSelectedShape;
		
		repaint(fillString);
    		}
    	}catch (Exception e) {
    		System.out.println(e);
    		if(selectedShapeCopy.getClass().getName().equals("application.LineClass")) {
    			setHight.setText(((LineClass)selectedShapeCopy).getLength()+"");
    		}else {
    		setWidth.setText(selectedShapDim.x+"");
    		setHight.setText(selectedShapDim.y+"");
    			}
		}
    }
	

    @FXML
    void colorBoxSelected(ActionEvent event) {//take the user color input
    	
    	setUPColors();
    	
    	colorselected=javafx.scene.paint.Color.web(ColorBox.getValue().toString());
    	selectedColorLabel.setText(ColorBox.getValue().toString());
    	
    }

    @FXML
    public void colorselected(ActionEvent event) {
    	
    	setUPColors();
    	
    	((Button)event.getSource()).setDisable(true);
    	
    	colorselected=javafx.scene.paint.Color.web(((Button)event.getSource()).getText());
    	ColorBox.setValue(colorselected);
    	
    	selectedColorLabel.setText(ColorBox.getValue().toString());
    	selectedShapeLabel.setText(toolSelected);
    
    }
    @FXML
    public void Drow(DragEvent event) {
    	
    }
    
    @FXML
    public void toolSlected(ActionEvent event) {//take the user tool selection
    	
    	setUpShapes();
    	
    	((Button)event.getSource()).setDisable(true);
    	
    	String temp=((Button)event.getSource()).getId();
    	toolSelected=(temp.substring(0, temp.length()-6));//to remove the "button" from the name
    	
    	if(toolSelected.isEmpty())selectedShapeLabel.setText("");
    	
    	else {
    		selectedShapeLabel.setText(toolSelected);
    		selectedColorLabel.setText(ColorBox.getValue().toString());
    	}
    }
    
    
    @FXML
    void mouselocation(MouseEvent event) {
    	
    	mouselocationLabel.setText("("+(int)event.getX()+","+(int)event.getY()+")");
    	
    }
    
    
    //menu
    
    @FXML
    void ShapeMenu(ActionEvent event) {
    	
    	if(((MenuItem)event.getSource()).getText().equals("Pen")) 
    		penButton.fire();
    	
    	if(((MenuItem)event.getSource()).getText().equals("Eraser")) 
    		eraserButton.fire();
    	
    	if(((MenuItem)event.getSource()).getText().equals("Line")) 
    		lineButton.fire();
    	
    	if(((MenuItem)event.getSource()).getText().equals("Circle")) 
    		circleButton.fire();
    	
    	if(((MenuItem)event.getSource()).getText().equals("Rectangle")) 
    		rectanglebutton.fire();
    	
    	if(((MenuItem)event.getSource()).getText().equals("Triangle")) 
    		triangleButton.fire();
    	
    	if(((MenuItem)event.getSource()).getText().equals("RightTriangle")) 
    		righttriangleButton.fire();
    	
    	if(((MenuItem)event.getSource()).getText().equals("Fill")) 
    		fillButton.fire();

    	
    }
   
    @FXML
    void fileMenu(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
    	
    	if(((MenuItem)event.getSource()).getText().equals("open")) {
    		if(!saved)Save();
    		
    		setUp();
    		clearCanvase();
    		shapesarray.clear();
    		penarray.clear();
    		fillString="";
    		
    		FileChooser fileChooser = new FileChooser();
    		
    		fileChooser.getExtensionFilters().addAll(
    			     new FileChooser.ExtensionFilter("Text Files", "*.txt")
    			);
    		 selectedFile = fileChooser.showOpenDialog(Stage);
    		
    		//fillString=selectedFile.toString();
    		
    		try (Scanner scanner = new Scanner(selectedFile)) {
    			
    			while (scanner.hasNextLine()) {
    				
    				String Shape=scanner.nextLine()+"\n";
    				if(!Shape.trim().isEmpty()) {
    				Scanner shapeScanner = new Scanner(Shape);
    				System.out.println("Shape:"+Shape);
    				String Shapename=shapeScanner.next();
    				
    					
    					if(Shapename.equals("circle")) {
    						javafx.scene.paint.Color fillcolor=javafx.scene.paint.Color.TRANSPARENT.web(shapeScanner.next());
    						javafx.scene.paint.Color color=javafx.scene.paint.Color.TRANSPARENT.web(shapeScanner.next());
    						EllipseClass tempShape=new EllipseClass
    							(color,fillcolor
    									, new XY(shapeScanner.nextDouble(),  shapeScanner.nextDouble()),new XY(shapeScanner.nextDouble(),  shapeScanner.nextDouble()));
    						
    						shapesarray.add(tempShape);
    						fillString=fillString+tempShape.toString();
    					}
    					
    					else if(Shapename.equals("line")) {
    						javafx.scene.paint.Color fillcolor=javafx.scene.paint.Color.TRANSPARENT.web(shapeScanner.next());
    						javafx.scene.paint.Color color=javafx.scene.paint.Color.TRANSPARENT.web(shapeScanner.next());
    						LineClass tempShape=new LineClass(color,fillcolor
									, new XY(shapeScanner.nextDouble(),  shapeScanner.nextDouble()),new XY(shapeScanner.nextDouble(),  shapeScanner.nextDouble()));
    					
    						shapesarray.add(tempShape);
    						fillString=fillString+tempShape.toString();
    					}
    					else if(Shapename.equals("rectangle")) {
    						javafx.scene.paint.Color fillcolor=javafx.scene.paint.Color.TRANSPARENT.web(shapeScanner.next());
    						javafx.scene.paint.Color color=javafx.scene.paint.Color.TRANSPARENT.web(shapeScanner.next());
    						RectangleClass tempShape=new RectangleClass(color,fillcolor
									, new XY(shapeScanner.nextDouble(),  shapeScanner.nextDouble()),new XY(shapeScanner.nextDouble(),  shapeScanner.nextDouble()));
    						
    						shapesarray.add(tempShape);
    						fillString=fillString+tempShape.toString();
    					}
    					
    					else if(Shapename.equals("righttriangle")) {
    						javafx.scene.paint.Color fillcolor=javafx.scene.paint.Color.TRANSPARENT.web(shapeScanner.next());
    						javafx.scene.paint.Color color=javafx.scene.paint.Color.TRANSPARENT.web(shapeScanner.next());
    						RightTriangleClass tempShape=new RightTriangleClass(color,fillcolor
									, new XY(shapeScanner.nextDouble(),  shapeScanner.nextDouble()),new XY(shapeScanner.nextDouble(),  shapeScanner.nextDouble()));
    						
    						shapesarray.add(tempShape);
    						fillString=fillString+tempShape.toString();
    					}
    					
    					else if(Shapename.equals("triangle")) {
    						javafx.scene.paint.Color fillcolor=javafx.scene.paint.Color.TRANSPARENT.web(shapeScanner.next());
    						javafx.scene.paint.Color color=javafx.scene.paint.Color.TRANSPARENT.web(shapeScanner.next());
    						TriangleClass tempShape=new TriangleClass(color,fillcolor
									, new XY(shapeScanner.nextDouble(),  shapeScanner.nextDouble()),new XY(shapeScanner.nextDouble(),  shapeScanner.nextDouble()));
    					
    						shapesarray.add(tempShape);
    						fillString=fillString+tempShape.toString();
    					}else {//in case of eraser or pen
    						fillString=fillString+Shape;
    						
    					}
    					
    					
    				
    	           
    				
    	           shapeScanner.close();
    				}
    			}
    	    } catch (FileNotFoundException e) {
    	        e.printStackTrace();
    	       
    	    }

    			repaint(fillString);
    			

    	}
    	try {
    	if(((MenuItem)event.getSource()).getText().equals("save")) {
    		if(selectedFile!=null) {
       		 PrintWriter writer = new PrintWriter(selectedFile, "UTF-8");
       		 writer.println(fillString);
       		 writer.flush();
       		 writer.close();
       		}
       		else {
           		FileChooser fileChooser = new FileChooser();
           		
           		fileChooser.getExtensionFilters().addAll(
           			     new FileChooser.ExtensionFilter("Text Files", "*.txt")
           			);
       			File file = fileChooser.showSaveDialog(Stage);
          		 PrintWriter writer = new PrintWriter(file, "UTF-8");
          		 writer.println(fillString);
          		 writer.flush();
          		 writer.close();
       		}
       		saved=true;
    	}
    	if(((MenuItem)event.getSource()).getText().equals("new")) {
    		if(!saved)Save();
    		
    		setUp();
    		clearCanvase();
    		shapesarray.clear();
    		penarray.clear();
    		fillString="";
    		selectedFile=null;
    		
    	}
    	
    	if(((MenuItem)event.getSource()).getText().equals("close")) {
    		if(!saved)Save();
    		System.exit(0);
    	}
    	
    	}catch (Exception e) {
			
		}
    }
    
    @FXML
    void colorBoxMenu(ActionEvent event) {
    	ColorBox.show();
    }
    
 
    

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
    
		
		
		canvas.autosize();
		//setup the tools
		pentool=canvas.getGraphicsContext2D();
		erasertool=canvas.getGraphicsContext2D();
		circaltool=canvas.getGraphicsContext2D();
		linetool=canvas.getGraphicsContext2D();
		rectangletool=canvas.getGraphicsContext2D();
		triangletool=canvas.getGraphicsContext2D();
		righttriangletool=canvas.getGraphicsContext2D();
		cleartool=canvas.getGraphicsContext2D();
		selecttool=canvas.getGraphicsContext2D();
		cleartool.setFill(javafx.scene.paint.Color.WHITE);

		setUp();
		
		
		ArrayList<XY> penpoints=new ArrayList<>();//to add the point to the save file  
		//this array list must be cleared after the mouse has been relesed

		canvas.setOnMousePressed(e ->{//to get the first XY
			repaint(fillString);
			
			 start=new XY(e.getX(),e.getY());
			 saved=false;

		} );
		
		
		
		/*
		 * to get the secand XY
		 * hear where the drowing will be showen and it will be stored in a string
		 */
		
		canvas.setOnMouseReleased(e->{
			
			end=new XY(e.getX(),e.getY());
			if(!(end.x==start.x)&&!(end.y==start.y)) {
			if(toolSelected!=null&&toolSelected.equals("pen")) {
				
				Pen tempPen=new Pen(toolSelected,colorselected,penpoints);
				penarray.add(tempPen);//macking pen obj
				fillString=fillString+tempPen.toString();
				penpoints.clear();//clear the array to use it agean
				
			}
			if(toolSelected!=null&&toolSelected.equals("eraser")) {
				
				
				Pen tempEraser=new Pen(toolSelected,javafx.scene.paint.Color.WHITE,penpoints);
				penarray.add(tempEraser);//macking pen obj
				fillString=fillString+tempEraser.toString();
				penpoints.clear();//clear the array to use it agean
				
			}
			
			

			
			if(toolSelected!=null&&toolSelected.equals("circle")) {
				EllipseClass tempShape=new EllipseClass(colorselected, javafx.scene.paint.Color.TRANSPARENT, start, end);
				
				shapesarray.add(tempShape);
				fillString=fillString+tempShape.toString();
				drowCircal(start,end,javafx.scene.paint.Color.TRANSPARENT,colorselected);
				
			}
			
			if(toolSelected!=null&&toolSelected.equals("line")) {
				LineClass tempShape=new LineClass(colorselected, javafx.scene.paint.Color.TRANSPARENT, start, end);
			
				shapesarray.add(tempShape);
				fillString=fillString+tempShape.toString();
				drowLine(start,end,javafx.scene.paint.Color.TRANSPARENT,colorselected);
				
			}
			if(toolSelected!=null&&toolSelected.equals("rectangle")) {
				RectangleClass tempShape=new RectangleClass(colorselected, javafx.scene.paint.Color.TRANSPARENT, start, end);
				
				shapesarray.add(tempShape);
				fillString=fillString+tempShape.toString();
				drowRectangle(start,end,javafx.scene.paint.Color.TRANSPARENT,colorselected);
				
			}
			
			if(toolSelected!=null&&toolSelected.equals("righttriangle")) {
				RightTriangleClass tempShape=new RightTriangleClass(colorselected, javafx.scene.paint.Color.TRANSPARENT, start, end);
				
				shapesarray.add(tempShape);
				fillString=fillString+tempShape.toString();
				drowRightTriangle(start,end,javafx.scene.paint.Color.TRANSPARENT,colorselected);
				
			}
			
			if(toolSelected!=null&&toolSelected.equals("triangle")) {
				TriangleClass tempShape=new TriangleClass(colorselected, javafx.scene.paint.Color.TRANSPARENT, start, end);
				
				shapesarray.add(tempShape);
				fillString=fillString+tempShape.toString();
				drowTriangle(start,end,javafx.scene.paint.Color.TRANSPARENT,colorselected);
				
			}
			
			
			
			
			
			
			
			
			
			
		}
	});
		canvas.setOnMouseDragged( e -> {
			if(!toolSelected.isEmpty()) {
			if(!toolSelected.equals("eraser")&&!toolSelected.equals("pen")) {
				
				clearCanvase();//to clear after showint the blase for the shape
				if(!fillString.isEmpty())repaint(fillString);
			}

			XY tempMousLocation =new XY(e.getX(),e.getY());
			mouselocationLabel.setText("("+(int)e.getX()+","+(int)e.getY()+")");
			
			if(toolSelected!=null&&toolSelected.equals("pen")) {
				penpoints.add(tempMousLocation);
				penPoint(tempMousLocation,10,colorselected);
			}
			if(toolSelected!=null&&toolSelected.equals("eraser")) {
				penpoints.add(tempMousLocation);
				eraserPoint(tempMousLocation,10);
			}
			
			if(toolSelected!=null&&toolSelected.equals("circle")) {
				drowCircal(start,tempMousLocation,javafx.scene.paint.Color.TRANSPARENT,colorselected);
			}
			
			if(toolSelected!=null&&toolSelected.equals("line")) {

				drowLine(start,tempMousLocation,javafx.scene.paint.Color.TRANSPARENT,colorselected);
				
			}
			if(toolSelected!=null&&toolSelected.equals("rectangle")) {

				drowRectangle(start,tempMousLocation,javafx.scene.paint.Color.TRANSPARENT,colorselected);
				
			}
			
			if(toolSelected!=null&&toolSelected.equals("righttriangle")) {

				drowRightTriangle(start,tempMousLocation,javafx.scene.paint.Color.TRANSPARENT,colorselected);
				
			}
			
			if(toolSelected!=null&&toolSelected.equals("triangle")) {

				drowTriangle(start,tempMousLocation,javafx.scene.paint.Color.TRANSPARENT,colorselected);
				
			}
			
			
			}

		});
	}
	
	public void setUPColors() {
		BlackButton.setDisable(false);
		whiteButton.setDisable(false);
		 grayButton.setDisable(false);
		redButton.setDisable(false);
		 lightblueButton.setDisable(false);
		 magentaButton.setDisable(false);
		 yellowButton.setDisable(false);
		 blueButton.setDisable(false);
		 greenButton.setDisable(false);
		orangeButton.setDisable(false);
		 lightgrayButton.setDisable(false);
		cyanButton.setDisable(false);
		 pinkButton.setDisable(false);
		 lightgreenButton.setDisable(false);

		
		
		
	}
	
	public void setUpShapes() {
		penButton.setDisable(false);
		 eraserButton.setDisable(false);
		 circleButton.setDisable(false);
		 rectanglebutton.setDisable(false);
		lineButton.setDisable(false);
		triangleButton.setDisable(false);
		righttriangleButton.setDisable(false);
		fillButton.setDisable(false);
	}
	
	
	
	public void setUpShapeSelected() {
		
		
		shapelabel.setText("NO Shape has been selected");
		setHight.setText("0");
		setHight.setDisable(true);
		setWidth.setText("0");
		setWidth.setDisable(true);
		
		moveDown.setDisable(true);
		moveUp.setDisable(true);
		
		moveLeft.setDisable(true);
		moveRight.setDisable(true);
		
		selectedShapeLabel.setText("");
		selectedColorLabel.setText("");
		
		deleteButton.setDisable(true);
		
	}
	
	public void setUp() {
		clearCanvase();
		setUPColors();
		setUpShapes();
		setUpShapeSelected();

	}
	
	
	
	/*
	this mathod finds the correct form of the coordinates where the first coordinate (indx 0) is the top left coordinate
	 the secend coordinates(indx 1) is the botome right coordinate 
	 */
	public XY[] getCorrectForm(XY first,XY secend) {
		XY[] array =new XY[2];

    	if(first.x>secend.x&&first.y>secend.y) {//case 1
        	array[0]=secend;
        	array[1]=first;

        	
    	}
    	if(first.x<secend.x&&first.y>secend.y) {//case 2
    		
        	
        	array[0]=new XY(first.x,secend.y);
        	array[1]=new XY(secend.x,first.y);
        	
    	}
    	if(first.x>secend.x&&first.y<secend.y) {//case 3

        	array[0]=new XY(secend.x,first.y);
        	array[1]=new XY(first.x,secend.y);
        	
    	}
    	if(first.x<secend.x&&first.y<secend.y) {//case 4 nourmal 
        	array[0]=first;
        	array[1]=secend;
        	
        	
    	}
		
		
		return array;
	}
	
	private void penPoint(XY point,double penSize,javafx.scene.paint.Color color) {
		double xmid=point.x-penSize/2.0;
		double ymid=point.y-penSize/2.0;
		pentool.setFill(color);
		pentool.fillOval(xmid, ymid, penSize, penSize);
		
	}
	
	
	
	private void eraserPoint(XY point,double eraserSize) {
		double xmid=point.x-eraserSize/2.0;
		double ymid=point.y-eraserSize/2.0;
		erasertool.setFill(javafx.scene.paint.Color.WHITE);
		erasertool.fillOval(xmid, ymid, eraserSize, eraserSize);
		
	}
	
    private void drowCircal(XY start,XY end,javafx.scene.paint.Color fillcolor,javafx.scene.paint.Color color) {
    		
        	circaltool.setStroke(color);
        	circaltool.setFill(fillcolor);

        	circaltool.closePath();
        	XY[] coorected =getCorrectForm(start, end);
        	

        	try {
        	XY dimo=coorected[0].getRectDimoinstioin(coorected[1]);
        		

	        	circaltool.strokeOval(coorected[0].x, coorected[0].y,dimo.x,dimo.y);
	        	circaltool.fillOval(coorected[0].x+1, coorected[0].y+1, dimo.x-1, dimo.y-1);
	        	
    		}catch (Exception e) {

            	
			}
        	
    
    }
    
    
    private void drowLine(XY start,XY end,javafx.scene.paint.Color fillcolor,javafx.scene.paint.Color color){
    	linetool.setStroke(color);
    	linetool.setFill(fillcolor);

    	linetool.closePath();
    	XY[] coorected =getCorrectForm(start, end);
    	

    	try {
    	
    		

    		linetool.strokeLine(start.x, start.y,end.x,end.y);
        	
        	
		}catch (Exception e) {

        	
		}
    	
    	
    	
    }
    
    private void drowRectangle(XY start,XY end,javafx.scene.paint.Color fillcolor,javafx.scene.paint.Color color) {
		
    	rectangletool.setStroke(color);
    	rectangletool.setFill(fillcolor);

    	rectangletool.closePath();
    	XY[] coorected =getCorrectForm(start, end);
    	

    	try {
    	XY dimo=coorected[0].getRectDimoinstioin(coorected[1]);
    		

    	rectangletool.strokeRect(coorected[0].x, coorected[0].y,dimo.x,dimo.y);
    	rectangletool.fillRect(coorected[0].x+1, coorected[0].y+1, dimo.x-1, dimo.y-1);
        	
		}catch (Exception e) {

        	
		}
    	
    	
    }
    
    private void drowTriangle(XY start,XY end,javafx.scene.paint.Color fillcolor,javafx.scene.paint.Color color) {
		
    	triangletool.setStroke(color);
    	triangletool.setFill(fillcolor);

    	triangletool.closePath();
    	
    	

    	try {
    	
    		
    		double[] xpoints={start.x,((end.x+start.x)/2),end.x};
    		double[] ypoints={end.y,start.y,end.y};
    		

    		
    		triangletool.strokePolygon(xpoints, ypoints, 3);
    		triangletool.fillPolygon(xpoints, ypoints, 3);
        	
		}catch (Exception e) {

        	
		}
    	

}
    
    
 private void drowRightTriangle(XY start,XY end,javafx.scene.paint.Color fillcolor,javafx.scene.paint.Color color) {
		
	 righttriangletool.setStroke(color);
	 righttriangletool.setFill(fillcolor);

	 righttriangletool.closePath();
    	XY[] coorected =getCorrectForm(start, end);
    	

    	try {
    	
    		
    		double[] xpoints={start.x,start.x,end.x};
    		double[] ypoints={start.y,end.y,end.y};

    		righttriangletool.strokePolygon(xpoints, ypoints, 3);
    		righttriangletool.fillPolygon(xpoints, ypoints, 3);
        	
		}catch (Exception e) {

        	
		}
    	

}

    
    
    public boolean isintheShape(Shape shape,XY point) {//to cheek if the point is in the shape
    		double XMax=Double.max(shape.firstCoordinate.x	, shape.secondCoordinate.x);
    		double XMin=Double.min(shape.firstCoordinate.x	, shape.secondCoordinate.x);
    		
    		double YMax=Double.max(shape.firstCoordinate.y	, shape.secondCoordinate.y);
    		double YMin=Double.min(shape.firstCoordinate.y	, shape.secondCoordinate.y);
    		
    		
   return     	XMin<=point.x&&XMax>=point.x
				&&YMin<=point.y&&YMax>=point.y;	

    }
    
    
    
    
    
    
    
    
    
    public void clearCanvase() {
    	cleartool.setFill(javafx.scene.paint.Color.WHITE);
    	cleartool.fillRect(0,0,canvas.getWidth(),canvas.getHeight());

    	
    }
    
    
    
    
    
    public void changeShape(String oldShape ,String newShape) {
    	oldShape=oldShape.replace("[", "");
    	oldShape=oldShape.replace("]", "");
    	oldShape=oldShape.replace(",", " ");
    	
    	newShape=newShape.replace("[", "");
    	newShape=newShape.replace("]", "");
    	newShape=newShape.replace(",", " ");
    	

    	
    	
    	
    	fillString=fillString.replace(oldShape, newShape);
    	
    	
    }

    public void deleteShape(String Shape) {
    	
    	
    	fillString=fillString.replace(Shape, "");
    	
    }
    
    public void Save() throws FileNotFoundException, UnsupportedEncodingException {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("the file is not saved!");
    	alert.setContentText("do you like to save it?");
    	try {
    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		if(selectedFile!=null) {
    		 PrintWriter writer = new PrintWriter(selectedFile, "UTF-8");
    		 writer.println(fillString);
    		 writer.flush();
    		 writer.close();
    		}
    		else {
        		FileChooser fileChooser = new FileChooser();
        		
        		fileChooser.getExtensionFilters().addAll(
        			     new FileChooser.ExtensionFilter("Text Files", "*.txt")
        			);
    			File file = fileChooser.showSaveDialog(Stage);
       		 PrintWriter writer = new PrintWriter(file, "UTF-8");
       		 writer.println(fillString);
       		 writer.flush();
       		 writer.close();
    		}
    		saved=true;
    	} else {
    	    // ... user chose CANCEL or closed the dialog
    	}}catch (Exception e) {
			// the user canceled
		}
    	
    }
    
    
    public void repaint(String S) {//this mathed was made soo thet we can paint the canves after the user opent it 
    								//or after clearing the canves from the tempery shapes(plase holder)
    	clearCanvase();
    		
    		S=S.replace("[", "");
    		S=S.replace("]", "");
    		S=S.replace(",", " ");
    		Scanner fileScanner = new Scanner(S);//to scane lines
    		if(!fillString.isEmpty())
    		while(fileScanner.hasNextLine()) {		
    			String shape=fileScanner.nextLine();
    			Scanner shapeScanner = new Scanner(shape);//to scanne the shape name and its info
    			if(!shape.trim().isEmpty()) {
    				String shapename=shapeScanner.next();
    				if(shapename.equals("pen")) {
    					String color=shapeScanner.next();
    					while(shapeScanner.hasNext()) {
    						XY xy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					penPoint(xy,10,javafx.scene.paint.Color.web(color));
    					
    					}
    					
    				}
    				
    				else if(shapename.equals("eraser")) {
    					
    					String color=shapeScanner.next();
    					while(shapeScanner.hasNext()) {
    						XY xy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					penPoint(xy,10,javafx.scene.paint.Color.web(color));
    					
    					}
    				}
    				
    				else if(shapename.equals("circle")) {
    				
    					String fillcolor=shapeScanner.next();
    					String color=shapeScanner.next();
    					
    					XY startxy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					XY endxy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					drowCircal(startxy, endxy, javafx.scene.paint.Color.web(fillcolor),javafx.scene.paint.Color.web(color));
    					
    				}
    				
    				else if(shapename.equals("line")) {
        				
    					String fillcolor=shapeScanner.next();
    					String color=shapeScanner.next();
    					
    					XY startxy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					XY endxy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					drowLine(startxy, endxy, javafx.scene.paint.Color.web(fillcolor),javafx.scene.paint.Color.web(color));
    					
    				}
    				else if(shapename.equals("rectangle")) {
        				
    					String fillcolor=shapeScanner.next();
    					String color=shapeScanner.next();
    					
    					XY startxy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					XY endxy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					drowRectangle(startxy, endxy, javafx.scene.paint.Color.web(fillcolor),javafx.scene.paint.Color.web(color));
    					
    				}
    				else if(shapename.equals("righttriangle")) {
        				
    					String fillcolor=shapeScanner.next();
    					String color=shapeScanner.next();
    					
    					XY startxy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					XY endxy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					drowRightTriangle(startxy, endxy, javafx.scene.paint.Color.web(fillcolor),javafx.scene.paint.Color.web(color));
    					
    				}
    				else if(shapename.equals("triangle")) {
        				
    					String fillcolor=shapeScanner.next();
    					String color=shapeScanner.next();
    					
    					XY startxy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					XY endxy=new XY(shapeScanner.nextDouble(),shapeScanner.nextDouble());
    					drowTriangle(startxy, endxy, javafx.scene.paint.Color.web(fillcolor),javafx.scene.paint.Color.web(color));
    					
    				}
    				
    				
    				
    			}
    			
    			shapeScanner.close();
    			
    		}
    		fileScanner.close();

    }



}
