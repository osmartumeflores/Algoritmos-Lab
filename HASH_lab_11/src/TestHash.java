public class TestHash {
    public static void main(String[] args) {
        HashC hash = new HashC(11); // tamaño fijo de la tabla

        // Inserciones
        hash.insert(34, "Ana");
        hash.insert(3, "Luis");
        hash.insert(7, "Mario");
        hash.insert(30, "Lucía");
        hash.insert(11, "Pepe");
        hash.insert(8, "Sara");
        hash.insert(7, "Marta"); // clave repetida
        hash.insert(23, "Tito");
        hash.insert(41, "Nora");
        hash.insert(16, "Iván");
        hash.insert(34, "Leo"); // clave repetida

        System.out.println("\nTabla después de insertar:");
        hash.printTable();

        // Eliminación lógica
        System.out.println("\nEliminando la clave 30...");
        hash.delete(30);

        // Búsqueda
        System.out.println("\nBuscando la clave 23...");
        Register result = hash.search(23);
        System.out.println(result != null ? "Encontrado: " + result : "No se encontró");

        System.out.println("\nTabla después de eliminar la clave 30:");
        hash.printTable();
    }
}
