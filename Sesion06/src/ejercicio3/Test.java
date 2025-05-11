package ejercicio3;

import actividad1.ExceptionIsEmpty;
import actividad3.PriorityQueue;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<String, Integer> cola = new PriorityQueueLinked<>(4);

        try {
            cola.enqueue("Tarea baja", 0);
            cola.enqueue("Tarea media", 2);
            cola.enqueue("Tarea urgente", 3);

            System.out.println("Cola de prioridad (varias listas):\n" + cola);
            cola.dequeue();
            System.out.println("Después de dequeue:\n" + cola);
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
