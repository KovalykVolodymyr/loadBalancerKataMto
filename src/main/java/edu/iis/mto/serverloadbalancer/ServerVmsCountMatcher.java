package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private int expectedVmsCount;
    public ServerVmsCountMatcher(int expectedVmsCount){
        this.expectedVmsCount=expectedVmsCount;

    }
    public void describeTo(Description description){
        description.appendText("a severs with cmos count of").appendValue(expectedVmsCount);

    }
    @Override
    protected void dscripbeMismatchSafely(Server item,Description description){
        description.appendText("a severs with vmos count of").appendValue(item.countVms());
    }
    @Override
    protected boolean matchesSafely(Server server){
        return expectedVmsCount ==server.countVms();
    }
    public static ServerVmsCountMatcher hasAVmCountOf(int expectedVmCount) {
        return new ServerVmsCountMatcher(expectedVmCount);
    }
}
