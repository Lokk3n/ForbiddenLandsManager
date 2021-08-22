package ForbiddenLandsManager.Utilities;

import ForbiddenLandsManager.View.ViewHandler;
import ForbiddenLandsManager.ViewModel.ViewModelFactory;

import java.util.HashMap;

public class ServiceLocator {
    //RegionManager
    //ViewModelFactory
    //ViewHandler
    //ModelFactory
    static HashMap<String, RegionManager> regionManagers = new HashMap<>();
    public static RegionManager getRegionManager(){
        return getRegionManager(null);
    }
    public static RegionManager getRegionManager(String name){
        return regionManagers.get(name);
    }
    public static void registerRegionManager(RegionManager regionManager){
        registerRegionManager(null, regionManager);
    }
    public static void registerRegionManager(String name, RegionManager regionManager){
        regionManagers.put(name, regionManager);
    }

    static ViewModelFactory vmFactory;
    public static void registerViewModelFactory(ViewModelFactory vmf){
        vmFactory = vmf;
    }
    public static ViewModelFactory getViewModelFactory(){
        return vmFactory;
    }

    static ViewHandler vHandler;
    public static void registerViewHandler(ViewHandler vh){
        vHandler = vh;
    }
    public static ViewHandler getViewHandler(){
        return vHandler;
    }

    private static EventAggregator eventAggregator;
    public static EventAggregator getEventAggregator(){
        return eventAggregator;
    }
    public static void registerEventAggregator(EventAggregator evAgr){
        eventAggregator = evAgr;
    }
}
