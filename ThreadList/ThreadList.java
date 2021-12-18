package Misc.CustomClasses.ThreadList;

import javafx.geometry.Point2D;

import java.util.ArrayList;

/**
 * A list that uses threads to search for a piece of data.
 * Otherwise, it may be treated as an <code>ArrayList</code>.
 *
 * WARNING: THIS CLASS IS REALLY RANDOM IN IT'S PERFORMANCE,
 * AND MAY SEARCH FOR MUCH LONGER THAN INTENDED BASED ON HOW
 * WELL A SYSTEM MANAGES THREADS.
 *
 * @author Jacob Swineford
 */
public class ThreadList<D> extends ArrayList<D> {

    private int numThreads;

    /**
     * Constructor that initializes the size of the internal <code>ArrayList</code>
     * and the number of threads to be used for finding any particular
     * piece of data in this list.
     *
     * @param size initial size of list
     * @param numThreads number of threads to use for searching
     */
    private ThreadList(int size, int numThreads) {
        super(size);
        this.numThreads = numThreads;
    }

    private static int DEFAULT_SIZE = 10;
    /**
     * Constructor that initializes the number of threads to be used
     * for finding any particular piece of data in this list.
     * The size of the starting list is determined by the <code>DEFAULT_SIZE</code>
     * class variable.
     *
     * @param numThreads number of threads to use for searching
     */
    private ThreadList(int numThreads) {
        this(DEFAULT_SIZE, numThreads);
    }

    private D found = null;
    private static int hits = 0;
    /**
     * Finds a piece of data in a list using a newly initialized batch of threads
     * to search through multiple parts of this list. Once a match is found,
     * or all of the threads are done executing, null or the data found is returned.
     *
     * If the number of threads allocated to find the data exceeds the length
     * of this list, then only one thread will be allocated to find the data.
     *
     * @param data data to search for in this list
     * @return null if the data is not in this list, A reference to the found data
     *         otherwise
     */
    private D find(D data) {
        if (data == null) return null;

        if (numThreads <= this.size()) {
            Thread[] threads = new Thread[numThreads];
            int to = this.size() / numThreads;
            int from = 0;
            int additiveDifference = to - from;
            for (int i = 0; i < numThreads; i++) {
                if (i == numThreads - 1) to = this.size();
                threads[i] = new Searcher(to, from, data, this);
                threads[i].start();
                to += additiveDifference;
                from += additiveDifference;
            }
            for (int i = 0; i < numThreads; i++) {
                try {
                    threads[i].join();
                } catch (Exception e) {
                    // an exception being thrown here will make a difference
                    // if the data is within this searcher's list indexes.
                    // currently, through, I don't have a good solution for
                    // that.
                }
            }
            D temp = found;
            found = null;
            return temp;
        } else {
            if (this.contains(data)) return data;
            else return null;
        }
    }

    /**
     * Private class that acts a separate thread to search through some
     * part of the list to find some piece of data.
     *
     * @author Jacob Swineford
     */
    private class Searcher extends Thread {

        // from should always come before to
        private int to;
        private int from;

        private ArrayList<D> parent;
        private D data;

        Searcher(int to, int from, D data, ArrayList<D> parent) {
            this.to = to;
            this.from = from;
            this.data = data;
            this.parent = parent;
        }

        @Override
        public void run() {
            for (int i = from; i < to; i++) {
                if (found == null) {
                    hits++;
                    if (parent.get(i) == data) {
                        found = data;
                        return;
                    }
                } else return;
            }
        }
    }

    public static void main(String[] args) {
        ThreadList<Point2D> list = new ThreadList<>(60);
        Point2D save = null;
        int length = 5000000;
        int find = 4313139;
        System.out.print("loading...");
        for (int i = 0; i < length; i++) {
            Point2D p = new Point2D(1, 1);
            if (i == find) save = p;
            list.add(p);
        }
        System.out.println(" loaded!");

        long t = System.currentTimeMillis();
        System.out.println(list.find(save));
        System.out.println();
        System.out.println("elapsed time (t): " + (System.currentTimeMillis()-t));
        System.out.println("hits-" + hits);

        t = System.currentTimeMillis();
        int hit = 0;
        for (int i = 0; i < list.size(); i++) {
            hit++;
            if (list.get(i) == save){
                System.out.println();
                break;
            }
        }
        System.out.println("elapsed time (l): " + (System.currentTimeMillis()-t));
        System.out.println("hit (l)-" + hit);
        System.out.println("difference- " + (ThreadList.hits - hit));
    }
}
