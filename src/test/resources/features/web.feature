#language: pt
# author: filipeSantos
Funcionalidade: Desafio Frontend - Parte 2

	Contexto: Inicio
		Dado Abrir Chrome

  Cenário: Preenchimento de Campos
    Dado acesso a página "DemoQA Practice Form page"
    Quando preencho formulário com dados valores aleatorios
    E realizo upload de arquivo
    E envio o formulário
    Então é exibido uma página de confirmação de cadastro
    E fecho o popup
    
  Cenário: Validar mensagem após abrir nova janela
  	Dado acesso a página "DemoQA Browser Windows"
    Quando clico no botão New Window
    Então uma nova janela é aberta com a mensagem "This is a sample page"
    E fecho a nova janela
  
  Cenário: Registro e validação em Web Tables
    Dado acesso a página "DemoQA Web Tables"
    Quando crio registro com dados valores aleatorios
    E altero registro com dados valores aleatorios e registro é editado com sucesso
    Então apago todos os registros
    #Aqui talvez fosse esperado um Esquema de Cenário
    
  Cenário: Controlar a Progress Bar e validar valores
		Dado acesso a página "DemoQA Web Progress Bar"
    Quando clico no botão Start
    Então paro a Progress Bar "antes" de 25%
    E valido que o valor da Progress Bar é menor ou igual a 25%
    Quando clico no botão Start
    Então paro a Progress Bar "chegar" de 100%
    E reseto a Progress Bar
  
