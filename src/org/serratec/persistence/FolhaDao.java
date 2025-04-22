package org.serratec.persistence;

import org.serratec.servico.FolhaPagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FolhaDao {
    private Connection con;

    public FolhaDao(Connection con) {
        this.con = con;
    }

    public void inserir(FolhaPagamento folha) throws SQLException {
        String sql = "insert into folha_pagamento (id_funcionario, data_pagamento, desconto_inss, desconto_ir, salario_liquido) values (?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, folha.getFuncionario().getId());
        stmt.setDate(2, java.sql.Date.valueOf(folha.getDataPagamento()));
        stmt.setDouble(3, folha.getDescontoINSS());
        stmt.setDouble(4, folha.getDescontoIR());
        stmt.setDouble(5, folha.getSalarioLiquido());
        stmt.executeUpdate();
        stmt.close();
    }
}
