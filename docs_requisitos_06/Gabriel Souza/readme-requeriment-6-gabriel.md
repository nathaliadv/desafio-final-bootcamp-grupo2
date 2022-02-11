### 6º Requisito
**Objetivo:** O Administrador de Vendas deseja porder gerenciar anuncios relacionados a produtos FRESCO, FRIO e CONGELADOS e também seus tipos de envios.



**Endpoints:**
| HTTP | Modelo da URI | Descrição |
|--|--|--|
|GET| /api/v1/fresh-products/advertising/querytype=id_advertising|Busca um anuncio por código|
|GET| /api/v1/fresh-products/advertising/querytype=free_shipping&querytype=advertising_product_type|Busca vários anuncios por filtros.|
|POST| /api/v1/fresh-products/advertising/|Salve um anúncio.|
|PUT|/api/v1/fresh-products/advertising/querytype=id_advertising|Edita um anúncio.|
|DELETE|/api/v1/fresh-products/advertising/querytype=id_advertising|Deleta um anúncio.|
