package Actividad;

public class AVLTree<E extends Comparable<E>> {
    public NodeAVL<E> root;

    public void insert(E value) {
        root = insert(root, value);
    }

    private NodeAVL<E> insert(NodeAVL<E> node, E value) {
        if (node == null) {
            return new NodeAVL<>(value);
        }

        int cmp = value.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert((NodeAVL<E>) node.left, value);
            node = updateBalanceLeft(node);
        } else if (cmp > 0) {
            node.right = insert((NodeAVL<E>) node.right, value);
            node = updateBalanceRight(node);
        }

        return node;
    }

    private NodeAVL<E> updateBalanceLeft(NodeAVL<E> node) {
        node.bf--;
        if (node.bf == -2) {
            return balanceToRight(node);
        }
        return node;
    }

    private NodeAVL<E> updateBalanceRight(NodeAVL<E> node) {
        node.bf++;
        if (node.bf == 2) {
            return balanceToLeft(node);
        }
        return node;
    }

    private NodeAVL<E> balanceToLeft(NodeAVL<E> node) {
        NodeAVL<E> right = (NodeAVL<E>) node.right;
        if (right.bf >= 0) {
            node.bf = 0;
            right.bf = 0;
            node = rotateSL(node);
        } else {
            NodeAVL<E> rl = (NodeAVL<E>) right.left;
            switch (rl.bf) {
                case -1:
                    node.bf = 0; right.bf = 1;
                    break;
                case 0:
                    node.bf = 0; right.bf = 0;
                    break;
                case 1:
                    node.bf = -1; right.bf = 0;
                    break;
            }
            rl.bf = 0;
            node.right = rotateSR(right);
            node = rotateSL(node);
        }
        return node;
    }

    private NodeAVL<E> balanceToRight(NodeAVL<E> node) {
        NodeAVL<E> left = (NodeAVL<E>) node.left;
        if (left.bf <= 0) {
            node.bf = 0;
            left.bf = 0;
            node = rotateSR(node);
        } else {
            NodeAVL<E> lr = (NodeAVL<E>) left.right;
            switch (lr.bf) {
                case -1:
                    node.bf = 1; left.bf = 0;
                    break;
                case 0:
                    node.bf = 0; left.bf = 0;
                    break;
                case 1:
                    node.bf = 0; left.bf = -1;
                    break;
            }
            lr.bf = 0;
            node.left = rotateSL(left);
            node = rotateSR(node);
        }
        return node;
    }

    private NodeAVL<E> rotateSL(NodeAVL<E> a) {
        NodeAVL<E> b = (NodeAVL<E>) a.right;
        a.right = b.left;
        b.left = a;
        a.bf = 0;
        b.bf = 0;
        return b;
    }

    private NodeAVL<E> rotateSR(NodeAVL<E> a) {
        NodeAVL<E> b = (NodeAVL<E>) a.left;
        a.left = b.right;
        b.right = a;
        a.bf = 0;
        b.bf = 0;
        return b;
    }

    public void preorder() {
        preorder(root);
        System.out.println();
    }

    private void preorder(NodeAVL<E> node) {
        if (node != null) {
            System.out.print(node + " ");
            preorder((NodeAVL<E>) node.left);
            preorder((NodeAVL<E>) node.right);
        }
    }

    // Recorrido por niveles (BFS)
    public void bfs() {
        if (root == null) return;
        java.util.Queue<NodeAVL<E>> queue = new java.util.LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            NodeAVL<E> node = queue.poll();
            System.out.print(node + " ");
            if (node.left != null) queue.add((NodeAVL<E>) node.left);
            if (node.right != null) queue.add((NodeAVL<E>) node.right);
        }
        System.out.println();
    }
        // Clase de prueba para verificar rotaciones
    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();

        // Rotación simple derecha (RSR)
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);

        // Rotación simple izquierda (RSL)
        tree.insert(40);
        tree.insert(50);

        // Rotación doble derecha (RDR)
        tree.insert(5);
        tree.insert(8);

        // Rotación doble izquierda (RDL)
        tree.insert(60);
        tree.insert(55);

        System.out.print("Preorden: ");
        tree.preorder();

        System.out.print("\nPor niveles (BFS): ");
        tree.bfs();
    }
}