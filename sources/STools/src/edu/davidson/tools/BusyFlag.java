package edu.davidson.tools;

/**
 * 
 * A class to disallow concurrent access to any sort of 
 *
 */
public class BusyFlag {
	protected Thread busyflag = null;
	protected int busycount = 0;

	public synchronized void getBusyFlag() {
		/**
		 * @j2sNative
		 * 
		 * 			return;
		 * 
		 */
		{
			while (tryGetBusyFlag() == false) {
				try {
					wait();
				} catch (Exception e) {
					;
				}
			}
		}
	}

	/**
	 * @j2sIgnore
	 * 
	 * @return true if busy
	 */
	private synchronized boolean tryGetBusyFlag() { // BH made private. Right?
		if (busyflag == null) {
			busyflag = Thread.currentThread();
			busycount = 1;
			return true;
		}
		if (busyflag == Thread.currentThread()) {
			busycount++;
			return true;
		}
		return false;
	}

	public synchronized void freeBusyFlag() {
		/**
		 * @j2sNative
		 * 
		 * 			return;
		 * 
		 */
		{
			if (getBusyFlagOwner() == Thread.currentThread()) {
				busycount--;
				if (busycount == 0) {
					busyflag = null;
					notify();
				}
			}
		}
	}

	public synchronized Thread getBusyFlagOwner() {
		return busyflag;
	}
}