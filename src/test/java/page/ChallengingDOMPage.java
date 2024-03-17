package page;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import core.TipoInfoElemento;
import core.TiposBrowser;
import core.TiposSeletores;
import core.WebdriverManager;
import util.Utils;

public class ChallengingDOMPage extends WebdriverManager {
	private static final String urlChallengingDOM= Utils.obterUrl(ChallengingDOMPage.class.getSimpleName());
	private static final String botaoAzulXpath= Utils.obterMapeamentoElemento(ChallengingDOMPage.class.getSimpleName(), "botaoAzul", TipoInfoElemento.seletor);
	private static final String botaoVermelhoXpath= Utils.obterMapeamentoElemento(ChallengingDOMPage.class.getSimpleName(), "botaoVermelho", TipoInfoElemento.seletor);
	private static final String botaoVerdeXpath= Utils.obterMapeamentoElemento(ChallengingDOMPage.class.getSimpleName(), "botaoVerde", TipoInfoElemento.seletor);
	private static final String listaRegistrosXpath= Utils.obterMapeamentoElemento(ChallengingDOMPage.class.getSimpleName(), "listaRegistros", TipoInfoElemento.seletor);
	private static final String listaCabecalhoXpath= Utils.obterMapeamentoElemento(ChallengingDOMPage.class.getSimpleName(), "listaCabecalho", TipoInfoElemento.seletor);
	
	public static WebElement botaoAzul;
	public static String textoBotaoAzul;
	public static WebElement botaoVermelho;
	public static String textoBotaoVermelho;
	public static WebElement botaoVerde;
	public static String textoBotaoVerde;
	public static ArrayList<WebElement> listaRegistros;
	public static ArrayList<WebElement> listaCabecalho;
	public static HashMap<Integer, ArrayList<HashMap<String, WebElement>>> mapaRegistros;
	public static WebElement linkEdit;
	public static WebElement linkDelete;
	
	public static void acessarOSiteChallengingDOM() {
		WebdriverManager.criarDriver(TiposBrowser.Chrome);
		WebdriverManager.acessarPaginaSite(ChallengingDOMPage.urlChallengingDOM);
		
		WebdriverManager.aguardarUrl(ChallengingDOMPage.urlChallengingDOM, 3000);
		WebdriverManager.aguardarPaginaSerCarregada(3000);
		
		ChallengingDOMPage.inicializarElementosPagina();
		ChallengingDOMPage.inicializarMapaRegistros();
	}
	
	private static void inicializarMapaRegistros() {
		ArrayList<HashMap<String, WebElement>> registro= null;
		HashMap<String, WebElement> itemColuna= null;
		WebElement elemento= null;
		int i, j, qtdLinhas, qtdColunas;
		String nomeColuna= null;
		
		ChallengingDOMPage.listaRegistros= ChallengingDOMPage.encontrarListaElementos(TiposSeletores.XPATH, ChallengingDOMPage.listaRegistrosXpath);
		qtdLinhas= ChallengingDOMPage.listaRegistros.size();
		ChallengingDOMPage.listaCabecalho= ChallengingDOMPage.encontrarListaElementos(TiposSeletores.XPATH, ChallengingDOMPage.listaCabecalhoXpath);
		qtdColunas= ChallengingDOMPage.listaCabecalho.size();
		ChallengingDOMPage.mapaRegistros= new HashMap<Integer, ArrayList<HashMap<String,WebElement>>>();
		
		for(i=0; i<qtdLinhas; i++) {
			registro= new ArrayList<HashMap<String,WebElement>>();
			
			for(j=0; j<qtdColunas; j++) {
				itemColuna= new HashMap<String, WebElement>();
				
				elemento= ChallengingDOMPage.encontrarElemento(TiposSeletores.XPATH, "//table//tbody/tr[" + (i+1) + "]/td[" + (j+1) + "]");
				nomeColuna= listaCabecalho.get(j).getText();
				itemColuna.put(nomeColuna, elemento);
				registro.add(itemColuna);
				
				itemColuna= null;
			}
			itemColuna= new HashMap<String, WebElement>();
			elemento= ChallengingDOMPage.encontrarElemento(TiposSeletores.XPATH, "//table//tbody/tr[1]/td[7]/a[1]");
			itemColuna.put("linkEdit", elemento);
			registro.add(itemColuna);
			elemento= ChallengingDOMPage.encontrarElemento(TiposSeletores.XPATH, "//table//tbody/tr[1]/td[7]/a[2]");
			itemColuna.put("linkDelete", elemento);
			registro.add(itemColuna);
			itemColuna= null;
			
			ChallengingDOMPage.mapaRegistros.put(i+1, registro);
			registro= null;
			itemColuna= null;
		}
	}
	
	private static void inicializarElementosPagina() {
		ChallengingDOMPage.aguardarElementoPagina(TiposSeletores.XPATH, ChallengingDOMPage.botaoAzulXpath);
		ChallengingDOMPage.botaoAzul= encontrarElemento(TiposSeletores.XPATH, ChallengingDOMPage.botaoAzulXpath);

		ChallengingDOMPage.aguardarElementoPagina(TiposSeletores.XPATH, ChallengingDOMPage.botaoVermelhoXpath);
		ChallengingDOMPage.botaoVermelho= encontrarElemento(TiposSeletores.XPATH, ChallengingDOMPage.botaoVermelhoXpath);
		
		ChallengingDOMPage.aguardarElementoPagina(TiposSeletores.XPATH, ChallengingDOMPage.botaoVerdeXpath);
		ChallengingDOMPage.botaoVerde= encontrarElemento(TiposSeletores.XPATH, ChallengingDOMPage.botaoVerdeXpath);
	}
	
