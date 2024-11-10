package br.com.DAO;

import br.com.DTO.EquipDTO;
import br.com.VIEWS.GerEquips;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kassio Dias Monteiro
 */
public class EquipsDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void NovoEquip(EquipDTO eDto) {
        String sql = "INSERT INTO equipa (tipo_equipamento, status_equip, identificacao, lab_pertencete, observacao ) VALUES ( ?, ?, ?, ?, ?)";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, eDto.getTipoEquipamento());
            pst.setString(2, eDto.getStatus());
            pst.setString(3, eDto.getIdentificacao());
            pst.setString(4, eDto.getLabPertencente());
            pst.setString(5, eDto.getObservacao());

            System.out.println(pst);
            int add = pst.executeUpdate();
            if (add > 0) {

                pst.close();
                //limparCampos();
                JOptionPane.showMessageDialog(null, "Maquina inserida com sucesso! ");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "cadastrar" + e.getMessage());
        }
    }

    public ResultSet listarLabins() {

        String sql = "select nome_laboratorio from labs";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "hum" + erro.getMessage());
        }
        return rs;
    }

    public void pesquisaAuto() {
        String sql = "SELECT * FROM equipa";
        conexao = ConexaoDAO.conector();

        try {
            if (conexao != null) {
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();

                DefaultTableModel model = (DefaultTableModel) GerEquips.TabelaEquips.getModel();
                model.setNumRows(0);

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String tipo = rs.getString("tipo_equipamento");
                    String status = rs.getString("status_equip");
                    String identificacao = rs.getString("identificacao");
                    String labPertencente = rs.getString("lab_pertencete");
                    String observacao = rs.getString("observacao");

                    model.addRow(new Object[]{tipo, status, identificacao, labPertencente, observacao, id});
                }
                rs.close();
                pst.close();
                conexao.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na pesquisa: " + e.getMessage());
        }
    }

    public void ApagaEquip(EquipDTO eDto) {
        String sql = "DELETE FROM equipa WHERE id = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, eDto.getId());

            int del = pst.executeUpdate();
            if (del > 0) {
                pesquisaAuto();
                JOptionPane.showMessageDialog(null, "Equipamento deletado com sucesso!");
            }
            pst.close();
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar: " + e.getMessage());
        }
    }

    public void EditarEquip(EquipDTO eDto) {
        String sql = "UPDATE equipa SET tipo_equipamento = ?, status_equip = ?, identificacao = ?, lab_pertencete = ?, observacao = ? WHERE id = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, eDto.getTipoEquipamento());
            pst.setString(2, eDto.getStatus());
            pst.setString(3, eDto.getIdentificacao());
            pst.setString(4, eDto.getLabPertencente());
            pst.setString(5, eDto.getObservacao());
            pst.setInt(6, eDto.getId());

            int edit = pst.executeUpdate();
            if (edit > 0) {
                pesquisaAuto();
                JOptionPane.showMessageDialog(null, "Equipamento editado com sucesso!");
            }
            pst.close();
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao editar: " + e.getMessage());
        }
    }

    public void ProcuraEquip(EquipDTO eDto) {
        String sql = "SELECT * FROM equipa WHERE id = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, eDto.getId());
            rs = pst.executeQuery();

            if (rs.next()) {
                eDto.setTipoEquipamento(rs.getString("tipo_equipamento"));
                eDto.setStatus(rs.getString("status_equip"));
                eDto.setIdentificacao(rs.getString("identificacao"));
                eDto.setLabPertencente(rs.getString("lab_pertencete"));
                eDto.setObservacao(rs.getString("observacao"));
            } else {
                JOptionPane.showMessageDialog(null, "Equipamento não encontrado.");
            }
            rs.close();
            pst.close();
            conexao.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na busca: " + e.getMessage());
        }
    }
}
