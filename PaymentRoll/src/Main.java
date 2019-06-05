import java.util.Calendar;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Main
{
    public static void adicionarFuncionar(String[][] funcionarios, int number, double[][] valoresfunci)
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

        if("hourly".equals(funcionarios[number][2]))
        {
            System.out.print("Funcionário horista, recebe 4.54R$/hora. Baseado no salário mínimo \n");
        }
        else
        {
            System.out.print("Digite o salário: ");
            valoresfunci[number][5] = input.nextDouble();
        }
        System.out.print("\n");
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
            System.out.println("1 - Para alterar nome");
            System.out.println("2 - Para alterar endereço");
            System.out.println("3 - Para alterar tipo do funcionario");
            System.out.println("4 - Para alterar método de pagamento");
            System.out.println("5 - Para alterar status no sindicato");
            System.out.println("6 - Para alterar taxa sindical");

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
                    if(valoresfunci[numero][0] == 0)
                    {
                        System.out.println("Operação não possível. Funcionário não pertence ao sindicato.");
                    }
                    else
                    {
                        System.out.print("Digite nova taxa sindical: ");
                        valoresfunci[numero][2] = input.nextInt();
                    }
                    break;
            }
            System.out.print("\n");
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
            System.out.println("Digite o valor da taxa de serviço: ");
            valoresfunci[x][7] = input.nextDouble();
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
        double valortotal;
        String tipo;
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o número do funcionaria que lançou o cartão de ponto");
        printarFunci(funcionarios, number, Max);

        x = input.nextInt();
        tipo = "hourly";

        //Tendo como base o salário mínimo. O valor hora do salário é calculado a partir deste site: http://www.guiatrabalhista.com.br/guia/salario_minimo.htm
        if(tipo.equals(funcionarios[x][2]))
        {
            System.out.println("Digite a quantidade de horas trabalhadas: ");
            horas = input.nextInt();
            if(horas > 8)
            {
                valortotal = 8 * 4.54;
                horas = horas - 8;
                valortotal = valortotal + (horas * 4.54 * 1.5);
                valoresfunci[x][6] = valoresfunci[x][6] + valortotal;
            }
            else
            {
                valortotal = 8 * 4.54;
                valoresfunci[x][6] = valoresfunci[x][6] + valortotal;
            }
        }
        else
            System.out.println("Esse funcionário não é do tipo horista");
    }

    public static void funciInfo(String[][] funcionarios, int[] number, int Max, double[][] valoresfunci)
    {
        int x;

        System.out.println("Digite o número do funcionário que deseja olhar informação: ");
        printarFunci(funcionarios, number, Max);

        Scanner input = new Scanner(System.in);
        x = input.nextInt();

        System.out.println("------------ Detalhes do Funcionário ------------");

        System.out.print("Nome: ");
        System.out.println(funcionarios[x][0]);
        System.out.print("Endereço: ");
        System.out.println(funcionarios[x][1]);
        System.out.print("Tipo: ");
        System.out.println(funcionarios[x][2]);
        System.out.print("Status sindicato: ");
        if(valoresfunci[x][0] == 0)
        {
            System.out.print("Não pertence");
        }
        else
        {
            System.out.print("Pertence");
        }
        System.out.print("  Taxa sindical: ");
        System.out.println(valoresfunci[x][2]);
        System.out.print("Método de pagamento: ");
        if(valoresfunci[x][3] == 1)
        {
            System.out.println("Cheque pelos correios");
        }
        else if(valoresfunci[x][3] == 2)
        {
            System.out.println("Cheque em mãos");
        }
        else if(valoresfunci[x][3] == 3)
        {
            System.out.println("Depósito bancário");
        }
        else
            System.out.println("Método de pagamento não especificado nos detalhes do funcionário");
        if("hourly".equals(funcionarios[x][2]))
        {
            System.out.println("Funcionário horista(não recebe salário mensal).");
        }
        else
        {
            System.out.print("Salário: ");
            System.out.println(valoresfunci[x][5]);
        }

        System.out.println("------------  //  // ------------");

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

    public static void rodarFolha(String[][] funcionarios, int[] number, int Max, double[][] valoresfunci, Date data)
    {
        int i;
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);

        //System.out.println(simpleDateformat.format(data));

        String dia = simpleDateformat.format(data);
        //System.out.println(dia);

        c.setTime(data);

        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        //System.out.println((dayOfMonth));

        for(i = 0; i < Max; i++)
        {
            if(number[i] == 1 && "hourly".equals(funcionarios[i][2]) && "sexta-feira".equals(dia))
            {
                System.out.println("------ Funcionário horista pago ------");
                System.out.println("Nome: " + funcionarios[i][0]);
                System.out.println("Código: " + i);
                System.out.println("Valor pago: " + valoresfunci[i][6]);
                valoresfunci[i][6] = 0;
            }
            else if(number[i] == 1 && "hourly".equals(funcionarios[i][2]) && dayOfMonth == 30)
            {
                if(valoresfunci[i][0] == 1)
                {
                    System.out.println("------ Funcionário horista pago ------");
                    System.out.println("Nome: " + funcionarios[i][0]);
                    System.out.println("Código: " + i);
                    System.out.println("Taxa sindical deduzida: " + valoresfunci[i][2]);
                    System.out.println("Taxa de serviço deduzida: " + valoresfunci[i][7]);
                    System.out.println("Valor pago: " + ((valoresfunci[i][6]) - (valoresfunci[i][2]+valoresfunci[i][7])));
                    valoresfunci[i][7] = 0;
                    valoresfunci[i][6] = 0;
                }
                else if(valoresfunci[i][0] == 0)
                {
                    System.out.println("------ Funcionário horista pago ------");
                    System.out.println("Nome: " + funcionarios[i][0]);
                    System.out.println("Código: " + i);
                    System.out.println("Valor pago: " + valoresfunci[i][6]);
                    valoresfunci[i][6] = 0;
                }
            }
            else if(number[i] == 1 && "salaried".equals(funcionarios[i][2]) && dayOfMonth == 30)
            {
                if(valoresfunci[i][0] == 1)
                {
                    System.out.println("------ Funcionário assalariado pago ------");
                    System.out.println("Nome: " + funcionarios[i][0]);
                    System.out.println("Código: " + i);
                    System.out.println("Taxa sindical deduzida: " + valoresfunci[i][2]);
                    System.out.println("Taxa de serviço deduzida: " + valoresfunci[i][7]);
                    System.out.println("Valor pago: " + ((valoresfunci[i][5]) - (valoresfunci[i][2]+valoresfunci[i][7])));
                    valoresfunci[i][7] = 0;
                }
                else if(valoresfunci[i][0] == 0)
                {
                    System.out.println("------ Funcionário assalariado pago ------");
                    System.out.println("Nome: " + funcionarios[i][0]);
                    System.out.println("Código: " + i);
                    System.out.println("Valor pago: " + valoresfunci[i][5]);
                }
            }
            else if(number[i] == 1 && "commissioned".equals(funcionarios[i][2]) && "sexta-feira".equals(dia))
            {
                if(valoresfunci[i][0] == 1)
                {
                    System.out.println("------ Funcionário comissionado pago ------");
                    System.out.println("Nome: " + funcionarios[i][0]);
                    System.out.println("Código: " + i);
                    System.out.println("Taxa sindical deduzida: " + valoresfunci[i][2]);
                    System.out.println("Taxa de serviço deduzida: " + valoresfunci[i][7]);
                    System.out.println("Valor pago: " + ((valoresfunci[i][5]+valoresfunci[i][4]) - (valoresfunci[i][2]+valoresfunci[i][7])));
                    valoresfunci[i][7] = 0;
                    valoresfunci[i][4] = 0;
                }
                else if(valoresfunci[i][0] == 0)
                {
                    System.out.println("------ Funcionário comissionado pago ------");
                    System.out.println("Nome: " + funcionarios[i][0]);
                    System.out.println("Código: " + i);
                    System.out.println("Valor pago: " + (valoresfunci[i][5]+valoresfunci[i][4]));
                    valoresfunci[i][4] = 0;
                }
            }
        }
    }

    public static void main(String[] args)
    {
        int casee, contarfunci = 0, i, j;
        int Maxfunci = 100, Atributos = 10;
        boolean truee = true;
        int numerofunci[] = new int[Maxfunci];
        double valoresfunci[][] = new double[Maxfunci][8];         // 0 - Se pretence ou não ao sindicato, 1 - Dia de Pagamento, 2 - taxa sindical, 3 - Método de pagamento
        String funcionarios[][] = new String[Maxfunci][Atributos]; // 4 - Resultado de vendas, 5 - Salário, 6 - Valor Horas, 7 - Taxa de Serviço
                                                                   //Em Atributos 0 - Nome, 1 - Endereço, 2 - Tipo

        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);

        for(i = 0; i < Maxfunci; i++)
        {
            for (j = 0; j < 7; j++)
            {
                valoresfunci[i][j] = 0;
            }
        }

        /*for(i = 0; i < Maxfunci; i++)
        {
            for (j = 0; j < 7; j++)
            {
                System.out.println(valoresfunci[i][j]);
            }
            System.out.println("\n");
        }*/

        for(i = 0; i < Maxfunci; i++)
        {
            numerofunci[i] = 0;
        }

        System.out.println("\n");
        System.out.println("\n -----Folha de Pagamento----- \n");

        while(true)
        {
            System.out.println("A data de hoje é: " + f.format(data));

            System.out.println("Opções: ");
            System.out.println("1 -  Adicionar novo empregado");
            System.out.println("2 -  Remover um empregado");
            System.out.println("3 -  Lançar cartão de ponto");
            System.out.println("4 -  Lançar resultado de venda");
            System.out.println("5 -  Lançar taxa de serviço");
            System.out.println("6 -  Alterar detalhes de um empregado");
            System.out.println("7 -  Mostrar informações dos funcionários");
            System.out.println("8 -  Rodar folha de pagamento do dia");
            System.out.println("9 -  Undo/Redo                            - Not Implemented");
            System.out.println("10 - Agenda de pagamento                  - Not Implemented");
            System.out.println("11 - Criação de nova agenda de pagamento  - Not Implemented\n");
            System.out.print("Digite o número de uma das opções: ");

            Scanner input = new Scanner(System.in);
            casee = input.nextInt();
            //System.out.println("Sindicato status: " + valoresfunci[0][0]);
            //System.out.println("Sindicato taxa: " + valoresfunci[0][2]);
            switch(casee)
            {
                case 1:
                    adicionarFuncionar(funcionarios, contarfunci, valoresfunci);
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
                    funciInfo(funcionarios, numerofunci, Maxfunci, valoresfunci);
                    break;
                case 8:
                    rodarFolha(funcionarios, numerofunci, Maxfunci, valoresfunci, data);
                    c.getTime();
                    c.add(Calendar.DATE, 1);
                    data = c.getTime();
                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 11:

                    break;
            }
        }
    }
}
