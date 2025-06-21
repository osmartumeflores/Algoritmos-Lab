package btree;

public class TestBTree {
    
    // Actividad 1.1: Insertar claves en árbol B de orden 5
    public static void testActividad1() {
        System.out.println("=== ACTIVIDAD 1.1: Inserción en Árbol B de orden 5 ===");
        BTree<Integer> btree = new BTree<>(5);
        
        int[] claves = {100, 50, 20, 70, 10, 30, 80, 90, 200, 25, 15, 5, 65, 35, 60, 18, 93, 94};
        
        System.out.println("Insertando claves: ");
        for (int clave : claves) {
            System.out.println("\nInsertando: " + clave);
            btree.insert(clave);
            System.out.println("Estado del árbol:");
            System.out.println(btree.toString());
            System.out.println("---");
        }
        
        System.out.println("\nÁrbol final:");
        System.out.println(btree.toString());
    }
    
    // Ejercicio 1: Prueba del método search
    public static void testSearch() {
        System.out.println("\n=== EJERCICIO 1: Prueba del método search ===");
        BTree<Integer> btree = new BTree<>(4);
        
        // Insertar algunas claves
        int[] claves = {10, 20, 30, 40, 50, 60, 70, 80, 90};
        for (int clave : claves) {
            btree.insert(clave);
        }
        
        System.out.println("Árbol construido:");
        System.out.println(btree.toString());
        
        // Probar búsquedas
        System.out.println("\nPruebas de búsqueda:");
        System.out.println("Buscar 50: " + btree.search(50));
        System.out.println("Buscar 25: " + btree.search(25));
        System.out.println("Buscar 80: " + btree.search(80));
    }
    
    // Ejercicio 2: Prueba del método remove
    public static void testRemove() {
        System.out.println("\n=== EJERCICIO 2: Prueba del método remove ===");
        BTree<Integer> btree = new BTree<>(4);
        
        // Insertar claves
        int[] claves = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        for (int clave : claves) {
            btree.insert(clave);
        }
        
        System.out.println("Árbol antes de eliminar:");
        System.out.println(btree.toString());
        
        // Eliminar algunas claves
        System.out.println("\nEliminando 50:");
        btree.remove(50);
        System.out.println(btree.toString());
        
        System.out.println("\nEliminando 20:");
        btree.remove(20);
        System.out.println(btree.toString());
    }
    
    // Ejercicio 4: Sistema de estudiantes
    public static void testSistemaEstudiantes() {
        System.out.println("\n=== EJERCICIO 4: Sistema de Estudiantes ===");
        BTree<RegistroEstudiante> btreeEstudiantes = new BTree<>(4);
        
        // Insertar estudiantes
        RegistroEstudiante[] estudiantes = {
            new RegistroEstudiante(103, "Ana"),
            new RegistroEstudiante(110, "Luis"),
            new RegistroEstudiante(101, "Carlos"),
            new RegistroEstudiante(120, "Lucía"),
            new RegistroEstudiante(115, "David"),
            new RegistroEstudiante(125, "Jorge"),
            new RegistroEstudiante(140, "Camila"),
            new RegistroEstudiante(108, "Rosa"),
            new RegistroEstudiante(132, "Ernesto"),
            new RegistroEstudiante(128, "Denis"),
            new RegistroEstudiante(145, "Enrique"),
            new RegistroEstudiante(122, "Karina")
            // Nota: Se omite el duplicado (108, "Juan") ya que el código 108 ya existe
        };
        
        System.out.println("Insertando estudiantes:");
        for (RegistroEstudiante estudiante : estudiantes) {
            btreeEstudiantes.insert(estudiante);
        }
        
        System.out.println("\nÁrbol de estudiantes:");
        System.out.println(btreeEstudiantes.toString());
        
        // Realizar búsquedas
        System.out.println("\nBúsquedas:");
        
        RegistroEstudiante buscar115 = new RegistroEstudiante(115, "");
        if (btreeEstudiantes.search(buscar115)) {
            System.out.println("Estudiante 115 encontrado");
        }
        
        RegistroEstudiante buscar132 = new RegistroEstudiante(132, "");
        if (btreeEstudiantes.search(buscar132)) {
            System.out.println("Estudiante 132 encontrado");
        }
        
        RegistroEstudiante buscar999 = new RegistroEstudiante(999, "");
        if (!btreeEstudiantes.search(buscar999)) {
            System.out.println("Estudiante 999 no encontrado");
        }
        
        // Eliminar estudiante
        System.out.println("\nEliminando estudiante 101:");
        RegistroEstudiante eliminar101 = new RegistroEstudiante(101, "");
        btreeEstudiantes.remove(eliminar101);
        
        // Insertar nuevo estudiante
        System.out.println("\nInsertando estudiante (106, \"Sara\"):");
        btreeEstudiantes.insert(new RegistroEstudiante(106, "Sara"));
        
        System.out.println("\nÁrbol final de estudiantes:");
        System.out.println(btreeEstudiantes.toString());
    }
    
    // Método principal para ejecutar todas las pruebas
    public static void main(String[] args) {
        testActividad1();
        testSearch();
        testRemove();
        testSistemaEstudiantes();
    }
}   
       