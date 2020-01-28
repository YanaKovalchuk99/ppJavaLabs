package ru.spbstu.telematics.java;


public class Passenger implements Runnable{
private Lift lift;
Passenger(Lift l){
	lift = l;
}
@Override
public void run() {
	
	lift.process();
}
}
