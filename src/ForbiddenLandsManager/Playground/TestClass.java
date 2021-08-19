package ForbiddenLandsManager.Playground;

import ForbiddenLandsManager.Utilities.EventAggregator;
import ForbiddenLandsManager.Utilities.Events.TestEvent;

public class TestClass {
    public int x;

    public TestClass(int x){
        this.x = x;
        TestEvent tev = EventAggregator.getEvent(TestEvent.class);
        tev.subscribe((text) -> printMe((String) text));
    }

    public void printMe(String text){
        System.out.println(x + text);
    }
}
