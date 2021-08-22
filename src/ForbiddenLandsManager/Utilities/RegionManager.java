package ForbiddenLandsManager.Utilities;

import ForbiddenLandsManager.View.View;

import java.util.HashMap;

public class RegionManager {
    private HashMap<String, Region> regionMap = new HashMap<>();

    static private RegionManager instance;

    public RegionManager(){
        instance = this;
    }

    public static RegionManager getInstance(){
        return instance;
    }

    public void registerRegion(String name, Region region){
        regionMap.remove(name);
        regionMap.put(name, region);
    }

    public void requestNavigate(String regionName, Class view, NavigationParameters parameters){
        regionMap.get(regionName).requestNavigate(view, parameters);
    }
}
