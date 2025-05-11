package actividad1;
public class Test {
    public static void main(String[] args) {
        Stack<String> pila = new StackArray<>(5);

        try {
            pila.push("Uno");
            pila.push("Dos");
            pila.push("Tres");

            System.out.println("Contenido de la pila: " + pila);
            System.out.println("Elemento en el tope: " + pila.top());
            pila.pop();
            System.out.println("Después del pop: " + pila);
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}
