package ejercicio2;
import actividad1.ExceptionIsEmpty;
import actividad2.Queue;
public class Test {
    public static void main(String[] args){
        Queue<Integer> cola =new QueueArray<>(4);
        try{
            cola.enqueue(1);
            cola.enqueue(2);
            cola.enqueue(3);
            cola.enqueue(4);
            System.out.println("Cola con arreglo: " + cola);
            cola.dequeue();
            System.out.println("Despues del dequeue: " + cola);    
        }catch (ExceptionIsEmpty e){
            System.out.println(e.getMessage());
        }
    }
}
