package com.example.olimpo_api_nosql.controller.messageFlow;

import com.example.olimpo_api_nosql.exception.ExceptionThrower;
import com.example.olimpo_api_nosql.model.mongo.Message;
import com.example.olimpo_api_nosql.service.messageFlow.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Message", description = "Endpoints de mensagem")
@RequestMapping("/v1/message")
public class MessageController {
    private final MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @Operation(summary = "Criar nova mensagem", description = "Endpoint cria uma nova a partir de um objeto JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping("/create")
    public ResponseEntity<Message> create(
            @Parameter(description = "Mensagem a ser criada")
            @RequestBody Message message, BindingResult result
    ) {
        if (result.hasErrors()) {
            ExceptionThrower.throwBadRequestException(result.getAllErrors().get(0).getDefaultMessage());
        }
        Message createdMessage = messageService.sendMessage(message);
        return ResponseEntity.ok().body(createdMessage);
    }

    @Operation(summary = "Listar todas as mensagens de um chat", description = "Endpoint lista todas as mensagens de um chat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de uma lista de mensagens"),
            @ApiResponse(responseCode = "404", description = "Mensagens não encontradas")
    })
    @GetMapping("/get/{chatId}")
    public ResponseEntity<List<Message>> getAll(
            @Parameter(description = "Id do chat")
            @PathVariable("chatId") String chatId
    ) {
        List<Message> messages = messageService.getAllMessagesOfAChat(chatId);
        return ResponseEntity.ok().body(messages);
    }

    @Operation(summary = "Deletar uma mensagem", description = "Endpoint deleta uma mensagem")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Mensagem não encontrada")
    })
    @DeleteMapping("/delete/{messageId}")
    public ResponseEntity<Message> delete(
            @Parameter(description = "Id da mensagem")
            @PathVariable("messageId") String messageId
    ) {
        Message deletedMessage = messageService.deleteMessage(messageId);
        return ResponseEntity.ok().body(deletedMessage);
    }
}
