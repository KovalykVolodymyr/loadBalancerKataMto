package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm:vms){
            addToLessLoadedServer(servers, vm);
        }
    }

    private void addToLessLoadedServer(Server[] servers, Vm vm) {
        List<Server> capableServers = findCapableServers(servers, vm);
        Server lessLoadServer = findLessLoadedServer(capableServers);
         if (lessLoadServer !=null){
        lessLoadServer.addVm(vm);
         }
    }

    private List<Server> findCapableServers(Server[] servers, Vm vm) {
        List <Server> capableServers = new ArrayList<Server>();
        for (Server server:servers){
            if (server.canFit(vm)){
                capableServers.add(server);
            }
        }
        return capableServers;
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
