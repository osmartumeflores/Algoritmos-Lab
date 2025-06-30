import java.util.LinkedList;

public class HashO {
    private LinkedList<Register>[] table;
    private int size;

    public HashO(int capacity) {
        size = capacity;
        table = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(int key, String name) {
        int index = hash(key);
        // Verifica si la clave ya existe para evitar duplicados
        for (Register r : table[index]) {
            if (r.key == key) {
                System.out.println("Clave ya existente: " + key);
                return;
            }
        }
        table[index].add(new Register(key, name));
    }

    public Register search(int key) {
        int index = hash(key);
        for (Register r : table[index]) {
            if (r.key == key) {
                return r;
            }
        }
        return null;
    }

    public boolean delete(int key) {
        int index = hash(key);
        for (Register r : table[index]) {
            if (r.key == key) {
                table[index].remove(r);
                return true;
            }
        }
        return false;
    }

    public void printTable() {
        System.out.println("Tabla Hash (Abierto):");
        for (int i = 0; i < size; i++) {
            System.out.print("[" + i + "]: ");
            if (table[i].isEmpty()) {
                System.out.println("vacío");
            } else {
                for (Register r : table[i]) {
                    System.out.print(r + " -> ");
                }
                System.out.println("null");
            }
        }
    }
}
