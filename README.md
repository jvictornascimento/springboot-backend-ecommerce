<h1 align="center">Spring Boot Backend Ecommerce</h1>

![Badge Concluído](https://img.shields.io/static/v1?label=Status&message=Desenvolvendo&color=red&style=for-the-badge)
![Badge Java](https://img.shields.io/static/v1?label=Java&message=21&color=red&style=for-the-badge&logo=java)
![Badge Spring](https://img.shields.io/static/v1?label=SpringBoot&message=v3.5.0&color=brightgreen&style=for-the-badge&logo=SpringBoot)
![Badge Mysql](https://img.shields.io/static/v1?label=Mysql&message=v&color=blue&style=for-the-badge&logo=PostgreSQL)

## Resumo do Projeto

Este projeto é um backend para um sistema de e-commerce desenvolvido com Java e Spring Boot. Ele fornece funcionalidades essenciais como cadastro de produtos, usuários, pedidos e muito mais.

## Diagrama de Classe
```mermaid
    classDiagram
    
    class User {
        - id : Long
        - email : String
        - firstName : String
        - lastName : String
        - password : String
        - cart : Cart
        - orders : Order[]
        - roles : Role[]
    }
    
    class Role {
        - id : Long
        - name : String
        - users : User[]
    }
    
    class UserRole {
        +userId : Long
        +roleId : Long
    }
    
    class Cart {
        - id : Long
        - totalAmount : BigDecimal
        - user : User
        - items : CartItem[ ]
        + void removeItem(cartItem : CartItem)
        + void addItem(cartItem : CartItem) 
        + void update() 
        - void updateTotalAmount() 
        + void clearCart() 
        
    }
    
    class CartItem {
        - id : Long
        - quantity : int
        - unitPrice : BigDecimal
        - totalPrice : BigDecimal
        - product : Product
        - cart : Cart
        + void setTotalPrice()
    }
    
    class Category {
        - id : Long
        - name : String
        - products : Product[ ]
    }
    
    class Product {
        - id : Long
        - name : String
        - brand : String
        - description : String
        - inventory : int
        - price : BigDecimal
        - category : Category
        - images : Image[]
    }
    
    class Image {
        - id : Long
        - fileName : String
        - fileType : String
        - downloadUrl : String
        - image : Blob
        - product : Product
    }
    
    class Order {
        - orderId : Long
        - orderDate : Date
        - orderStatus : Enum
        - totalAmount : BigDecimal
        - user : User
        - orderItems : OrderItem[]
    }
    
    class OrderItem {
        - id : Long
        - price : BigDecimal
        - quantity : int
        - order : Order
        - Product : Product
    }
    
    %% Relacionamentos
    User "1" --> "1" Cart : has
    User "1" --> "*" Order : places
    User "*" -- "*" Role : via UserRole
    Cart "1" --> "*" CartItem : contains
    CartItem "*" --> "1" Product : references
    Product "1" --> "*" Image : has
    Category "1" --> "*" Product : classifies
    Order "1" --> "*" OrderItem : contains
    OrderItem "*" --> "1" Product : references

````

## Funcionalidades Atuais
- CRUD de Produtos
- Cadastro de Imagens e associação com Produto
- Cadastro e Listagem de Pedidos
- Cadastro e Listagem de Carrinhos
- Associação de pedidos com usuários
- Integração com banco Mysql
- Mapeamento de Dto Com Model Mapper
- Autenticação e Autorização com Spring Security
- Controle de acesso por perfil
- Token com Jwt

## Atualizações Futuras
- Spring AI. (em fase de pesquisa)
- Containerização do projeto com Docker

## Tecnologias

- `Intellij`
- `Java 21`
- `Maven`
- `Spring Boot, Spring Web, Spring Data JPA`
- `Mysql`
- `Lombok`
- `Model Mapper`