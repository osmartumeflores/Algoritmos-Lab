package ejercicio;
import actividad.LinkedBST;
import actividad.ExceptionIsEmpty;
import java.util.*;
public class Ejercicios<E extends Comparable<E>>{
    public final LinkedBST<E> bst;
    public Ejercicios(LinkedBST<E> bst){
        this.bst=bst;
    }
    //Ejercicio 1.a
    public void desrtyNodes() throws ExceptionIsEmpty{
        bst.destroy();
    }
    //Ejercicio 1.b
    public int countAllNodes(){
        return countAllNodes(bst.getRoot());
    }

    public int countAllNodes(LinkedBST<E>.Node node){
        if(node==null|| (node.left==null && node.right==null)) return 0;
        return 1+countAllNodes(node.left)+ countAllNodes(node.right);
    }
    //Ejercicio 1.c
    public int countLeafNodes() {
        return countLeafNodes(bst.getRoot());
    }
    public int countLeafNodes(LinkedBST<E>.Node node){
        if(node==null) return 0;
        if(node.left==null && node.right==null) return 1;
        return countLeafNodes(node.left)+countLeafNodes(node.right);
    }
    //Ejercicio 1.d
    public int height(E x){
        LinkedBST<E>.Node node =bst.searchNode(x);
        if(node==null) return -1;
        Queue<LinkedBST<E>.Node> queue =new LinkedList<>();
        queue.add(node);
        int height =-1;
        while (!queue.isEmpty()){
            int size=queue.size();
            height++;
            for(int i=0;i<size;i++){
                LinkedBST<E>.Node curr=queue.poll();
                if(curr.left!=null)queue.add(curr.left);
                if(curr.right!=null)queue.add(curr.right);
            }
        }
        return height;
    }
    //Ejercicio1.e
    public int amplitude(int level){
        if(bst.getRoot()==null|| level<0)return 0;
        Queue<LinkedBST<E>.Node> queue =new LinkedList<>();
        queue.add(bst.getRoot());
        int currentLevel=0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (currentLevel == level) return size;
            for (int i = 0; i < size; i++) {
                LinkedBST<E>.Node curr = queue.poll();
                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
            currentLevel++;
        }
        return 0;

    }
    // Ejercicio 2.a: área del BST (nodos hoja * altura total)
    public int areaBST() {
        int hojas = countLeafNodes();
        int altura = bst.height();
        return hojas * altura;
    }

    // Ejercicio 2.b: dibujo del árbol
    public String drawBST() {
        return draw(bst.getRoot(), "", true);
    }

    private String draw(LinkedBST<E>.Node node, String prefix, boolean isTail) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append(isTail ? "└── " : "├── ").append(node.data).append("\n");

        List<LinkedBST<E>.Node> children = new ArrayList<>();
        if (node.left != null) children.add(node.left);
        if (node.right != null) children.add(node.right);

        for (int i = 0; i < children.size(); i++) {
            sb.append(draw(children.get(i), prefix + (isTail ? "    " : "│   "), i == children.size() - 1));
        }
        return sb.toString();
    }

    // Ejercicio 2.c: comparar área entre dos árboles
    public boolean sameArea(Ejercicios<E> otro) {
        return this.areaBST() == otro.areaBST();
    }

    // Ejercicio 3: representación con sangría
    public String parenthesize() {
        return parenthesize(bst.getRoot(), 0);
    }

    public String parenthesize(LinkedBST<E>.Node node, int level) {
        if (node == null) return "";
        StringBuilder sb = new StringBuilder("  ".repeat(level) + node.data + "\n");
        sb.append(parenthesize(node.left, level + 1));
        sb.append(parenthesize(node.right, level + 1));
        return sb.toString();
    }
}
