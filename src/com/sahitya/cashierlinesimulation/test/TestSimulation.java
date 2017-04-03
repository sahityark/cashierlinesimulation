package com.sahitya.cashierlinesimulation.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.sahitya.cashierlinesimulation.CashierLineHelper;
import com.sahitya.cashierlinesimulation.CashierLineMain;
import com.sahitya.cashierlinesimulation.RegisterService;


/*
 * This class is to test the cashier line simulation
 */
public class TestSimulation {

    @Test
    public void test() {
        String[] path = {
                "input.txt","input1.txt","input2.txt","input3.txt","input4.txt"};
        int[] actualTime={7,13,6,9,11};
        for (int i = 0; i < path.length; i++) {
            String[] input={path[i]};
            CashierLineMain clm = CashierLineHelper.initiateSimulation(input);
            RegisterService rs = clm.getRegisterService();
            int time = CashierLineMain.calculateTime(rs,
                    clm);
            assertTest(time,actualTime[i]);
        }
    }

    /*
     * This method checks if the expectedTime and actualTime are equal or not.
     */
    public void assertTest(int expectedTime,int actualTime) {
        assertEquals(expectedTime, actualTime);
    }


}

