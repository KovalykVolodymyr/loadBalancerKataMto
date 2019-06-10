package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {


        for (Vm vm : vms) {
            addToLessLoadedServer(servers, vm);

        }

    }

    private void addToLessLoadedServer(Server[] servers, Vm vm) {
        List<Server> capacleServers = findeCableServers(servers, vm);

        Server lessLoadServer = findLessLoadedServer(capacleServers);
            if (lessLoadServer != null){
                lessLoadServer.addVm(vm);
            }
        lessLoadServer.addVm(vm);
    }

    private List<Server> findeCableServers(Server[] servers, Vm vm) {
        List<Server> capacleServers = new ArrayList<Server>();
        for(Server server : servers){
            if (server.canFit(vm)){
                capacleServers.add(server);

            }
        }
        return capacleServers;
    }

    private Server findLessLoadedServer(List<Server> servers) {
        Server lessLoadServer = null;
        for (Server server : servers) {
            if (lessLoadServer == null || server.getCurrentLoadPercentage() < lessLoadServer.getCurrentLoadPercentage()){
                lessLoadServer = server;

            }
        }
        return lessLoadServer;
    }
}
