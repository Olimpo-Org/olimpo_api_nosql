package com.example.olimpo_api_nosql.controller.feedFlow;

import com.example.olimpo_api_nosql.exception.ExceptionThrower;
import com.example.olimpo_api_nosql.model.Comment;
import com.example.olimpo_api_nosql.service.feedFlow.CommentService;
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
@RequestMapping("/v1/comment")
@Tag(name = "Comments", description = "Endpoints de comentário")
public class CommentController {
    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Criar novo Comentário", description = "Endpoint cria uma nova a partir de um objeto JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping("/create")
    public ResponseEntity<Comment> create(
            @Parameter(description = "Comentário a ser criado")
            @RequestBody Comment comment, BindingResult result) {
        if (result.hasErrors()) {
            ExceptionThrower.throwBadRequestException(result.getAllErrors().get(0).getDefaultMessage());
        }
        Comment createdComment = commentService.create(comment);
        return ResponseEntity.ok().body(createdComment);
    }

    @Operation(summary = "Listar todos os comentários de uma publicação", description = "Endpoint lista todos os comentários de uma publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comentários retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Comentários não encontrados")
    })
    @GetMapping("/get/{publicationId}")
    public ResponseEntity<List<Comment>> getAllOfPublication(
            @Parameter(description = "Id da publicação")
            @PathVariable("publicationId") String publicationId
    ) {
        List<Comment> comments = commentService.getAllOfPublication(publicationId);
        return ResponseEntity.ok().body(comments);
    }
}
