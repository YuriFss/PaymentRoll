import jdk.swing.interop.SwingInterOpUtils;

import java.util.Scanner;
public class Main
{
    public static void adicionarFuncionar(String[][] funcionarios, int number)
    {
        int x;
        Scanner input = new Scanner(System.in);

        System.out.print("Nome do empregrado: ");
        funcionarios[number][0] = input.nextLine();

        System.out.print("Endereço do empregado: ");
        funcionarios[number][1] = input.nextLine();

        System.out.print("Digite o tipo de funcionarios");
        System.out.print(" 1 - hourly,");
        System.out.print(" 2 - salaried,");
        System.out.print(" 3 - commissioned: ");

        x = input.nextInt();

        switch(x)
        {
            case 1:
                funcionarios[number][2] = "hourly";
                break;
            case 2:
                funcionarios[number][2] = "salaried";
                break;
            case 3:
                funcionarios[number][2] = "commissioned";
                break;
        }
    }

    public static void removerFuncinar(String[][] funcionarios, int[] number, int Max)
    {
        int i;
        String nome;

        System.out.println("Lista de funcionários disponiveis: ");
        printarFunci(funcionarios, number, Max);
        System.out.println("Digite o nome do funcionário que deseja remover: ");

        Scanner input = new Scanner(System.in);
        nome = input.nextLine();

        for(i = 0; i < Max; i++)
        {
            if(nome.equals(funcionarios[i][0]))
            {
                number[i] = 0;
            }
        }
        printarFunci(funcionarios, number, Max);
    }

    public static void alterarFunci(String[][] funcionarios, int[] number, int Max, double[][] valoresfunci)
    {
        int numero, x;

        System.out.println("\nDigite o numero do funcionario que deseja alterar os detalhes: ");

        printarFunci(funcionarios, number, Max);

        Scanner input = new Scanner(System.in);
        numero = input.nextInt();

        if(number[numero] == 1)
        {
            System.out.println("Digite 1 para alterar nome");
            System.out.println("Digite 2 para alterar endereço");
            System.out.println("Digite 3 para alterar tipo do funcionario");
            System.out.println("Digite 4 para alterar método de pagamento");
            System.out.println("Digite 5 para alterar status no sindicato");
            System.out.println("Digite 6 para alterar taxa sindical");

            x = input.nextInt();
            input.nextLine();
            switch (x)
            {
                case 1:
                    System.out.print("Digite novo nome: ");
                    funcionarios[numero][0] = input.nextLine();
                    break;
                case 2:
                    System.out.print("Digite novo endereço: ");
                    funcionarios[numero][1] = input.nextLine();
                    break;
                case 3:
                    System.out.print("Digite novo tipo: ");
                    funcionarios[numero][2] = input.nextLine();
                    break;
                case 4:
                    System.out.print("Digite novo método de pagamento: 1 - Cheque pelos correios, 2 - Cheque em mãos, 3 - Depósito");
                    valoresfunci[numero][3] = input.nextInt();
                    break;
                case 5:
                    System.out.print("Digite novo status sindical(1 - pertence, 0 - não pertence):");
                    valoresfunci[numero][0] = input.nextInt();
                    break;
                case 6:
                    System.out.print("Digite nova taxa sindical: ");
                    valoresfunci[numero][2] = input.nextInt();
                    break;
            }
        }
        else
            System.out.println("Funcionario não existe \n");

    }

    public static void lançarTaxa(String[][] funcionarios, int[] number, int Max, double[][] valoresfunci)
    {
        int x;

        printarFunci(funcionarios, number, Max);
        System.out.println("Digite o número do funcionário ao qual a taxa de serviço será associada: ");

        Scanner input = new Scanner(System.in);
        x = input.nextInt();

        if(valoresfunci[x][0] == 1)
        {
            System.out.println("Digite o valor da taxa sindical: ");
            valoresfunci[x][2] = input.nextDouble();
        }
        else
        {
            System.out.println("Funcionario não pertence ao sindicato\n");
        }
    }

