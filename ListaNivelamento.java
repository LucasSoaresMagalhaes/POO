//Lucas Soares Magalhães - Análise e Desenvolvimento de Sistemas

import java.util.*;
import java.io.*;

class ListaNivelamento {
    public static void main(String[] args) {
        Scanner lerInteiro = new Scanner(System.in);
        Scanner lerString = new Scanner(System.in);

        // #region EX1 - MAIN
        System.out.println("INICIO DO EX1");
        int[] vetor = lerVetor(lerInteiro);
        System.out.println("VETOR ORIGINAL");
        imprimirVetor(vetor);
        System.out.println();
        vetor = inverter(vetor);
        System.out.println("VETOR INVERTIDO");
        imprimirVetor(vetor);
        System.out.println();
        System.out.println("FIM DO EX1");
        System.out.println();
        // #endregion

        // #region EX2 - MAIN
        System.out.println("INICIO DO EX2");
        int[] vetor2 = lerVetor(lerInteiro);
        System.out.println("VETOR ORIGINAL:");
        imprimirVetor(vetor2);
        System.out.println("VETOR COM OS VIZINHOS SOMADOS");
        vetor2 = somaVizinhos(vetor2);
        imprimirVetor(vetor2);
        System.out.println("FIM DO EX2");
        System.out.println();
        // #endregion

        // #region EX3 - MAIN
        System.out.println("INICIO DO EX3");
        System.out.println("INSIRA UMA DATA NO FORMATO DD/MM/AAAA");
        String data = lerString.nextLine();
        verificarData(data);
        System.out.println("FIM DO EX3");
        System.out.println();
        // #endregion

        // #region EX4 - MAIN
        System.out.println("INICIO DO EX4");
        System.out.println("INSIRA UMA DATA");
        data = lerString.nextLine();
        System.out.println(data);
        diaDaSemana(data);
        System.out.println("FIM DO EX4");
        System.out.println();
        // #endregion

        // #region EX5 - MAIN
        System.out.println("INICIO EX5");
        System.out.println("DIGITE O NOME DO ARQUIVO INCLUINDO SEU TIPO DE ARQUIVO (EXEMPLO: Arquivo.txt)");
        String caminho = lerString.nextLine();
        somaVizinhosArquivo(lerInteiro, caminho);
        System.out.println("FIM DO EX5");
        System.out.println();

        // #endregion

        // #region EX6 - MAIN
        System.out.println("INICIO DO EX6");
        System.out.println("DIGITE O NOME DO ARQUIVO");
        caminho = lerString.nextLine();
        verificarDiaEData(lerInteiro, caminho);
        System.out.println("FIM DO EX6");
        System.out.println();
        // #endregion

        // FECHAMENTO DO PROGRAMA
        System.out.println("FIM DA LISTA");
        lerInteiro.close();
        lerString.close();
    }

    // #region EX1 CODIGO:
    public static int[] inverter(int[] vetor) {
        int i = 0, j = vetor.length - 1;
        while (i < j) {
            // ARMAZENAR - TROCAR ENTRE VETORES - TROCAR COM ARMAZENADO
            int aux = vetor[j];
            vetor[j] = vetor[i];
            vetor[i] = aux;
            i++;
            j--;
        }
        return vetor;
    }
    // #endregion

    // #region EX2 CODIGO:
    public static int[] somaVizinhos(int[] vetor) {

        // #region VARIAVEIS
        int[] resultante;
        int i = 0;
        int index = 0;
        int j = 1;
        // #endregion

        if (vetor.length % 2 == 0) {
            resultante = new int[vetor.length / 2];
            while (index < resultante.length) {
                resultante[index] = vetor[i] + vetor[j];
                // ANDANDO DE DUAS EM DUAS CASAS DO VETOR TEMOS I+2 E J+2
                i = i + 2;
                j = j + 2;
                index++;
            }
        } else {
            resultante = new int[(vetor.length / 2) + 1];
            while (index < resultante.length) {

                // VERIFICAR SE O VALOR DO INDEX É O ÚLTIMO
                if (index == resultante.length - 1) {
                    resultante[index] = vetor[i] * 2;
                } else {
                    resultante[index] = vetor[i] + vetor[j];
                    i = i + 2;
                    j = j + 2;
                }
                index++;
            }

        }

        return resultante;
    }
    // #endregion

