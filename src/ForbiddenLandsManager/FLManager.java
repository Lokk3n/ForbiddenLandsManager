package ForbiddenLandsManager;

import ForbiddenLandsManager.Model.ModelFactory;
import ForbiddenLandsManager.Utilities.Event;
import ForbiddenLandsManager.Utilities.EventAggregator;
import ForbiddenLandsManager.Utilities.Events.TestEvent;
import ForbiddenLandsManager.Utilities.NavigationParameters;
import ForbiddenLandsManager.Utilities.RegionManager;
import ForbiddenLandsManager.View.MainTestView;
import ForbiddenLandsManager.View.TestView1;
import ForbiddenLandsManager.View.TestView2;
import ForbiddenLandsManager.View.ViewHandler;
import ForbiddenLandsManager.ViewModel.TestViewModel1;
import ForbiddenLandsManager.ViewModel.TestViewModel2;
import ForbiddenLandsManager.ViewModel.ViewModelFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FLManager extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory mf = new ModelFactory();
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler viewHandler = new ViewHandler(stage, vmf);
        RegionManager regionManager = new RegionManager();
        viewHandler.registerViewViewModel(TestView1.class, TestViewModel1.class);
        viewHandler.registerViewViewModel(TestView2.class, TestViewModel2.class);
        stage.setTitle("Forbidden Lands Manager");
        MainTestView mtv = new MainTestView();
        Scene scene = new Scene(mtv);
        stage.setScene(scene);
        stage.show();
        regionManager.requestNavigate("region1", TestView1.class, new NavigationParameters());
        Thread.sleep(5000);
        regionManager.requestNavigate("region1", TestView2.class, new NavigationParameters());

        TestEvent tev = EventAggregator.getEvent(TestEvent.class);

        tev.fire("hejho");
    }
}
