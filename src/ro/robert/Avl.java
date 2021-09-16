package ro.robert;

import java.util.Stack;

public class Avl {
    private Node root;

    private int getBalanced(Node node) {
        return height(node.getLeft()) - height(node.getRight());
    }

    private int height(Node node) {
        if (node == null)
            return 0;
        return node.getHeight();
    }

    private boolean setRoot(Node node) {
        if (root == null) {
            root = node;
            return true;
        }
        return false;
    }

    private Stack<Node> addNode(int key) {
        Node newNode = new Node(key);
        Stack<Node> stack = new Stack<>();
        if (!setRoot(newNode)) {
            Node focusNode = root;
            Node parent;
            while (true) {
                parent = focusNode;
                if (key < focusNode.getKey()) {
                    stack.push(parent);
                    focusNode = focusNode.getLeft();
                    if (focusNode == null) {
                        parent.setLeft(newNode);
                        return stack;
                    }
                } else {
                    if (key > focusNode.getKey()) {
                        stack.push(parent);
                        focusNode = focusNode.getRight();
                        if (focusNode == null) {
                            parent.setRight(newNode);

                            return stack;
                        }
                    }
                    //cod pentru noduri duplicate
//                    else
//                    {
//                        if(key == focusNode.getKey()){
//                            parent.setCount(1);
//                        }
//                    }
                }
            }
        }
        return stack;
    }

    public void insert(int key) {
        Stack<Node> stack = addNode(key);
        if (!stack.isEmpty()) {
            Node parent = stack.pop();
            parent.setHeight(Math.max(height(parent.getLeft()), height(parent.getRight())) + 1);
            int balanced = getBalanced(parent);

            if (balanced > 1) {
                if(key > parent.getKey()){

                }
                parent = rotateRight(parent);

            }

            //subarbore stang right rotation
            if (balanced > 1 && key < parent.getLeft().getKey()) {
                parent = rotateRight(parent);
            }
            //subarbore stang rotatie la stanga
            if (balanced > 1 && key > parent.getLeft().getKey()) {
                parent = rotateLeft(parent);
            }
            //subarbore drept right rotation
            if (balanced < -1 && key < parent.getLeft().getKey()) {
                parent = rotateRight(parent);
            }
            //subarbore drept left rotation
            if (balanced < -1 && key > parent.getLeft().getKey()) {
                parent = rotateLeft(parent);
            }
        }
    }

    private Node rotateLeft(Node parent) {
        Node x = parent.getRight();
        Node b = x.getLeft();

        x.setLeft(parent);
        parent.setRight(b);

        parent.setHeight(Math.max(height(parent.getLeft()), height(parent.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);

        return x;
    }

    private Node rotateRight(Node parent) {
        Node x = parent.getLeft();
        Node b = x.getRight();

        x.setRight(parent);
        parent.setLeft(b);

        parent.setHeight(Math.max(height(parent.getLeft()), height(parent.getRight())) + 1);
        x.setHeight(Math.max(height(x.getLeft()), height(x.getRight())) + 1);

        return x;
    }

}