    // #region EX3 CODIGO:
    private static boolean verificarData(String data) {
        boolean analise = true;
        if (data.contains("/")) {
            int mes = 0;
            int dia = 0;
            int ano = 0;
            String[] stringData = data.split("/");
            try {
                dia = Integer.parseInt(stringData[0]);
                mes = Integer.parseInt(stringData[1]);
                ano = Integer.parseInt(stringData[2]);
            } catch (NumberFormatException x) {
                System.out.println("FAVOR INSERIR APENAS NÚMEROS INTEIROS");
            }

            // VERIFICAR TODOS OS CASOS POSSÍVEIS DE ERRO NA FORMATAÇÃO DA DATA
            if (stringData[0].length() > 2) {
                System.out.println("DIAS FORA DO FORMATO");
                analise = false;
            }
            if (mes == 2) {
                if (bisexto(ano) && dia > 28) {
                    System.out.println("ANO BISEXTO DE 28 DIAS");
                    analise = false;
                } else {
                    if (dia > 27) {
                        System.out.println("ANO NÃO BISEXTO DE 27 DIAS");
                        analise = false;
                    }
                }
            }
            if (stringData[1].length() > 2) {
                System.out.println("MESES FORA DO FORMATO");
                analise = false;
            }
            if (ano > 9999 || ano < 1000) {
                System.out.println("ANO FORA DO FORMATO");
                analise = false;
            }
            if (stringData.length > 3) {
                System.out.println("FORMATO DA DATA ERRADO");
                analise = false;
            }

            if (mes > 12) {
                System.out.println("MES ACIMA DO PERMITIDO");
                analise = false;
            }
            if (ano < 1) {
                System.out.println("ANO ABAIXO DO POSSÍVEL");
                analise = false;
            }
            if (dia > 31) {
                System.out.println("DIA ACIMA DO PERMITIDO");
                analise = false;
            }
            if (dia > 28 && mes == 2) {
                System.out.println("FEVEREIRO TEM 28 DIAS");
                analise = false;
            }
            if (dia > 30 && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) {
                System.out.println("MES DE 30 DIAS");
                analise = false;
            }
            if (dia > 31 && (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8)) {
                System.out.println("MES DE 31 DIAS");
                analise = false;
            }
            if (mes <= 0) {
                System.out.println("MES ABAIXO DO POSSIVEL");
                analise = false;
            }
            if (dia <= 0) {
                System.out.println("DIA ABAIXO DO POSSIVEL");
                analise = false;
            }

        } else {
            System.out.println("FORA DO FORMATO");
            analise = false;
        }
        if (analise) {
            System.out.println("DATA CORRETA");
        }
        return analise;

    }

    // #endregion

