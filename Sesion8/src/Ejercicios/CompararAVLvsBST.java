package Ejercicios;
import Actividad.AVLTree;
import Actividad.NodeAVL;
import Actividad.BSTree;

class CompararAVLvsBST {
    public static void main(String[] args) {
        AVLTree<Integer> avl = new AVLTree<>();
        BSTree<Integer> bst = new BSTree<>();

        int[] datos = {10, 20, 30, 40, 50, 25};
        for (int d : datos) {
            avl.insert(d);
            bst.insert(d);
        }

        System.out.println("--- AVL Tree ---");
        System.out.print("Preorden: "); avl.preorder();
        System.out.print("BFS: "); avl.bfs();

        System.out.println("--- BST Tree ---");
        System.out.print("Inorden: "); bst.inorder();
        System.out.println("Altura del BST: " + bst.height());
    }
}
