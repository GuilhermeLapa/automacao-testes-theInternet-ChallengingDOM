package util;

import java.io.BufferedReader;
import java.io.FileReader;
import org.json.JSONObject;
import core.TipoInfoElemento;

public class Utils {
	public static String obterUrl(String campo) {
		String resultado= null;
		JSONObject jsonObj;
		
		//conteúdo do arquivo urls.json em um objeto json
		jsonObj= Utils.obterJson("urls");
		//selecionamos o valor desejado conforme parâmetro
		resultado= jsonObj.get(campo).toString();
		
		return resultado;
	}
	
	public static String obterMapeamentoElemento(String nomeArquivo, String campo, TipoInfoElemento tipoInfoElemento) {
		String resultado= null;
		JSONObject jsonObj;
		
		//conteúdo do arquivo em um objeto json
		jsonObj= Utils.obterJson(nomeArquivo);
		//selecionamos o valor desejado conforme parâmetro
		switch (tipoInfoElemento.toString()) {
		case "seletor":
			resultado= jsonObj.getJSONObject(campo).toMap().get(TipoInfoElemento.seletor.toString()).toString();
			break;
		case "tipoSeletor":
			resultado= jsonObj.getJSONObject(campo).toMap().get(TipoInfoElemento.tipoSeletor.toString()).toString();
			break;
		default:
			break;
		}
		
		return resultado;
	}
	
	private static JSONObject obterJson(String nomeArquivo) {
		String linha;
		boolean fimArq= false;
		JSONObject resultado= null;
		StringBuilder stringJson= new StringBuilder();
		
		try {
			//leitura do arquivo json
			FileReader arquivoJson= new FileReader("./src/test/resources/properties/" + nomeArquivo + ".json");
			BufferedReader leitorArquivo= new BufferedReader(arquivoJson);
			while(!fimArq) {
				linha= leitorArquivo.readLine();
				if(linha == null) {
					fimArq= true;
				}
				else {
					stringJson.append(linha);
				}
			}
			//conteúdo do arquivo json em um objeto json
			resultado= new JSONObject(stringJson.toString());
			
			leitorArquivo.close();
			arquivoJson.close();
		}
		catch(Exception e) {
			System.out.println("Falha ao obter URL.\n" + e.getMessage());
		}
		
		return resultado;
	}
}
