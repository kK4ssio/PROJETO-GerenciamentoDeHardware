import br.com.DAO.ConexaoDAO;
import br.com.DTO.LabsDTO;
import java.sql.*;
import javax.swing.JOptionPane; 
import br.com.VIEWS.NovoLab;

/**
 *
 * @author Kassio Dias Monteiro
 */
public class LabDAO {
     Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

public void NovoLabs(LabsDTO objLabsDTO) {

        String sql = "insert into labs ( nome_laboratorio) values (?)";

        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objLabsDTO.getNomeLab());

            
            pst.execute();

         
            JOptionPane.showMessageDialog(null, "Laboratorio cadastrada com sucesso!");
            LimpaCampos();

            pst.close();

        } catch (Exception e) {
            if (e.getMessage().contains("'")) {
                
                JOptionPane.showMessageDialog(null, "Erro.");
                
            }
            JOptionPane.showMessageDialog(null, e);
        }

    }
        public void LimpaCampos() {
        NovoLab.txtNomeLab.setText(null); //altere o nome do campo de texto  para o nome que tu botar !!!!!!!!

    }
    
}