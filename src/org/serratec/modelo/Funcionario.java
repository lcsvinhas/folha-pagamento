package org.serratec.modelo;

import org.serratec.excecao.DependenteException;
import org.serratec.servico.CalculoINSS;
import org.serratec.servico.Operacao;
import org.serratec.servico.CalculoIR;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

public class Funcionario extends Pessoa implements Operacao {
    private Double salarioBruto = 0.0;
    private Double salarioLiquido;
    private Double descontoIR;
    private Double descontoINSS;
    private Set<Dependente> dependentes;

    public Funcionario(String nome, String cpf, LocalDate dataNascimento,
                       Double salarioBruto) {
        super(nome, cpf, dataNascimento);
        this.salarioBruto = salarioBruto;
        this.dependentes = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "salarioBruto=" + salarioBruto +
                ", salarioLiquido=" + salarioLiquido +
                ", descontoIR=" + descontoIR +
                ", descontoINSS=" + descontoINSS +
                ", dependentes=" + dependentes +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }

    public Double getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(Double salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public Double getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(Double salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    public Double getDescontoIR() {
        return descontoIR;
    }

    public void setDescontoIR(Double descontoIR) {
        this.descontoIR = descontoIR;
    }

    public Double getDescontoINSS() {
        return descontoINSS;
    }

    public void setDescontoINSS(Double descontoINSS) {
        this.descontoINSS = descontoINSS;
    }

    public Set<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(Set<Dependente> dependentes) {
        this.dependentes = dependentes;
    }

    @Override
    public void adicionateDependente(Dependente dependente) throws DependenteException {
        Integer idade = Period.between(dependente.getDataNascimento(), LocalDate.now()).getYears();
        if (idade < 18) {
            dependentes.add(dependente);
        } else {
            throw new DependenteException("Dependente deve ser menor de idade!");
        }
    }

    @Override
    public void calcularDescontoINSS() {
        if (this.getSalarioBruto() <= CalculoINSS.FAIXA1.getRemuneracaoINSS()) {
            this.descontoINSS = this.getSalarioBruto() * CalculoINSS.FAIXA1.getAliquotaINSS() - CalculoINSS.FAIXA1.getParcelaINSS();
        } else if (this.getSalarioBruto() <= CalculoINSS.FAIXA2.getRemuneracaoINSS()) {
            this.descontoINSS = this.getSalarioBruto() * CalculoINSS.FAIXA2.getAliquotaINSS() - CalculoINSS.FAIXA2.getParcelaINSS();
        } else if (this.getSalarioBruto() <= CalculoINSS.FAIXA3.getRemuneracaoINSS()) {
            this.descontoINSS = this.getSalarioBruto() * CalculoINSS.FAIXA3.getAliquotaINSS() - CalculoINSS.FAIXA3.getParcelaINSS();
        } else if (this.getSalarioBruto() <= CalculoINSS.FAIXA4.getRemuneracaoINSS()) {
            this.descontoINSS = this.getSalarioBruto() * CalculoINSS.FAIXA4.getAliquotaINSS() - CalculoINSS.FAIXA4.getParcelaINSS();
        } else {
            this.descontoINSS = this.getSalarioBruto() * CalculoINSS.FAIXA4.getAliquotaINSS() - CalculoINSS.FAIXA4.getParcelaINSS();
        }
        if (this.descontoINSS > CalculoINSS.teto) {
            this.descontoINSS = CalculoINSS.teto;
        }
    }

    @Override
    public void calcularDescontoIR() {
        Double salarioBase = this.salarioBruto - dependentes.size()* CalculoIR.valorDependentes - this.descontoINSS;

        if (salarioBase <= CalculoIR.BASE1.getBaseCalculoIR()) {
            this.descontoIR = salarioBase * CalculoIR.BASE1.getAliquotaIR() - CalculoIR.BASE1.getParcelaIR();
        } else if (salarioBase <= CalculoIR.BASE2.getBaseCalculoIR()) {
            this.descontoIR = salarioBase * CalculoIR.BASE2.getAliquotaIR() - CalculoIR.BASE2.getParcelaIR();
        } else if (salarioBase <= CalculoIR.BASE3.getBaseCalculoIR()) {
            this.descontoIR = salarioBase * CalculoIR.BASE3.getAliquotaIR() - CalculoIR.BASE3.getParcelaIR();
        } else if (salarioBase <= CalculoIR.BASE4.getBaseCalculoIR()) {
            this.descontoIR = salarioBase * CalculoIR.BASE4.getAliquotaIR() - CalculoIR.BASE4.getParcelaIR();
        } else {
            this.descontoIR = salarioBase * CalculoIR.BASE5.getAliquotaIR() - CalculoIR.BASE5.getParcelaIR();
        }
    }

    @Override
    public void calcularSalarioLiquido() {
        this.salarioLiquido = this.salarioBruto - this.descontoINSS - this.descontoIR;
    }
}
