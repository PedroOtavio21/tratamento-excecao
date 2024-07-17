public class Robo {
    // Atributos
    private int x;
    private int y;
    private String cor;

    // Construtor
    public Robo(String cor){
        this.cor = cor;
        this.x = 0;
        this.y = 0;
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

    public String getCor(){
        return this.cor;
    }

    // mover() padrão
    public void mover(String direcao) throws MovimentoInvalidoException{
        int novoX = this.getX();
        int novoY = this.getY();
        switch (direcao) {
            case "up":
                novoY += 1;
                break;
            case "down":
                novoY -= 1;
                break;
            case "right":
                novoX += 1;
                break;
            case "left":
                novoX -= 1;
                break;
            default:
                // Verifica se opção recebe um dos cases acima.
                throw new IllegalArgumentException("Direção aplicada inválida! " + direcao);
        }
        validarMovimento(novoX, novoY);

        this.setX(novoX);
        this.setY(novoY);
        System.out.println("Nova coordenada do robô: (" + this.getX() + "," + this.getY() + ").");
    }

    // mover() string
    public String mover(int opcao){
        String direcaoStr;
        switch (opcao) {
            case 1:
                direcaoStr = "up";
                break;
            case 2:
                direcaoStr = "down";
                break;
            case 3:
                direcaoStr = "right";
                break;
            case 4:
                direcaoStr = "left";
                break;
            default:
                // Verifica se opção recebe um dos cases acima.
                throw new IllegalArgumentException("Opção aplicada inválida! " + opcao);
        }
        return direcaoStr;
    }

    // encontrouAlimento()
    public boolean encontrouAlimento(Alimento alimento){
        return this.getX() == alimento.getX() && this.getY() == alimento.getY();
    }

    // validarMovimento -> Exception
    public void validarMovimento(int x, int y) throws MovimentoInvalidoException{
        if(x < 0 || y < 0){
            throw new MovimentoInvalidoException("Movimento inválido para a posição: (" + x + "," + y + ")!");
        }
    }
}
