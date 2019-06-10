package edu.iis.mto.serverloadbalancer;


import static edu.iis.mto.serverloadbalancer.CurreentLoadPercentegeMatcher.hasCurrentLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
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

@Test
	public void balancingServerWithNoVms_serverStayEmpty(){
		Server theServer =a(server().withCapacity(1));
		balancing(aServerListWith(theServer),anEmptyListOfVm());

		assertThat(theServer, hasCurrentLoadOf(0.0d));

	}
	@Test
	public void balancingOneServerWithOneSloteCapacity_andOneSloteVm_fillsServerwiththeVm(){
		Server theServer =a(server().withCapacity(1));
		Vm theVm =a(vm().ofsize(1));
		balancing(aServerListWith(theServer),aVmsListWith(theVm));

		assertThat(theServer, hasCurrentLoadOf(100.0d));
		assertThat("serve should contain the vm ", theServer.contains(theVm));
	}
	@Test
	public void balancingOnServerWithtenSlotsCapacity_andOneslotVmfillsTheServerWithTenPercent(){
		Server theServer =a(server().withCapacity(10));
		Vm theVm =a(vm().ofsize(1));
		balancing(aServerListWith(theServer),aVmsListWith(theVm));

		assertThat(theServer, hasCurrentLoadOf(10.0d));
		assertThat("serve should contain the vm ", theServer.contains(theVm));
	}

	private Vm[] aVmsListWith(Vm... vms) {
		return vms;
	}




	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers,vms);
	}

	private Vm[] anEmptyListOfVm() {
		return new Vm[0];
	}

	private Server[] aServerListWith(Server... servers) {
		return new Server[0];
	}

	private <T> T a(Builder<T> builder){
		return builder.build();
	}



}
