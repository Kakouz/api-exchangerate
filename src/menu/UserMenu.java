package menu;

import enums.Currencies;
import service.ValueService;

import java.util.Scanner;

public class UserMenu {
    private final ValueService service;
    private final Scanner sc;

    public UserMenu() {
        this.service = new ValueService();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        this.displayMessage("""
                #############################################
                #      BEM VINDO AO CONVERSOR DE MOEDA      #
                #############################################
                """);

        while (true) {
            int option = -1;
            this.displayMessage("Escolha a opção que deseja para realizar a conversão de valores!");
            this.displayMessage("1 -> Comparar valores entre moedas");
            this.displayMessage("0 -> Sair");

            try {
                option = sc.nextInt();
            } catch (Exception e) {
                this.displayMessage("ERRO: Por favor digite a opção desejada corretamente");
                continue;
            }

            switch (option) {
                case 1 -> this.compareValues();
                case 0 -> System.exit(0);
                default -> this.displayMessage("ATENÇÃO: A opção escolhida está incorreta, tente novamente");
            }
        }


    }

    private void compareValues() {
        sc.nextLine();
        String firstValue, secondValue, amount;
        this.displayMessage("Escolha a moeda que deseja usar como base, temos como calcular de todas que existem!");
        this.displayMessage("As principais são: EUR, USD, BRL, CHF, ARS. Mas fique a vontade para escolher outras");
        this.displayMessage("O FORMATO ESPERADO É CÓDIGO DE 3 LETRAS DA ISO 4217");

        firstValue = sc.nextLine();

        while (!existsByIsoCurrencyFromEnum(firstValue)) {
            this.displayMessage("Esse código de moeda não foi encontrado, favor insira corretamente");
            firstValue = sc.nextLine();
        }

        this.displayMessage("Agora escolha a moeda que deseja usar como alvo");

        secondValue = sc.nextLine();

        while (!existsByIsoCurrencyFromEnum(secondValue)) {
            this.displayMessage("Esse código de moeda não foi encontrado, favor insira corretamente");
            secondValue = sc.nextLine();
        }

        this.displayMessage("Agora escolha a quantidade monetária para calcular");
        amount = sc.nextLine();

        while (!isValidValue(amount)) {
            this.displayMessage("O valor digitado não é valido, favor insira corretamente");
            amount = sc.nextLine();
        }

        String valorCalculado = service.findValueInPairs(firstValue, secondValue, amount);

        this.displayMessage(String.format("O valor encontrado foi %s, a base foi '%s', o alvo foi '%s', o valor inicial foi %s", valorCalculado, firstValue, secondValue, amount));
    }

    private void displayMessage(String msg) {
        System.out.println(msg);
    }

    private boolean existsByIsoCurrencyFromEnum(String currency) {
        Currencies item = Currencies.findByISOValue(currency.toUpperCase());

        return item != null;
    }

    private boolean isValidValue(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}

