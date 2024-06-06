<h1 align="center">Recycle Guardians 🐢 </h1>

<div align="center">

| Integrantes            |            Responsabilidades          | Turma      |   RM     |
| ---------------------- | ------------------------------------- | ---------- | -------- |
| Alysson Alves Pinheiro | Tabelas do Banco de Dados             |  2TDSA     |  552410  |
| Ana Júlia Almeida      | Documentação do sistema               |  2TDSS     |  98974   |
| Anna Beatriz Soares    | Documentação do sistema               |  2TDSA     |  99814   |
| Cauã Couto             | Desenvolvimento do projeto em Java    |  2TDSS     |  97755   |
| Nicoly Oliveira        | Documentação do sistema               |  2TDSS     |  552410  |

</div>

## 📝 Descrição do Projeto 

> O projeto Recycle Guardians foi criado com a proposta de ajudar na busca de materiais reciclavéis e incentivar os usuários a fazerem-o.

Nosso sistema fornece recompensas baseadas na quantidade de material reciclável que nossos usuários buscaram em áreas maritmas contaminadas, dessa forma, oferecemos um estimulo
para ajudarem e procurarem se orientar perante o descarte inconsciênte de lixo

<h2 name="objetivo">🎯 Objetivos do Projeto</h2>
<li> Incentivar os usuários a se orientarem sobre o descarte de lixo marítmo </li>
<li> Recompensar usuários com base na sua contribuição </li>
<li> Armazenar pontos dos usuários e os tipos de resíduo/material que trouxeram </li>

## 📋  Modelo Relacional das Entidades 
![Modelo Relacional](https://github.com/ccoutob/marine-guard/assets/126828978/4eafbb62-0962-4b62-bd7b-79f7044c76d3)
## 📋  Modelo Relacional das Entidades 
![Logical](https://github.com/ccoutob/recycle-guardians/assets/126828978/3418d665-db61-4386-8c01-0b870a3c69c4)

>Os Modelos de banco de dados foram fornecidos para atender os requisitos da matéria de Banco de dados

<div>
<h1 align="center"> 📦 Pacotes do Projeto </h1>

<h2> 📦 Model </h2>
<li> Pacote contém as classes responsáveis por serem as base de modelagem da aplicação</li>
<li> Classes responsáveis por mapearem a tabela do Banco de Dados e suas colunas de acordo com as regras de negócio </li>

<h2> 📦 Dto </h2>
<li> As classes contidas nesse pacote são responsáveis pelo mapeamento das transferências que serão feitas no pacote controller</li>
<li> A princípio definem como as informações de nosso sistema serão navegadas e utilizadas</li>

<h2> 📦 Repository </h2>
<li> As classes contidas nesse pacote são responsáveis pela persistência JPA das tabelas</li>
<li> Essas classes serão utilizadas na controller para acessarmos o banco de dados</li>

<h2> 📦 Controller </h2>
<li> As classes contidas nesse pacote são responsáveis por controlarem as requisições que faremos no Postman</li>
<li> Contém o CRUD do projeto com os métodos GET, POST, PUT e DELETE</li>

<h2> 📦 Handler </h2>
<li> A classe nesse pacote é responsável por lançar a exceção de "error 404 not found"</li>
<li> A exceção será lançada caso tentemos realizar a requisição de algum dado que não existe, por exemplo, um id</li>
</div>

## ❗❗ COMO NOSSO SISTEMA FUNCINA ❗❗
<li> Inicie a aplicação spring após clonar o repositório ou baixar o arquivo do projeto</li>
<li> Baixe o arquivo de requisições do postman que está logo abaixo das tabelas de endpoints</li>
<li> Importe o arquivo para o seu postman</li>
<li> realize as requisições de GET, POST, PUT e DELETE</li>
<li> Utilize na URL "http://localhost:8080/{Endpoint}" Confira abaixo em Endpoints</li>

<h2 name="endpoints">🌐 Endpoints</h2>

### 💻 Usuario

| Método | Endpoint                 | Descrição                |
| ------ | ------------------------ | -------------------------|
| GET    | /usuario                 | Listar todos os usuarios |
| GET    | /usuario/&lt;id&gt;      | Buscar usuario pelo id   |
| POST   | /usuario                 | Cadastrar um usuario     |
| PUT    | /usuario/&lt;id&gt;      | Atualizar um usuario     |
| DELETE | /usuario/&lt;id&gt;      | Deletar um usuario       |

### 💻 Residuo
> O id a ser passado no POST deve ser do usuário

| Método | Endpoint                            | Descrição                            |
| ------ | ------------------------------------| -------------------------------------|
| GET    | /residuos                           | Listar todos os residuos             |
| GET    | /residuos/&lt;id&gt;                | Buscar residuo pelo id               |
| POST   | /empresas/&lt;id&gt;/residuosUsuario| Cadastrar um residuo a um usuario    |
| PUT    | /residuos/&lt;id&gt;                | Atualizar um residuo                 |
| DELETE | /residuos/&lt;id&gt;                | Deletar um residuo                   |

### 💻 Historico
> O id a ser passado no POST deve ser do usuário

| Método | Endpoint                               | Descrição                              |
| ------ | ---------------------------------------| ---------------------------------------|
| GET    | /historico                             | Listar todos os historicos             |
| GET    | /historico/&lt;id&gt;                  | Buscar historico pelo id               |
| POST   | /historico/&lt;id&gt;/historicosUsuario| Cadastrar um historico a um usuario    |
| PUT    | /historico/&lt;id&gt;                  | Atualizar um historico                 |
| DELETE | /historico/&lt;id&gt;                  | Deletar um historico                   |

### 💻 Brinde
> O id a ser passado no POST deve ser do usuário

| Método | Endpoint                          | Descrição                         |
| ------ | ----------------------------------| ----------------------------------|
| GET    | /brinde                           | Listar todos os brinde            |
| GET    | /brinde/&lt;id&gt;                | Buscar brinde pelo id             |
| POST   | /brinde/&lt;id&gt;/brindesUsuario | Cadastrar um brinde a um usuario  |
| PUT    | /brinde/&lt;id&gt;                | Atualizar um brinde               |
| DELETE | /brinde/&lt;id&gt;                | Deletar um brinde                 |

### 💻 Coleta
> O id a ser passado no POST deve ser do usuário

| Método | Endpoint                          | Descrição                           |
| ------ | ----------------------------------| ------------------------------------|
| GET    | /coleta                           | Listar todos as coletas             |
| GET    | /coleta/&lt;id&gt;                | Buscar coleta pelo id               |
| POST   | /coleta/&lt;id&gt;/coletaHistorico| Cadastrar uma coleta a um historico |
| PUT    | /coleta/&lt;id&gt;                | Atualizar uma coleta                |
| DELETE | /coleta/&lt;id&gt;                | Deletar uma coleta                  |

❗❗ Acesse o arquivo de requisições do postman <a href="https://github.com/ccoutob/recycle-guardians/blob/main/Recycle%20Guardians.postman_collection.json">AQUI</a> ❗❗

### 🧑🏻‍💻 Autor 
> Cauã Couto Basques - Turma 2TDSS
