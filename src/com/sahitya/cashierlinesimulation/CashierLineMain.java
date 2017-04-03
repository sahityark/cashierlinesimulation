package com.sahitya.cashierlinesimulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/*
 * This is the Main class which simulates cashier line simulation.
 */
public class CashierLineMain {

    private static Queue<Customer> customerQueue = new LinkedList<Customer>();
    RegisterService registerService = null;
    
    public CashierLineMain(RegisterService registerService) {
        this.registerService = registerService;
    }
    
    public RegisterService getRegisterService() {
        return registerService;
    }
    
    protected static Queue<Customer> getCustomerQueue() {
        return customerQueue;
    }
    /*
     * This is the main method where the file name is read from  
     * and the result is printed to the console
     */
    public static void main(String args[]) {
    	Scanner sc = new Scanner(System.in);
    	String filename = sc.next();
    	String[] str = filename.split(" ");
        CashierLineMain groceryMain = CashierLineHelper.initiateSimulation(str);
        RegisterService registerService = groceryMain.getRegisterService();
        int totalTime=calculateTime(registerService, groceryMain);
        System.out.println("Finished at: t=" + totalTime + " minutes");
    }    
    
    /*
     * This method is to calculate the Time of the simulation, the simulation is executed until there are customers
     * or any register is in service. For every iteration we assign customers to the available registers
     * and increment the time.
     */
    public static int calculateTime(RegisterService registerService,CashierLineMain groceryMain){
        int time = 1;
        while (!customerQueue.isEmpty() || registerService.isRegisterinService()) {
            List<Customer> customerListArrivedAtSameTime = new ArrayList<Customer>();
            CashierLineHelper.collectSameTimeCustomers(customerListArrivedAtSameTime, time);
            //Customers arrived at same time are sorted first according to their item count,
            //if that is same then according to their type.
            Collections.sort(customerListArrivedAtSameTime);
            registerService.serviceCustomer(customerListArrivedAtSameTime);
            int index = 0;
            while (index < registerService.getRegisters().size()) {
                //getting the customerList of the register and 
                //passing it to the serve methods where the customer get served
                Queue<Customer> customer = registerService.getRegisters().get(index).getCustomerList();
                if (index == registerService.getRegisters().size() - 1) {
                    CashierLineHelper.traineeService(customer);
                } else {
                    CashierLineHelper.experiencedService(customer);
                }
                index++;
            }
            time++;
        }
        return time;
    }
}