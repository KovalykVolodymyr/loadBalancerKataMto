package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server>{
    private int capacity;
    private double initalLoad;
    public ServerBuilder withCapacity(int capacity){
        this.capacity= capacity;
        return this;
    }
    private VmBuilder vm() {
        return new VmBuilder();
    }

    public  Server build() {
        Server server = new Server(capacity);
        if (initalLoad >0) {
            int initialVmsize = (int) (initalLoad / (double) capacity * 100.0d);
            Vm initialVm = VmBuilder.vm().ofSize(initialVmsize).build();
            server.addVm(initialVm);
        }
        return server;
    }

    public static ServerBuilder  server (){
        return new ServerBuilder();
    }

    public  ServerBuilder withCurrentLoadOf(double initalLoad){
        this.initalLoad = initalLoad;
        return this;
    }
}
