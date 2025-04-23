package org.serratec.leituragravacao;

import org.serratec.conexao.ConnectionFactory;
import org.serratec.excecao.DependenteException;
import org.serratec.modelo.Dependente;
import org.serratec.modelo.Funcionario;
import org.serratec.modelo.Parentesco;
import org.serratec.persistence.DependenteDao;
import org.serratec.persistence.FolhaDao;
import org.serratec.persistence.FuncionarioDao;
import org.serratec.servico.FolhaPagamento;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
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
                if (!linha.isEmpty()) {
                    String[] dados = linha.split(";");
                    String nome = dados[0];
                    String cpf = dados[1];
                    LocalDate dataNascimento = LocalDate.parse(dados[2], dtf);

                    try {
                        Double salario = Double.parseDouble(dados[3]);
                        funcionario = new Funcionario(nome, cpf, dataNascimento, salario);
                        funcionarios.add(funcionario);
                    } catch (NumberFormatException e) {
                        Parentesco parentesco = Parentesco.valueOf(dados[3]);
                        Dependente dependente = new Dependente(nome, cpf, dataNascimento, parentesco);
                        funcionario.adicionateDependente(dependente);
                    }
                }

            }
            input.close();
            for (Funcionario f : funcionarios) {
                System.out.println(f.getNome() + " " + f.getCpf() + " " + f.getDataNascimento() + " " + f.getSalarioBruto());
                for (Dependente d : f.getDependentes()) {
                    System.out.println(d.getNome() + " " + d.getCpf() + " " + d.getDataNascimento() + " " + d.getParentesco());

                }
            }
            System.out.print("Digite o caminho do arquivo de saída: ");
            String saida = sc.next();
            FileWriter fw = new FileWriter(saida);
            PrintWriter pw = new PrintWriter(fw);

            Connection connection = new ConnectionFactory().getConnection();
            FuncionarioDao funcionarioDao = new FuncionarioDao(connection);
            DependenteDao dependenteDao = new DependenteDao(connection);
            FolhaDao folhaDao = new FolhaDao(connection);

            List<String> rejeitados = new ArrayList<>();

            for (Funcionario f : funcionarios) {
                f.calcularDescontoINSS();
                f.calcularDescontoIR();
                f.calcularSalarioLiquido();

                boolean funcionarioInserido = true;

                try {
                    funcionarioDao.inserir(f);
                } catch (SQLException e) {
                    if (e.getMessage().contains("cpf_key")) {
                        rejeitados.add(f.getNome() + ";" + f.getCpf() + ";" + f.getDataNascimento() + ";" + f.getSalarioBruto());
                        funcionarioInserido = false;
                    } else {
                        throw new RuntimeException(e);
                    }
                }

                if (!funcionarioInserido) {
                    for (Dependente d : f.getDependentes()) {
                        rejeitados.add(d.getNome() + ";" + d.getCpf() + ";" + d.getDataNascimento() + ";" + d.getParentesco());
                    }
                    continue;
                }

                for (Dependente d : f.getDependentes()) {
                    try {
                        dependenteDao.inserir(d, f.getId());
                    } catch (SQLException e) {
                        if (e.getMessage().contains("cpf_key")) {
                            rejeitados.add(d.getNome());
                        } else {
                            throw new RuntimeException(e);
                        }
                    }
                }

                FolhaPagamento folha = new FolhaPagamento(f, LocalDate.of(2025, 5, 5));
                try {
                    folhaDao.inserir(folha);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                String linha = f.getNome() + ";" + String.format("%.2f", f.getDescontoINSS()) + ";" + String.format("%.2f", f.getDescontoIR()) + ";" + String.format("%.2f", f.getSalarioLiquido());
                pw.println(linha);
            }

            if (!rejeitados.isEmpty()) {
                String caminhoRejeitados = new File(saida).getParent() + "/rejeitados.csv";
                FileWriter fwr = new FileWriter(caminhoRejeitados);
                PrintWriter pwr = new PrintWriter(fwr);

                for (String rejeitado : rejeitados) {
                    pwr.println(rejeitado);
                }
                pwr.close();
                System.err.println("Arquivo gerado com nomes rejeitados");
            }

            pw.close();
            connection.close();
            System.out.println("Arquivo gerado com nomes aceitos");


        } catch (FileNotFoundException e) {
            System.err.println("Erro ao ler arquivo");
        } catch (DependenteException d) {
            System.err.println(d.getMessage());
        } catch (IOException i) {
            System.err.println("Erro ao gravar arquivo");
        } catch (SQLException e) {
            System.err.println("Erro ao gravar no banco");
            e.printStackTrace();
        }
    }
}
