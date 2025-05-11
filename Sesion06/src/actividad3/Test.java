package actividad3;
import actividad1.ExceptionIsEmpty;
public class Test {
    public static void main(String[] args) {
        PriorityQueue<String, Integer> colaPrioridad = new PriorityQueueLinkSort<>();
        try {
            colaPrioridad.enqueue("A", 2);
            colaPrioridad.enqueue("B", 54);
            colaPrioridad.enqueue("C", 3);
            colaPrioridad.enqueue("D", 5);
            colaPrioridad.enqueue("E", 1);

            System.out.println("Cola de prioridad ordenada: " + colaPrioridad);
            System.out.println("Frente: " + colaPrioridad.front());
            System.out.println("Final: " + colaPrioridad.back());
            colaPrioridad.dequeue();
            System.out.println("Después de dequeue: " + colaPrioridad);
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
