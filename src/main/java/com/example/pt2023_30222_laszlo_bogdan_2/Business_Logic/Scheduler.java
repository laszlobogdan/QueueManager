package
        com.example.pt2023_30222_laszlo_bogdan_2.Business_Logic;

import com.example.pt2023_30222_laszlo_bogdan_2.Model.Client;
import com.example.pt2023_30222_laszlo_bogdan_2.Model.Server;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Server> servers;
    private int maxNoServers;
    private int maxClientsPerServer;
    private Thread thread;
    public Scheduler(int maxNoServers,int maxClientsPerServer){
        this.maxNoServers=maxNoServers;
        this.maxClientsPerServer=maxClientsPerServer;
        this.servers=new ArrayList<Server>(maxClientsPerServer);
        for(int i=0;i<maxNoServers;i++){
            Server serv=new Server();
            this.servers.add(serv);
        }
        for(Server s:servers){
            thread=new Thread(s);
            thread.start();
        }
    }

    public void dispatchClient(Client client){
        Strategy.addClient(client, servers);
    }
    public ArrayList<Server> getServers(){
        return servers;
    }


}