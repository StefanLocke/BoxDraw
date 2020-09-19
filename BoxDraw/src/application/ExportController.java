package application;

import java.util.LinkedList;

import application.ExportController.coord;

public class ExportController {
	
	public ExportController() {
		coords = new LinkedList<coord>();
	}
	
	public class coord {
		public coord() {
			relativeX=0;
			relativeY=0;
			relativeWidth=0;
			relativeHeight=0;
		}
		
		public coord(double x, double y , double width , double height) {
			relativeX=x;
			relativeY=y;
			relativeWidth=width;
			relativeHeight=height;
		}
		double relativeX;
		double relativeY;
		double relativeWidth;
		double relativeHeight;
	}
	public LinkedList<coord> coords;
	public void add(double x, double y , double width , double height) {
		coords.add(new coord(x,y,width,height));
		String s = "Added x : " + x*100 + " y : " + y*100 + " Width : " + width*100 + " Height : " + height*100 ;
		System.out.println(s);
	}
	
	
	
}
