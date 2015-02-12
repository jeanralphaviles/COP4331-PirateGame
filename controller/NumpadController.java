package controller;

import utility.Course;
import model.Model;

public final class NumpadController extends Controller {

	public NumpadController(Model model) {
		super(model);
	}

	public void onNum1() {
		move(new Course(-1, -1));
	}
	
	public void onNum2() {
		move(new Course(0, -1));
	}
	
	public void onNum3() {
		move(new Course(1, -1));
	}
	
	public void onNum4() {
		move(new Course(-1, 0));
	}
	
	public void onNum5() {
		// No op
	}
	
	public void onNum6() {
		move(new Course(1, 0));
	}
	
	public void onNum7() {
		move(new Course(-1, 1));
	}
	
	public void onNum8() {
		move(new Course(0, 1));
	}
	
	public void onNum9() {
		move(new Course(1,1));
	}
}
