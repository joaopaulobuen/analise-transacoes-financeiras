package org.example;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

// Classe Transacao representa a entidade do domínio
class Transacao {
    private List<Double> transacoes;

    public Transacao(List<Double> transacoes) {
        this.transacoes = transacoes;
    }

    public List<Double> getTransacoes() {
        return transacoes;
    }
}

// Classe AnaliseTransacoesUseCase contém a lógica do caso de uso
class AnaliseTransacoesUseCase {
    public String analisar(Transacao transacao) {
        List<Double> transacoes = transacao.getTransacoes();
        double saldoFinal = 0;
        double maiorDeposito = Double.MIN_VALUE;
        double maiorRetirada = Double.MAX_VALUE;
        String resultado;

        for (Double transacaoAtual : transacoes) {
            saldoFinal += transacaoAtual; // Adiciona o valor da transação ao saldo

            // Atualiza o maior depósito
            if (transacaoAtual > 0) {
                if (transacaoAtual > maiorDeposito) {
                    maiorDeposito = transacaoAtual;
                }
            } else { // Atualiza a maior retirada
                if (transacaoAtual < maiorRetirada) {
                    maiorRetirada = transacaoAtual;
                }
            }
        }
        if(saldoFinal > 0){
            resultado = "Saldo positivo";
        }else{
            resultado = "Saldo negativo";
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.00", symbols);
        // Constrói a saída de forma adequada e como solicitado na descrição do desafio:
        StringBuilder sb = new StringBuilder();
        sb.append("Saldo: ").append(df.format(saldoFinal)).append("\n");
        sb.append("Deposito: ").append(df.format(maiorDeposito)).append("\n");
        sb.append("Retirada: ").append(df.format(maiorRetirada)).append("\n");
        sb.append(resultado);

        return sb.toString();
    }
}

// Classe principal que funciona como interface de entrada/saída e execução do caso de uso
public class AnaliseTransacoes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leitura do número de transações
        int numeroTransacoes = scanner.nextInt();

        // Leitura das transações
        List<Double> transacoes = new ArrayList<>();
        for (int i = 0; i < numeroTransacoes; i++) {
            transacoes.add(scanner.nextDouble());
        }

        // Criação da entidade Transacao
        Transacao transacao = new Transacao(transacoes);

        // Criação e execução do caso de uso
        AnaliseTransacoesUseCase useCase = new AnaliseTransacoesUseCase();
        String resultado = useCase.analisar(transacao);

        // Saída do resultado
        System.out.println(resultado);

        scanner.close();
    }
}
