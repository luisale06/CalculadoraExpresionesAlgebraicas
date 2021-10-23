/**
 *
 * @author Estudiante
 */
public class Node {

    Node() {

    }
    String data;
    Node left, right;

    Node(String data) {
        this.data = data;
        this.left = this.right = null;
    }

    Node(String data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

}
