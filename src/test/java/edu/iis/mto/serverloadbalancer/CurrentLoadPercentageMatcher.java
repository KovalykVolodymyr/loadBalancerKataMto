package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CurrentLoadPercentageMatcher extends TypeSafeMatcher<Server> {
    public static final double KPSLION = 0.01d;
    private double expectedLoadPercentage;

    public CurrentLoadPercentageMatcher(double expectedLoadPercentage) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }


    public void describeTo(Description description) {
        description.appendText("a server with load percentage of").appendValue(expectedLoadPercentage);
    }


    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a server with load percentage of").appendValue(item.getCurrentLoadPercetage());
    }

    @Override
    protected boolean matchesSafely(Server server) {

//
        return doubblesAreEqual(expectedLoadPercentage, server.getCurrentLoadPercetage());
    }

    private boolean doubblesAreEqual(double d1, double d2) {
        return d1 == d2 || Math.abs(d1 - d2) < KPSLION;
    }

    public static CurrentLoadPercentageMatcher hasCurrentLoaadOf(double expectedLoadPercentage) {
        return new CurrentLoadPercentageMatcher(expectedLoadPercentage);
    }
}
