package com.example.webproject;

/**
 * Clase encargada de administrar el comportamiento de los nodos.
 * Tiene punteros a la izquierda y a la derecha para referenciar a los hijos.
 * Si es una hoja la referencia es nula.
 * @author Luis Pablo Cespedes Sequeira
 * @see Node
 */
public class Node {
    Node() {}
    String data;
    Node left, right;

    /**
     * Ingresa datos al nodo con referencia a los hijos nula
     * @param data
     */
    Node(String data) {
        this.data = data;
        this.left = this.right = null;
    }

    /**
     * Ingresa datos a los nodos, referenciando a los hijos con otros nodos.
     * @param data
     * @param left
     * @param right
     */
    Node(String data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}
