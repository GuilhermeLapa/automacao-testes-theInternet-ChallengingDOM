package steps;

import core.TiposSeletores;
import core.WebdriverManager;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import page.ChallengingDOMPage;

public class ChallengingDOMSteps {
	@Dado("que acesso o site Challenging DOM")
	public static void queAcessoOSiteChallengingDOM() {
		ChallengingDOMPage.acessarOSiteChallengingDOM();
	}
	
	@E("salvo o texto do botao para verificar {string}")
	public static void salvoOTextoDoBotaoParaVerificar(String corBotao) {
		ChallengingDOMPage.salvarTextoBotaoPagina(corBotao);
	}
	
	@Quando("clico no botão {string}")
	public static void clicoNoBotao(String corBotao) {
		ChallengingDOMPage.clicarBotaoPagina(corBotao, TiposSeletores.XPATH);
	}
	
	@Então("verifico que houve refresh na página")
	public static void verificoQueHouveRefreshNaPagina() {
		ChallengingDOMPage.verificarRefreshNaPagina();
	}
	
	@E("verifico mudança no texto do botão {string}")
	public static void verificoMudançaNoTextoDoBotao(String corBotao) {
		ChallengingDOMPage.verificarMudançaNoTexto(corBotao);
	}
	
	@Quando("clico no link {string} do registro {int}")
	public static void clicoNoLinkDoRegistro(String nomeLink, int numeroRegistro) {
		ChallengingDOMPage.clicarLinkDoRegistro(nomeLink, numeroRegistro);
	}
	
	@Então("verifico que a url da página contém {string}")
	public static void verificoQueAUrlDaPaginaContem(String valorNaUrl) {
		ChallengingDOMPage.verificarUrlContemValor(valorNaUrl);
	}
	
	@After
	public void fecharBrowser() {
		WebdriverManager.fecharDriver();
	}
}