	private static void salvarTextoElemento(WebElement elemento, String textoSalvar) {
		Assert.assertNotNull(elemento);
		textoSalvar= elemento.getText();
	}
	
	private static void salvarTextoBotaoAzul() {
		ChallengingDOMPage.salvarTextoElemento(ChallengingDOMPage.botaoAzul, ChallengingDOMPage.textoBotaoAzul);
	}
	
	private static void salvarTextoBotaoVermelho() {
		ChallengingDOMPage.salvarTextoElemento(ChallengingDOMPage.botaoVermelho, ChallengingDOMPage.textoBotaoVermelho);
	}
	
	private static void salvarTextoBotaoVerde() {
		ChallengingDOMPage.salvarTextoElemento(ChallengingDOMPage.botaoVerde, ChallengingDOMPage.textoBotaoVerde);
	}
	
	public static void salvarTextoBotaoPagina(String corBotao) {
		switch (corBotao) {
			case "Azul":
				ChallengingDOMPage.salvarTextoBotaoAzul();
				break;
			case "Vermelho":
				ChallengingDOMPage.salvarTextoBotaoVermelho();
				break;
			case "Verde":
				ChallengingDOMPage.salvarTextoBotaoVerde();
				break;
			default:
				break;
		}
	}
	
	private static void aguardarElementoPagina(TiposSeletores tipoSeletor, String seletor) {
		WebdriverManager.aguardarPresencaElemeto(tipoSeletor, seletor, 3000);
		WebdriverManager.aguardarElemetoVisivel(tipoSeletor, seletor, 3000);
	}
	
	private static void clicarElementoPagina(WebElement elemento) {
		WebdriverManager.aguardarElemetoClicavel(elemento, 3000);
		WebdriverManager.clicarElemento(elemento);
	}
	
	public static void clicarBotaoPagina(String corBotao, TiposSeletores tipoSeletor) {
		switch (corBotao) {
		case "Azul":
			ChallengingDOMPage.clicarElementoPagina(ChallengingDOMPage.botaoAzul);
			break;
		case "Vermelho":
			ChallengingDOMPage.clicarElementoPagina(ChallengingDOMPage.botaoVermelho);
			break;
		case "Verde":
			ChallengingDOMPage.clicarElementoPagina(ChallengingDOMPage.botaoVerde);
			break;
		default:
			break;
		}
	}
	
	public static void clicarLinkDoRegistro(String nomeLink, int numeroRegistro) {
		switch (nomeLink) {
		case "edit":
			ChallengingDOMPage.clicarElementoPagina(ChallengingDOMPage.mapaRegistros.get(numeroRegistro).get(7).get("linkEdit"));
			break;
		case "delete":
			ChallengingDOMPage.clicarElementoPagina(ChallengingDOMPage.mapaRegistros.get(numeroRegistro).get(7).get("linkDelete"));
			break;
		default:
			break;
		}
	}
	
	public static void verificarUrlContemValor(String valorNaUrl) {
		Assert.assertTrue(ChallengingDOMPage.driver.getCurrentUrl().contains(valorNaUrl));
	}
	
	private static void verificarMudancaTextoBotao(String textoAterior, WebElement botao) {
		String textoAtual;
		textoAtual= botao.getText();
		Assert.assertNotEquals(textoAterior, textoAtual);
	}
	
	private static void verificarMudancaTextoBotaoAzul() {
		ChallengingDOMPage.verificarMudancaTextoBotao(ChallengingDOMPage.textoBotaoAzul, ChallengingDOMPage.botaoAzul);
	}
	
	private static void verificarMudancaTextoBotaoVermelho() {
		ChallengingDOMPage.verificarMudancaTextoBotao(ChallengingDOMPage.textoBotaoVermelho, ChallengingDOMPage.botaoVermelho);
	}
	
	private static void verificarMudancaTextoBotaoVerde() {
		ChallengingDOMPage.verificarMudancaTextoBotao(ChallengingDOMPage.textoBotaoVerde, ChallengingDOMPage.botaoVerde);
	}
	
	public static void verificarMudançaNoTexto(String corBotao) {
		switch (corBotao) {
		case "Azul":
			ChallengingDOMPage.verificarMudancaTextoBotaoAzul();
			break;
		case "Vermelho":
			ChallengingDOMPage.verificarMudancaTextoBotaoVermelho();
			break;
		case "Verde":
			ChallengingDOMPage.verificarMudancaTextoBotaoVerde();
			break;
		default:
			break;
		}
	}
	
	public static void verificarRefreshNaPagina() {
		ChallengingDOMPage.verificarElementoObsoleto(ChallengingDOMPage.botaoAzul, 3000);
		ChallengingDOMPage.verificarElementoObsoleto(ChallengingDOMPage.botaoVermelho, 3000);
		ChallengingDOMPage.verificarElementoObsoleto(ChallengingDOMPage.botaoVerde, 3000);
				
		WebdriverManager.aguardarUrl(ChallengingDOMPage.urlChallengingDOM, 3000);
		WebdriverManager.aguardarPaginaSerCarregada(3000);
		ChallengingDOMPage.inicializarElementosPagina();
	}
}
