package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasCurrentLoaadOf;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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
        assertThat(theServer, hasCurrentLoaadOf(0.0d));
    }

    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm(){
        Server theServer = a(server().withCapacity(1));
        Vm theVm = a(vm().ofSize(1));
        balancing(aServersListWith(theServer), aVmsListWith(theVm));

        assertThat(theServer, hasCurrentLoaadOf(100.0d));
        assertThat("the server should contain vm", theServer.contains(theVm));
    }
    @Test
    public void balancingOneServerWithTenSlotCapacity_andOneSlotVm_fillsTheServerWithTHENpercent(){
        Server theServer = a(server().withCapacity(10));
        Vm theVm = a(vm().ofSize(1));
        balancing(aServersListWith(theServer), aVmsListWith(theVm));

        assertThat(theServer, hasCurrentLoaadOf(10.0d));
        assertThat("the server should contain vm", theServer.contains(theVm));
    }

    private Vm[] aVmsListWith(Vm vm) {
        return new Vm[]{vm};
    }

    private Vm a(VmBuilder builder) {
        return builder.build();
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

    private <T> T a(Builder<T> builder){
       return  builder.build();
    }

}
