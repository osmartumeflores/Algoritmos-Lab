import java.util.List;
public class Main {
    public static void main(String[] args) {
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        gestor.agregarTarea(new Tarea("Estudiar para el examen", 3));
        gestor.agregarTarea(new Tarea("Entregar el proyecto", 5));
        gestor.agregarTarea(new Tarea("Hacer deporte", 1));

        System.out.println("Tareas actuales:");
        gestor.imprimirTareas();

        gestor.eliminarTarea(new Tarea("Hacer deporte", 2)); // No elimina porque son diferentes objetos
        System.out.println("\nDespués de intentar eliminar 'Hacer deporte':");
        gestor.imprimirTareas();

        // Buscar una tarea
        boolean existe = gestor.contieneTarea(new Tarea("Estudiar para el examen", 3));
        System.out.println("\n¿Existe 'Estudiar para el examen'?: " + existe);

        // Contar tareas
        System.out.println("\nTotal de tareas: " + gestor.contarTareas());

        // Obtener tarea de mayor prioridad
        Tarea masImportante = gestor.obtenerTareaMasPrioritaria();
        System.out.println("\nTarea de mayor prioridad: " + masImportante);

        // Invertir tareas
        gestor.invertirTareas();
        System.out.println("\nLista invertida:");
        gestor.imprimirTareas();

        // Completar una tarea
        gestor.completarTarea(new Tarea("Estudiar para el examen", 3));
        System.out.println("\nLista actual:");
        gestor.imprimirTareas();

        gestor.imprimirTareasCompletadas();

        // Uso de funciones auxiliares
        List<Integer> numeros = List.of(1, 2, 3, 4, 5);
        System.out.println("\nBuscar 3 en lista de números: " + FuncionesAuxiliares.buscarElemento(numeros, 3));
        System.out.println("Lista invertida de números: " + FuncionesAuxiliares.invertirLista(numeros));
    }
}
