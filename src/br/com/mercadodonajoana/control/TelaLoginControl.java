package br.com.mercadodonajoana.control;

import br.com.mercadodonajoana.dao.FuncionarioDao;
import br.com.mercadodonajoana.model.Funcionario;
import br.com.mercadodonajoana.uteis.Mensagem;
import br.com.mercadodonajoana.uteis.Texto;
import br.com.mercadodonajoana.view.TelaLogin;

/**
 *
 * @author William
 */
public class TelaLoginControl {
    
    TelaLogin telaLogin; 
    TelaPrincipalControl telaPrincipalControl;
    TelaPrincipalCaixaControl telaPrincipalCaixaControl;
    FuncionarioDao funcionarioDao;
    Funcionario funcionario;
    
    public static final int ADMINISTRADOR = 1;
    public static final int CAIXA = 2;

    public TelaLoginControl() {
        funcionarioDao = new FuncionarioDao();
    }
    
    public void chamarTelaLoginAction() {
        telaLogin = new TelaLogin(this);
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(true);
    }

    private void chamarTelaPrincipal() {
        telaPrincipalControl = new TelaPrincipalControl();
        telaPrincipalControl.chamarTelaPrincipal();
    }
    private void chamarTelaPrincipalCaixa() {
        telaPrincipalCaixaControl = new TelaPrincipalCaixaControl();
        telaPrincipalCaixaControl.chamarTelaPrincipalCaixa();
    }
    
    public void acessarTelaPrincipalAction() {
        funcionario = funcionarioDao.pesquisarLogin(telaLogin.getTfLogin().getText());
        if (funcionario == null) {
            Mensagem.info(Texto.ERRO_USUARIO);
            return;
        }
        /**
         * Get password retorna Char[] String então deve se instanciar uma nova string com os caracteres.
         */
        if (!funcionario.getSenha().equals(new String(telaLogin.getTfSenha().getPassword()))) { 
            Mensagem.atencao(Texto.SENHA_USUARIO);
            return;
        }
        
        if (funcionario.getTipoUsuario().getTipoPermissao() == ADMINISTRADOR) {
            chamarTelaPrincipal();
        } 
        
        if (funcionario.getTipoUsuario().getTipoPermissao() == CAIXA) {
            chamarTelaPrincipalCaixa();
        }
        telaLogin.dispose();
    }
    
    
    
    
}