    // #region EX4 CODIGO:
    private static void diaDaSemana(String data) {

        String[] vetData = data.split("/");

        int dia = Integer.parseInt(vetData[0]);
        int mes = Integer.parseInt(vetData[1]);
        int ano = Integer.parseInt(vetData[2]);
        int totalDias = 0;

        int contador = 0;
        // Definir total de dias de primeiro de janeiro até a data do último dia do mês
        // informado
        for (int j = 1; j < mes; j++) {
            switch (j) {
                case 1:
                    totalDias += 31;
                    break;
                case 2:
                    if (bisexto(ano)) {
                        totalDias += 29;
                    } else {
                        totalDias += 28;
                    }
                    break;
                case 3:
                    totalDias += 31;
                    break;
                case 4:
                    totalDias += 30;
                    break;
                case 5:
                    totalDias += 31;
                    break;
                case 6:
                    totalDias += 30;
                    break;
                case 7:
                    totalDias += 31;
                    break;
                case 8:
                    totalDias += 31;
                    break;
                case 9:
                    totalDias += 30;
                    break;
                case 10:
                    totalDias += 31;
                    break;
                case 11:
                    totalDias += 30;
                    break;
                case 12:
                    totalDias += 31;
                default:
                    break;
            }
        }
        totalDias = totalDias + dia - 1;
        // Contar cada dia até o total de dias expirar alterando o dia da semana (quarta
        // quinta sexta...)

        if (ano >= 2022) {
            /*
             * 0 SABADO
             * 1 DOMINGO
             * 2 SEGUNDA
             * 3 TERÇA
             * 4 QUARTA
             * 5 QUINTA
             * 6 SEXTA
             * CONTANDO PARA FRENTE
             */
            int diferenca = ano - 2022;

            for (int i = 1; i <= totalDias + diferenca; i++) {
                contador++;

                if (contador == 7) {
                    contador = 0;
                }

            }

            switch (contador) {
                case 0:
                    System.out.println("SÁBADO");
                    break;
                case 1:
                    System.out.println("DOMINGO");
                    break;
                case 2:
                    System.out.println("SEGUNDA");
                    break;
                case 3:
                    System.out.println("TERÇA");
                    break;
                case 4:
                    System.out.println("QUARTA");
                    break;
                case 5:
                    System.out.println("QUINTA");
                    break;
                case 6:
                    System.out.println("SEXTA");
                    break;
                default:
                    break;
            }

        } else {
            /*
             * 0 SABADO
             * 1 SEXTA
             * 2 QUINTA
             * 3 QUARTA
             * 4 TERCA
             * 5 SEGUNDA
             * 6 DOMINGO
             * CONTANDO PARA TRÁS
             */

            for (int i = 1; i <= totalDias; i++) {
                contador++;

                if (contador == 7) {
                    contador = 0;
                }

            }
            switch (contador) {
                case 0:
                    System.out.println("SÁBADO");
                    break;
                case 1:
                    System.out.println("SEXTA");
                    break;
                case 2:
                    System.out.println("QUINTA");
                    break;
                case 3:
                    System.out.println("QUARTA");
                    break;
                case 4:
                    System.out.println("TERÇA");
                    break;
                case 5:
                    System.out.println("SEGUNDA");
                    break;
                case 6:
                    System.out.println("DOMINGO");
                    break;
                default:
                    break;
            }

        }
    }
    // #endregion

    // #region EX5 CODIGO:
    public static void somaVizinhosArquivo(Scanner lerInteiro, String path) {
        // Leitura do arquivo
        lerInteiro = ReturnScannerFile(lerInteiro, path);
        while (lerInteiro.hasNextLine()) {

            // Ler a linha do arquivo e armazenar em um vetor já separando os valores
            String[] vetString = lerInteiro.nextLine().split(";");

            // Declaração do vetor int para conversão
            int[] vetInt = new int[vetString.length];

            // Conversão de string para int
            for (int i = 0; i < vetString.length; i++) {
                vetInt[i] = Integer.parseInt(vetString[i]);
            }

            // aplicar metodo da soma dos vizinhos
            vetInt = somaVizinhos(vetInt);
            imprimirVetor(vetInt);
            System.out.println();

        }

    }

    // #endregion

    // #region MÉTODOS COMPLEMENTARES
    public static boolean bisexto(int ano) {
        if (ano % 4 != 0) {
            if (ano % 400 != 0) {
                return false;
            }
        }

        if (ano % 100 != 0) {
            return true;
        }
        return false;

    }

    public static void verificarDiaEData(Scanner lerInteiro, String path) {
        // Leitura do arquivo
        lerInteiro = ReturnScannerFile(lerInteiro, path);
        String[] vetString = new String[Integer.parseInt(lerInteiro.nextLine())];

        for (int i = 0; i < vetString.length; i++) {
            vetString[i] = lerInteiro.nextLine();
        }

        for (int i = 0; i < vetString.length; i++) {

            System.out.println(vetString[i]);

            if (verificarData(vetString[i])) {
                diaDaSemana(vetString[i]);
            }

            System.out.println();

        }

    }

    public static Scanner ReturnScannerFile(Scanner leitura, String path) {
        File arquivo = new File(path);
        try {
            leitura = new Scanner(arquivo);
        } catch (IOException x) {
        }
        return leitura;
    }

