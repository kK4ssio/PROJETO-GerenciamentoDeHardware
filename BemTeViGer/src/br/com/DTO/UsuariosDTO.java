package br.com.DTO;

/**
 * @author Kassio Dias Monteiro
 */
public class UsuariosDTO {
    private int id;
    private String nomeUsu, TipoUsu, SenhaUsu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeUsu() {
        return nomeUsu;
    }

    public void setNomeUsu(String nomeUsu) {
        this.nomeUsu = nomeUsu;
    }

    public String getTipoUsu() {
        return TipoUsu;
    }

    public void setTipoUsu(String TipoUsu) {
        this.TipoUsu = TipoUsu;
    }

    public String getSenhaUsu() {
        return SenhaUsu;
    }

    public void setSenhaUsu(String SenhaUsu) {
        this.SenhaUsu = SenhaUsu;
    }
}
