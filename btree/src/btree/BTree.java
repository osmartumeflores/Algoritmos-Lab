package btree;
import btree.BinarySearchTree;
import btree.ItemDuplicated;
import btree.ItemNotFoundException;
import btree.ExceptionIsEmpty;

public class BTree<E extends Comparable<E>> {
    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;
    
    public BTree(int orden) {
        this.orden = orden;
        this.root = null;
    }
    
    public boolean isEmpty() {
        return this.root == null;
    }
    
    // Insertar una clave en el árbol B
    public void insert(E cl) {
        up = false;
        E mediana;
        BNode<E> pnew;
        mediana = push(this.root, cl);
        if (up) {
            pnew = new BNode<E>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }
    
    private E push(BNode<E> current, E cl) {
        int pos[] = new int[1];
        E mediana;
        
        if (current == null) {
            up = true;
            nDes = null;
            return cl;
        } else {
            boolean fl;
            fl = current.searchNode(cl, pos);
            if (fl) {
                System.out.println("Item duplicado: " + cl);
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), cl);
            if (up) {
                if (current.nodeFull(this.orden - 1))
                    mediana = dividedNode(current, mediana, pos[0]);
                else {
                    putNode(current, mediana, nDes, pos[0]);
                    up = false;
                }
            }
            return mediana;
        }
    }
    
