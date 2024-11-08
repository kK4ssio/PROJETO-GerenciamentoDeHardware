package br.com.DAO;

import br.com.DTO.LabsDTO;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import br.com.VIEWS.GerLabs;

/**
 * Author: Kassio Dias Monteiro
 */
public class labsDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void NovoLab(LabsDTO objLabDTO) {
        String sql = "INSERT INTO labs (nome_laboratorio) VALUES (?)";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objLabDTO.getNomeLab());

            pst.execute();

            JOptionPane.showMessageDialog(null, "Laboratório cadastrado com sucesso!");
            pst.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar laboratório: " + e);
        }
    }

 
    public void pesquisaAuto() {
        String sql = "SELECT * FROM labs";
        conexao = ConexaoDAO.conector();

        try {
            if (conexao != null) {
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) GerLabs.TabelaLabs.getModel();
                model.setNumRows(0);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String sala = rs.getString("nome_laboratorio");
                    model.addRow(new Object[]{id, sala});
                }

                rs.close();
                pst.close();
                conexao.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar laboratórios: " + e);
        }
    }

   
    public void ApagaLab(LabsDTO objLabDTO) {
        String sql = "DELETE FROM labs WHERE id = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objLabDTO.getIdLab());

            int del = pst.executeUpdate();

            if (del > 0) {
                pesquisaAuto();
                JOptionPane.showMessageDialog(null, "Laboratório deletado com sucesso!");
            }
            pst.close();
            conexao.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar laboratório: " + e);
        }
    }


    
    public void Procura(LabsDTO objLabDTO) {
        String sql = "SELECT * FROM labs WHERE id = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objLabDTO.getIdLab());
            rs = pst.executeQuery();

            if (rs.next()) {
                objLabDTO.setNomeLab(rs.getString("nome_laboratorio"));
            } else {
                JOptionPane.showMessageDialog(null, "Laboratório não encontrado.");
            }

            rs.close();
            pst.close();
            conexao.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar laboratório: " + e);
        }
    }

   
    public void LimpaCampos() {
   
         GerLabs.txtNomeLab.setText(null);
         GerLabs.txtIdLab.setText(null);
        
        
    }
}
