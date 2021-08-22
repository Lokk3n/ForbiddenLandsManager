package ForbiddenLandsManager.Utilities;

import ForbiddenLandsManager.Utilities.Events.NavigationEvent;
import ForbiddenLandsManager.View.View;

import java.security.Provider;
import java.security.SecureRandom;
import java.util.HashMap;

public class RegionManager {
    private HashMap<String, Region> regionMap = new HashMap<>();
    //static private RegionManager instance;

    public RegionManager(){
        ServiceLocator.registerRegionManager(this);
    }

    public RegionManager(String name){
        ServiceLocator.registerRegionManager(name, this);
    }

    public static RegionManager getInstance(){
        return ServiceLocator.getRegionManager();
    }
    public static RegionManager getInstance(String name) { return ServiceLocator.getRegionManager(name); }

    public void registerRegion(String name, Region region){
        regionMap.remove(name);
        regionMap.put(name, region);
    }

    public void requestNavigate(String regionName, Class view, NavigationParameters parameters){
        regionMap.get(regionName).requestNavigate(view, parameters);
    }
}
