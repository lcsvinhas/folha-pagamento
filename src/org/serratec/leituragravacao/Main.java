package org.serratec.leituragravacao;

import org.serratec.excecao.DependenteException;
import org.serratec.modelo.Dependente;
import org.serratec.modelo.Funcionario;
import org.serratec.modelo.Parentesco;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try{
            Scanner sc = new Scanner(System.in);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
            System.out.print("Digite o caminho do arquivo: ");
            String arquivo = sc.next();
            FileReader file = new FileReader(arquivo);
            Scanner input = new Scanner(file);
            Set<Funcionario> funcionarios = new HashSet<>();
            Funcionario funcionario = null;

            while (input.hasNext()) {
                String linha = input.nextLine();
                if(!linha.isEmpty()){
                    String[] dados = linha.split(";");
                    String nome = dados[0];
                    String cpf = dados[1];
                    LocalDate dataNascimento = LocalDate.parse(dados[2], dtf);

                    try{
                        Double salario = Double.parseDouble(dados[3]);
                        funcionario = new Funcionario(nome, cpf, dataNascimento, salario);
                        funcionarios.add(funcionario);
                    } catch (NumberFormatException e) {
                        Parentesco parentesco = Parentesco.valueOf(dados[3]);
                        Dependente dependente = new Dependente(nome,cpf,dataNascimento,parentesco);
                        funcionario.adicionateDependente(dependente);
                    }
                }

            }
            input.close();
            for(Funcionario f : funcionarios){
                System.out.println(f.getNome()+ " " + f.getCpf()+ " " + f.getDataNascimento() + " " + f.getSalarioBruto()) ;
                for (Dependente d : f.getDependentes()){
                    System.out.println(d.getNome()+ " " + d.getCpf()+ " " + d.getDataNascimento() + " " + d.getParentesco()) ;

                }
            }
            System.out.print("Digite o caminho do arquivo de saída: ");
            String saida = sc.next();
            FileWriter fw = new FileWriter(saida);
            PrintWriter pw = new PrintWriter(fw);

            for(Funcionario f : funcionarios){
                f.calcularDescontoINSS();
                f.calcularDescontoIR();
                f.calcularSalarioLiquido();

                String linha = f.getNome() + ";" + String.format("%.2f",f.getDescontoINSS())  + ";" + String.format("%.2f",f.getDescontoIR()) + ";" +  String.format("%.2f",f.getSalarioLiquido());
                pw.println(linha);
            }
            pw.close();

        } catch (FileNotFoundException e) {
            System.err.println("Erro ao ler arquivo");

        } catch(DependenteException d ){
            System.err.println(d.getMessage());
        } catch(IOException i){
            System.err.println("Erro ao gravar arquivo");
        }
    }
}
