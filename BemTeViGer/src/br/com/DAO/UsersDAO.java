package br.com.DAO;

import br.com.DTO.UsuariosDTO;
import java.sql.*;
import javax.swing.JOptionPane;
import br.com.VIEWS.Cadastro;

/**
 * @author Kassio Dias Monteiro
 */
public class UsersDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void NovoUsu(UsuariosDTO objUsuaDTO) {

        String sql = "insert into usuario (nome_usuario, senha_usuario, tipo_usuario) values (?, ?, ?)";

        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objUsuaDTO.getNomeUsu());
            pst.setString(2, objUsuaDTO.getSenhaUsu());
            pst.setString(3, objUsuaDTO.getTipoUsu());

            pst.execute();

         
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            LimpaCampos();

            pst.close();

        } catch (Exception e) {
            if (e.getMessage().contains("'usuario.username'")) {
                JOptionPane.showMessageDialog(null, "Login já existente, usuário não foi inserido.");
                LimpaCampos();
            }
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void ApagaUsu(UsuariosDTO objUsuaDTO) {
        String sql = "delete from usuario where id = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuaDTO.getId());

            int del = pst.executeUpdate();

            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
                LimpaCampos();

                conexao.close();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no método apagar: " + e);
        }
    }

    public void EditarUsu(UsuariosDTO objUsuaDTO) {

        String sql = "update usuario set nome_usuario = ?, senha_usuario = ?, tipo_usuario = ? where id = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objUsuaDTO.getNomeUsu());
            pst.setString(2, objUsuaDTO.getSenhaUsu());
            pst.setString(3, objUsuaDTO.getTipoUsu());
            pst.setInt(4, objUsuaDTO.getId());

            int edit = pst.executeUpdate();

            if (edit > 0) {
                JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
                LimpaCampos();

                conexao.close();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no método editar: " + e);
        }

    }

    public void LimpaCampos() {
        Cadastro.txtNomeUsu.setText(null);
        Cadastro.txtSenha.setText(null);
        Cadastro.TipoUsu.setSelectedIndex(0);
    }

}
