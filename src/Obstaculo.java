public abstract class Obstaculo {
    // Atributos
    protected int id;

    // Metodos
    public Obstaculo(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract void bater(Robo robo);
}