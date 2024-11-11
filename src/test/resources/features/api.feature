#language: pt
# author: filipeSantos
Funcionalidade: Desafio API - Parte 1


  Cenário: Criar usuário, autenticar, alugar livros e listar detalhes
    Dado que crio um novo usuário
    Quando gero um token de acesso
    Então verifico se o usuário está autorizado
    Dado listo os livros disponíveis
    Então alugo dois livros aleatórios
    E verifico os detalhes do usuário com os livros alugados
    #Aqui esta um exemplo consolidado, no projeto Karate está segmentado