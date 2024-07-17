public class Alimento {
    // Atributos
    private int x;
    private int y;
    private String nome;

    // Construtor
    public Alimento(int x, int y, String nome){
        this.x = x;
        this.y = y;
        this.nome = nome;
    }
    
    // Getters e Setters
    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }

    public String getNome(){
        return this.nome;
    }

}
