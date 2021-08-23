package ForbiddenLandsManager.View;

import ForbiddenLandsManager.ViewModel.TestViewModel1;
import javafx.scene.control.Label;

public class TestView1 extends View{

    Label text1 = new Label("Test View 1");

    public TestView1(){
        this.getChildren().add(text1);
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }
}
