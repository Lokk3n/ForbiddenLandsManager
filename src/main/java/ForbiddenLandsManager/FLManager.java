package ForbiddenLandsManager;

import ForbiddenLandsManager.Model.ModelFactory;
import ForbiddenLandsManager.NettyClient.NettyClient;
import ForbiddenLandsManager.Playground.TestClass;
import ForbiddenLandsManager.Utilities.*;
import ForbiddenLandsManager.Utilities.Events.NavigationEvent;
import ForbiddenLandsManager.Utilities.Events.TestEvent;
import ForbiddenLandsManager.Utilities.WorkExecutor.WorkExecutor;
import ForbiddenLandsManager.View.*;
import ForbiddenLandsManager.ViewModel.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FLManager extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        NettyClient server = new NettyClient("127.0.0.1", 3663, new WorkExecutor());
        ModelFactory mf = new ModelFactory(server);
        ViewModelFactory vmf = new ViewModelFactory(mf);
        ViewHandler viewHandler = new ViewHandler(stage, vmf);
        RegionManager regionManager = new RegionManager();
        EventAggregator eventAggregator = new EventAggregator();
        DialogManager dialogManager = new DialogManager(stage);
        SessionInstance sessionInstance = new SessionInstance();
        ServerProxy serverProxy = new ServerProxy(server, sessionInstance);

        ServiceLocator.registerEventAggregator(eventAggregator);
        ServiceLocator.registerRegionManager(regionManager);
        ServiceLocator.registerViewHandler(viewHandler);
        ServiceLocator.registerViewModelFactory(vmf);
        ServiceLocator.registerDialogManager(dialogManager);
        ServiceLocator.registerServerProxy(serverProxy);

        viewHandler.registerViewViewModel(TestView1.class, TestViewModel1.class);
        viewHandler.registerViewViewModel(TestView2.class, TestViewModel2.class);
        viewHandler.registerViewViewModel(CharacterSheetView.class, CharacterSheetViewModel.class);
        viewHandler.registerViewViewModel(MainView.class, MainViewModel.class);


        NavigationEvent navEvent = ServiceLocator.getEventAggregator().getEvent(NavigationEvent.class);
        navEvent.subscribe((text)->{
            //stage.sizeToScene();
            System.out.println("navigation observed");
        });

        stage.setTitle("Forbidden Lands Manager");
        MainTestView mtv = new MainTestView();
        MainView mv = new MainView();
        Scene scene = new Scene(mv);
        stage.setScene(scene);
        stage.setMinWidth(700);
        stage.setMinHeight(500);
        mv.prefWidthProperty().bind(stage.widthProperty());
        mv.prefHeightProperty().bind(stage.heightProperty());
        stage.show();
        stage.setOnCloseRequest(e -> {
            System.out.println("Closing app");
            server.close();
        });
        //regionManager.requestNavigate("region1", TestView1.class, new NavigationParameters());
        //regionManager.requestNavigate("region1", TestView2.class, new NavigationParameters());
        TestEvent tev = ServiceLocator.getEventAggregator().getEvent(TestEvent.class);

        TestClass tc = new TestClass(5);
        tc.x = 12;
        TestClass tc2 = new TestClass(7);
        tev.fire("hejho");
        tev.fire("hejho");

    }
}
