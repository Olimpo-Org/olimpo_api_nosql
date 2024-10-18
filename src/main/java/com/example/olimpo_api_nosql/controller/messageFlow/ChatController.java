package com.example.olimpo_api_nosql.controller.messageFlow;

import com.example.olimpo_api_nosql.exception.ExceptionThrower;
import com.example.olimpo_api_nosql.model.mongo.Chat;
import com.example.olimpo_api_nosql.service.messageFlow.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Chat", description = "Endpoints de chat")
@RequestMapping("/v1/chat")
public class ChatController{
    private final ChatService chatService;
    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @Operation(summary = "Criar novo Chat", description = "Endpoint cria uma nova a partir de um objeto JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chat criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping("/create")
    public ResponseEntity<Chat> create(
            @Parameter(description = "Chat a ser criado")
            @Valid @RequestBody Chat chat, BindingResult result) {
        System.out.println(chat.toString());
        if (result.hasErrors()) {
            ExceptionThrower.throwBadRequestException(result.getAllErrors().get(0).getDefaultMessage());
        }
        Chat createdChat = chatService.create(chat);
        return ResponseEntity.ok().body(createdChat);
    }

    @Operation(summary = "Listar todos os Chats", description = "Endpoint lista todos os chats")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chats retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chats não encontrados")
    })
    @GetMapping("/get")
    public ResponseEntity<List<Chat>> getAll() {
        List<Chat> chats = chatService.getAll();
        return ResponseEntity.ok().body(chats);
    }

    @Operation(summary = "Listar todos os Chats de uma comunidade", description = "Endpoint lista todos os chats de uma comunidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chats retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chats não encontrados")
    })
    @GetMapping("/get/{communityId}")
    public ResponseEntity<List<Chat>> getAllOfCommunity(
            @Parameter(description = "ID da comunidade")
            @PathVariable("communityId") String communityId
    ) {
        List<Chat> chats = chatService.getAllByCommunityId(communityId);
        if (chats.isEmpty()) {
            ExceptionThrower.throwNotFoundException("Chats not found");
        } else if (communityId == null) {
            ExceptionThrower.throwBadRequestException("Community id must be not null");
        }
        return ResponseEntity.ok().body(chats);
    }

    @Operation(summary = "Listar todos os Chats de um usuário", description = "Endpoint lista todos os chats de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chats retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chats não encontrados")
    })
    @GetMapping("/get/{communityId}/{userId}")
    public ResponseEntity<List<Chat>> getAllOfCommunityByUser(
            @Parameter(description = "ID da comunidade")
            @PathVariable("communityId") String communityId,
            @Parameter(description = "ID do usuário")
            @PathVariable("userId") String userId
    ) {
        List<Chat> chats = chatService.getAllByUserId(userId, communityId);
        if (chats.isEmpty()) {
            ExceptionThrower.throwNotFoundException("Chats not found fot this user");
        } else if (communityId == null) {
            ExceptionThrower.throwBadRequestException("Community id must be not null");
        }
        return ResponseEntity.ok().body(chats);
    }

    @Operation(summary = "Adicionar um usuário a um chat", description = "Endpoint adiciona um usuário a um chat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário adicionado ao chat com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário ou chat não encontrado")
    })
    @PutMapping("/add/{chatId}/{userId}")
    public ResponseEntity<Chat> addUserToChat(
            @Parameter(description = "ID do chat")
            @PathVariable("chatId") String chatId,
            @Parameter(description = "ID do usuário")
            @PathVariable("userId") String userId
    ) {
        Chat chat = chatService.addUserToChat(chatId, userId);
        return ResponseEntity.ok().body(chat);
    }

    @Operation(summary = "Listar um Chat pelo ID", description = "Endpoint lista um chat pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chat retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chat não encontrado")
    })
    @GetMapping("/get/{chatId}")
    public ResponseEntity<Chat> getChat(
            @Parameter(description = "ID do chat")
            @PathVariable("chatId") String chatId) {
        Chat chat = chatService.getChatById(chatId);
        if (chat == null) {
            ExceptionThrower.throwNotFoundException("Chat not found");
        }
        return ResponseEntity.ok().body(chat);
    }
}
