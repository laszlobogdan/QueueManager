package com.example.pt2023_30222_laszlo_bogdan_2.Model;

import com.example.pt2023_30222_laszlo_bogdan_2.Model.Client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{
    boolean run;
    private ArrayBlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;
    private Client currClient;
    private int id;
    private static int index=0;
    public Server(){
        this.clients=new ArrayBlockingQueue<Client>(10000);
        waitingPeriod=new AtomicInteger(0);
        id=++index;
    }
    public void addClient(Client newClient){
        clients.add(newClient);
        waitingPeriod.addAndGet(newClient.getServiceTime());
    }
    public void run(){
        run=true;
        while(run){try{
            if(!clients.isEmpty() || currClient!=null){
                if(currClient==null ||  currClient.getServiceTime()==0){
                    currClient=clients.take();
                }
                Thread.sleep(1000);
                waitingPeriod.decrementAndGet();
            }
        }catch (Exception e){
            run=false;
        }
        }
    }

    public void setRunFalse() {
        this.run = false;
    }

    public ArrayBlockingQueue<Client> getClients(){
        return clients;
    }

    public Client getCurrClient() {
        return currClient;
    }

    public int getId() {
        return id;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setCurrClient(Client currClient) {
        this.currClient = currClient;
    }
}
