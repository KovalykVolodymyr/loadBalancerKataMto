package edu.iis.mto.serverloadbalancer;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


import org.hamcrest.Matcher;
import org.junit.Test;

public class ServerLoadBalancerTest {
    @Test
    public void itCompiles() {
        assertThat(true, equalTo(true));
    }


    private ServerBuilder server() {
        return new ServerBuilder();
    }

    @Test
    public void balanciServerWithoutVmServerEmpty() {
        Server theServer = a(server().withCapacity(1));
        balancing(aServersListWith(theServer), anEmtyList());
        assertThat(theServer, CurrentLoadPercentageMatcher.hasCurrentLoaadOf(0.0d));
    }

    @Test
    public void balancingOneServerWithoneSlotCapacity_andOneSlotVm_fillisServerWithTheVm() {

        Server theServer = a(server().withCapacity(1));
        Vm theVm =a(vm().ofSize(1));
        balancing(aServersListWith(theServer), aVmListWith(theVm));
        assertThat(theServer, CurrentLoadPercentageMatcher.hasCurrentLoaadOf(100.0d));
        assertThat("server should contain the vm ",theServer.contains(theVm));
    }

    private Vm[] aVmListWith(Vm... vms) {
        return vms;
    }

    private VmBuilder a(VmBuilder builder) {
        return builder.build() ;
    }

    private VmBuilder vm() {
        return new VmBuilder() ;
    }

    private void balancing(Server[] servers, Vm[] vms) {
        new ServerLoadBalancer().balance(servers, vms);
    }

    private Vm[] anEmtyList() {
        return new Vm[0];
    }

    private Server[] aServersListWith(Server... servers) {
        return servers;
    }

    private Server a(ServerBuilder builder) {
        return builder.build();
    }


}