    public static int[] lerVetor(Scanner lerInteiro) {
        System.out.println("INSIRA 6 VALORES INTEIROS");
        int[] vetor = new int[6];
        for (int i = 0; i < 6; i++) {
            int valor = lerInteiro.nextInt();
            vetor[i] = valor;
        }
        return vetor;
    }

    public static void imprimirVetor(int[] vet) {
        for (int i = 0; i < vet.length; i++) {
            System.out.print(vet[i] + ";");
        }
        System.out.println();
    }

    // #endregion

}

/*
 * QUESTOES ABERTAS:
 * 
 * 07 – Um método em um código trata-se de uma parte de uma classe que pode ser
 * chamada a qualquer momento dentro do código, ou seja, é uma função com nome
 * que condensa linhas de código sendo possível chamar essa função dentro da
 * própria função, essa sendo a chamada “recursividade”.
 * 
 * 
 * 
 * 08 – Dividir o sistema em métodos é vantajoso pois acaba por proporcionar um
 * entendimento mais conciso da lógica, deixando o código mais limpo e por sua
 * vez diminuindo o número de linhas no código já que posso chamar apenas o
 * método ao invés de reescrever a mesma lógica várias vezes.
 * 
 * 
 * 
 * 09 – Parâmetros de forma simplificada são as variáveis necessárias para o
 * funcionamento do método, para que o método possa se adaptar a diferentes
 * valores necessários para a lógica, assim podendo ser utilizado para diversas
 * situações e não se prendendo a um valor fixo.
 * 
 * 
 * 
 * 10 – O dado passado por parâmetro pode sofrer alterações dentro do método
 * para modela-lo dentro da função, porém, é possível apenas utilizar o
 * parâmetro e não altera-lo como por exemplo utilizando o parâmetro para uma
 * comparação ou inserir o parâmetro em alguma estrutura de dados.
 * 
 * 
 * 
 * 11-
 * b) É possível criar duas classe para o automóvel, contendo dentro dela uma
 * lista de serviços que foram realizados e o proprietário do automóvel, sendo
 * esse último uma outra classe contendo as informações necessárias para definir
 * se trata-se de uma pessoa jurídica ou não (uma variável booleana por exemplo
 * seria o suficiente).
 * 
 * 
 * 
 * c) Podemos criar uma lista para registrar todos os candidatos, sendo esses
 * candidatos por sua vez uma classe que possuí suas habilidades armazenadas
 * dentro dele. Então é possível realizar um método dentro da lista de
 * organização utilizando pesos diferentes para as determinadas habilidades
 * requeridas pela vaga, a lógica quicksort seria então utilizada levando em
 * conta a somatória de peso de cada candidato para a manipulação dos ponteiros,
 * colocando em sua última posição o candidato mais adequado e em ordem até o
 * menos adequado de acordo com o peso das habilidades fornecidos por via de
 * parâmetro desse método.
 * 
 * 
 * 
 * d) Criando uma classe paciente, podemos definir as vacinas tomadas dentro
 * dessa classe, sendo essa vacina mais uma classe contendo em si nome, lote,
 * data da aplicação e uma booleana para verificar se o paciente já recebeu a
 * vacina. Armazenando todas essas pessoas com os devidos dados, em uma lista de
 * pacientes, podemos utilizar um método que verifica se há alguma vacina nesta
 * semana utilizando o calendário anual como parâmetro, esse último sendo
 * novamente uma lista de dias com as vacinas em suas devidas datas, sendo que
 * essas datas possuem em si uma booleana que indica se existe ou não vacina
 * nesse dia e por sua vez todas as vacinas do dia listadas e paramentadas de
 * acordo com seu nome e lote, assim o método da lista de pacientes pode ser
 * facilmente atualizada para caso o paciente receba a vacina por meio de um
 * método que altere os dados do cliente utilizando o nome da vacina e
 * parametrizando que foi aplicada e copiando do calendário o nome, lote e por
 * fim a data de aplicação.
 * 
 */