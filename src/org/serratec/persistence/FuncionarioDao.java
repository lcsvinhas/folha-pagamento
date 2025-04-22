package org.serratec.persistence;

import org.serratec.modelo.Funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FuncionarioDao {
    private Connection con;

    public FuncionarioDao(Connection con) {
        this.con = con;
    }

    public void inserir(Funcionario funcionario) throws SQLException {
        String sql = "insert into funcionario (nome, cpf, data_nascimento, salario_bruto) values (?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getCpf());
        stmt.setDate(3, java.sql.Date.valueOf(funcionario.getDataNascimento()));
        stmt.setDouble(4, funcionario.getSalarioBruto());
        stmt.executeUpdate();

        var rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            funcionario.setId(rs.getInt(1));
        }

        stmt.close();
    }
}
