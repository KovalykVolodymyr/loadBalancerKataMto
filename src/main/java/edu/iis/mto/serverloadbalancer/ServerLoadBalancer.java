package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm:vms){
            addToLessLoadedServer(servers, vm);
        }
    }

    private void addToLessLoadedServer(Server[] servers, Vm vm) {
        Server lessLoadServer = findLessLoadedServer(servers);
        lessLoadServer.addVm(vm);
    }

    private Server findLessLoadedServer(Server[] servers) {
        Server lessLoadServer =null;
        for (Server server :servers){
            if (lessLoadServer == null || server.currentLoadPercetage <lessLoadServer.currentLoadPercetage){
                lessLoadServer = server;
            }
        }
        return lessLoadServer;
    }


}
