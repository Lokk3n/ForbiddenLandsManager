package ForbiddenLandsManager;

import ForbiddenLandsManager.Playground.TestClass;
import javafx.application.Application;

public class StartFLManager {

    public static void main(String[] args) {

        TestClass tc = new TestClass(5);
        tc.x = 12;
        TestClass tc2 = new TestClass(7);
        Application.launch(FLManager.class);
    }
}
