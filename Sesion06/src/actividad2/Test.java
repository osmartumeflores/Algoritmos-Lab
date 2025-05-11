package actividad2;

import actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) {
        Queue<Integer> cola = new QueueLink<>();

        try {
            cola.enqueue(100);
            cola.enqueue(200);
            cola.enqueue(300);

            System.out.println("Contenido de la cola: " + cola);
            System.out.println("Frente: " + cola.front());
            System.out.println("Final: " + cola.back());
            cola.dequeue();
            System.out.println("Después del dequeue: " + cola);
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
