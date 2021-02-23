package com.company.object;
import com.company.object.CookieCutter;

import java.util.ArrayList;

public class Order {
    int orderId;
    int clientId;
    ArrayList<Integer> features;
    CookieCutter cookieCutter;
    public Order(int orderId_){
        orderId = orderId_;
    }

    public CookieCutter getCc() {
        return cookieCutter;
    }

    public void setCookieCutter(CookieCutter cc) {
        this.cookieCutter = cc;
    }

    public ArrayList<Integer> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<Integer> features) {
        this.features = features;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int calculateTotal(){
        if(this.getCc() == null) return 0;

        int total = 0;

        total += this.getCc().getBasePrice();

        for(int cost : this.getFeatures()) {
            total += this.getCc().getFeaturePrice(cost);
        }
        return total;
    }
}
