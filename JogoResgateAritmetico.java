import java.util.Scanner;
import java.util.Random;
 
public class JogoResgateAritmetico {
 
    // Vetor para armazenar o histórico de pontuações das partidas
    static int[] historicoPontuacao = new int[1];
    static int totalPartidas = 0;
 
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        Random rand = new Random();
        boolean executando = true;
 
        // LAÇO WHILE E SWITCH CASE PARA O MENU PRINCIPAL
        while (executando) {
            System.out.println("\n\n==================================================");
            System.out.println("      MENU PRINCIPAL - O RESGATE ARITMÉTICO       ");
            System.out.println("==================================================");
            System.out.println("1 - Iniciar Novo Jogo");
            System.out.println("2 - Ver Histórico de Pontuações (Vetor)");
            System.out.println("3 - Sair do Jogo");
            System.out.println("==================================================");
            System.out.print("Escolha uma opção: ");
            
            int opcao = entrada.nextInt();
 
            switch (opcao) {
                case 1:
                    jogarPartida(entrada, rand); // Chama a função principal do jogo
                    break;
                case 2:
                    exibirHistorico(); // Exibe o vetor de pontuações
                    break;
                case 3:
                    System.out.println("\nObrigado por jogar! Até a próxima.");
                    executando = false;
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }
        }
        entrada.close();
    }
 
 
    // MÉTODO DA PARTIDA COMPLETA
    public static void jogarPartida(Scanner entrada, Random rand) {
        int vidas = 3;
        int pontuacao = 0;
        int tempoLimite = 10;
 
        // --------------------------------------------------
        // ETAPA 1: ESCOLHA DE PERSONAGEM
        // --------------------------------------------------
        System.out.println("\n\n==================================================");
        System.out.println("             ESCOLHA SEU PERSONAGEM               ");
        System.out.println("==================================================");
        System.out.println("1. Shrek (O Ogro da Adição) - Bônus: +2 Vidas iniciais");
        System.out.println("2. Burro (O Mestre da Multiplicação) - Bônus: Dobra pontos na multiplicação");
        System.out.println("3. Gato de Botas (O Ás da Divisão) - Bônus: Dicas nas divisões");
        System.out.println("--------------------------------------------------");
        System.out.print("Digite o número do seu personagem (1, 2 ou 3): ");
        int personagem = entrada.nextInt();
 
        if (personagem == 1) {
            vidas += 2;
            System.out.println("\nVocê escolheu o Shrek! Vidas totais: " + vidas);
        } else if (personagem == 2) {
            System.out.println("\nVocê escolheu o Burro! Pontos de multiplicação dobrados.");
        } else if (personagem == 3) {
            System.out.println("\nVocê escolheu o Gato de Botas! Dicas ativadas.");
        } else {
            System.out.println("\nOpção inválida. Jogando com estatísticas padrão.");
        }
 
 
        // --------------------------------------------------
        // ETAPA 2: DESAFIO 1 (PONTE DO PÂNTANO)
        // --------------------------------------------------
        System.out.println("\n\n==================================================");
        System.out.println(" DESAFIO 1: A PONTE DO PÂNTANO (Multiplicação/Adição)");
        System.out.println("==================================================");
        System.out.println("⏰ Limite de tempo: " + tempoLimite + " segundos!");
        
        int baldes = rand.nextInt(5) + 3;  
        int sapos = rand.nextInt(6) + 2;   
        int soltos = rand.nextInt(10) + 1; 
        int resp1 = (baldes * sapos) + soltos;
 
        System.out.println("\nEnigma do Troll:");
        System.out.println("\"Quantos sapos há em " + baldes + " baldes com " + sapos + " sapos cada, mais " + soltos + " soltos?\"");
        System.out.println("Expressão: " + baldes + " * " + sapos + " + " + soltos);
        System.out.println("\nA) " + (resp1 - 2));
        System.out.println("B) " + resp1);
        System.out.println("C) " + (resp1 + 4));
        System.out.println("--------------------------------------------------");
 
        if (executarDesafio(entrada, 'B', tempoLimite)) {
            int pts = (personagem == 2) ? 20 : 10; // Bônus do Burro
            pontuacao += pts;
            System.out.println("\n✨ Resposta Correta! A ponte baixa. (+ " + pts + " pontos)");
        } else {
            vidas--;
            System.out.println("\n❌ Errou ou tempo esgotado! Vidas restantes: " + vidas);
        }
 
        if (vidas <= 0) {
            finalizarJogo(pontuacao, false);
            return;
        }
 
 
        // --------------------------------------------------
        // ETAPA 3: DESAFIO 2 (FLORESTA SOMBRIA)
        // --------------------------------------------------
        System.out.println("\n\n==================================================");
        System.out.println(" DESAFIO 2: A FLORESTA SOMBRIA (Divisão/Subtração)");
        System.out.println("==================================================");
        System.out.println("⏰ Limite de tempo: " + tempoLimite + " segundos!");
 
        int grupos = rand.nextInt(4) + 2;
        int totalFadas = grupos * (rand.nextInt(5) + 4);
        int fugiram = rand.nextInt(3) + 1;
        int resp2 = (totalFadas / grupos) - fugiram;
 
        if (personagem == 3) { // Dica do Gato de Botas
            System.out.println("\n💡 DICA DO GATO: Primeiro divida " + totalFadas + " por " + grupos + ", depois subtraia " + fugiram + "!");
        }
 
        System.out.println("\nEnigma da Placa:");
        System.out.println("\"" + totalFadas + " fadas divididas em " + grupos + " grupos, menos " + fugiram + " que fugiram.\"");
        System.out.println("Expressão: (" + totalFadas + " / " + grupos + ") - " + fugiram);
        System.out.println("\nA) " + resp2);
        System.out.println("B) " + (resp2 + 3));
        System.out.println("C) " + (resp2 - 1));
        System.out.println("--------------------------------------------------");
 
        if (executarDesafio(entrada, 'A', tempoLimite)) {
            pontuacao += 10;
            System.out.println("\n✨ Resposta Correta! O caminho se ilumina. (+10 pontos)");
        } else {
            vidas--;
            System.out.println("\n❌ Errou ou tempo esgotado! Vidas restantes: " + vidas);
        }
 
        if (vidas <= 0) {
            finalizarJogo(pontuacao, false);
            return;
        }
 
 
        // --------------------------------------------------
        // ETAPA 4: NAVEGAÇÃO NA TORRE (MATRIZ 3x3)
        // --------------------------------------------------
        System.out.println("\n\n==================================================");
        System.out.println(" FASE 3: NAVEGAÇÃO NA TORRE DO CASTELO (MATRIZ)");
        System.out.println("==================================================");
        System.out.println("Escolha as coordenadas para encontrar a sala da Princesa:");
 
        int[][] mapaTorre = {
            {1, 0, 1},
            {0, 2, 0},
            {1, 0, 3}
        };
 
        System.out.print("Digite a linha da sala (0 a 2): ");
        int linha = entrada.nextInt();
        System.out.print("Digite a coluna da sala (0 a 2): ");
        int coluna = entrada.nextInt();
 
        if (mapaTorre[linha][coluna] == 0) {
            vidas--;
            System.out.println("\n⚠️ Sala com armadilha! Perdeu 1 vida. Vidas restantes: " + vidas);
        } else {
            System.out.println("\n🚪 Sala segura! Você chegou ao topo da torre!");
        }
 
        if (vidas <= 0) {
            finalizarJogo(pontuacao, false);
            return;
        }
 
 
        // --------------------------------------------------
        // ETAPA 5: CLÍMAX FINAL
        // --------------------------------------------------
        System.out.println("\n\n==================================================");
        System.out.println(" DESAFIO FINAL: A TORRE DO LORDE X");
        System.out.println("==================================================");
        System.out.println("⏰ Limite de tempo: " + tempoLimite + " segundos!");
        System.out.println("\nEnigma do Lorde X:");
        System.out.println("Expressão: (15 - 5) * 2 + (10 / 2)");
        System.out.println("\nA) 20");
        System.out.println("B) 25");
        System.out.println("C) 30");
        System.out.println("--------------------------------------------------");
 
        if (executarDesafio(entrada, 'B', tempoLimite)) {
            pontuacao += 20;
            System.out.println("\n🎉 PARABÉNS! A jaula se abre e a Princesa é salva!");
            finalizarJogo(pontuacao, true);
        } else {
            vidas--;
            finalizarJogo(pontuacao, false);
        }
    }
 
 
    // MÉTODO AUXILIAR PARA CRONOMETRAR RESPOSTAS
    public static boolean executarDesafio(Scanner entrada, char respostaCorreta, int tempoLimiteSec) {
        long inicio = System.currentTimeMillis();
        System.out.print("Sua opção (A, B ou C): ");
        char resp = entrada.next().toUpperCase().charAt(0);
        long tempoGasto = (System.currentTimeMillis() - inicio) / 1000;
 
        if (tempoGasto > tempoLimiteSec) {
            System.out.println("\n⏰ Tempo esgotado! Você levou " + tempoGasto + "s.");
            return false;
        }
        return resp == respostaCorreta;
    }
 
 
    // MÉTODO PARA SALVAR E MOSTRAR O FIM DO JOGO
    public static void finalizarJogo(int pontuacao, boolean venceu) {
        System.out.println("\n\n==================================================");
        System.out.println("                RESULTADO DA PARTIDA              ");
        System.out.println("==================================================");
        if (!venceu) {
            System.out.println("☠️ Fim de Jogo! Suas vidas acabaram.");
        }
        System.out.println("Pontuação obtida: " + pontuacao + " pontos.");
 
        if (totalPartidas < historicoPontuacao.length) {
            historicoPontuacao[totalPartidas] = pontuacao; // Salva no vetor
            totalPartidas++;
        }
    }
 
 
    // MÉTODO PARA EXIBIR O VETOR DE HISTÓRICO
    public static void exibirHistorico() {
        System.out.println("\n\n==================================================");
        System.out.println("        HISTÓRICO DE PONTUAÇÕES (VETOR)           ");
        System.out.println("==================================================");
        if (totalPartidas == 0) {
            System.out.println("Nenhuma partida registrada até o momento.");
        } else {
            for (int i = 0; i < totalPartidas; i++) {
                System.out.println("Partida " + (i + 1) + ": " + historicoPontuacao[i] + " pontos");
            }
        }
        System.out.println("==================================================");
    }
}
 