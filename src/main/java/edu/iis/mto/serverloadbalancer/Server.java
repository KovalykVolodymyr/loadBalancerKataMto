package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final double MAXMUM_LOAD=100.0d;
    public double currentLoadPercetage;
    public int capacity;
    private List<Vm> vms =new ArrayList<Vm>();

    public boolean contains(Vm theVm) {
        return true;
    }


    public Server(int capacity) {
        super();
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercetage =(double)vm.size/(double)capacity*MAXMUM_LOAD;
        this.vms.add(vm);
    }

    public int countVms() {
        return vms.size();
    }
}
