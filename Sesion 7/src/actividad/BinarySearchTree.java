package actividad;

public interface BinarySearchTree<E extends Comparable<E>> {
    void insert(E data) throws ItemDuplicated;
    E search(E data) throws ItemNoFound;
    void delete(E data) throws ItemNoFound, ExceptionIsEmpty;
    boolean isEmpty();
    void destroy() throws ExceptionIsEmpty;
    String toString(); // recorrido inOrden por defecto
}
