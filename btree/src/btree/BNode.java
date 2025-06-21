package btree;

import java.util.ArrayList;

public class BNode<E> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;
    protected static int nextId = 1;
    protected int idNode;
    
    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;
        this.idNode = nextId++;
        
        // Inicializar con valores null
        for(int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
    }
    
    // Verificar si el nodo actual está lleno
    public boolean nodeFull(int maxKeys) {
        return count >= maxKeys;
    }
    
    // Verificar si el nodo actual está vacío
    public boolean nodeEmpty() {
        return count == 0;
    }
    
    // Buscar una clave en el nodo actual
    // Si se encuentra, retorna true y la posición donde está ubicada
    // Si no se encuentra, retorna false y la posición del hijo donde debería descender
    @SuppressWarnings("unchecked")
    public boolean searchNode(E key, int[] pos) {
        int i = 0;
        
        // Buscar la posición correcta
        while (i < count && ((Comparable<E>)key).compareTo(keys.get(i)) > 0) {
            i++;
        }
        
        pos[0] = i;
        
        // Si encontró la clave exacta
        if (i < count && ((Comparable<E>)key).compareTo(keys.get(i)) == 0) {
            return true;
        }
        
        return false;
    }
    
    // Verificar si es un nodo hoja
    public boolean isLeaf() {
        return childs.get(0) == null;
    }
    
    // Obtener el getter para idNode
    public int getIdNode() {
        return idNode;
    }
    
    // Retornar las claves encontradas en el nodo
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Node ").append(idNode).append(": [");
        
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        
        return sb.toString();
    }
}