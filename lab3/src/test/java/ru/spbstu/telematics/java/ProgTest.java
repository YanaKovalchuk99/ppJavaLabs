package ru.spbstu.telematics.java;

import static org.junit.Assert.*;


import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ProgTest extends TestCase
{
  
    public ProgTest(String testName)
    {
        super(testName);
    }
    
   
   public static Test suite()
    {
        return new TestSuite(ProgTest.class);
    }
    
  
    public void testProg()
    {
        assertTrue(true);
    }
}