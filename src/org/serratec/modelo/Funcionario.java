package org.serratec.modelo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Funcionario  extends Pessoa{
    private Double SalarioBruto;
    private Double SalarioLiquido;
    private Double DescontoIR;
    private Double DescontoINSS;
    private Set<Dependente> dependentes;

    public Funcionario(String nome, String cpf, LocalDate dataNascimento,
                       Double salarioBruto) {
        super(nome, cpf, dataNascimento);
        SalarioBruto = salarioBruto;
        this.dependentes = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "SalarioBruto=" + SalarioBruto +
                ", SalarioLiquido=" + SalarioLiquido +
                ", DescontoIR=" + DescontoIR +
                ", DescontoINSS=" + DescontoINSS +
                ", dependentes=" + dependentes +
                '}';
    }

    public Double getSalarioBruto() {
        return SalarioBruto;
    }

    public void setSalarioBruto(Double salarioBruto) {
        SalarioBruto = salarioBruto;
    }

    public Double getSalarioLiquido() {
        return SalarioLiquido;
    }

    public void setSalarioLiquido(Double salarioLiquido) {
        SalarioLiquido = salarioLiquido;
    }

    public Double getDescontoIR() {
        return DescontoIR;
    }

    public void setDescontoIR(Double descontoIR) {
        DescontoIR = descontoIR;
    }

    public Double getDescontoINSS() {
        return DescontoINSS;
    }

    public void setDescontoINSS(Double descontoINSS) {
        DescontoINSS = descontoINSS;
    }

    public Set<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(Set<Dependente> dependentes) {
        this.dependentes = dependentes;
    }
}
