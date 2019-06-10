package edu.iis.mto.serverloadbalancer;

public class ServerBuilder  implements Builder<Server>{
    private int capacity;
    private double initLoad;

    public ServerBuilder withCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server = new Server(capacity);
        addInitialLoad(server);
        return server;
    }

    private void addInitialLoad(Server server) {
        if(initLoad>0) {
            int initialVmSize = (int) (initLoad / (double) capacity * Server.MASIMUM_LOAD);
            Vm initialVm = VmBuilder.vm().ofsize(initialVmSize).build();
            server.addVm(initialVm);
        }
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withCurrentLoadOf(double initLoad) {
        this.initLoad = initLoad;
        return this;
    }
}
