# language: pt
#encoding iso-8859-1

@Testar
Funcionalidade: Clicar Botões - Challenging DOM

Cenário: Clicar Botões da Página
	Dado que acesso o site Challenging DOM
	E salvo o texto do botao para verificar "<cor>"
	Quando clico no botão "<cor>"
	Então verifico que houve refresh na página
	E verifico mudança no texto do botão "<cor>"
	Exemplos:
	| cor      |
	| Azul     |
	| Vermelho |
	| Verde    |

Cenário: Clicar Todos Links Edit da Página
	Dado que acesso o site Challenging DOM
	Quando clico no link "edit" do registro <registro>
	Então verifico que a url da página contém "#edit"
	E clico no link "delete" do registro <registro>
	E verifico que a url da página contém "#delete"
	Exemplos:
	| registro |
	| 1        |
	| 2        |
	| 3        |
	| 4        |
	| 5        |
	| 6        |
	| 7        |
	| 8        |
	| 9        |
	| 10       |
