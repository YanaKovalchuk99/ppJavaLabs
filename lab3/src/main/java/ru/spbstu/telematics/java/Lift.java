package ru.spbstu.telematics.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Lift {
private int n_passengers;
ReentrantLock locker;
    Lift(){
	 locker = new ReentrantLock(); 
     n_passengers = 0;
    }
    public void enter() {
    		n_passengers++;
    }
    public void exit() {
		n_passengers--;
    }
    public void process() {
        locker.lock();
        if(n_passengers<10)
            enter();
        else
           	exit();
        System.out.println("Количество пассажиров в лифте:" + n_passengers);
        locker.unlock();
    }
}