    public static void resultadoVenda(String[][] funcionarios, int[] number, int Max, double[][] valoresfunci)
    {
        int x;
        String tipo;
        Scanner input = new Scanner(System.in);

        System.out.println("Qual o número do funcionário que realizou a venda: ");
        printarFunci(funcionarios, number, Max);

        x = input.nextInt();
        tipo = "commissioned";

        if(tipo.equals(funcionarios[x][2]))
        {
            System.out.println("Digite o valor da venda: ");
            valoresfunci[x][4] = input.nextDouble();
        }
        else
            System.out.println("Funcionário não é do tipo comissionado para realiza esta operação\n");
    }

    public static void lançarCartão(String[][] funcionarios, int[] number, int Max, double[][] valoresfunci)
    {
        int horas, x;
        String tipo;
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o número do funcionaria que lançou o cartão de ponto");
        printarFunci(funcionarios, number, Max);

        x = input.nextInt();
        tipo = "hourly";

        if(tipo.equals(funcionarios[x][2]))
        {
            System.out.println("Digite a quantidade de horas trabalhadas: ");
            horas = input.nextInt();
            valoresfunci[x][6] += horas;
        }
        else
            System.out.println("Esse funcionário não é do tipo horista");
    }

    public static void printarFunci(String[][] funcionarios, int[] number, int Max)
    {
        int i;

        for (i = 0; i < Max; i++)
        {
            if(number[i] == 1)
            {
                System.out.print(i);
                System.out.print(" : ");
                System.out.println(funcionarios[i][0]);
            }
        }
    }

    public static void main(String[] args)
    {
        int casee, contarfunci = 0, i, j;
        int Maxfunci = 100, Atributos = 10;
        boolean truee = true;
        int numerofunci[] = new int[Maxfunci];
        double valoresfunci[][] = new double[Maxfunci][7]; // 0 - Se pretence ou não ao sindicato, 1 - Dia de Pagamento, 2 - taxa sindical, 3 - Método de pagamento, 4 - Resultado de vendas, 5 - Salário, 6 - Horas
        String funcionarios[][] = new String[Maxfunci][Atributos]; //Em Atributos 0 - Nome, 1 - Endereço, 2 - Tipo

        for(i = 0; i < Maxfunci; i++)
        {
            for (j = 0; j < 7; j++)
            {
                valoresfunci[i][j] = 0;
            }
        }

        for(i = 0; i < Maxfunci; i++)
        {
            for (j = 0; j < 7; j++)
            {
                System.out.println(valoresfunci[i][j]);
            }
            System.out.println("\n");
        }

        for(i = 0; i < Maxfunci; i++)
        {
            numerofunci[i] = 0;
        }

        System.out.println("\n -----Folha de Pagamento----- \n");

        while(true)
        {
            System.out.println("Opções: ");
            System.out.println("1 -  Adicionar novo empregado");
            System.out.println("2 -  Remover um empregado");
            System.out.println("3 -  Lançar cartão de ponto");
            System.out.println("4 -  Lançar resultado de venda");
            System.out.println("5 -  Lançar taxa de serviço");
            System.out.println("6 -  Alterar detalhes de um empregado");
            System.out.println("7 -  Rodar folha de pagamento do dia");
            System.out.println("8 -  Undo/Redo");
            System.out.println("9 -  Agenda de pagamento");
            System.out.println("10 - Criação de nova agenda de pagamento \n");
            System.out.print("Digite o número de uma das opções: ");

            Scanner input = new Scanner(System.in);
            casee = input.nextInt();
            //System.out.println("Sindicato status: " + valoresfunci[0][0]);
            //System.out.println("Sindicato taxa: " + valoresfunci[0][2]);
            switch(casee)
            {
                case 1:
                    adicionarFuncionar(funcionarios, contarfunci);
                    numerofunci[contarfunci] = 1;
                    contarfunci ++;
                    break;

                case 2:
                    removerFuncinar(funcionarios, numerofunci, Maxfunci);
                    break;

                case 3:
                    lançarCartão(funcionarios, numerofunci, Maxfunci, valoresfunci);
                    break;

                case 4:
                    resultadoVenda(funcionarios, numerofunci, Maxfunci, valoresfunci);
                    break;

                case 5:
                    lançarTaxa(funcionarios, numerofunci, Maxfunci, valoresfunci);
                    break;

                case 6:
                    alterarFunci(funcionarios, numerofunci, Maxfunci, valoresfunci);
                    break;

                case 7:

                    break;
                case 8:

                    break;

                case 9:

                    break;
                case 10:

                    break;
            }
        }
    }
}
