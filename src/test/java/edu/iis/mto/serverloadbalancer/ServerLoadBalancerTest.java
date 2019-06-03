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

    @Test
    public void balancingTheServerWithEnoughRoom_fillsTheServerWithAllVms(){
        Server theServer = a(server().withCapacity(100));
        Vm theFirstVm = a(vm().ofSize(1));
        Vm theSecondVm = a(vm().ofSize(1));
        balancing(aServersListWith(theServer), aVmsListWith(theFirstVm,theSecondVm));

        assertThat(theServer, ServerVmsCountMatcher.hasAVmCountOf(2));
        assertThat("the server should contain first vm", theServer.contains(theFirstVm));
        assertThat("the server should contain Second vm", theServer.contains(theSecondVm));
    }

    @Test
    public  void vmshouldBebalanceOnLessLoadedServerFirst(){
        Server moreLoadedServer = a(server().withCapacity(100).withCurrentLoadOf(50.0d));
        Server lessLoadedServer = a(server().withCapacity(100).withCurrentLoadOf(45.0d));
        Vm theVm = a(vm().ofSize(10));
        balancing(aServersListWith(moreLoadedServer,lessLoadedServer), aVmsListWith(theVm));

        assertThat("the server should contain  vm", lessLoadedServer.contains(theVm));
        assertThat("the server should contain the vm", !moreLoadedServer.contains(theVm));
    }
    @Test
    public void  balancingServerWithNotEnoughRoom_shouldNotBeFilledWithTheVm(){
        Server theServer = a(server().withCapacity(10).withCurrentLoadOf(90.0d));
        Vm theVm = a(vm().ofSize(2));
        balancing(aServersListWith(theServer), aVmsListWith(theVm));
        assertThat("the server should contain the vm", !theServer.contains(theVm));

    }
     @Test
     public void balancingServerAndVms(){
        Server server1 = a(server().withCapacity(4));
        Server server2 = a(server().withCapacity(6));

        Vm vm1 = a(vm().ofSize(1));
         Vm vm2 = a(vm().ofSize(4));
         Vm vm3 = a(vm().ofSize(2));

         balancing(aServersListWith(server1,server2),aVmsListWith(vm1,vm2,vm3));
         assertThat("the server  1should contain  vm1", server1.contains(vm1));
         assertThat("the server  2should contain  vm2", server1.contains(vm2));
         assertThat("the server  1should contain  vm3", server1.contains(vm3));
         assertThat(server1,hasCurrentLoaadOf(75.0d));
         assertThat(server1,hasCurrentLoaadOf(66.0d));

     }
    private Matcher<? super Server > hasAVmCountOf(int expectedVmCount) {
        return new ServerVmsCountMatcher(expectedVmCount);
    }

    private Vm[] aVmsListWith(Vm... vm) {
        return vm;
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
