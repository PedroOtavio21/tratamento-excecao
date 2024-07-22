public abstract class Obstaculo {
    // Atributos
    protected String id;

    // Metodos
    public Obstaculo(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public abstract void bater(Robo robo);
}