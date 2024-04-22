# Alura Oracle One - Challenge Backend #01

Este desafio foi realizado durante a etapa do backend da turma 06 da parceria Alura com Oracle ONE

A proposta desse projeto é criar uma ferramenta conversora de moeda, que utiliza uma api externa para validação dos valores

### Dependências: Java 17, Gson 2.10.1, ExchangeRate-API
### A api sugerida e usada foi a [ExchangeRate-API](https://www.exchangerate-api.com/)

A implementação da solução levou em consideração a possibilidade de se utilizar de todas as moedas disponiveis na plataforma
Obs: Data referência 04/2024

### Para executar a aplicação é só rodar a classe Main.java(localizada dentro da pasta src) com sua IDE preferida, foi usado o IntelliJ

### Estrutura
O projeto seguiu uma arquitetura simples MVC, porém substituida a camada controller, pela camada menu, visto que a aplicação roda em console

#### Pacote API
Realiza comunicação externa com a API, utiliza de uma interface para facilitar a implementação de outras API's caso seja necessário mudar

#### Pacote ENUMS
Possui um enum com todas as Currencies(moedas) que existem na documentação da API

#### Pacote MENU
Possui o menu de usuário, onde serve de 'controller' para entrada e saída de informações de usuário, tem o display dos dados e realiza algumas validações de moedas

#### Pacote MODEL
Possui os modelos referentes as API's (Response Model), podendo ser extendido futuramente para persistir em banco de dados da preferencia

#### Pacote SERVICE
Possui as classes de serviços em que são implementadas as regras de negócios para chamar API, realizar log e histórico da sessão atual

#### Pacote UTILS
Possui as classes para execução de formatações simples de valores

#### Pacote RESOURCES
Possui o arquivo para configuração (parecido com o .env)

### OBS: É PRECISO CONFIGURAR SUA API KEY NO PACOTE RESOURCES PARA REALIZAR CHAMADAS A API EXTERNA
Como realizar a configuração, abra o arquivo external-resources-example.properties e coloque a sua chave da API na linha correspondente
após isso, renomeie o arquivo para **'external-resources.properties'**
