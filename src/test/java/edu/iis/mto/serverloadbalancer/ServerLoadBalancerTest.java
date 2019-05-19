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
	public void balanciServerWithoutVmServerEmpty (){
		Server theServer = a(server().withCapacity(1));
		balancing(aServersListWith(theServer),anEmtyList());
		assertThat(theServer,CurrentLoadPercentageMatcher.hasCurrentLoaadOf(0.0d));
	}



	private void balancing(Server[] servers, Vm[] vms) {
		new ServerLoadBalancer().balance(servers,vms);
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
