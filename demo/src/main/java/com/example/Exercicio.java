package com.example;
import java.util.Scanner;

public class Exercicio {
    static void imprimirSaudacao() {
        System.out.println("Bem-vindo ao sistema!");
    }

    public static void main(String[] args) {
        imprimirSaudacao();
    }
}

class Exercicio2 {
    static int somar(int num1, int num2) {
        return num1 + num2;
    }

    public static void main(String[] args) {
        int resultado = somar(20, 10);
        System.out.println("Soma: " + resultado);
    }
}

class Exercicio3 {
    static int dobrar(int num) {
        return num * 2;
    }

    public static void main(String[] args) {
        int resultado = dobrar(67);
        System.out.println("Dobro: " + resultado);
    }
}

class Exercicio4 {
    static double converteCelsiusParaFahrenheit(double celsius) {
        return celsius * 1.8 + 32;
    }

    public static void main(String[] args) {
        double celsius = 25;
        double fahrenheit = converteCelsiusParaFahrenheit(celsius);
        System.out.printf("%.1f °C = %.1f °F%n", celsius, fahrenheit);
    }
}

class Exercicio5 {
    static double calcularMedia(double nota1, double nota2, double nota3) {
        return (nota1 + nota2 + nota3) / 3;
    }

    public static void main(String[] args) {
        double media = calcularMedia(2.5, 8.0, 6.7);
        System.out.printf("Média: %.2f%n", media);
    }
}

class Exercicio6 {
    static boolean verificarPar(int num) {
        return num % 2 == 0;
    }

    public static void main(String[] args) {
        int num = 12;
        System.out.println(num + (verificarPar(num) ? " é par." : " é ímpar."));
    }
}

class Exercicio7 {
    static int maiorNum(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }

    public static void main(String[] args) {
        int maior = maiorNum(15, 42, 27);
        System.out.println("Maior número: " + maior);
    }
}

class Exercicio8 {
    static long calcularFatorial(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("O número deve ser positivo ou zero.");
        }

        long fatorial = 1;
        for (int i = 2; i <= num; i++) {
            fatorial *= i;
        }
        return fatorial;
    }

    public static void main(String[] args) {
        int num = 5;
        System.out.println(num + "! = " + calcularFatorial(num));
    }
}

class Exercicio9 {
    static int calcularPotencia(int base, int expoente) {
        if (expoente < 0) {
            throw new IllegalArgumentException("Neste exercício, o expoente deve ser não negativo.");
        }

        int resultado = 1;
        for (int i = 0; i < expoente; i++) {
            resultado *= base;
        }
        return resultado;
    }

    public static void main(String[] args) {
        int base = 2;
        int expoente = 5;
        System.out.println(base + "^" + expoente + " = " + calcularPotencia(base, expoente));
    }
}

class Exercicio10 {
    static double calcularIMC(double peso, double altura) {
        if (peso <= 0 || altura <= 0) {
            throw new IllegalArgumentException("Peso e altura devem ser maiores que zero.");
        }
        return peso / (altura * altura);
    }

    public static void main(String[] args) {
        double peso = 70;
        double altura = 1.75;
        System.out.printf("IMC: %.2f%n", calcularIMC(peso, altura));
    }
}

class DesafioCadastro {
    private static final int MAX_USUARIOS = 100;
    private static final int COLUNA_ID = 0;
    private static final int COLUNA_NOME = 1;
    private static final int COLUNA_EMAIL = 2;

    private static final String[][] usuarios = new String[MAX_USUARIOS][3];
    private static int quantidadeUsuarios = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> consultarUsuarios();
                case 3 -> atualizarUsuario();
                case 4 -> deletarUsuario();
                case 0 -> System.out.println("Programa encerrado.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n _______ Sistema De Cadastro _______");
        System.out.println("1 Cadastrar  o usuário");
        System.out.println("2 Consultar os usuários");
        System.out.println("3 Atualizar status do usuário");
        System.out.println("4 Deletar usuário");
        System.out.println("0 Sair");
    }

    private static void cadastrarUsuario() {
        if (quantidadeUsuarios == MAX_USUARIOS) {
            System.out.println("Limite de usuários atingido.");
            return;
        }

        String id = lerTexto("ID Usuário: ");
        if (buscarIndicePorId(id) != -1) {
            System.out.println("Já existe um usuário com esse mesmo ID, por favor, informe outro ID.");
            return;
        }

        String nome = lerTexto("Nome: ");
        String email = lerTexto("Email: ");

        usuarios[quantidadeUsuarios][COLUNA_ID] = id;
        usuarios[quantidadeUsuarios][COLUNA_NOME] = nome;
        usuarios[quantidadeUsuarios][COLUNA_EMAIL] = email;
        quantidadeUsuarios++;

        System.out.println("Usuário cadastrado com sucesso.");
    }

    private static void consultarUsuarios() {
        if (quantidadeUsuarios == 0) {
            System.out.println("Nenhum usuário cadastrado.");
            return;
        }

        System.out.printf("%-10s %-25s %-35s%n", "ID", "NOME", "EMAIL");
        System.out.println("__________________________________________________________________________");

        for (int i = 0; i < quantidadeUsuarios; i++) {
            System.out.printf("%-10s %-25s %-35s%n",
                    usuarios[i][COLUNA_ID],
                    usuarios[i][COLUNA_NOME],
                    usuarios[i][COLUNA_EMAIL]);
        }
    }

    private static void atualizarUsuario() {
        String id = lerTexto("Informe o ID do usuário que deseja atualizar: ");
        int indice = buscarIndicePorId(id);

        if (indice == -1) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.println("Deixe o campo vazio para manter o valor atual.");
        String novoNome = lerTexto("Novo nome (atual: " + usuarios[indice][COLUNA_NOME] + "): ");
        String novoEmail = lerTexto("Novo email (atual: " + usuarios[indice][COLUNA_EMAIL] + "): ");

        if (!novoNome.isBlank()) {
            usuarios[indice][COLUNA_NOME] = novoNome;
        }
        if (!novoEmail.isBlank()) {
            usuarios[indice][COLUNA_EMAIL] = novoEmail;
        }

        System.out.println("Usuário atualizado com sucesso!");
    }

    private static void deletarUsuario() {
        String id = lerTexto("Informe o ID do usuário que deseja deletar: ");
        int indice = buscarIndicePorId(id);

        if (indice == -1) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        for (int i = indice; i < quantidadeUsuarios - 1; i++) {
            usuarios[i] = usuarios[i + 1];
        }
        usuarios[quantidadeUsuarios - 1] = new String[3];
        quantidadeUsuarios--;

        System.out.println("Usuário deletado com sucesso.");
    }

    private static int buscarIndicePorId(String id) {
        for (int i = 0; i < quantidadeUsuarios; i++) {
            if (usuarios[i][COLUNA_ID].equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(lerTexto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }
}
