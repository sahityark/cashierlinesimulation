package com.sahitya.cashierlinesimulation;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

/*
 * This class is for Registers. It compares to find the correct register for different Type of customers 
 * based on the number of items or number of customers in the Queue.
 */
public class Register implements Comparable<Register> {

    private Queue<Customer> customerList = null;
    private Integer index;

    public Register(int index) {
        this.index = index;
        customerList = new LinkedList<Customer>();
    }

    public Queue<Customer> getCustomerList() {
        return customerList;
    }

    public Integer getIndex() {
        return index;
    }

    /*
     * This is used for sorting the Register Objects by their size. 
     * This will be useful for assigning the correct registers to the customers of Type A.
     */
    public static Comparator<Register> sizeComparator = new Comparator<Register>() {
        @Override
        public int compare(Register o1, Register o2) {
            Integer size = o1.customerList.size();
            Integer size1 = o2.customerList.size();
            return size.compareTo(size1);
        }
    };

    /*
     * This is For sorting the Register Objects by their index which will be useful for assigning
     * the correct register to both the type of customers depending on the situation. 
     */
    @Override
    public int compareTo(Register o) {
        return this.index.compareTo(o.index);
    }
}