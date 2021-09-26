package ForbiddenLandsManager.Playground;

import ForbiddenLandsManager.Utilities.EventAggregator;
import ForbiddenLandsManager.Utilities.Events.TestEvent;
import ForbiddenLandsManager.Utilities.ServiceLocator;

public class TestClass {
    public int x;

    public TestClass(int x){
        this.x = x;
        TestEvent tev = ServiceLocator.getEventAggregator().getEvent(TestEvent.class);
        tev.subscribe((text) -> {
            printMe((String) text);
            this.x += 1;
        });
    }

    public void printMe(String text){
        System.out.println(x + text);
    }
}
