# Documentação Fluxo de Feed, Negociação e Mensagem da API

Este projeto inclui vários endpoints RESTful para gerenciar *Publicações*, *Comentários*, *Propagandas*, *Anúncios*, *Chats* e *Mensagens*. A seguir estão descritos os **Endpoints**, os **Models** envolvidos, e a estratégia de tratamento de exceções usada no projeto.

## Comment Endpoints

| Method   | Endpoint                      | Description                                           | Responses                          |
|----------|-------------------------------|-------------------------------------------------------|------------------------------------|
| `POST`   | `/v1/comment/create`           | Creates a new comment from a JSON object.             | `200 OK`: Comment created. <br> `400 Bad Request`: Invalid data. |
| `GET`    | `/v1/comment/get/{publicationId}` | Lists all comments for a specific publication.        | `200 OK`: Comments returned. <br> `404 Not Found`: Comments not found. |

## Publication Endpoints

| Method   | Endpoint                                | Description                                                | Responses                          |
|----------|-----------------------------------------|------------------------------------------------------------|------------------------------------|
| `POST`   | `/v1/publication/create`                | Creates a new publication from a JSON object.               | `200 OK`: Publication created. <br> `400 Bad Request`: Invalid data. |
| `GET`    | `/v1/publication/get/{communityId}`      | Lists all publications for a specific community.            | `200 OK`: Publications returned. <br> `404 Not Found`: Publications not found. |
| `GET`    | `/v1/publication/get/{communityId}/{userId}` | Lists all publications from a specific user in a community. | `200 OK`: Publications returned. <br> `404 Not Found`: Publications not found. |
| `PATCH`  | `/v1/publication/like/{publicationId}/{userId}` | Likes a specific publication.                               | `200 OK`: Like added. <br> `404 Not Found`: Publication not found. |
| `PATCH`  | `/v1/publication/unlike/{publicationId}/{userId}` | Dislikes a specific publication.                            | `200 OK`: Dislike added. <br> `404 Not Found`: Publication not found. |

## Chat Endpoints

| Method   | Endpoint                                | Description                                                | Responses                          |
|----------|-----------------------------------------|------------------------------------------------------------|------------------------------------|
| `POST`   | `/v1/chat/create`                       | Creates a new chat from a JSON object.                      | `200 OK`: Chat created. <br> `400 Bad Request`: Invalid data. |
| `GET`    | `/v1/chat/get`                          | Lists all chats.                                            | `200 OK`: Chats returned. <br> `404 Not Found`: Chats not found. |
| `GET`    | `/v1/chat/get/{communityId}`            | Lists all chats for a specific community.                   | `200 OK`: Chats returned. <br> `404 Not Found`: Chats not found. |
| `GET`    | `/v1/chat/get/{communityId}/{userId}`   | Lists all chats for a specific user in a community.         | `200 OK`: Chats returned. <br> `404 Not Found`: Chats not found. |
| `PUT`    | `/v1/chat/add/{chatId}/{userId}`        | Adds a user to a specific chat.                             | `200 OK`: User added to chat. <br> `404 Not Found`: Chat or user not found. |
| `GET`    | `/v1/chat/get/{chatId}`                 | Retrieves a chat by its ID.                                 | `200 OK`: Chat returned. <br> `404 Not Found`: Chat not found. |

## Message Endpoints

| Method   | Endpoint                      | Description                                           | Responses                          |
|----------|-------------------------------|-------------------------------------------------------|------------------------------------|
| `POST`   | `/v1/message/create`           | Creates a new message from a JSON object.             | `200 OK`: Message created. <br> `400 Bad Request`: Invalid data. |
| `GET`    | `/v1/message/get/{chatId}`     | Lists all messages for a specific chat.               | `200 OK`: Messages returned. <br> `404 Not Found`: Messages not found. |

### Controller: AnnouncementController

