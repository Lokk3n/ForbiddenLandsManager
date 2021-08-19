package ForbiddenLandsManager;

import ForbiddenLandsManager.Model.ModelFactory;
import ForbiddenLandsManager.Utilities.Event;
import ForbiddenLandsManager.Utilities.EventAggregator;
import ForbiddenLandsManager.Utilities.Events.TestEvent;
import ForbiddenLandsManager.View.ViewHandler;
import ForbiddenLandsManager.ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class FLManager extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler viewHandler = new ViewHandler(stage, vmf);


        TestEvent tev = EventAggregator.getEvent(TestEvent.class);

        tev.fire("hejho");
    }
}
