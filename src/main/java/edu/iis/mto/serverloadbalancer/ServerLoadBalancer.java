package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm:vms){
            Server lessLoadServer =null;
            for (Server server :servers){
                if (lessLoadServer == null || server.currentLoadPercetage <lessLoadServer.currentLoadPercetage){
                    lessLoadServer = server;
                }
            }
           lessLoadServer.addVm(vm);
        }
    }


}
