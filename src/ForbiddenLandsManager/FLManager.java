package ForbiddenLandsManager;

import ForbiddenLandsManager.Model.ModelFactory;
import ForbiddenLandsManager.Playground.TestClass;
import ForbiddenLandsManager.Utilities.*;
import ForbiddenLandsManager.Utilities.Events.NavigationEvent;
import ForbiddenLandsManager.Utilities.Events.TestEvent;
import ForbiddenLandsManager.View.*;
import ForbiddenLandsManager.ViewModel.*;
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
        EventAggregator eventAggregator = new EventAggregator();

        ServiceLocator.registerEventAggregator(eventAggregator);
        ServiceLocator.registerRegionManager(regionManager);
        ServiceLocator.registerViewHandler(viewHandler);
        ServiceLocator.registerViewModelFactory(vmf);

        viewHandler.registerViewViewModel(TestView1.class, TestViewModel1.class);
        viewHandler.registerViewViewModel(TestView2.class, TestViewModel2.class);
        viewHandler.registerViewViewModel(CharacterSheetView.class, CharacterSheetViewModel.class);


        NavigationEvent navEvent = ServiceLocator.getEventAggregator().getEvent(NavigationEvent.class);
        navEvent.subscribe((text)->{
            stage.sizeToScene();
            System.out.println("navigation observed");
        });

        stage.setTitle("Forbidden Lands Manager");
        MainTestView mtv = new MainTestView();
        Scene scene = new Scene(mtv);
        stage.setScene(scene);
        stage.show();
        regionManager.requestNavigate("region1", TestView1.class, new NavigationParameters());
        regionManager.requestNavigate("region1", TestView2.class, new NavigationParameters());
        TestEvent tev = ServiceLocator.getEventAggregator().getEvent(TestEvent.class);

        TestClass tc = new TestClass(5);
        tc.x = 12;
        TestClass tc2 = new TestClass(7);
        tev.fire("hejho");
    }
}
