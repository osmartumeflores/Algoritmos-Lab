public class Principal {
    public static void main(String[] args) {
        Bolsa<Chocolatina> bolsaCho = new Bolsa<>(3);
        bolsaCho.add(new Chocolatina("Milka"));
        bolsaCho.add(new Chocolatina("Ferrero"));
        bolsaCho.add(new Chocolatina("Nestlé"));
        
        for (Chocolatina chocolatina : bolsaCho) {
            System.out.println(chocolatina.getMarca());
        }
        
        Bolsa<Golosina> bolsaGolo = new Bolsa<>(3);
        bolsaGolo.add(new Golosina("Caramelo", 10.5));
        bolsaGolo.add(new Golosina("Gomita", 8.3));
        bolsaGolo.add(new Golosina("Malvavisco", 15.2));
        
        for (Golosina golosina : bolsaGolo) {
            System.out.println(golosina.getNombre() + " - " + golosina.getPeso() + "g");
        }
    }
}