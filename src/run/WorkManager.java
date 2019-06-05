package run;

import java.io.Serializable;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Used to run parallel Runnable tasks
 * 
 * @author Oliver
 *
 */
public class WorkManager implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer maxAvailableProcessors;
	private Stack<Runnable> stack;

	/**
	 * Limits the number of threads that can be used to execute tasks
	 * 
	 * @param maxAvailableProcessors
	 *            Maximum amount of threads
	 */
	public WorkManager(Integer maxAvailableProcessors) {
		stack = new Stack<Runnable>();
		this.maxAvailableProcessors = maxAvailableProcessors;
	}

	/**
	 * No limit is imposed on the number of threads that can be used to execute
	 * tasks; all threads available in the machine will be used
	 */
	public WorkManager() {
		stack = new Stack<Runnable>();
		maxAvailableProcessors = null;
	}

	/**
	 * Limits the number of threads that can be used to execute tasks
	 * 
	 * @param maxAvailableProcessors
	 *            Maximum amount of threads
	 * @return This
	 */
	public WorkManager setMaxAvailableProcessors(Integer maxAvailableProcessors) {
		this.maxAvailableProcessors = maxAvailableProcessors;
		return this;
	}

	/**
	 * Adds a Runnable task to be executed afterwards using the runJobs() method
	 * 
	 * @param job
	 *            Task
	 * @return This
	 */
	public WorkManager addJob(Runnable job) {
		stack.push(job);
		return this;
	}

	/**
	 * Runs all the jobs sent via the addJob() method
	 * 
	 * @return This
	 */
	public WorkManager runJobs() {
		int availableProcessors = Runtime.getRuntime().availableProcessors();
		if (maxAvailableProcessors != null) {
			availableProcessors = Math.min(availableProcessors, maxAvailableProcessors);
		}
		ExecutorService es = Executors.newFixedThreadPool(availableProcessors);
		while (!stack.isEmpty()) {
			Runnable nextJob = stack.pop();
			es.execute(nextJob);
		}
		es.shutdown();
		try {
			es.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			// handle exception
		}
		return this;
	}
}
