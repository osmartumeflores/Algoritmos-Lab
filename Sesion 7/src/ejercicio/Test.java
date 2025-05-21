package ejercicio;
import actividad.LinkedBST;
import ejercicio.Ejercicios;

public class Test {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();
        try {
            bst.insert(8);
            bst.insert(3);
            bst.insert(10);
            bst.insert(1);
            bst.insert(6);
            bst.insert(14);
            bst.insert(4);

            Ejercicios<Integer> ej = new Ejercicios<>(bst);

            System.out.println("Altura(3): " + ej.height(3));
            System.out.println("Amplitude(nivel 2): " + ej.amplitude(2));
            System.out.println("Area BST: " + ej.areaBST());
            System.out.println("Dibujo del árbol:\n" + ej.drawBST());
            System.out.println("Parenthesize:\n" + ej.parenthesize());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
