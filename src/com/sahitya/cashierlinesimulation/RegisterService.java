package com.sahitya.cashierlinesimulation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * This class has a list of Registers which will be put into a registerList.
 * The registerList will hold Register Objects.
 */
public class RegisterService {

    private List<Register> registerList = new ArrayList<Register>();
    
    /*
     *  This constructor is used to initialize the RegisterService with the number of registers.
     */
    public RegisterService(int registers) {
        for (int i = 0; i < registers; i++) {
            registerList.add(new Register(i));
        }
    }

    public List<Register> getRegisters() {
        return registerList;
    }
    
    /*
     * This method helps customer of Type to choose a register
     */
    public Register getShortRegisterBySize() {
        List<Register> sortedList = new ArrayList<Register>();
        for (Register register : registerList) {
            sortedList.add(register);
        }
        Collections.sort(sortedList, Register.sizeComparator);
        return sortedList.get(0);
    }
    
    /*
     * This method helps customer of Type to choose a register
     */
    public Register getShortRegisterByIndex() {
        List<Register> sortedListByIndex = new ArrayList<Register>();
        for (Register register : registerList) {
            sortedListByIndex.add(register);
        }
        Collections.sort(sortedListByIndex);
        return sortedListByIndex.get(0);
    }


    /*
     * This method helps the customer of Type B to choose a register who chooses the last customer with less number of items or
     * looks for an empty register.
     */
    public Register getRegisterLeastItemsEnd() {
        Map<Customer,Register> custRegMap=new HashMap<Customer,Register>();
        List<Register> emptyList = new ArrayList<Register>();
        List<Customer> listWithItems = new ArrayList<Customer>();
        for (Register register : registerList) {
            if (register.getCustomerList().size() == 0) {
                emptyList.add(register);
            } else {
                Customer lastCustomer=getLastElement(register.getCustomerList());
                custRegMap.put(lastCustomer, register);
                listWithItems.add(lastCustomer);
            }
        }
        if (emptyList.size() > 0) {
            Collections.sort(emptyList);
            return emptyList.get(0);
        } else {
            Collections.sort(listWithItems);
            return custRegMap.get(listWithItems.get(0));
        }
    }
 
    /*
     * This method is used to get the Last element from the customer queue.
     */
    private Customer getLastElement(Queue<Customer> customerList) {
        Customer lastCustomer=null;
        Iterator<Customer> iterator=customerList.iterator();
        while (iterator.hasNext()) {
            lastCustomer=iterator.next();
        }
        return lastCustomer;
    }

    /*
     * This method is used to provide service to the customers basing on the Type of the customer and their conditions of choosing a register
     */
    public void serviceCustomer(List<Customer> customerList) {
        for (Customer customer : customerList) {
            if (customer.getType().equals(Type.A)) {
                Register shortestRegister = getShortRegisterBySize();
                shortestRegister.getCustomerList().offer(customer);
            } else {
                Register registerwithleastItems = getRegisterLeastItemsEnd();
                registerwithleastItems.getCustomerList().offer(customer);
            }
        }
    }


    /*
     * This method checks if any register is serving customers.
     */
    public boolean isRegisterinService() {
        for (Register register : registerList) {
            if (register.getCustomerList().size() != 0)
                return true;
        }
        return false;
    }

}