//EJERCICIO 3
package btree;
import java.io.*;
import java.util.*;
import btree.ItemNotFoundException;
// Clase auxiliar para almacenar información de nodos
class NodeInfo {
    int level;
    int idNode;
    ArrayList<Integer> keys;
    
    public NodeInfo(int level, int idNode, ArrayList<Integer> keys) {
        this.level = level;
        this.idNode = idNode;
        this.keys = keys;
    }
}

// Extensión de BTree 
public class BTreeFileReader<E extends Comparable<E>> extends BTree<E> {
    
    public BTreeFileReader(int orden) {
        super(orden);
    }
    
    public static BTree<Integer> buildingBtree(String filename) throws ItemNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            
            // Leer orden del árbol
            line = br.readLine();
            if (line == null) {
                throw new ItemNotFoundException("Archivo vacío o formato incorrecto");
            }
            
            int orden = Integer.parseInt(line.trim());
            if (orden < 3) {
                throw new ItemNotFoundException("El orden del árbol debe ser al menos 3");
            }
            
            BTree<Integer> btree = new BTree<>(orden);
            
            // Estructura para validar el árbol
            Map<Integer, NodeInfo> nodeMap = new HashMap<>();
            List<NodeInfo> nodeList = new ArrayList<>();
            
            // Leer información de nodos
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length < 3) {
                    throw new ItemNotFoundException("Formato de línea incorrecto: " + line);
                }
                
                // Parsear nivel e idNodo
                int level = Integer.parseInt(parts[0].trim());
                int idNode = Integer.parseInt(parts[1].trim());
                
                // Parsear claves
                ArrayList<Integer> keys = new ArrayList<>();
                for (int i = 2; i < parts.length; i++) {
                    keys.add(Integer.parseInt(parts[i].trim()));
                }
                
                // Validar que las claves estén ordenadas
                for (int i = 1; i < keys.size(); i++) {
                    if (keys.get(i - 1) >= keys.get(i)) {
                        throw new ItemNotFoundException("Las claves en el nodo " + idNode + " no están ordenadas");
                    }
                }
                
                NodeInfo nodeInfo = new NodeInfo(level, idNode, keys);
                nodeMap.put(idNode, nodeInfo);
                nodeList.add(nodeInfo);
            }
            
            if (nodeList.isEmpty()) {
                throw new ItemNotFoundException("No se encontraron nodos en el archivo");
            }
            
            // Ordenar nodos por nivel
            nodeList.sort(Comparator.comparingInt(n -> n.level));
            
            // Validar propiedades del B-Tree
            validateBTreeProperties(nodeList, orden);
            
            // Construir el árbol insertando las claves en orden
            Set<Integer> allKeys = new TreeSet<>();
            for (NodeInfo node : nodeList) {
                allKeys.addAll(node.keys);
            }
            
            // Insertar todas las claves para construir el árbol
            for (Integer key : allKeys) {
                btree.insert(key);
            }
            
            return btree;
            
        } catch (IOException e) {
            throw new ItemNotFoundException("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new ItemNotFoundException("Error en el formato numérico: " + e.getMessage());
        }
    }
    
    // Método auxiliar para validar propiedades del B-Tree
    private static void validateBTreeProperties(List<NodeInfo> nodeList, int orden) throws ItemNotFoundException {
        if (nodeList.isEmpty()) {
            throw new ItemNotFoundException("Lista de nodos vacía");
        }
        
        // Agrupar nodos por nivel
        Map<Integer, List<NodeInfo>> levelMap = new HashMap<>();
        for (NodeInfo node : nodeList) {
            levelMap.computeIfAbsent(node.level, k -> new ArrayList<>()).add(node);
        }
        
        // Validar que existe un nodo raíz (nivel 0)
        if (!levelMap.containsKey(0)) {
            throw new ItemNotFoundException("No se encontró nodo raíz (nivel 0)");
        }
        
        List<NodeInfo> rootNodes = levelMap.get(0);
        if (rootNodes.size() != 1) {
            throw new ItemNotFoundException("Debe haber exactamente un nodo raíz");
        }
        
        NodeInfo root = rootNodes.get(0);
        
        // Validar propiedades de cada nivel
        for (Map.Entry<Integer, List<NodeInfo>> entry : levelMap.entrySet()) {
            int level = entry.getKey();
            List<NodeInfo> nodesAtLevel = entry.getValue();
            
            for (NodeInfo node : nodesAtLevel) {
                validateNodeProperties(node, orden, level == 0, isLeafLevel(level, levelMap));
            }
        }
        
        // Validar que todas las hojas están al mismo nivel
        int maxLevel = Collections.max(levelMap.keySet());
        validateLeafLevel(levelMap.get(maxLevel), orden);
    }
    
    // Determinar si un nivel es de hojas (no hay niveles superiores)
    private static boolean isLeafLevel(int level, Map<Integer, List<NodeInfo>> levelMap) {
        return level == Collections.max(levelMap.keySet());
    }
    
    // Validar propiedades de un nodo individual
    private static void validateNodeProperties(NodeInfo node, int orden, boolean isRoot, boolean isLeaf) throws ItemNotFoundException {
        int keyCount = node.keys.size();
        
        // Validar número máximo de claves
        if (keyCount >= orden) {
            throw new ItemNotFoundException("El nodo " + node.idNode + " tiene demasiadas claves (" + keyCount + " >= " + orden + ")");
        }
        
        // Validar número mínimo de claves
        if (!isRoot && !isLeaf) {
            int minKeys = (orden / 2) - 1;
            if (keyCount < minKeys) {
                throw new ItemNotFoundException("El nodo interno " + node.idNode + " tiene muy pocas claves (" + keyCount + " < " + minKeys + ")");
            }
        }
        
        // Para la raíz, debe tener al menos 1 clave si no es hoja
        if (isRoot && !isLeaf && keyCount == 0) {
            throw new ItemNotFoundException("La raíz debe tener al menos una clave");
        }
        
        // Validar que las claves están ordenadas (ya se hizo en buildingBtree)
        for (int i = 1; i < node.keys.size(); i++) {
            if (node.keys.get(i - 1) >= node.keys.get(i)) {
                throw new ItemNotFoundException("Las claves en el nodo " + node.idNode + " no están ordenadas");
            }
        }
    }
    
    // Validar que todos los nodos hoja tienen propiedades correctas
    private static void validateLeafLevel(List<NodeInfo> leafNodes, int orden) throws ItemNotFoundException {
        int minKeys = (orden / 2) - 1;
        
        for (NodeInfo leaf : leafNodes) {
            // Las hojas pueden tener menos claves que el mínimo en algunos casos
            if (leaf.keys.isEmpty()) {
                throw new ItemNotFoundException("El nodo hoja " + leaf.idNode + " está vacío");
            }
            
            if (leaf.keys.size() >= orden) {
                throw new ItemNotFoundException("El nodo hoja " + leaf.idNode + " tiene demasiadas claves");
            }
        }
    }
    
    // Método de prueba
    public static void main(String[] args) {
        try {
            // Crear archivo de prueba
            createTestFile();
            
            // Construir árbol desde archivo
            BTree<Integer> btree = buildingBtree("arbolB.txt");
            
            System.out.println("Árbol B construido exitosamente:");
            System.out.println(btree.toString());
            
        } catch (ItemNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }
    
    // Método auxiliar para crear archivo de prueba
    private static void createTestFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("arbolB.txt"))) {
            writer.println("4");
            writer.println("0,6,25");
            writer.println("1,2,10,16");
            writer.println("1,5,30,40");
            writer.println("2,0,1,3,8");
            writer.println("2,1,12,15");
            writer.println("2,3,18,19,21");
            writer.println("2,4,27,28");
            writer.println("2,8,33,36,39");
            writer.println("2,7,42,45");
        } catch (IOException e) {
            System.err.println("Error al crear archivo de prueba: " + e.getMessage());
        }
    }
}