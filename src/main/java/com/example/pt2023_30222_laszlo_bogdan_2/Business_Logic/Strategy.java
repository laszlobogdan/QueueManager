package com.example.pt2023_30222_laszlo_bogdan_2.Business_Logic;

import com.example.pt2023_30222_laszlo_bogdan_2.Model.Client;
import com.example.pt2023_30222_laszlo_bogdan_2.Model.Server;

import java.util.ArrayList;

public class Strategy {
    public static void addClient(Client client, ArrayList <Server> servers){
        int min=servers.get(0).getWaitingPeriod().get();
        Server server=servers.get(0);
        for(Server s:servers){
            if(min>s.getWaitingPeriod().get()){
                min=s.getWaitingPeriod().get();
                server=s;
            }
        }
        server.addClient(client);
    }
}