    private void putNode(BNode<E> current, E cl, BNode<E> rd, int k) {
        int i;
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }
        current.keys.set(k, cl);
        current.childs.set(k + 1, rd);
        current.count++;
    }
    
    private E dividedNode(BNode<E> current, E cl, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<E>(this.orden);
        
        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        
        if (k <= this.orden / 2)
            putNode(current, cl, rd, k);
        else
            putNode(nDes, cl, rd, k - posMdna);
            
        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;
        return median;
    }
    
    // Método toString para mostrar el árbol
    public String toString() {
        String s = "";
        if (isEmpty())
            s += "BTree is empty...";
        else
            s = writeTree(this.root, 0);
        return s;
    }
    
    private String writeTree(BNode<E> current, int level) {
        StringBuilder sb = new StringBuilder();
        
        if (current != null) {
            // Agregar información del nodo actual
            for (int i = 0; i < level; i++) {
                sb.append("  "); // Indentación por nivel
            }
            sb.append("Level ").append(level).append(": ");
            sb.append(current.toString()).append("\n");
            
            // Recorrer recursivamente los hijos
            for (int i = 0; i <= current.count; i++) {
                if (current.childs.get(i) != null) {
                    sb.append(writeTree(current.childs.get(i), level + 1));
                }
            }
        }
        
        return sb.toString();
    }
    
    // EJERCICIO 1: Método search
    public boolean search(E cl) {
        return searchHelper(this.root, cl);
    }
    
    private boolean searchHelper(BNode<E> current, E cl) {
        if (current == null) {
            return false;
        }
        
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);
        
        if (found) {
            System.out.println(cl + " se encuentra en el nodo " + 
                             current.getIdNode() + " en la posición " + pos[0]);
            return true;
        } else {
            return searchHelper(current.childs.get(pos[0]), cl);
        }
    }
    
    // EJERCICIO 2: Método remove
    public void remove(E cl) {
        if (isEmpty()) {
            System.out.println("El árbol está vacío");
            return;
        }
        
        removeHelper(this.root, cl);
        
        // Si la raíz queda vacía después de eliminar
        if (root != null && root.count == 0 && !root.isLeaf()) {
            root = root.childs.get(0);
        }
    }
    
    private boolean removeHelper(BNode<E> current, E cl) {
        if (current == null) {
            return false;
        }
        
        int[] pos = new int[1];
        boolean found = current.searchNode(cl, pos);
        
        if (found) {
            // La clave está en este nodo
            if (current.isLeaf()) {
                // Caso simple: eliminar de una hoja
                removeFromLeaf(current, pos[0]);
            } else {
                // Caso complejo: eliminar de un nodo interno
                removeFromInternal(current, pos[0]);
            }
            return true;
        } else {
            // La clave no está en este nodo, buscar en los hijos
            boolean removed = removeHelper(current.childs.get(pos[0]), cl);
            
            if (removed && current.childs.get(pos[0]) != null) {
                // Verificar si el hijo necesita rebalanceo
                BNode<E> child = current.childs.get(pos[0]);
                int minKeys = (orden - 1) / 2;
                
                if (child.count < minKeys) {
                    rebalanceChild(current, pos[0]);
                }
            }
            
            return removed;
        }
    }
    
    private void removeFromLeaf(BNode<E> leaf, int pos) {
        // Desplazar las claves hacia la izquierda
        for (int i = pos; i < leaf.count - 1; i++) {
            leaf.keys.set(i, leaf.keys.get(i + 1));
        }
        leaf.keys.set(leaf.count - 1, null);
        leaf.count--;
    }
    
    private void removeFromInternal(BNode<E> internal, int pos) {
        E key = internal.keys.get(pos);
        BNode<E> leftChild = internal.childs.get(pos);
        BNode<E> rightChild = internal.childs.get(pos + 1);
        
        int minKeys = (orden - 1) / 2;
        
        if (leftChild.count > minKeys) {
            // Usar el predecesor
            E predecessor = getPredecessor(leftChild);
            internal.keys.set(pos, predecessor);
            removeHelper(leftChild, predecessor);
        } else if (rightChild.count > minKeys) {
            // Usar el sucesor
            E successor = getSuccessor(rightChild);
            internal.keys.set(pos, successor);
            removeHelper(rightChild, successor);
        } else {
            // Fusionar los dos hijos
            mergeNodes(internal, pos);
            removeHelper(leftChild, key);
        }
    }
    
    private E getPredecessor(BNode<E> node) {
        while (!node.isLeaf()) {
            node = node.childs.get(node.count);
        }
        return node.keys.get(node.count - 1);
    }
    
    private E getSuccessor(BNode<E> node) {
        while (!node.isLeaf()) {
            node = node.childs.get(0);
        }
        return node.keys.get(0);
    }
    
    private void rebalanceChild(BNode<E> parent, int childIndex) {
        BNode<E> child = parent.childs.get(childIndex);
        int minKeys = (orden - 1) / 2;
        
        // Intentar redistribución con hermano izquierdo
        if (childIndex > 0) {
            BNode<E> leftSibling = parent.childs.get(childIndex - 1);
            if (leftSibling.count > minKeys) {
                redistributeFromLeft(parent, childIndex);
                return;
            }
        }
        
        // Intentar redistribución con hermano derecho
        if (childIndex < parent.count) {
            BNode<E> rightSibling = parent.childs.get(childIndex + 1);
            if (rightSibling.count > minKeys) {
                redistributeFromRight(parent, childIndex);
                return;
            }
        }
        
        // No se puede redistribuir, fusionar
        if (childIndex > 0) {
            mergeNodes(parent, childIndex - 1);
        } else {
            mergeNodes(parent, childIndex);
        }
    }
    
    private void redistributeFromLeft(BNode<E> parent, int childIndex) {
        BNode<E> child = parent.childs.get(childIndex);
        BNode<E> leftSibling = parent.childs.get(childIndex - 1);
        
        // Mover la clave del padre al hijo
        for (int i = child.count; i > 0; i--) {
            child.keys.set(i, child.keys.get(i - 1));
        }
        child.keys.set(0, parent.keys.get(childIndex - 1));
        
        // Mover puntero si no es hoja
        if (!child.isLeaf()) {
            for (int i = child.count + 1; i > 0; i--) {
                child.childs.set(i, child.childs.get(i - 1));
            }
            child.childs.set(0, leftSibling.childs.get(leftSibling.count));
        }
        
        child.count++;
        
        // Mover la última clave del hermano izquierdo al padre
        parent.keys.set(childIndex - 1, leftSibling.keys.get(leftSibling.count - 1));
        leftSibling.keys.set(leftSibling.count - 1, null);
        leftSibling.childs.set(leftSibling.count, null);
        leftSibling.count--;
    }
    
    private void redistributeFromRight(BNode<E> parent, int childIndex) {
        BNode<E> child = parent.childs.get(childIndex);
        BNode<E> rightSibling = parent.childs.get(childIndex + 1);
        
        // Mover la clave del padre al hijo
        child.keys.set(child.count, parent.keys.get(childIndex));
        
        // Mover puntero si no es hoja
        if (!child.isLeaf()) {
            child.childs.set(child.count + 1, rightSibling.childs.get(0));
        }
        
        child.count++;
        
        // Mover la primera clave del hermano derecho al padre
        parent.keys.set(childIndex, rightSibling.keys.get(0));
        
        // Desplazar claves en el hermano derecho
        for (int i = 0; i < rightSibling.count - 1; i++) {
            rightSibling.keys.set(i, rightSibling.keys.get(i + 1));
        }
        rightSibling.keys.set(rightSibling.count - 1, null);
        
        // Desplazar punteros si no es hoja
        if (!rightSibling.isLeaf()) {
            for (int i = 0; i < rightSibling.count; i++) {
                rightSibling.childs.set(i, rightSibling.childs.get(i + 1));
            }
            rightSibling.childs.set(rightSibling.count, null);
        }
        
        rightSibling.count--;
    }
    
    private void mergeNodes(BNode<E> parent, int index) {
        BNode<E> leftChild = parent.childs.get(index);
        BNode<E> rightChild = parent.childs.get(index + 1);
        
        // Mover la clave del padre al hijo izquierdo
        leftChild.keys.set(leftChild.count, parent.keys.get(index));
        leftChild.count++;
        
        // Mover todas las claves del hijo derecho al izquierdo
        for (int i = 0; i < rightChild.count; i++) {
            leftChild.keys.set(leftChild.count, rightChild.keys.get(i));
            leftChild.count++;
        }
        
        // Mover todos los punteros del hijo derecho al izquierdo
        if (!leftChild.isLeaf()) {
            for (int i = 0; i <= rightChild.count; i++) {
                leftChild.childs.set(leftChild.count - rightChild.count + i, 
                                   rightChild.childs.get(i));
            }
        }
        
        // Eliminar la clave del padre y ajustar punteros
        for (int i = index; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }
}