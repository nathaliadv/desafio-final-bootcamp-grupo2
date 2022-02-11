# Projeto Integrador Bootcamp Meli (Wave 4 - Grupo 2)
Hoje o Mercado Livre, ou MELI como é conhecido na bolsa de valores, já vende produtos alimentícios, mas quer se aventurar a poder incluir produtos que precisam de refrigeração, chamados produtos frescos.

Como projeto final, o grupo foi propostos a implementar uma API REST sobre produtos FRESCOS para o marketplace do MELI e, assim, aplicar todo o conteúdo e conhecimento adquiridos ao longo do BOOTCAMP MELI. (Git, Java, Spring, Armazenamento, Qualidade e Segurança).

## Tecnologias
Este projeto foi desenvolvido utilizando as seguintes tecnologias:

- Linguagem de programação: JAVA 11
- Frameworks: Spring Boot, Spring WEB, Spring Data JPA, Spring Security, Validation API
- Banco de dados: MySQL
- Ferramentas: Postman, IntelliJ e MySQL Workbench

<div style="display: inline_block"><br>
<img src=https://raw.githubusercontent.com/github/explore/5b3600551e122a3277c2c5368af2ad5725ffa9a1/topics/java/java.png width="65" height="60"
/> <img src=https://spring.io/images/projects/spring-boot-7f2e24fb962501672cc91ccd285ed2ba.svg width="60" height="55"
/>
<img src=https://spring.io/images/projects/spring-data-79cc203ed8c54191215a60f9e5dc638f.svg width="60" height="55"
/>
<img src=https://spring.io/images/projects/spring-security-b712a4cdb778e72eb28b8c55ec39dbd1.svg width="60" height="55"
/>
<img src=https://raw.githubusercontent.com/github/explore/80688e429a7d4ef2fca1e82350fe8e3517d3494d/topics/mysql/mysql.png width="60" height="55"
/>
<img src=https://www.vhv.rs/dpng/d/571-5718602_transparent-ubuntu-logo-png-logo-postman-icon-png.png width="60" height="55"
/>
<img src=https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAaUBgVyY4CJWh02Lx0PuWeq4EcbeY0-3v0PUJ5BqTxIMAxgSvlkWLY9pKM8ZIo71s4xs&usqp=CAU width="60" height="55"
/>

## Uso

A classe principal para este aplicativo é o DesafioFinalBootcampGrupo2Application, onde o contexto Spring é inicializado. É necessário ter o JDK 11 instalado localmente.

## Desenvolvimento do Projeto

Este projeto foi dividido em 6 partes, sendo as 5 primeiras em grupo e a 6ª individualmente, conforme orientado pela Digital House.

### 1º Requisito
**Objetivo:** O Representante do armazém de distribuição, deseja inserir um lote de produtos no armazém de distribuição PARA registrar a existência de estoque.

**Endpoints:**
| HTTP | Modelo da URI | Descrição |
|--|--|--|
| POST | /api/v1/fresh-products/inboundorder/ | Cadastre um lote com o estoque de produtos que o compõe.Devolva o lote criado com o código de status "201 CREATED". |
| PUT | /api/v1/fresh-products/inboundorder/ | Caso o lote já exista e deva ser atualizado. Devolva o estoque atualizado com o código de status "201 CREATED". |

### 2º Requisito
**Objetivo:** O comprador deseja adicionar produtos ao carrinho de compras do
Marketplace para comprá-los, se desejar.

**Endpoints:**
| HTTP | Modelo da URI | Descrição |
|--|--|--|
| GET | /api/v1/fresh-products/ | Veja uma lista completa de produtos. Se a lista não existir, ela deve retornar um "404 Not Found". |
| GET | /api/v1/fresh-products/list?querytype=[categoriaProduto] | Veja uma lista de produtos por categoria. Categoria: FS = Fresco, RF = Refrigerado , FF = Congelado. Se a lista não existir, ela deve retornar um "404 Not Found". |
|POST| /api/v1/fresh-products/orders/ |Registre um pedido com a lista de produtos que compõem o PurchaseOrder. Calcule o preço final e devolva-o juntamente com o código de status "201 CREATED". Se não houver estoque de um produto, notifique a situação retornando um erro por produto, não no nível do pedido.
|GET| /api/v1/fresh-products/orders/querytype=[idOrder]|Mostrar produtos no pedido.
|PUT| /api/v1/fresh-products/orders/ query param=[idOrder] | Modifique o pedido existente. torná-lo do tipo de carrinho para modificar|

