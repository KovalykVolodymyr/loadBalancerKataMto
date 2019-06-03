package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final double MAXMUM_LOAD=100.0d;
    public double currentLoadPercetage;
    public int capacity;
    private List<Vm> vms =new ArrayList<Vm>();

    public boolean contains(Vm theVm) {
        return vms.contains(theVm);
    }


    public Server(int capacity) {
        super();
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercetage = loadOfVm(vm);
        this.vms.add(vm);
    }

    private double loadOfVm(Vm vm) {
        return (double) vm.size / (double) capacity * MAXMUM_LOAD;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return currentLoadPercetage + (loadOfVm(vm))<= MAXMUM_LOAD;
    }
}
