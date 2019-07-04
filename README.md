### API de DEMONSTRAÇÃO de CRUD

---

#### Requisitos

- Java JDK 8+
- Maven
- Mysql (Nome do banco = dbTest)

---

#### Instruções 

1- Clone este projeto
```Shell
git clone https://github.com/gabriellima96/demo-crud.git
```

2- Adicione seu usuario e senha do MySQL no arquivo **application.yaml** em:
```
demo/src/main/resources/application.yaml
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

#### SEGUE ABAIXO ALGUNS ENDPOINTS DISPONÍVEIS

* **URL** - CRUD

  _*http://localhost:8080/api/v1/products*_

* **Method:**

  ` `POST` | `GET` | `DELETE` | `PUT` 
  
*  **POST**

  *  **Body:**
     ```
      {
        "description": "Pilha AAA",
        "price": 4.75
      }
      
*  **success:**
  
  * **Code:** 201 Created <br />
    **Content:** `{
        "id": 1,
        "description": "Pilha AAA",
        "price": 4.75
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
                              "field": "description",
                              "message": "não pode estar em branco"
                          }
                      ]
                  }`
*  **GET**

  *  **params:**
     `id=[long]
      description=[string]
      price=[double]
      sort=[string]
      page=[integer]
      size=[integer]`
      
   * **Example:**
   http://localhost:8080/api/v1/products?sort=description,desc&size=5&page=0
      
*  **success:**
  
  * **Code:** 200 Ok <br />
    **Content:** `{
    "content": [
        {
          "id": 1,
          "description": "TV LED 50 LG",
          "price": 2199
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
    "totalPages": 1,
    "totalElements": 1,
    "last": true,
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


* **DELETE** -

  _*http://localhost:8080/api/v1/products/{id}*_
      
*  **success:**
  
  * **Code:** 204 No Content <br />
                  
*  **error:**
  
  * **Code:** 404 Not Found <br />
    **Content:** `{
                    "timestamp": 1562266107556,
                    "status": 404,
                    "error": "Object not found",
                    "message": "Product 1 not found",
                    "path": ""
                  }`
                  
  * **Outros** -
  * **PUT** -
  _*http://localhost:8080/api/v1/products/{id}*_
  * **GET** -
  _*http://localhost:8080/api/v1/products/{id}*_
                  
                
