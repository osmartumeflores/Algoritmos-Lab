public class Ejercicio1 {
    public static void main(String[] args) {
        int size = 7;
        Integer[] table = new Integer[size];
        int[] values = {3, 10, 17, 24};

        for (int x : values) {
            int index = x % size;
            table[index] = x;
            System.out.println("Insertando " + x + " en posición " + index);
        }

        System.out.println("\nTabla hash final:");
        for (int i = 0; i < size; i++) {
            System.out.println("[" + i + "] " + (table[i] != null ? table[i] : "vacío"));
        }
    }
}
