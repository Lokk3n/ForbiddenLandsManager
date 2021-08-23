package ForbiddenLandsManager.View;

import ForbiddenLandsManager.ViewModel.TestViewModel1;
import javafx.scene.control.Label;

public class TestView1 extends View<TestViewModel1>{

    Label text1 = new Label("Test View 1");

    public TestView1(){
        this.getChildren().add(text1);
    }
}
