package controller;

import utility.Course;
import model.Model;

public abstract class Controller extends Thread {

    /*Properties*/
    
    private Model model;

    /*Constructors*/
    
    public Controller(Model model) {

    }
    
    /*Methods*/
    
    protected void print(String content) {
        System.out.println(content);
    }

    public void move(Course course) {

    }

    private void load() {

    }

    private void save() {

    }

    private void exit() {

    }

//    protected void action(Intent intent) {
//        switch (intent) {
//            case LOAD:
//                load();
//                break;
//            case SAVE:
//                save();
//                break;
//            case EXIT:
//                exit();
//                break;
//            default:
//                break;
//        }
//    }

    protected void mapActionToComponent() {

    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}
