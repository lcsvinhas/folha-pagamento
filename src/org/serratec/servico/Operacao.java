package org.serratec.servico;

import org.serratec.excecao.DependenteException;
import org.serratec.modelo.Dependente;

public interface Operacao {
    void adicionateDependente(Dependente dependente) throws DependenteException;
    void calcularDescontoINSS();
    void calcularDescontoIR();
    void calcularSalarioLiquido();
}
