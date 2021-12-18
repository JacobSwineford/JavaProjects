package Misc.CustomClasses.Tree;

/**
 * @author Jacob Swineford
 */
class Node<T extends Comparable<T>> {

    Node left;
    Node right;
    Node parent;
    T data;

    Node(Node left, Node right, Node parent, T data) {
        this.left = left;
        this.right = right;
        this.parent = parent;
        this.data = data;
    }
}
