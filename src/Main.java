import controller.Controller;
import model.Model;
import view.MainWindow;

public class Main {
    public static void main (String [] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        MainWindow mainWindow = new MainWindow(controller);
    }

}
