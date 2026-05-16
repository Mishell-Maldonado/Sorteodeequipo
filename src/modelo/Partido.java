package modelo;


public class Partido {
    private String local;
    private String visitante;

    public Partido(String local, String visitante) {
        this.local = local;
        this.visitante = visitante;
    }

    @Override
    public String toString() {
        return local + " vs " + visitante;
    }
}