package core;

import java.time.Duration;
import java.util.ArrayList;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverManager {
	protected static WebDriver driver;
	
	protected static void criarDriver(TiposBrowser nomeBrowser) {
		switch (nomeBrowser) {
			case Chrome:
				//instanciamos o driver como um driver do Chrome Browser
				driver= new ChromeDriver(ChromeDriverManager.criarDriver());
				break;
			/*case Firefox:
				driver= new FirefoxDriver(GeckoDriverManager.criarDriver());
				break;*/
			default:
				break;
		}
		
		//solicita ao webdriver que espere um determinado tempo padrao 
		WebdriverManager.driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		//solicita ao driver que maximize a tela assim que abri-la
		WebdriverManager.driver.manage().window().maximize();
	}
	
	public static void fecharDriver() {
		if(driver != null) {
			driver.quit();
			driver= null;
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	protected static void acessarPaginaSite(String url) {
		driver.get(url);
	}
	
	protected static void aguardarPresencaElemeto(TiposSeletores tipoSeletor, String caminho, long tempoMiliseg) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofMillis(tempoMiliseg));
		
		switch (tipoSeletor) {
			case XPATH:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(caminho)));
				break;
			case CSS:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(caminho)));
				break;
		default:
			break;
		}
	}
	
	protected static void aguardarElemetoVisivel(TiposSeletores tipoSeletor, String caminho, long tempoMiliseg) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofMillis(tempoMiliseg));
		
		switch (tipoSeletor) {
			case XPATH:
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(caminho)));
				break;
			case CSS:
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(caminho)));
				break;
		default:
			break;
		}
	}
	
	protected static void aguardarElemetoVisivel(WebElement elemento, long tempoMiliseg) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofMillis(tempoMiliseg));
		wait.until(ExpectedConditions.visibilityOf(elemento));
	}
	
	protected static void aguardarElemetoClicavel(WebElement elemento, long tempoMiliseg) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofMillis(tempoMiliseg));
		wait.until(ExpectedConditions.elementToBeClickable(elemento));
	}
	
	protected static void aguardar(long tempoMiliseg) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofMillis(tempoMiliseg));
		try {
			wait.wait(tempoMiliseg);
		}
		catch(Exception e) {
			e.getMessage();
		}
	}
	
	protected static void aguardarPaginaSerCarregada(long tempoMiliseg) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofMillis(tempoMiliseg));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//body")));
	}
	
	protected static void aguardarUrl(String url, long tempoMiliseg) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofMillis(tempoMiliseg));
		Assert.assertTrue(wait.until(ExpectedConditions.urlToBe(url)));
	}
	
	protected static void clicarElemento(WebElement elemento) {
		aguardar(3000);
		
		Actions acao= new Actions(driver);
		acao.moveToElement(elemento).build().perform();
		acao.scrollToElement(elemento).build().perform();
		
		try {
			aguardarElemetoClicavel(elemento, 3000);
			elemento.click();
		}
		catch(Exception e) {
			acao.click().build().perform();
			
			e.getMessage();
		}
	}
	
	protected static WebElement encontrarElemento(TiposSeletores tipoSeletor, String caminho) {
		WebElement elemento= null;
		aguardar(3000);
		aguardarPresencaElemeto(tipoSeletor, caminho, 3000);
		
		switch (tipoSeletor) {
			case XPATH:
				elemento= driver.findElement(By.xpath(caminho));
				break;
			case CSS:
				elemento= driver.findElement(By.cssSelector(caminho));
				break;
			default:
				break;
		}
		
		return elemento;
	}
	
	protected static ArrayList<WebElement> encontrarListaElementos(TiposSeletores tipoSeletor, String caminho) {
		ArrayList<WebElement> listaElementos= new ArrayList<WebElement>();
		aguardar(3000);
		aguardarPresencaElemeto(tipoSeletor, caminho, 3000);
		
		switch (tipoSeletor) {
			case XPATH:
				listaElementos= (ArrayList<WebElement>) driver.findElements(By.xpath(caminho));
				break;
			case CSS:
				listaElementos= (ArrayList<WebElement>) driver.findElements(By.cssSelector(caminho));
				break;
			default:
				break;
		}
		
		return listaElementos;
	}
	
	protected static void verificarElementoObsoleto(WebElement elemento, long tempoMiliseg) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofMillis(tempoMiliseg));
		Assert.assertTrue(wait.until(ExpectedConditions.stalenessOf(elemento)));
	}
}
