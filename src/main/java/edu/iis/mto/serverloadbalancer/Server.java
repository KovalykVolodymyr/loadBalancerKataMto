package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {
    public static final double MASIMUM_LOAD = 100.0d;
    private double currentLoadPercentage;
    private int capacity;

    private List<Vm> vms = new ArrayList<Vm>();
    public boolean contains(Vm theVm) {
        return vms.contains(theVm);
    }

    public Server(int capacity) {
        super();
        this.capacity =capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage+= loadOFVm(vm);
        this.vms.add(vm);
    }

    private double loadOFVm(Vm vm) {
        return (double) vm.getSize() / (double) getCapacity() * MASIMUM_LOAD;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return getCurrentLoadPercentage() + (loadOFVm(vm))<= MASIMUM_LOAD;
    }

    public int getCapacity() {
        return capacity;
    }


    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }


}
