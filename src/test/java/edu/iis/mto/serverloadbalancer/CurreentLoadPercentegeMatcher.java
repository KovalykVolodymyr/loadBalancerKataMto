package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class CurreentLoadPercentegeMatcher extends TypeSafeMatcher<Server> {
    private double excpectedLoadPercentage;

    public CurreentLoadPercentegeMatcher(double excpectedLoadPercentage) {
        this.excpectedLoadPercentage = excpectedLoadPercentage;
    }

    @Override
    protected boolean matchesSafely(Server server) {
        return doublesAreEqual(this.excpectedLoadPercentage, server.currentLoadPercentage);
    }

    private boolean doublesAreEqual(double d1, double d2) {
        return d1 == d2 || Math.abs(d1 - d2)< 0.01;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a server with load percentage of").appendValue(excpectedLoadPercentage);
    }
    public static CurreentLoadPercentegeMatcher hasCurrentLoadOf(double excpectedLoadPercentage) {
        return new CurreentLoadPercentegeMatcher(excpectedLoadPercentage);
    }
}































