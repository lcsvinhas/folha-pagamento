package org.serratec.persistence;

import org.serratec.modelo.Dependente;
import org.serratec.modelo.Parentesco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DependenteDao {
    private Connection con;

    public DependenteDao(Connection con) {
        this.con = con;
    }

    public void inserir(Dependente dependente, Integer funcionarioId) throws SQLException {
        String sql = "insert into dependente (nome, cpf, data_nascimento, parentesco, id_funcionario) values (?, ?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, dependente.getNome());
        stmt.setString(2, dependente.getCpf());
        stmt.setDate(3, java.sql.Date.valueOf(dependente.getDataNascimento()));
        stmt.setString(4, dependente.getParentesco().toString());
        stmt.setInt(5, funcionarioId);
        stmt.executeUpdate();
        stmt.close();
    }
}
