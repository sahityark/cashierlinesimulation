package com.sahitya.cashierlinesimulation;

/*
 * This class is to initialize the customer details like, Type, arrival Time and Total number of items they have.
 * Here, we also compare the customers basing on number of items and their Type.
 */
public class Customer implements Comparable<Customer> {

    private Integer totalItem; 
    private Type type;
    private boolean inService;
    private int arrivedTime;
    
    /*
     * constructor for initializing
     */
    public Customer(Type type,int arrivedTime,Integer totalItem) {
        this.type=type;
        this.arrivedTime=arrivedTime;
        this.totalItem=totalItem;
    }

    public int getItemCount() {
        return totalItem;
    }

    public void setItemCount(int totalItem) {
        this.totalItem = totalItem;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isInService() {
        return inService;
    }

    public void setInService(boolean inService) {
        this.inService = inService;
    }

    public int getTimeArrived() {
        return arrivedTime;
    }

    public void setTimeArrived(int registerArrived) {
        this.arrivedTime = registerArrived;
    }

    public Integer servedItems(){
        return --this.totalItem;
    }

    /*
	 * This to compare the customers based on their count of item. If number of items is same
     * then, it compares according to their Type.
     */
    @Override
    public int compareTo(Customer obj) {
        int val = 0;
            val = this.totalItem.compareTo(obj.totalItem);
        if (val == 0) {
            val = this.type.compareTo(obj.type);
        }
        return val;
    }

}
enum Type {
    A, B;
}
