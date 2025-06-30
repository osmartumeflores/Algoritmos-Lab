public class Ejercicio4 {
    static class Entry {
        int key;
        boolean isDeleted;

        public Entry(int key) {
            this.key = key;
            this.isDeleted = false;
        }

        public String toString() {
            return isDeleted ? "(eliminado)" : String.valueOf(key);
        }
    }

    public static void main(String[] args) {
        int size = 7;
        Entry[] table = new Entry[size];
        int[] values = {5, 12, 19};

        for (int x : values) {
            int index = x % size;
            while (table[index] != null && !table[index].isDeleted) {
                index = (index + 1) % size;
            }
            table[index] = new Entry(x);
        }

        System.out.println("Tabla antes de eliminar:");
        for (int i = 0; i < size; i++) {
            System.out.println("[" + i + "]: " + (table[i] != null ? table[i] : "vacío"));
        }

        // Eliminar 12 (lógicamente)
        for (int i = 0; i < size; i++) {
            if (table[i] != null && table[i].key == 12) {
                table[i].isDeleted = true;
                break;
            }
        }

        // Buscar 19 después de eliminar 12
        System.out.println("\nBuscando 19 después de eliminar 12:");
        int idx = 19 % size;
        boolean found = false;
        int start = idx;
        do {
            if (table[idx] == null) break;
            if (!table[idx].isDeleted && table[idx].key == 19) {
                found = true;
                break;
            }
            idx = (idx + 1) % size;
        } while (idx != start);

        System.out.println(found ? "19 encontrado." : "19 no encontrado.");

        System.out.println("\nTabla después de eliminar:");
        for (int i = 0; i < size; i++) {
            System.out.println("[" + i + "]: " + (table[i] != null ? table[i] : "vacío"));
        }
    }
}
