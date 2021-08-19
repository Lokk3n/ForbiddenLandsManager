package ForbiddenLandsManager.Utilities;

import ForbiddenLandsManager.View.View;

import java.util.HashMap;

public class RegionManager {
    private HashMap<String, Region> regionMap = new HashMap<>();

    public void registerRegion(String name, Region region){
        if(regionMap.containsKey(name)){
            regionMap.remove(name);
        }
        regionMap.put(name, region);
    }

    public void requestNavigate(String regionName, Class<? extends View> view, NavigationParameters parameters){
        regionMap.get(regionName).requestNavigate(view, parameters);
    }
}
