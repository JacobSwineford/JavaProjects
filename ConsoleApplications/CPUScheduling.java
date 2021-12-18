package Misc.ConsoleApplications;

import java.util.*;

/**
 * Class to simulate scheduling algorithms for processes coming
 * into the CPU. A process class is used to keep track of all
 * the data related to a process, and a comparator is used
 * to shuffle all the processes programmatically based on any
 * preemptive or non-preemptive properties of an algorithm.
 *
 * Note that this program is a simulation, and is privy to information
 * present about processes before-hand that an actual CPU wouldn't
 * be able to completely determine, such as full time of execution
 * and 100% correct burst length.
 *
 * @author Jacob Swineford
 */
public class CPUScheduling {

    private static PriorityQueue<Process> readyQueue;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter process arrival times and burst lengths: ");
        String[] info = in.nextLine().split(" ");
        if (info.length % 2 != 0) {
            System.out.println("Error! the last process is not given a burst length!");
            return;
        }

        readyQueue = new PriorityQueue<>(new PC());
        System.out.println();
        SRTF(freshProcesses(info, "SRTF"));
        System.out.println();
        SJF(freshProcesses(info, "SJF"));
        System.out.println();
        FCFS(freshProcesses(info, "FCFS"));
    }

    /**
     * Returns a set of fresh processes for use in each of the CPU scheduling
     * algorithm methods.
     *
     * @param info information entered through the console
     * @param algorithm algorithm to be used
     *
     * @return array of <code> Process </code> configured with the information
     *         from the console
     */
    private static Process[] freshProcesses(String[] info, String algorithm) {
        String key = "ABCDEFGHIJKLMNOPQRSTUPWXYZ"; // used for naming a process
        Process[] processes = new Process[info.length / 2];
        for (int i = 0; i < processes.length; i++) {
            processes[i] = new Process();
            processes[i].arrivalTime = Integer.parseInt(info[i * 2]);
            processes[i].bursts = Integer.parseInt(info[(i * 2) + 1]);
            processes[i].name = key.toCharArray()[i] + "";
            processes[i].algorithm = algorithm;
        }
        return processes;
    }

    /**
     * The "Shortest Remaining Time First" scheduling algorithm. This is
     * preemptive, so a reordering is necessary for each insertion of a new
     * process.
     *
     * This is algorithm will have the best waiting time.
     *
     * @param processes array of <code> Process </code> objects
     */
    private static void SRTF(Process[] processes) {
        readyQueue.clear(); // just in case
        System.out.print("SRTF: ");

        // execute the processes as they come in, and make
        // a note of their execution time. we want a boolean
        // value for telling if a process needs to print out it's
        // values before being preempted
        Process executing = null;
        int fullTime = fullTime(processes);
        for (int i = 0; i < fullTime; i++) {
            for (Process pro : processes) {
                if (pro.arrivalTime == i) {
                    if (executing == null) executing = pro;
                    readyQueue.add(pro);
                    if (readyQueue.peek() != executing) {
                        System.out.print(executing.name + executing.preemptiveExecutedTime + " ");
                        executing.preemptiveExecutedTime = 0;
                        executing = readyQueue.peek();
                    }
                    break;
                }
            }

            if (executing != null) {
                executing.executedTime++;
                executing.bursts--;
                executing.preemptiveExecutedTime++;

                for (Process p : processes) {
                    if (p != executing && readyQueue.contains(p)) {
                        p.waitingTime++;
                    }
                }

                if (executing.bursts == 0) {
                    System.out.print(executing.name + executing.preemptiveExecutedTime + " ");
                    readyQueue.remove(executing);
                    executing = readyQueue.peek();
                }
            }
        }
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime(processes));
    }

    /**
     * The "Shortest Job First" scheduling algorithm. This algorithm
     * is not preemptive, and will not interrupt an executing process
     * in favor of another process. It will, however, organize the
     * ready queue for computing the shortest job first (the shortest job
     * then will always be the head of the queue).
     *
     * Out of the three algorithms here, this algorithm has the second best
     * waiting time.
     *
     * @param processes array of <code> Process </code> objects
     */
    private static void SJF(Process[] processes) {
        readyQueue.clear(); // just in case
        System.out.print("SJF: ");

        // execute the processes as they come in, and make
        // a note of their execution time.
        Process executing = null;
        int fullTime = fullTime(processes);
        for (int i = 0; i < fullTime; i++) {
            for (Process pro : processes) {
                if (pro.arrivalTime == i) {
                    if (executing == null) executing = pro;
                    readyQueue.add(pro);
                    break;
                }
            }

            if (executing != null) {
                executing.executedTime++;
                executing.bursts--;

                for (Process p : processes) {
                    if (p != executing && readyQueue.contains(p)) {
                        p.waitingTime++;
                    }
                }

                if (executing.bursts == 0) {
                    System.out.print(executing.name + executing.executedTime + " ");
                    readyQueue.remove(executing);
                    executing = readyQueue.peek();
                }
            }
        }
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime(processes));
    }

    /**
     * The "First Come First Served" scheduling algorithm. This algorithm
     * is not preemptive, and will not interrupt an executing process
     * in favor of another process. Like the name implies, this
     * algorithm executes the first process it gets fully, then the leading
     * processes all in order based on how fast it got scheduled.
     *
     * This algorithm will have the worst average waiting time.
     *
     * @param processes array of <code> Process </code> objects
     */
    private static void FCFS(Process[] processes) {
        readyQueue.clear(); // just in case
        System.out.print("FCFS: ");

        // execute the processes as they come in, and make
        // a note of their execution time
        Process executing = null;
        int fullTime = fullTime(processes);
        for (int i = 0; i < fullTime; i++) {
            for (Process pro : processes) {
                if (pro.arrivalTime == i) {
                    if (executing == null) executing = pro;
                    readyQueue.add(pro);
                    break;
                }
            }

            if (executing != null) {
                executing.executedTime++;
                executing.bursts--;

                for (Process p : processes) {
                    if (p != executing && readyQueue.contains(p)) {
                        p.waitingTime++;
                    }
                }

                if (executing.bursts == 0) {
                    System.out.print(executing.name + executing.executedTime + " ");
                    readyQueue.remove(executing);
                    executing = readyQueue.peek();
                }
            }
        }
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime(processes));
    }

    /**
     * Finds the average waiting time given the array of waiting times
     * after executing one of the above CPU scheduling algorithms.
     *
     * @param processes processes that all have their wait time iterated over
     *
     * @return the average waiting time
     */
    private static double averageWaitingTime(Process[] processes) {
        double sum = 0;
        for (Process p : processes) {
            sum += p.waitingTime;
        }
        return sum / processes.length;
    }

    /**
     * The full time it will take for all processes in this program to finish.
     * This is based on all the processes burst lengths.
     *
     * Note that is actual non-simulation code, this is not a valid way to handle
     * the problem of CPU scheduling. There is no way to predict the length of
     * every single program to be executed before it gets to the CPU.
     *
     * @param arr array of processes to count time for
     *
     * @return full time of processes
     */
    private static int fullTime(Process[] arr) {
        int sum = 0;
        for (Process pro : arr) {
            sum += pro.bursts;
        }
        return sum;
    }
}

/**
 * Class that represents a process to be processed by a CPU.
 * Note that any variables listed as preemptive have a special
 * role in only preemptive processes. In general, this means
 * these variables only have non-default values during
 * a process' execution.
 *
 * @author Jacob Swineford
 */
class Process {
    int arrivalTime;
    int waitingTime;
    int executedTime;
    int bursts;

    // preemptive wait time, for only when a process is running
    // and when it is not
    int preemptiveExecutedTime;

    String algorithm;
    String name;
}

/**
 * Comparator that is used to organize the ready queue
 * based on a process and their sorting algorithm.
 * It is implied that the sorting algorithm for each element
 * to be sorted is held by both <code> Process </code> objects
 * that are being compared.
 *
 * @author Jacob Swineford
 */
class PC implements Comparator<Process> {

    @Override
    public int compare(Process o1, Process o2) {
        String al = o1.algorithm;

        int val;
        if (!al.equalsIgnoreCase("FCFS")) {
            val = o1.bursts - o2.bursts;
        } else {
            val = o1.arrivalTime - o2.arrivalTime;
        }
        return val;
    }
}
