package Misc.CustomClasses.Tree;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Jacob Swineford
 */
public class BST<T extends Comparable<T>> {

    private Node<T> origin;
    private int size;

    public BST() {
        origin = null;
        size = 0;
    }

    /**
     * Inserts the given data into it's proper position in this BST.
     * Returns the node that was inserted.
     */
    public Node add(T data) {
        Node parent;
        if (origin == null) {
            origin = new Node<>(null, null, null, data);
            size++;
            return origin;
        }
        parent = traverse(origin, data); // leaf used for insertion
        Node inserted;
        int i = parent.data.compareTo(data); // unsafe
        if (i < 0 || i == 0) {
            inserted = new Node<>(null, null, parent, data);
            parent.right = inserted;
            System.out.println("Inserted " + data.toString() + " To the right of " + parent.data.toString());
        }
        else {
            inserted = new Node<>(null, null, parent, data);
            parent.left = inserted;
            System.out.println("Inserted " + data.toString() + " To the left of " + parent.data.toString());
        }
        size++;
        return inserted;
    }

    /**
     * Traverses this BST to find the proper to be parent of some inserted data.
     * @return leaf that will serve as parent
     */
    private Node traverse(Node cur, T data) {
        int i = cur.data.compareTo(data); // unsafe
        if (i < 0 || i == 0) {
            if (cur.right == null) return cur;
            return traverse(cur.right, data);
        }
        if (cur.left == null) return cur;
        return traverse(cur.left, data);
    }

    public boolean contains(Node root, T data) {
        if (root == null) return false;
        if (root.data.equals(data)) return true;
        if (contains(root.left, data)) return true;
        return contains(root.right, data);
    }

    /**
     * deletes the first encountered node with the same data value as data,
     * and manipulates the tree to compensate.
     * @param data data to be deleted
     */
    public void delete(T data) {
        // WIP
    }

    public void clear() {delete(origin.data);}

    private void prefix(Node n) {
        if (n == null) return;
        System.out.print(n.data.toString() + " ");
        prefix(n.left);
        prefix(n.right);
    }

    private void infix(Node n) {
        if (n == null) return;
        infix(n.left);
        System.out.print(n.data.toString() + " ");
        infix(n.right);
    }

    private void postfix(Node n) {
        if (n == null) return;
        postfix(n.left);
        postfix(n.right);
        System.out.print(n.data.toString() + " ");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("How many nodes? ");
        int nodes = in.nextInt();
        Integer[] vals = randomize(nodes);
        BST<Integer> tree = new BST<>();
        for (Integer i : vals) {
            tree.add(i);
        }
        System.out.print("prefix: "); tree.prefix(tree.origin);
        System.out.println();
        System.out.print("infix: ");tree.infix(tree.origin);
        System.out.println();
        System.out.print("postfix: ");tree.postfix(tree.origin);
        System.out.println();
        System.out.println("size of tree: " + tree.size);
    }

    private static Integer[] randomize(int num) {
        Integer[] re = new Integer[num];
        Random rand = new Random();
        for (int i = 0; i < num; i++) {
            re[i] = (rand.nextInt(100));
        }
        return re;
    }

}
