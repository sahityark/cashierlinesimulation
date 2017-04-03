package com.sahitya.cashierlinesimulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Queue;

/*
 * This class is a helper Class which has methods that assign registers and simulate time.
 */
public class CashierLineHelper {
    
    static Queue<Customer> customerQueue=CashierLineMain.getCustomerQueue();
 
    /*
     * This method picks customer from the customerqueue and puts it in a separate list,which
     * will be sorted as per the Type of the customer and number of items the customers hold. 
     * Then, we can assign the customer with least items first.
     */
    public static void collectSameTimeCustomers(
            List<Customer> customerListArrivedAtSameTime, int time) {
        Customer customer = customerQueue.peek();
        while (customer != null && customer.getTimeArrived() == time) {
            customerListArrivedAtSameTime.add(customerQueue.poll());
            customer = customerQueue.peek();
        }

    }


    /*
     * This method is used to serve the customer until all the items are empty. After all the items are done,
     * it removes that customer from the queue.
     */
    public static void experiencedService(Queue<Customer> customer) {
        Customer cust = customer.peek();
        if (cust != null && cust.servedItems() == 0) {
            customer.poll();
        }
    }
  
    /*
     * This method is to serve the customers and remove them from the queue once all the items are served.
     * This method is to indicate the trainee register who take 2 minutes for each item.
     */
    public static void traineeService(Queue<Customer> customer) {
        Customer cust = customer.peek();
        if (cust != null) {
            if (!cust.isInService()) {
                cust.setInService(true);
            } else {
                if (cust.servedItems() == 0) {
                    customer.poll();
                } else {
                    cust.setInService(false);  
                }
            }
        }
    }

    /*
     * This method reads from the line, the number of registers and instantiate the rs object
     * This method also reads the customer records to instantiate the customer objects.
     * Then put them in a customerQueue and return the clm object, which in turn has the rs Object.

     */
    public static CashierLineMain initiateSimulation(String[] args) {
        CashierLineMain clm = null;
        RegisterService rs = null;
        String line = "";
        int firstLine = 0;
        BufferedReader buReader = null;
        try {
            buReader = new BufferedReader(
                    new FileReader(new File(args[0])));
        } catch (FileNotFoundException e) {
            System.out.println("File not found-->" +e.getMessage());
          //abnormal termination
            System.exit(-1);
        }
        try {
            while ((line = buReader.readLine()) != null) {
                if (firstLine == 0) {
                    try {
                        int noOfRegisters = Integer.parseInt(line);
                        rs = new RegisterService(noOfRegisters);
                    } catch (NumberFormatException e) {
                        System.out.println(
                                "Error in parsing number of registers->"
                                        + e.getMessage());
                    }
                } else {
                    Customer customer = buildCustomerObjects(line);
                    customerQueue.offer(customer);
                }
                firstLine++;
            }
            clm = new CashierLineMain(rs);
        } catch (IOException e) {
            System.out.println("Exception in reading the file" +e.getMessage());
          //abnormal termination
            System.exit(-1);
        }
        return clm;
    }

    /*
     * This method reads the line from the file, builds and returns a customer object.
     */
    public static Customer buildCustomerObjects(String line) {
        String[] items = line.split(" ");
        if(items.length!=3){
            System.out.println("Error in Input-->"+line);
            //abnormal termination
            System.exit(-1);
        }
        if (items[0].equals(Type.A.toString())) {
            return new Customer(Type.A, Integer.parseInt(items[1]),
                    Integer.parseInt(items[2]));
        } else if(items[0].equals(Type.B.toString())) {
            return new Customer(Type.B, Integer.parseInt(items[1]),
                    Integer.parseInt(items[2]));
        }else{
            System.out.println("Customer Type is Invalid");
          //abnormal termination
            System.exit(-1);
            return null;
        }
    }
}
