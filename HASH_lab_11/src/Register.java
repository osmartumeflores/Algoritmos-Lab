public class Register {
    int key;
    String name;
    boolean isDeleted;

    public Register(int key, String name) {
        this.key = key;
        this.name = name;
        this.isDeleted = false;
    }

    public String toString() {
        return "(" + key + ", " + name + (isDeleted ? ", eliminado" : "") + ")";
    }
}
    