import java.util.*;

public class Ejercicio3 {
    public static void main(String[] args) {
        int size = 5;
        LinkedList<Register>[] table = new LinkedList[size];
        for (int i = 0; i < size; i++) table[i] = new LinkedList<>();

        Register[] registros = {
            new Register(10, "Juan"),
            new Register(15, "Ana"),
            new Register(20, "Luis"),
            new Register(25, "Rosa")
        };

        for (Register r : registros) {
            int index = r.key % size;
            table[index].add(r);
        }

        System.out.println("Tabla Hash con Encadenamiento:");
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "]: ");
            for (Register r : table[i]) {
                System.out.print(r + " -> ");
            }
            System.out.println("null");
        }

        // Buscar clave 20
        System.out.println("\nBuscando clave 20:");
        boolean found20 = false;
        for (Register r : table[20 % size]) {
            if (r.key == 20) {
                System.out.println("Encontrado: " + r);
                found20 = true;
                break;
            }
        }
        if (!found20) System.out.println("No encontrado.");

        // Buscar clave 30
        System.out.println("\nBuscando clave 30:");
        boolean found30 = false;
        for (Register r : table[30 % size]) {
            if (r.key == 30) {
                System.out.println("Encontrado: " + r);
                found30 = true;
                break;
            }
        }
        if (!found30) System.out.println("No encontrado.");
    }
}
