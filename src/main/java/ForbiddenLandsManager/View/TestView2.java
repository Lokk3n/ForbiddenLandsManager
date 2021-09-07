package ForbiddenLandsManager.View;

import ForbiddenLandsManager.ViewModel.TestViewModel2;
import javafx.scene.control.Label;

public class TestView2 extends View{

    Label text2 = new Label("Test View 2");

    public TestView2(){
        this.getChildren().add(text2);
    }

    @Override
    protected void postSetDataContextUpdates() {

    }

    @Override
    protected void registerBindings() {

    }
}