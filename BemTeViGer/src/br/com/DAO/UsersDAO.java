package br.com.DAO;

import br.com.DTO.UsuariosDTO;
import java.sql.*;
import javax.swing.JOptionPane;
import br.com.VIEWS.Cadastro;
import br.com.VIEWS.GerUsers;
import javax.swing.table.DefaultTableModel;

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
     public void pesquisaAuto() {
        String sql = "select * from usuario";
        Connection conexao = ConexaoDAO.conector();

        try {

            if (conexao != null) {
                PreparedStatement pst = conexao.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) GerUsers.TabelaUsers.getModel();
                model.setNumRows(0);

                while (rs.next()) {

		    int id = rs.getInt("id");

                    String nome = rs.getString("nome_usuario");
                    
                    String tipo = rs.getString("tipo_usuario");
                    
                    String senha = rs.getString("senha_usuario");

        
                    model.addRow(new Object[]{id, nome, tipo, senha});
                }

                rs.close();
                pst.close();
                conexao.close();
            }
        } catch (Exception e) {

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
                pesquisaAuto();
                JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
                LimpaCampos();

                conexao.close();

            }

        } catch (Exception e) {
          
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
                pesquisaAuto();
                JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
                LimpaCampos();

                conexao.close();

            }

        } catch (Exception e) {
           
        }

    }
    
     public void Procura(UsuariosDTO objUsuaDTO) {
             
        String sql = "select * from usuario where id = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuaDTO.getId());
            rs = pst.executeQuery();

            if (rs.next()) {
            objUsuaDTO.setNomeUsu(rs.getString("nome_usuario"));
            objUsuaDTO.setSenhaUsu(rs.getString("senha_usuario"));
            objUsuaDTO.setTipoUsu(rs.getString("tipo_usuario"));
              
                conexao.close();

            } else {
                JOptionPane.showMessageDialog(null, "Usuario não existe");
               LimpaCampos();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Método Pesquisar" + e);
        }

    }
        

    public void LimpaCampos() {
        Cadastro.txtNomeUsu.setText(null);
        Cadastro.txtSenha.setText(null);
        Cadastro.TipoUsu.setSelectedIndex(0);
        GerUsers.txtID.setText(null);
        GerUsers.txtNome.setText(null);
        GerUsers.txtSenha.setText(null);
    }

}
