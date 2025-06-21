package btree;
public interface BinarySearchTree<E extends Comparable<E>> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNotFoundException;
    void delete(E data) throws ItemNotFoundException, ExceptionIsEmpty;
    boolean isEmpty();
    void destroy() throws ExceptionIsEmpty;
    String toString(); // recorrido inOrden por defecto
}