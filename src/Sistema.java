import Alugueis.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        boolean controlador = false;
        int opcao;

        while (!controlador) {
            System.out.println("--- BEM VINDO AO ALUGUEL DE VEICULOS ---");
            System.out.println("--- SELECIONE A OPÇÃO QUE DESEJA ---");
            System.out.println("1. CADASTRAR NOVO VEICULO");
            System.out.println("2. ALTERAR O VALOR DO ALUGUEL DE UM VEICULO");
            System.out.println("3. VISUALIZAR VEICULOS CADASTRADOS");
            System.out.println("4. ALUGAR OU DEVOLVER UM VEICULO");
            System.out.println("5. REALIZAR O ORÇAMENTO DE UM VEICULO");
            System.out.println("6. CONSULTAR DISPONIBILIDADE DE VEICULO");
            System.out.println("7. ENCERRAR O SISTEMA");
            if (scr.hasNextInt()) {
                opcao = scr.nextInt();
            } else {
                System.out.println("Digite um numero valido");
                scr.next();
                continue;
            }
            switch (opcao) {
                case 1:
                    System.out.println("--- Qual deseja cadastrar ---");
                    System.out.println("1. Carro");
                    System.out.println("2. Moto");
                    int qual = scr.nextInt();
                    System.out.print("Código do Veiculo: ");
                    int codigoVeiculo = scr.nextInt();
                    scr.nextLine();
                    System.out.print("Modelo do Veiculo: ");
                    String modeloVeiculo = scr.nextLine();
                    System.out.print("Valor da Diária: ");
                    double valorVeiculo = scr.nextDouble();
                    scr.nextLine();

                    if (qual == 1) {
                        veiculos.add(new Carro(codigoVeiculo, modeloVeiculo, valorVeiculo));
                    } else {
                        veiculos.add(new Moto(codigoVeiculo, modeloVeiculo, valorVeiculo));
                    }
                    System.out.println("Veículo cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.print("Código do veículo: ");
                    int codigoAlterar = scr.nextInt();
                    System.out.print("Novo valor da diária: ");
                    double novoValor = scr.nextDouble();
                    boolean alterado = false;
                    for (Veiculo v : veiculos) {
                        if (v.getCodigo() == codigoAlterar) {
                            alterado = v.alterarValorDiaria(novoValor);
                        }
                    }
                    System.out.println(alterado ? "o Valor da diaria foi alterado!" : "Valor não alterado! Verifique as informações");
                    break;
                case 3:
                    if (veiculos.isEmpty()) {
                        System.out.println("Nenhum veículo cadastrado.");
                    }
                    for (Veiculo v : veiculos) {
                        String situacao = v.isDisponivel() ? "disponível" : "alugado";
                        System.out.printf("%d - %s - R$ %.2f - %s%n", v.getCodigo(), v.getModelo(), v.getValorDiaria(), situacao);
                    }
                    break;
                case 4:
                    System.out.print("Código do veículo: ");
                    int codAluguel = scr.nextInt();
                    System.out.println("--- Qual opção deseja ---");
                    System.out.println("1 - Alugar Veiculo");
                    System.out.println("2 - Devolver Veiculo");
                    int acao = scr.nextInt();
                    Veiculo alvoAluguel = null;
                    for (Veiculo v : veiculos) {
                        if (v.getCodigo() == codAluguel) {
                            alvoAluguel = v;
                        }
                    }
                    if (alvoAluguel == null) {
                        System.out.println("Veículo não encontrado!");
                    } else if (acao == 1) {
                        System.out.println(alvoAluguel.alugar() ? "Veículo alugado com sucesso!" : "Não foi possível alugar.");
                    } else if (acao == 2) {
                        System.out.println(alvoAluguel.devolver() ? "Veículo devolvido com sucesso!" : "Não foi possível devolver.");
                    } else {
                        System.out.println("Opção inválida!");
                    }
                    break;
                case 5:
                    System.out.print("Código do veículo: ");
                    int codOrcamento = scr.nextInt();
                    System.out.print("Quantidade de dias: ");
                    int dias = scr.nextInt();
                    Veiculo alvoOrcamento = null;
                    for (Veiculo v : veiculos) {
                        if (v.getCodigo() == codOrcamento) {
                            alvoOrcamento = v;
                        }
                    }
                    if (alvoOrcamento == null) {
                        System.out.println("Veículo não encontrado!");
                    } else {
                        System.out.printf("Orçamento: R$ %.2f", alvoOrcamento.calcularValor(dias));
                    }
                    break;
                case 6:
                    System.out.print("Código do veículo: ");
                    int codigoV = scr.nextInt();
                    Veiculo veiculo = buscarPorCodigo(veiculos, codigoV);
                    if (veiculo == null) {
                        System.out.println("Veículo não encontrado!");
                    } else {
                        System.out.println(veiculo.isDisponivel() ? "O veículo está disponível." : "O veículo está alugado.");
                    }
                    break;
                case 7:
                    controlador = true;
                    System.out.println("Obrigado por usar nosso sistema!");
                    break;
                default:
                    System.out.println("Digite uma opção valida!");
            }
        }

        scr.close();
    }

    private static Veiculo buscarPorCodigo(ArrayList<Veiculo> veiculos, int codigo) {
        for (Veiculo v : veiculos) {
            if (v.getCodigo() == codigo) {
                return v;
            }
        }
        return null;
    }
}
