package com.example.pt2023_30222_laszlo_bogdan_2.Model;
public class Client {
    private int arrivalTime;
    private int serviceTime;
    private int id;
    private static int index=0;
    public Client(int arrivalTime,int serviceTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        id=++index;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
    public void decServiseTime(){
        serviceTime--;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getId() {
        return id;
    }
}
