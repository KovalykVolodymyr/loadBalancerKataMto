package edu.iis.mto.serverloadbalancer;

public class Server {

    public static final double MAXMUM_LOAD=100.0d;
    public double currentLoadPercetage;
    public int capacity;

    public boolean contains(Vm theVm) {
        return true;
    }


    public Server(int capacity) {
        super();
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercetage =(double)vm.size/(double)capacity*MAXMUM_LOAD;
    }
}
