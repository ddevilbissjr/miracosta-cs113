package Midterm.Question6;

import java.util.LinkedList;
import java.util.Queue;

public class JobScheduler {

    /* Data structure to use */
    Queue<Process> queue;

    /* Constructor */
    public JobScheduler() {
        queue = new LinkedList<>();
    }

    /* Adds a new job to the system.*/
    public void addProcess(Process p) {
        queue.add(p);
    }

    /* Gets the next job and adds this job back to the
    system, if no other jobs exist this job is sent back.*/
    public Process switchProcess(Process p) {
        Process first = queue.peek();
        if (first != null) {
            queue.add(p);
            queue.remove();
            return first;
        } else {
            return p;
        }
    }

    public Process getNextProcess () {
        Process first = queue.peek();
        if (first != null) {
            queue.remove();
            return first;
        } else {
            return null;
        }
    }
}