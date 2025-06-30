public class Ejercicio2 {
    public static void main(String[] args) {
        int size = 6;
        Integer[] table = new Integer[size];
        int[] values = {12, 18, 24, 30};

        for (int x : values) {
            int index = x % size;
            int original = index;
            while (table[index] != null) {
                System.out.println("Colisión en " + index + " para " + x + ", sondeo...");
                index = (index + 1) % size;
                if (index == original) {
                    System.out.println("Tabla llena. No se pudo insertar " + x);
                    break;
                }
            }
            if (table[index] == null) {
                table[index] = x;
                System.out.println("Insertado " + x + " en posición " + index);
            }
        }

        System.out.println("\nTabla final:");
        for (int i = 0; i < size; i++) {
            System.out.println("[" + i + "] " + (table[i] != null ? table[i] : "vacío"));
        }
    }
}
