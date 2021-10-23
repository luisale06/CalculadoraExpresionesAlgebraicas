package com.example.webproject;

import java.util.Scanner;
import java.util.Stack;

public class expressionsTree {
    // Function to check if a given token is an operator
    public static boolean isOperator(String c) {
        return (c =="+" || c == "-" || c == "*" || c == "/" || c == "^");
    }

    // Print the postfix expression for an expression tree
    public static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data);
    }

    // Print the infix expression for an expression tree
    public static void inorder(Node root) {
        if (root == null) {
            return;
        }

        // if the current token is an operator, print open parenthesis
        if (isOperator(root.data)) {
            System.out.print("(");
        }

        inorder(root.left);
        System.out.print(root.data);
        inorder(root.right);

        // if the current token is an operator, print close parenthesis
        if (isOperator(root.data)) {
            System.out.print(")");
        }
    }

    // Function to construct an expression tree from the given postfix expression
    public static Node construct(Stack<String> Pila) {
        // create an empty stack to store tree pointers
        Stack<Node> s = new Stack<>();
        // traverse the postfix expression
        for (String c : Pila) {
            // if the current token is an operator
            if (c.contentEquals("+") || c.contentEquals("-") ||
                    c.contentEquals("*")|| c.contentEquals("/")
                    ||c.contentEquals("^")
            ) {
                // pop two nodes `x` and `y` from the stack
                Node x = s.pop();
                Node y = s.pop();

                // construct a new binary tree whose root is the operator and whose
                // left and right children point to `y` and `x`, respectively
                Node node = new Node(c, y, x);

                // push the current node into the stack
                s.add(node);
            } // if the current token is an operand, create a new binary tree node
            // whose root is the operand and push it into the stack
            else {
                s.add(new Node(c));
                //System.out.print(" "+c);
            }
        }

        // a pointer to the root of the expression tree remains on the stack
        return s.peek();
    }

    private static int toInt(String s) {
        int num = 0;

        // Check if the integral value is
        // negative or not
        // If it is not negative, generate
        // the number normally
        if (s.charAt(0) != '-') {
            for (int i = 0; i < s.length(); i++) {
                num = num * 10 + ((int) s.charAt(i) - 48);
            }
        } // If it is negative, calculate the +ve number
        // first ignoring the sign and invert the
        // sign at the end
        else {
            for (int i = 1; i < s.length(); i++) {
                num = num * 10 + ((int) (s.charAt(i)) - 48);
                num = num * -1;
            }
        }

        return num;

    }

    // This function receives a node of the syntax
// tree and recursively evaluate it
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

    //Depurar expresión algebraica
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

    //Jerarquia de los operadores
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
    public static void main(String[] args) {
        //Entrada de datos
        System.out.println("*Escribe una expresión algebraica: ");
        Scanner leer = new Scanner(System.in);

        //Depurar la expresion algebraica
        String expr = depurar(leer.nextLine());
        String[] arrayInfix = expr.split(" ");
        //Declaración de las pilas

        Stack< String> O = new Stack<>();
        Stack< String> S = new Stack<>();
        Stack< String> E = new Stack<>(); //Pila entrada
        Stack< String> M = new Stack<>();
        //Añadir la array a la Pila de entrada (E)
        for (int i = arrayInfix.length - 1;
             i >= 0; i--) {
            E.push(arrayInfix[i]);
            //M.push(arrayInfix[i]);
        }
        try {
            //Algoritmo Infijo a Postfijo
            //Eliminacion de `impurezas´ en la expresiones algebraicas
            //Pila salida
            //Stack< String> p = new Stack< String>();
            //  ((10+3)*(6*(7+5)))
            //  ((10/2)+(7-(3*2)))
            //  (25*(7*(8+9))+1)
            //  ((25*(7*(8+9))+1)/2)
            //String infix = expr.replace(" ", "");
            //String postfix = S.toString().replaceAll("[\\]\\[,]", "");
            //Mostrar resultados:
            // System.out.println("Expresion Infija: " + infix);
            //System.out.println("Expresion Postfija: " + postfix);
            //System.out.println(E);
            S = postfix(E);

            Node raiz = new Node();
            //root = construct(S);
            raiz = construct(S);
            System.out.println();
            System.out.println(evalTree(raiz));

        } catch (Exception ex) {
            System.out.println("Error en la expresión algebraica");
            System.err.println(ex);
        }
    }*/
}
