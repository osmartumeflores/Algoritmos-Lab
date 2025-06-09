// MainEjercicios.java - Prueba de ejercicios teóricos del Lab 11
package graph;

public class MainEjercicios {
    public static void main(String[] args) {
        GraphLink<String> g1 = new GraphLink<>();
        g1.insertVertex("1");
        g1.insertVertex("2");
        g1.insertVertex("3");
        g1.insertVertex("4");

        g1.insertEdge("1", "2");
        g1.insertEdge("2", "3");
        g1.insertEdge("3", "4");
        g1.insertEdge("4", "1");

        System.out.println("\nEjercicio: Tipo de grafo");
        System.out.println("¿Es camino?: " + GraphAnalyzer.esCamino(g1));
        System.out.println("¿Es ciclo?: " + GraphAnalyzer.esCiclo(g1));
        System.out.println("¿Es rueda?: " + GraphAnalyzer.esRueda(g1));
        System.out.println("¿Es completo?: " + GraphAnalyzer.esCompleto(g1));

        GraphLink<String> g2 = new GraphLink<>();
        g2.insertVertex("A");
        g2.insertVertex("B");
        g2.insertVertex("C");
        g2.insertVertex("D");

        g2.insertEdge("A", "B");
        g2.insertEdge("B", "C");
        g2.insertEdge("C", "D");
        g2.insertEdge("D", "A");
        g2.insertEdge("A", "C");
        g2.insertEdge("B", "D");

        System.out.println("\nEjercicio: Propiedades grafo G2");
        System.out.println("¿Es plano?: " + GraphAnalyzer.esPlano(g2));
        System.out.println("¿Es conexo?: " + GraphAnalyzer.esConexo(g2));
        System.out.println("¿Es auto-complementario?: " + GraphAnalyzer.esAutoComplementario(g2));
        System.out.println("¿Es isomorfo con g1?: " + GraphAnalyzer.esIsomorfo(g1, g2));
    }
}
