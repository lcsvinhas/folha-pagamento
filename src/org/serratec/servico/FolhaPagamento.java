package org.serratec.servico;

import java.time.LocalDate;

import org.serratec.modelo.Funcionario;

public class FolhaPagamento {

	private Integer codigo;
	private Funcionario funcionario;
	private LocalDate dataPagamento;
	private Double descontoINSS;
	private Double descontoIR;
	private Double salarioLiquido;
	
	
	
	public FolhaPagamento(Funcionario funcionario, LocalDate dataPagamento) {
		this.funcionario = funcionario;
		this.dataPagamento = dataPagamento;
		this.descontoINSS = funcionario.getDescontoINSS();
		this.descontoIR = funcionario.getDescontoIR();
		this.salarioLiquido = funcionario.getSalarioLiquido();
		
		
		
	}



	@Override
	public String toString() {
		return "FolhaPagamento [codigo=" + codigo + ", funcionario=" + funcionario + ", dataPagamento=" + dataPagamento
				+ ", descontoINSS=" + descontoINSS + ", descontoIR=" + descontoIR + ", salarioLiquido=" + salarioLiquido
				+ "]";
	}



	public Integer getCodigo() {
		return codigo;
	}



	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}



	public Funcionario getFuncionario() {
		return funcionario;
	}



	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}



	public LocalDate getDataPagamento() {
		return dataPagamento;
	}



	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}



	public Double getDescontoINSS() {
		return descontoINSS;
	}



	public void setDescontoINSS(Double descontoINSS) {
		this.descontoINSS = descontoINSS;
	}



	public Double getDescontoIR() {
		return descontoIR;
	}



	public void setDescontoIR(Double descontoIR) {
		this.descontoIR = descontoIR;
	}



	public Double getSalarioLiquido() {
		return salarioLiquido;
	}



	public void setSalarioLiquido(Double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}
	
	
	
	
	
}
