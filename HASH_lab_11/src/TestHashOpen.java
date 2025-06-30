public class TestHashOpen {
    public static void main(String[] args) {
        HashO hash = new HashO(5); // Tamaño de la tabla hash

        // Inserciones con colisiones (todas claves % 5)
        hash.insert(10, "Juan");
        hash.insert(15, "Ana");
        hash.insert(20, "Luis");
        hash.insert(25, "Rosa");
        hash.insert(30, "Carlos");

        System.out.println("\nTabla después de insertar:");
        hash.printTable();

        // Búsqueda
        System.out.println("\nBuscando la clave 20...");
        Register found = hash.search(20);
        System.out.println(found != null ? "Encontrado: " + found : "No se encontró");

        System.out.println("\nBuscando la clave 30...");
        Register notFound = hash.search(30);
        System.out.println(notFound != null ? "Encontrado: " + notFound : "No se encontró");

        // Eliminación
        System.out.println("\nEliminando clave 15...");
        hash.delete(15);

        System.out.println("\nTabla después de eliminar 15:");
        hash.printTable();
    }
}
