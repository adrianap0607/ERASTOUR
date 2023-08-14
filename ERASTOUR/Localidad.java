public class Localidad {
    private int id;
    private String nombre;
    private int capacidadMaxima;
    private double precio;
    private int boletosVendidos;

    public Localidad(int id, String nombre, int capacidadMaxima, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public double getPrecio() {
        return precio;
    }

    public int getBoletosVendidos() {
        return boletosVendidos;
    }

    public void venderBoletos(int cantidad) {
        boletosVendidos += cantidad;
    }

    public boolean hayEspacio() {
        return boletosVendidos < capacidadMaxima;
    }

    public boolean hayDisponibilidad(int cantidadBoletos) {
        return (capacidadMaxima - boletosVendidos) >= cantidadBoletos;
    }
}

