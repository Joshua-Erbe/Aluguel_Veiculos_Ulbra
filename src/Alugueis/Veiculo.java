package Alugueis;

public abstract class Veiculo implements Tarifavel {
    private int codigo;
    private String modelo;
    private double valorDiaria;
    private boolean disponivel;

    public Veiculo(int codigo, String modelo, double valorDiaria) {
        //Ja programo em javascript então pesquisei e usei a mesma logica de "new Error ("mensagem aqui")"
        if (valorDiaria <= 0) {
            throw new IllegalArgumentException("Valor diário esta inválido! O valor da diaria deve ser maior que 0");
        }

        if (codigo <= 0) {
            throw new IllegalArgumentException("Codigo do veiculo esta invalido! Insira o correto antes de prosseguir");
        }

        if (modelo == null || modelo.isBlank()) {
            throw new IllegalArgumentException("Insira o Modelo do veiculo antes de prosseguir!");
        }

        this.codigo = codigo;
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.disponivel = true;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getModelo() {
        return this.modelo;
    }

    public double getValorDiaria() {
        return this.valorDiaria;
    }

    public boolean isDisponivel(){
        return this.disponivel;
    }

    public boolean alterarValorDiaria(double novoValor) {
        if (novoValor > 0) {
            this.valorDiaria = novoValor;
            return true;
        }
        return false;
    }

    public boolean alugar() {
        if (!disponivel) { return false; }
        this.disponivel = false;
        return true;
    }

    public boolean devolver() {
        if (disponivel) { return false; }
        this.disponivel = true;
        return true;
    }

    @Override
    public abstract double calcularValor(int dias);
}
