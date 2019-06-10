package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {
    private int expecteVmsCount;

    public ServerVmsCountMatcher(int expecteVmsCount) {

        this.expecteVmsCount = expecteVmsCount;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a serverwith vms count of").appendValue(expecteVmsCount);
    }

    @Override
    protected void describeMismatchSafely(Server item, Description description) {
        description.appendText("a serverwith vms count of").appendValue(item.countVms());

    }

    @Override
    protected boolean matchesSafely(Server server) {
        return expecteVmsCount == server.countVms();
    }
    public static ServerVmsCountMatcher hasAVmsCountOf(int expecteVmsCount) {
        return new ServerVmsCountMatcher(expecteVmsCount);
    }
}
