import java.util.ArrayList;
import java.util.List;

public class MainEjercicios {

    public static void main(String[] args) {
        // Ejercicio 1: buscarElemento
        List<Integer> listaNumeros = new ArrayList<>();
        listaNumeros.add(10);
        listaNumeros.add(20);
        listaNumeros.add(30);
        
        boolean encontrado = FuncionesEjercicios.buscarElemento(listaNumeros, 20);
        System.out.println("¿El número 20 está en la lista?: " + encontrado);

        // Ejercicio 2: invertirLista
        List<Integer> listaInvertida = FuncionesEjercicios.invertirLista(listaNumeros);
        System.out.println("Lista invertida: " + listaInvertida);

        // Ejercicio 3: insertarAlFinal
        Node<Integer> head = new Node<>(1);
        head = FuncionesEjercicios.insertarAlFinal(head, 2);
        head = FuncionesEjercicios.insertarAlFinal(head, 3);
        System.out.print("Lista enlazada después de insertar al final: ");
        imprimirLista(head);

        // Ejercicio 4: contarNodos
        int cantidadNodos = FuncionesEjercicios.contarNodos(head);
        System.out.println("Cantidad de nodos en la lista: " + cantidadNodos);

        // Ejercicio 5: sonIguales
        Node<Integer> listaA = new Node<>(1);
        listaA = FuncionesEjercicios.insertarAlFinal(listaA, 2);
        listaA = FuncionesEjercicios.insertarAlFinal(listaA, 3);

        Node<Integer> listaB = new Node<>(1);
        listaB = FuncionesEjercicios.insertarAlFinal(listaB, 2);
        listaB = FuncionesEjercicios.insertarAlFinal(listaB, 3);

        boolean iguales = FuncionesEjercicios.sonIguales(listaA, listaB);
        System.out.println("¿Las dos listas son iguales?: " + iguales);

        // Ejercicio 6: concatenarListas
        Node<Integer> listaC = new Node<>(4);
        listaC = FuncionesEjercicios.insertarAlFinal(listaC, 5);

        Node<Integer> concatenada = FuncionesEjercicios.concatenarListas(listaA, listaC);
        System.out.print("Lista concatenada: ");
        imprimirLista(concatenada);
    }

    // Método auxiliar para imprimir una lista enlazada
    public static <T> void imprimirLista(Node<T> head) {
        Node<T> actual = head;
        while (actual != null) {
            System.out.print(actual.getData() + " -> ");
            actual = actual.getNext();
        }
        System.out.println("null");
    }
}

