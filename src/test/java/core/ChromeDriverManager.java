package core;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager {
	private static final String chromeDriverPath = "src/test/resources/drivers/chromedriver.exe";
	private static final String webDriverType = "webdriver.chrome.driver";
		
	public static ChromeOptions criarDriverOptions() {
		//criamos as opcoes de configuracao para uma instancia do chrome driver
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--disable-extensions");
		//criamos uma propriedade para informar que vamos usar o driver do chrome e sua localizacao nesse projeto
		System.setProperty(ChromeDriverManager.webDriverType, ChromeDriverManager.chromeDriverPath);
				
		return options;
	}
}