| **Método** | **Endpoint** | **Descrição** | **Request Body** | **Respostas** |
| --- | --- | --- | --- | --- |
| **POST** | `/v1/announcement/create` | Cria um novo anúncio. | Objeto JSON do anúncio. | 200: Anúncio criado com sucesso<br>400: Dados inválidos |
| **GET** | `/v1/announcement/get/services/{communityId}` | Lista todos os anúncios filtrados por serviços de uma comunidade. | N/A | 200: Anúncios retornados com sucesso<br>404: Anúncios não encontrados |
| **GET** | `/v1/announcement/get/{communityId}/{userId}` | Lista todos os anúncios de um usuário específico dentro de uma comunidade. | N/A | 200: Anúncios retornados com sucesso<br>404: Anúncios não encontrados |
| **GET** | `/v1/announcement/get/sales/{communityId}` | Lista todos os anúncios de vendas dentro de uma comunidade. | N/A | 200: Anúncios retornados com sucesso<br>404: Anúncios não encontrados |
| **GET** | `/v1/announcement/get/donations/{communityId}` | Lista todos os anúncios de doações dentro de uma comunidade. | N/A | 200: Anúncios retornados com sucesso<br>404: Anúncios não encontrados |

## Models

### **Announcement ** (Anúncio)
```java
public class Announcement {
    @Id
    private ObjectId id;

    private String announcementId;
    @NotNull private String communityId;
    @NotNull private String senderId;
    private String senderName;
    @NotNull @Size(min = 1, max = 5) private List<String> images;
    @NotNull private String description;
    @NotNull private String type;
    private Date sentAt;
}
```

### **Publication** (Publicação)
```java
@Document(collection = "publications")
public class Publication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private String id;
    private String publicationId;
    @NotNull private String communityId;
    @NotNull private String senderId;
    private String senderName;
    private List<String> images;
    @NotNull @Max(value = 300) private String description;
    private List<String> likes;
}
```

### **Comment** (Comentário)
```java
@Document(collection = "comments")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private String id;
    private String commentId;
    @NotNull private String publicationId;
    @NotNull private String senderId;
    private String senderName;
    @NotNull private String content;
    private String senderImage;
}
```

### **Advertisement** (Propaganda)
```java
@Entity
@Table(name = "advertisement")
public class Advertisement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @NotNull private String title;
    @NotNull private String description;
    @NotNull private LocalDate divulgationDate;
    private Integer category;
    private String imageUrl;
    private Long userId;
    private Long idPlan;
}
```
### **Chat**
```java
@Document(collection = "chats")
public class Chat {
    @Id private ObjectId id;
    private String chatId;
    @NotNull private String communityId;
    @NotNull private List<String> usersIds;
    @NotNull private String chatName;
    @NotNull private List<String> chatOwners;
    @NotNull private String channelType;
    private Date createdAt;
}
```

### **Message** (Mensagem)
```java
@Document(collection = "Message")
public class Message {
    @Id private String id;
    private String messageId;
    @NotNull private String chatId;
    @NotNull private String senderId;
    @NotNull private String senderName;
    @NotNull @Max(value = 600) private String content;
    private Date sentAt;
}
```

## Tratamento Global de Exceções

A aplicação possui um **GlobalExceptionHandler** que lida com diferentes exceções que podem ocorrer durante a execução da API. As principais exceções tratadas são:

- **CustomNotFoundException**: Retorna um status `404` (Not Found) quando uma entidade solicitada não é encontrada.
- **CustomBadRequestException**: Retorna um status `400` (Bad Request) quando há problemas com os dados enviados na requisição.
- **IllegalArgumentException**: Lança um `400` (Bad Request) quando um argumento inválido é fornecido.
- **HttpServerErrorException**: Lança um `500` (Internal Server Error) para erros inesperados do servidor.
- **Exception**: Uma exceção genérica para capturar quaisquer outras situações inesperadas.

Cada exceção é manipulada com uma mensagem de erro apropriada, fornecendo informações úteis para o cliente da API.
