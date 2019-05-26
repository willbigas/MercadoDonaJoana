package br.com.mercadojoana.control;

import br.com.mercadodonajoana.dao.TipoUsuarioDao;
import br.com.mercadodonajoana.model.Fornecedor;
import br.com.mercadodonajoana.model.TipoUsuario;
import br.com.mercadojoana.view.TelaPrincipal;
import br.com.mercadojoana.view.TelaFuncionarioGerenciar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author William
 */
public class TelaFuncionarioGerenciarControl {

    TelaFuncionarioGerenciar funcionarioGerenciar;
    private List<TipoUsuario> listTiposUsuarios;
    private TipoUsuarioDao tipoUsuarioDao;

    public TelaFuncionarioGerenciarControl() {
        tipoUsuarioDao = new TipoUsuarioDao();
        carregarTiposUsuarios();
    }

    public void chamarTelaFuncionarioGerenciar() {
        if (funcionarioGerenciar == null) { // se tiver nulo chama janela normalmente
            funcionarioGerenciar = new TelaFuncionarioGerenciar(this);
            TelaPrincipal.desktopPane.add(funcionarioGerenciar);
            funcionarioGerenciar.setVisible(true);
        } else {//se ele estiver criado
            if (funcionarioGerenciar.isVisible()) {
                funcionarioGerenciar.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(funcionarioGerenciar);
                funcionarioGerenciar.setVisible(true);
            }
        }
    }

    private void carregarTiposUsuarios() {
        listTiposUsuarios = tipoUsuarioDao.pesquisar();
        DefaultComboBoxModel<TipoUsuario> model = new DefaultComboBoxModel(listTiposUsuarios.toArray());
        funcionarioGerenciar.getCbTipoUsuario().setModel(model);
    }

}