

/*
 * 	Author: Carlos Vizcaino
 *  Development Days:
 *  	-> 2/11/2015 
 * 			: 
 * 		->
 *
 */

// ---------- Imports --------------
// ---------          --------------

import java.util.ArrayList;
import java.awt.Point;


public class Course {
		
		// Attributes
		private ArrayList<Course> wayPoints;
		private int yDisplacement;
		private int xDisplacement;
		
		
		// Constructor
		Course(int x, int y){
			
			wayPoints = null;
			xDisplacement = x;
			yDisplacement = y;
			
		}
	
		// ------------- Methods Definition ----------------
	    // -------------                    ----------------
		
		// -------------------------------------------------
		public int getX() {return xDisplacement;}
		//-------------------------------------------------
		public int getY() {return yDisplacement;}
		// ------------------------------------------------
		public Point getLocation(){ return new Point(xDisplacement,yDisplacement);}
		
		
}
