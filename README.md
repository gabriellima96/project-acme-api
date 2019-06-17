### API da aplicação ACME - fluxo de aprovação para compra de material de escritório

---

#### Requisitos

- Java JDK 8+
- Maven
- Mysql (Nome do banco = db)

---

#### Instruções 

1- Clone este projeto
```Shell
git clone https://github.com/gabriellima96/project-acme-api.git
```

2- Adicione seu usuario e senha do MySQL no arquivo **application.yml** em:
```
acmeapi/src/main/resources/application.yml
```

3- Build - Dentro da pasta do projeto execute:
```Shell
mvn clean install
```

4- Run
```Shell
mvn spring-boot:run
```

5- Acesse a página inicial em **http://localhost:8080/api/v1/**

#### ENDPOINTS DISPONÍVEIS

* **URL** - Criar novas solicitações | Buscar solicitações cadastradas

  _*http://localhost:8080/api/v1/orders*_

* **Method:**

  ` `POST` | GET` 
  
*  **POST**

  *  **Body:**
     ```
      {
        "requester": "Gabriel Lima",
        "product": {
          "description": "Pilha AAA",
          "price": 4.75
         }
      }
      
*  **success:**
  
  * **Code:** 201 Created <br />
    **Content:** `{
                    "id": 36,
                    "requester": "Gabriel Lima",
                    "note": null,
                    "status": "PENDING",
                    "product": {
                        "description": "Pilha AAA",
                        "price": 4.75
                    }
                  }`
                  
*  **error:**
  
  * **Code:** 422 Unprocessable Entity <br />
    **Content:** `{
                      "timestamp": 1560758640971,
                      "status": 422,
                      "error": "Field Validation Error",
                      "message": "Check all fields",
                      "path": "",
                      "errors": [
                          {
                              "field": "requester",
                              "message": "não pode estar em branco"
                          }
                      ]
                  }`
*  **GET**

  *  **params:**
     `status=[string]
      requester=[string]
      product=[string]
      sort=[string]
      page=[integer]
      size=[integer]`
      
   * **Example:**
   http://localhost:8080/api/v1/orders?sort=status,requester,desc&status=DENIED&requester=gabriel
      
*  **success:**
  
  * **Code:** 200 Ok <br />
    **Content:** `{
    "content": [
        {
            "id": 8,
            "requester": "Diogo Lopes",
            "note": null,
            "status": "APPROVED",
            "product": {
                "description": "TV LED 50 LG",
                "price": 2199
            }
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageSize": 1,
        "pageNumber": 0,
        "paged": true,
        "unpaged": false
    },
    "totalPages": 3,
    "totalElements": 3,
    "last": false,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "size": 1,
    "first": true,
    "numberOfElements": 1,
    "empty": false
}`


* **URL** -Atualizar o status de uma solicitação

  _*http://localhost:8080/api/v1/orders/{id}/status*_

* **Method:**

  `PUT` 
  
*  **PUT**

  *  **Body:**
     ```
      {
        "status": "DENIED",
        "note": "Fornecedor sem estoque no momento."
      }
      
*  **success:**
  
  * **Code:** 204 No Content <br />
                  
*  **error:**
  
  * **Code:** 422 Unprocessable Entity <br />
    **Content:** `{
                      "timestamp": 1560759384770,
                      "status": 422,
                      "error": "Field Validation Error",
                      "message": "Check all fields",
                      "path": "",
                      "errors": [
                          {
                              "field": "status",
                              "message": "não pode ser nulo"
                          }
                      ]
                  }`