### 3º Requisito
**Objetivo:** O Representante deseja poder consultar um produto em stock no
armazém para saber a sua localização num setor e os diferentes lotes onde se
encontra.

**Endpoints:**
| HTTP | Modelo da URI | Descrição |
|--|--|--|
|GET| /api/v1/fresh-products/list?querytype=[idProducto]|Veja uma lista de produtos com todos os lotes onde aparece. Se a lista não existir, ela deve retornar um “404 Not Found”.|
|GET| /api/v1/fresh-products/list?querytype=[idProducto]querytype=[L]|Veja uma lista de produtos com todos os lotes onde aparece. Ordenados por: L = ordenado por lote C = ordenado por quantidade atual F = ordenado por data de vencimento

### 4º Requisito
**Objetivo:** O Representante deseja poder consultar um produto em todos os
armazéns para saber o estoque em cada armazém do referido produto

**Endpoints:**
| HTTP | Modelo da URI | Descrição |
|--|--|--|
| GET| /api/v1/fresh-products/warehouse/querytype=id product]|Obtenha a quantidade total de produtos por armazém. Se o produto não existe em nenhum depósito, você deve retornar um "404 Not Found".

### 5º Requisito
**Objetivo:** O Representante QUERO poder consultar os produtos em estoque que
estão prestes a expirar no almoxarifado, a fim de aplicar alguma ação comercial
com eles.

**Endpoints:**
| HTTP | Modelo da URI | Descrição |
|--|--|--|
| GET| /api/v1/fresh-products/due-date/queryparam=[number of days]queryparam=[section]| Obtenha todos os lotes armazenados em um setor de um armazém ordenados por sua data de vencimento.|
|GET| /api/v1/fresh-products/due-date/list?queryparam=[number of days]queryparam=[category]queryparam=[asc]|Obtenha uma lista dos lotes pedidos por prazo de validade, que pertencem a uma determinada categoria de produto. category: FS = Fresco RF = Refrigerado FF = Congelado

### 6º Requisito
**Objetivo:** A instrução consiste em criar/adicionar uma nova User Story ao Projeto Final, mas desta vez sem especificar o problema a ser resolvido pelo Product Owner (PO), ou seja, de natureza GRATUITA. O participante terá a possibilidade de explorar criativamente diferentes alternativas de possíveis problemas enquanto estes se inserem no universo e na lógica de negócio proposta pela cadeira no Projecto Final, para depois desenvolver e implementar a solução que considere mais adequada.

Para

## Autores
Este projeto foi desenvolvido pelo GRUPO 02 / WAVE 4:

- Aderson Neto *(https://github.com/avcneto)*
- Gabriel Souza *(https://github.com/gaasouza)*
- Luan Albert *(https://github.com/luanAlbertMeli)*
- Matheus Henrique *(https://github.com/MatheusHenrique129)*
- Nathalia Dantas *(https://github.com/nathaliadantasv)*
- Rodrigo Franco *(https://github.com/rodalmeidafranco)*

## Agradecimentos
Agradecemos primeiramente ao MELI por ter nos proporcionado a experiência e oportunidade de ingressar como Software Developer e participarmos do IT Bootcamp. Agradecemos aos instrutores da Digital House: Kenyo Faria, Mauri Klein, Michelle de Souza, Joice Lima e Betania Melo, por sempre nos orientar a sermos melhor como pessoa e profissional. Por fim, agradecemos a todos grandes profissionais que palestraram e que nos mostraram os caminhos do Meli, da tecnologia e da ética, mas principalmente a Manu Braga e Natalia Ventura por todo acompanhamento ao longo do bootcamp.