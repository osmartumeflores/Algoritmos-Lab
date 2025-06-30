public class HashC {
    private Register[] table;
    private int size;

    public HashC(int capacity) {
        table = new Register[capacity];
        size = capacity;
    }

    private int hash(int key) {
        return key % size;
    }

    public boolean insert(int key, String name) {
        int index = hash(key);
        int startIndex = index;

        do {
            if (table[index] == null || table[index].isDeleted) {
                table[index] = new Register(key, name);
                return true;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        System.out.println("No hay espacio disponible para insertar: " + key);
        return false;
    }

    public Register search(int key) {
        int index = hash(key);
        int startIndex = index;

        do {
            if (table[index] == null) {
                return null;
            } else if (!table[index].isDeleted && table[index].key == key) {
                return table[index];
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        return null;
    }

    public boolean delete(int key) {
        int index = hash(key);
        int startIndex = index;

        do {
            if (table[index] == null) {
                return false;
            } else if (!table[index].isDeleted && table[index].key == key) {
                table[index].isDeleted = true; // eliminación lógica
                return true;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        return false;
    }

    public void printTable() {
        System.out.println("Tabla Hash:");
        for (int i = 0; i < size; i++) {
            System.out.println("[" + i + "] " + (table[i] != null ? table[i] : "vacío"));
        }
    }
}
