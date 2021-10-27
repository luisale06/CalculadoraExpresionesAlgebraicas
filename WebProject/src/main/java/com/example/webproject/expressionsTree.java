package com.example.webproject;

import java.util.Stack;

/**
 * Clase encargada de construir, depurar y calcular el arbol binario de expresiones
 * @author Luis Pablo Cespedes Sequeira
 * @see expressionsTree
 */

public class expressionsTree {

    /**
     * metodo que recibe una pila con los operadores y orepandos en orden postfijo, respetando el orden de prioridad
     * de las operaciones sin recurrir a parentesis. Deja el arbol ordenado de tal forma que los nodos
     * internos son operadores y las hojas operandos
     * @param Pila
     * @return tree
     */

    public static Node construct(Stack<String> Pila) {
        // create an empty stack to store tree pointers
        Stack<Node> s = new Stack<>();
        // traverse the postfix expression
        for (String c : Pila) {
            // if the current token is an operator
            if (c.contentEquals("+") || c.contentEquals("-") || c.contentEquals("*") || c.contentEquals("/") ||c.contentEquals("^")) {
                // pop two nodes `x` and `y` from the stack
                Node x = s.pop();
                Node y = s.pop();

                // construct a new binary tree whose root is the operator and whose left and right children point to `y` and `x`, respectively
                Node node = new Node(c, y, x);

                // push the current node into the stack
                s.add(node);
            } // if the current token is an operand, create a new binary tree node whose root is the operand and push it into the stack
            else {
                s.add(new Node(c));
                //System.out.print(" "+c);
            }
        }
        // a pointer to the root of the expression tree remains on the stack
        return s.peek();
    }

    /**
     * retorna el numero entero de la operacion introducida en el arbol, opera desde lo
     * mas bajo de una de las ramas y sube de forma recursiva. Realiza lo mismo con la
     * segunda rama y termina por operar ambos resultados con el operador en el nodo raiz
     * @param root
     * @return
     */
    public static int evalTree(Node root) {

        // Empty tree
        if (root == null) {
            return 0;
        }

        // Leaf node i.e, an integer
        if (root.left == null && root.right == null) {
            return Integer.parseInt(root.data);
        }

        // Evaluate left subtree
        int leftEval = evalTree(root.left);

        // Evaluate right subtree
        int rightEval = evalTree(root.right);

        // Check which operator to apply
        if (root.data.equals("+")) {
            return leftEval + rightEval;
        }

        if (root.data.equals("-")) {
            return leftEval - rightEval;
        }

        if (root.data.equals("*")) {
            return leftEval * rightEval;
        }

        return leftEval / rightEval;
    }

    /**
     * Este metodo se encarga de eliminar cualquier espacio en blanco que el usuario haya dejado
     * para no estropear la logica de los demas metodos
     * @param s
     * @return
     */
    public static String depurar(String s) {
        s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
        s = "(" + s + ")";
        String simbols = "+-*/()";
        String str = "";

        //Deja espacios entre operadores
        for (int i = 0; i < s.length(); i++) {
            if (simbols.contains("" + s.charAt(i))) {
                str += " " + s.charAt(i) + " ";
            } else {
                str += s.charAt(i);
            }
        }
        return str.replaceAll("\\s+", " ").trim();
    }

    /**
     * metodo que recibe como parametro el valor que se extrae de la pila donde se almaceno la
     * expresion original (infija). Le brinda un orden de prioridad segun las normas aritmetiocas
     * siendo el 4, el numero asignado a los operadores con mayor prioridad
     * @param op
     * @return
     */
    private static int pref(String op) {
        int prf = 99;
        if (op.equals("^")) {
            prf = 5;
        }
        if (op.equals("*") || op.equals("/") || op.equals("%")) {
            prf = 4;
        }
        if (op.equals("+") || op.equals("-")) {
            prf = 3;
        }
        if (op.equals(")")) {
            prf = 2;
        }
        if (op.equals("(")) {
            prf = 1;
        }
        return prf;
    }

    /**
     * metodo que convierte la pila de expresion matematica infija a orden postfijo. Utiliza dos listas.
     * Una para almacenar los operadores de mayor prioridad y poder agregarlos de menor a mayor en otra
     * pila que almacena toda la expresion
     * @param p
     * @return
     */
    public static Stack <String> postfix(Stack<String> p) {
        Stack< String> E = new Stack<>();
        Stack< String> P = new Stack<>(); //Pila temporal para operadores
        Stack< String> S = new Stack<>(); //Pila salida
        E = p;
        while (!E.isEmpty()) {
            switch (pref(E.peek())){
                case 1:
                    P.push(E.pop());
                    break;
                case 3:
                case 4:
                    while(pref(P.peek()) >= pref(E.peek())) {
                        S.push(P.pop());
                    }
                    P.push(E.pop());
                    break;
                case 2:
                    while(!P.peek().equals("(")) {
                        S.push(P.pop());
                    }
                    P.pop();
                    E.pop();
                    break;
                default:
                    S.push(E.pop());
            }
        }
        return S;
    }
    /*
    ((10+3)*(6*(7+5)))
    ((10/2)+(7-(3*2)))
    (25*(7*(8+9))+1)
    ((25*(7*(8+9))+1)/2)
    */
}
