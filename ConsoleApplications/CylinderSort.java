package Misc.ConsoleApplications;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Sorts cylinders based on their volume.
 *
 * @author Jacob Swineford
 */
public class CylinderSort {
    public static void main(String[] args) {
        int num_cyl = 5;
        Cylinder[] cyls = new Cylinder[num_cyl];
        for (int i = 0; i < num_cyl; i++) {
            cyls[i] = new Cylinder();
        }

        // unsorted
        System.out.println("Unsorted -> ");
        for (Cylinder c : cyls) {
            System.out.println(c);
        }

        System.out.println("Sorted by volume -> ");
        Arrays.sort(cyls);
        // sorted by volume
        for (Cylinder c : cyls) {
            System.out.println(c);
        }
    }
}

class Cylinder implements Comparable<Cylinder> {

    private double radius;
    private double length;

    public Cylinder() {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        length = rand.nextDouble(1, 31);
        radius = rand.nextDouble(1, 11);
    }

    public double getVolume() {
        return Math.PI * Math.pow(radius, 2) * length;
    }

    public double getSurfaceArea() {
        return (2 * Math.PI * radius * length)
                + (2 * Math.PI * Math.pow(radius, 2));
    }

    @Override
    public String toString() {
        String l = String.format("[length: %.3f, ", length);
        String r = String.format("radius: %.3f, ", radius);
        String s = String.format("surface area: %.3f, ", getSurfaceArea());
        String v = String.format("volume: %.3f]", getVolume());
        return l + r + s + v;
    }

    @Override
    public int compareTo(Cylinder o) {
        double diff = getVolume() - o.getVolume();
        return (int) diff;
    }
}
