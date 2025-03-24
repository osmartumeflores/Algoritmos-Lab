public interface Operable {
    <E extends Number> E suma (E a, E b);
    <E extends Number> E resta (E a, E b);
    <E extends Number> E producto (E a, E b);
    <E extends Number> E division (E a, E b);
    <E extends Number> E potencia(E base, E exponente);
    <E extends Number> E raizCuadrada(E a);
    <E extends Number> E raizCubica(E a);
}