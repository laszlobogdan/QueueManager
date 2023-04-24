package com.example.pt2023_30222_laszlo_bogdan_2.Business_Logic;

import com.example.pt2023_30222_laszlo_bogdan_2.Model.Client;
import com.example.pt2023_30222_laszlo_bogdan_2.Model.Server;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.random;

public class SimulationManager implements Runnable {
    private FileWriter log;
    public int timeLimit=200;
    public int maxProcessingTime=9;
    public int minProcessingTime=3;
    public int numberOfServers=20;
    public int numberOfClients=1000;
    private int simTime;
    private int minArrivalTime=10;
    private int maxArrivalTime=100;
    private Scheduler scheduler;
    //private SimulationFrame frame;
    private ArrayList<Client> generatedClients;
    public SimulationManager(){
        generateNRandomClients();
        scheduler= new Scheduler(numberOfServers,numberOfClients);
        simTime=0;
        try{
            log=new FileWriter("src/main/java/Log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateNRandomClients(){
        generatedClients=new ArrayList<Client>();
        for(int i=0;i<numberOfClients;i++) {
            int randNrProc = (int)Math.floor(Math.random() * (maxProcessingTime - minProcessingTime + 1) + minProcessingTime);
            int randNrArr =(int)Math.floor(Math.random() * (maxArrivalTime - minArrivalTime + 1) + minArrivalTime);
            generatedClients.add(new Client(randNrArr, randNrProc));
        }
    }

    public String toString(){
        String s="Time "+String.valueOf(simTime)+"\n"+"Waiting clients: ";
        for(Client c:generatedClients){
            if(c.getArrivalTime()>simTime){
                s+="("+String.valueOf(c.getId())+","+String.valueOf(c.getArrivalTime())+","+String.valueOf(c.getServiceTime())+") ";
            }
        }
        for(Server a:scheduler.getServers()){
            s+="\nQueue"+String.valueOf(a.getId())+": ";
            if(a.getCurrClient()==null && a.getClients().isEmpty()){
                s+="closed";
            }else{
                if(a.getCurrClient()!=null) {
                    s += "(" + String.valueOf(a.getCurrClient().getId()) + "," + String.valueOf(a.getCurrClient().getArrivalTime()) + "," + String.valueOf(a.getCurrClient().getServiceTime()) + ") ";
                    for (Client c : a.getClients()) {
                        s += "(" + String.valueOf(c.getId()) + "," + String.valueOf(c.getArrivalTime()) + "," + String.valueOf(c.getServiceTime()) + ") ";
                    }
                }
            }
        }
        s+="\n";
        return s;
    }
    public void closeFile(){
        try{
            log.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeFile(String string){
        try{
            log.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        simTime=0;
        while(simTime<timeLimit){
            for(Client c:generatedClients){
                if(simTime==c.getArrivalTime()){
                    scheduler.dispatchClient(c);
                }
            }
            writeFile(this.toString());
            for(Server s: scheduler.getServers()){
                if(s.getCurrClient()!=null ){
                    s.getCurrClient().decServiseTime();

                }
                if(s.getCurrClient()!=null && s.getCurrClient().getServiceTime()==0){
                    s.setCurrClient(null);
                }
            }

            simTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        for(Server s: scheduler.getServers()){
            s.setRunFalse();
        }
        closeFile();
    }

    public static void main(String[] args) {
        SimulationManager gen=new SimulationManager();
        Thread t=new Thread(gen);
        t.start();
    }

}