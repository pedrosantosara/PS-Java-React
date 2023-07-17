# Processo Seletivo Java

<h1 align="center">
  <img src="https://cdn-icons-png.flaticon.com/512/226/226777.png?w=360" width=256 alt="java">
  <br />
  Api Banco Processo seletivo  <a href="https://github.com/pedrosantosara/PS-Java-React/archive/refs/heads/main.zip">Baixar</a>
</h1>

## O que está faltando fazer?

- [X] 1 - A sua api deve fornecer os dados de transferência de acordo com o número da conta bacária. : rota id da conta = `transferencias/conta/{id}`
- [X] 2 - Caso não seja informado nenhum filtro, retornar todos os dados de transferência. : retorna todos dados do banco = `/transferencias`
- [X] 3 - Caso seja informado um período de tempo, retornar todas as transferências relacionadas à aquele período de tempo. Retorna um periodo de tempo da conta {id}   = `/transferencias/conta/{id}?inicio={data_inicial}&fim={data_final}&operador={nome_operador}`
- [X] 4 - Caso seja informado um período de tempo, retornar todas as transferências relacionadas à aquele período de tempo. Retorna todas contas e parametros  = `/transferencias?inicio={data_inicial}&fim={data_final}&operador={nome_operador}`
- [X] 5 - Caso todos os filtros sejam informados, retornar todas as transferências com base no período de tempo informado e o nome do operador. = `/transferencias/conta/{id}?inicio={data_inicial}&fim={data_final}&operador={nome_operador}`
- [X] 6 - Operador de transação nada mais é que, o nome do responsável   de destino da transação caso seja uma operação de transferência   de saida ou o nome do responsável de onde se originou `a transação caso seja uma operação de transferência de entrada. : operador de entrada = me mandou dinheiro o nome dele e operador de saida = para quem estou mandando nome do cara que recebe`
- [X] 7 - Os valores devem ser de ponto flutuante, e deve-se considerar apenas duas casas decimais.`-500.5 ----> -500.50`
- [X] 9 - Retornar lista de contas com id em forma de objeto

# Tutorial Instalação para Desenvolvedores

[![GitHub](https://img.shields.io/badge/GitHub-pedrosantosara/PsReactFrontend-black?logo=github)](https://github.com/pedrosantosara/PS-React-Frontend)

Para instalar o Frontend Entrar no Repositorio acima e seguir passo a passo.

# 1º Passo - Clonar Repositorio

- Colocoque no seu terminal os comando abaixo
  * `git clone https://github.com/pedrosantosara/PS-Java-React`
  * acesse a pasta via `cd PS-Java-React` ou abrindo no seu editor de codigo

# 2º Passo - Executar Aplicação

* Execute com sua IDE de preferencia
* Ou via Linha de comando
* Necessario ter o java 11

# Acessar rotas e documentação

* Documentação APi
  * localizada no `http://localhost:8080/swagger-ui/index.html`

- Caso queira acessar via JSON

  * Rota para todas transferencias `http://localhost:8080/transferencias`
  * Rota para conta Id `http://localhost:8080/transferencias/conta/{id}`
- QueryParameters

  * Caso deseje acessar algo especifico como
  * Se for adicionar mais de um queryparameters coloque `&` entre eles exemplo : `http://localhost:8080/transferencias?inicio=2019-04-05&fim=2021-04-04&nomeOperadorTransacao=Beltrano`
    * nomeOperadorTransacao = `?nomeOperadorTransacao={nome}`
    * inicio = `?inicio=yyyy-MM-dd` inicio e fim devem ser enviados juntos
    * fim = `?fim=yyyy-MM-dd`
    * pageable = `?size=4&page=0`
      * size = Quantidade de elementos por pagina que deseja dividir
      * page = Pagina que deseja acessar (O page começa com 0 então se dividir a pagina em size = 4 e a pagina tiver 9 elementos ela vai ter 2 page = page=0 e page=1)
    * Devido ao tempo o pageable não foi implementado 100% e esta funcional mas não esta sendo consumido pelo frontend
# Preview Documentação
![image](https://github.com/pedrosantosara/PS-Java-React/assets/59851589/61ac27f4-63ac-496a-b052-8ddd5cdfcfa3)
