package edu.iis.mto.serverloadbalancer;

public class VmBuilder implements Builder<Vm>{
    private int size;
    public ServerBuilder ofSize(int size) {
        this.size=size;
        return this;
    }

    public Vm build() {
        return new Vm();
    }

    public VmBuilder vm() {
        return new VmBuilder() ;
    }
}
