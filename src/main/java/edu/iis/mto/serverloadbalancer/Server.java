package edu.iis.mto.serverloadbalancer;

public class Server {


    public double currentLoadPercetage;
    public int capacity;

    public boolean contains(Vm theVm) {
        return true;
    }


    public Server(int capacity) {
        super();
        this.capacity = capacity;
    }
}
