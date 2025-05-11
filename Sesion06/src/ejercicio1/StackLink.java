package ejercicio1;
import actividad1.ExceptionIsEmpty;
import actividad1.Stack;
import actividad2.Node;
public class StackLink<E> implements Stack<E> {
    private Node<E> top;
    public StackLink(){
        top=null;
    }
    public void push(E x){
        Node<E> newNode = new Node<>(x);
        newNode.setNext(top);
        top=newNode;
    }
    public E pop() throws ExceptionIsEmpty{
        if (isEmpty()) throw new ExceptionIsEmpty("Pila vacia");
        E data=top.getData();
        top=top.getNext();
        return data;
    }
    public E top() throws ExceptionIsEmpty{
        if(isEmpty()) throw new ExceptionIsEmpty("Pila vacia");
        return top.getData();
    }
    public boolean isEmpty(){
        return top==null;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder("[");
        Node<E> aux=top;
        while(aux != null){
            sb.append(aux.getData()).append(" ");
            aux=aux.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
