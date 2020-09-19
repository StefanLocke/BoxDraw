package application;
	



import java.util.ArrayList;
import java.util.LinkedList;

import application.ExportController.coord;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	public ExportController exportController = new ExportController();
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root  = FXMLLoader.load(getClass().getResource("TryOne.fxml"));
			
			Scene scene = new Scene(root,1230,720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	
	public double getRelative(double current, double max) {
		
		
		
		return current/max;
	}
	
	
	
	
	public LinkedList<Rectangle> rects = new LinkedList<Rectangle>();
	
	public double startDragX = 0;
	public double startDragY = 0;
	public void startDragging(Event e) {
		if (e instanceof MouseEvent) {	
			
			MouseEvent mouseevent = (MouseEvent) e;
			startDragX = mouseevent.getX();
			startDragY = mouseevent.getY();
			Pane g = (Pane) mouseevent.getSource();
			Rectangle rect = new Rectangle(startDragX,startDragY, 0, 0);
			rect.setFill(Color.rgb(200,200,200,0.2));
			rect.setStroke(Color.BLACK);
			rect.setStrokeWidth(1);
			g.getChildren().add(rect);
			rects.addFirst(rect);
			
		}
		
	}
	
	
	
	
	@FXML
	public void dragging(Event e) {
		if (e instanceof MouseEvent) {	
			MouseEvent mouseevent = (MouseEvent) e;
			Node source = (Node) mouseevent.getSource();
			Rectangle rect = rects.getFirst();
			double mouseX = mouseevent.getX();
			double mouseY = mouseevent.getY();
			mouseX = Math.min(mouseX,source.maxWidth(-1));
			mouseY = Math.min(mouseY,source.maxHeight(-1));
			mouseX = Math.max(mouseX,0);
			mouseY = Math.max(mouseY,0);
			
			if (mouseX >= startDragX) {
				rect.setX(startDragX);
				rect.setWidth(mouseX - startDragX);
			}
			else {
				rect.setX(mouseX);
				rect.setWidth(startDragX - mouseX);
			}
				
				
			if (mouseY >= startDragY) {	
				rect.setY(startDragY);
				rect.setHeight(mouseY - startDragY);
			}
			else {
				rect.setY(mouseY);
				rect.setHeight(startDragY - mouseY);
			}

		}
	}
	@FXML
	public void endDragging(Event e) {
		Rectangle rect = rects.getFirst();
		Pane source = (Pane) e.getSource();
		
		double relaX = getRelative(rect.getX(),source.getWidth());
		double relaY = getRelative(rect.getY(),source.getHeight());
		double relaWidth = getRelative(rect.getWidth(),source.getWidth());
		double relaHeight = getRelative(rect.getHeight(),source.getHeight());
		exportController.add(relaX,relaY,relaWidth,relaHeight);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@FXML
	public void CreateBox(Event event) {
		System.out.println("hi");
		if (event instanceof MouseEvent) {
			MouseEvent mouseevent = (MouseEvent) event;
			if (mouseevent.getSource() instanceof Pane ) {
				Pane g = (Pane) mouseevent.getSource();
				Rectangle rect = new Rectangle(mouseevent.getX(), mouseevent.getY(), 100, 50);
				rect.setFill(Color.rgb(200,200,200,0.2));
				rect.setStroke(Color.BLACK);
				rect.setStrokeWidth(1);
				g.getChildren().add(rect);
				String x = "Coords are :" + mouseevent.getX() + ", " + mouseevent.getY() + ", " + (mouseevent.getX()+100) + ", " + (mouseevent.getY()- 50);
				System.out.println(x);
			}
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	double StartX = 0;
	double StartY = 0;
	
	@FXML
	public void MouseDown(Event event) {
		System.out.println("hi");
		if (event instanceof MouseEvent) {
			System.out.println("Mouse event");
			MouseEvent mouseevent = (MouseEvent) event;
			StartX = mouseevent.getX();
			StartY = mouseevent.getY();
		}
		
		
		
	}
	@FXML
	public void MouseUp(Event event) {
		if (event instanceof MouseEvent) {
			System.out.println("Mouse event");
			MouseEvent mouseevent = (MouseEvent) event;
			Pane g = (Pane) mouseevent.getSource();
			if (mouseevent.getSource() instanceof Pane ) {
				Rectangle rect = new Rectangle(StartX,StartY, mouseevent.getX()-StartX,mouseevent.getY()-StartY);
				rect.setFill(Color.rgb(200,200,200,0.2));
				rect.setStroke(Color.BLACK);
				rect.setStrokeWidth(1);		
				g.getChildren().add(rect);
				
			}
		}
		
		
		
	}
	
	
	
	
	
	@FXML
	public void Zoom(Event event) {
		if (event instanceof ScrollEvent) {
			ScrollEvent scrollevent = (ScrollEvent) event;
			Object source = scrollevent.getSource();
			double dirY = scrollevent.getDeltaY();
			Node node = (Node) source;
			if (dirY > 0) {
				node.setScaleX(node.getScaleX()*0.95);
				node.setScaleY(node.getScaleY()*0.95);
			}
			else {
				node.setScaleX(node.getScaleX()*1.05);
				node.setScaleY(node.getScaleY()*1.05);
			}
		}
	}
}
