/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import toystopinventorymanagementsystem.ToyStopInventoryManagementSystem;
import toystopinventorymanagementsystem.ToyStopService;

/**
 *
 * @author Sohaib
 */
public class UnitTest {
    
    public UnitTest() {
    }
    
    @Test
    public void testSerialization(){
        ToyStopInventoryManagementSystem tsims = new ToyStopInventoryManagementSystem();
        tsims.init();
        tsims.saveData(tsims.tsService);
        tsims.tsService=null;
        //tsims.printAll();
        tsims.tsService=tsims.loadData();
        tsims.printAll();

    
    }

    
}
