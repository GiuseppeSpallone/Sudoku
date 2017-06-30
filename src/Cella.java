 public class Cella {
    private int riga;
    private int colonna;

    public Cella(int riga, int colonna) {
        this.riga = riga;
        this.colonna = colonna;
    }

    @Override
    public String toString() {
        return "Cella{" +
                "riga=" + riga +
                ", colonna=" + colonna +
                '}';
    }
}
