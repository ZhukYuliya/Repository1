package by.training.logisticbase.entity;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class TruckSemaphoreWithPriority {
	private final static Logger logger = Logger.getLogger(TruckSemaphoreWithPriority.class);

	private final int terminalsQuantity;
	private int occupiedTerminals;
	private LinkedList<Truck> waitingTrucks;

	public TruckSemaphoreWithPriority(int terminalsQuantity) {
		this.terminalsQuantity = terminalsQuantity;
		waitingTrucks = new LinkedList<Truck>();
	}

	private final Lock lock = new ReentrantLock();
	private final Condition shouldWait = lock.newCondition();

	public void acquire() throws InterruptedException {
		lock.lock();
		Truck truck = (Truck) Thread.currentThread();
		logger.info(truck.getOrderNumber() + ", has perishable cargo: " + truck.hasPerishableCargo()
		        + " joined queue");
		addTruckToQueue(truck);
		// the queue of waiting trucks is logged to check the correct addition to queue of trucks with
		//perishable and non-perishable goods at every step of running the programm
		logger.info(waitingTrucks);
		while (terminalsQuantity == occupiedTerminals || !truck.equals(waitingTrucks.peekFirst())) {
			shouldWait.await();
		}
		waitingTrucks.pollFirst();
		// if the truck was not the first in the queue, but there are still free terminals 
		//in the warehouse, the current first truck in the queue signals other to prevent them from
		// being blocked in waiting in vane
		shouldWait.signalAll();
		occupiedTerminals++;
		logger.info(truck.getOrderNumber() + ", has perishable cargo: " + truck.hasPerishableCargo()
		        + " started loading/unloading");
		lock.unlock();
	}

	public void release() {
		lock.lock();
		Truck truck = (Truck) Thread.currentThread();
		logger.info(truck.getOrderNumber() + ", has perishable cargo: " + truck.hasPerishableCargo()
		        + " has left the warehouse");
		shouldWait.signalAll();
		occupiedTerminals--;
		lock.unlock();

	}

	private void addTruckToQueue(Truck truck) {
		boolean inserted = false;
		// tries to insert a new arrived perishable truck before non perishable, but after 
		// the perishable trucks that arrived first
		// if there are no perishable trucks in the queue or the arrived truck itself is non perishable,
		// just adds to the end
		if (truck.hasPerishableCargo()) {
			ListIterator<Truck> iter = waitingTrucks.listIterator();
			while (iter.hasNext()) {
				if (!iter.next().hasPerishableCargo()) {
					iter.previous();
					iter.add(truck);
					inserted = true;
					break;
				}
			}
		}
		if (!inserted) {
			waitingTrucks.addLast(truck);
		}
	}
}
