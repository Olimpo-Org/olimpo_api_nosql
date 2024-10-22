package com.example.olimpo_api_nosql.controller.feedFlow;

import com.example.olimpo_api_nosql.exception.ExceptionThrower;
import com.example.olimpo_api_nosql.model.mongo.Publication;
import com.example.olimpo_api_nosql.service.feedFlow.PublicationService;
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
@RequestMapping("/v1/publication")
@Tag(name = "Publication", description = "Endpoints de publicação")
public class PublicationController {
    private final PublicationService publicationService;
    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @Operation(summary = "Criar nova publicação" , description = "Endpoint que cria uma nova a partir de um objeto JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publicação criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping("/create")
    public ResponseEntity<Publication> create(
            @Parameter(description = "Publicação a ser criada")
            @RequestBody Publication publication, BindingResult result) {
        if (result.hasErrors()) {
            ExceptionThrower.throwBadRequestException(result.getAllErrors().get(0).getDefaultMessage());
        }
        Publication createdPublication = publicationService.create(publication);
        return ResponseEntity.ok().body(createdPublication);
    }

    @Operation(summary = "Listar todas as publicações de uma comunidade", description = "Endpoint lista todas as publicações de uma comunidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de uma lista de publicações"),
            @ApiResponse(responseCode = "404", description = "Publicações não encontradas")
    })
    @GetMapping("/get/{communityId}")
    public ResponseEntity<List<Object>> getAllOfCommunity(
            @Parameter(description = "Id da comunidade")
            @PathVariable("communityId") String communityId
    ) {
        List<Object> publications = publicationService.getAllOfCommunity(communityId);
        return ResponseEntity.ok().body(publications);
    }

    @Operation(summary = "Listar todas as publicações de um usuário", description = "Endpoint lista todas as publicações de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorno de uma lista de publicações"),
            @ApiResponse(responseCode = "404", description = "Publicações não encontradas")
    })
    @GetMapping("/get/{communityId}/{userId}")
    public ResponseEntity<List<Publication>> getAllOfUser(
            @Parameter(description = "Id da comunidade")
            @PathVariable("communityId") String communityId,
            @Parameter(description = "Id do usuário")
            @PathVariable("userId") String userId
    ) {
        List<Publication> publications = publicationService.getAllOfUser(communityId, userId);
        return ResponseEntity.ok().body(publications);
    }

    @Operation(summary = "Dar like uma publicação", description = "Endpoint que faz o like de uma publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Like realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    })
    @PatchMapping("/like/{publicationId}/{userId}")
    public ResponseEntity<List<String>> likePublication(
            @Parameter(description = "Id da publicação")
            @PathVariable("publicationId") String publicationId,
            @Parameter(description = "Id do usuário")
            @PathVariable("userId") String userId
    ) {
        List<String> likes = publicationService.likePublication(publicationId, userId);
        return ResponseEntity.ok().body(likes);
    }

    @Operation(summary = "Dar dislike uma publicação", description = "Endpoint que faz o dislike de uma publicação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dislike realizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Publicação não encontrada")
    })
    @PatchMapping ("/unlike/{publicationId}/{userId}")
    public ResponseEntity<List<String>> unlikePublication(
            @Parameter(description = "Id da publicação")
            @PathVariable("publicationId") String publicationId,
            @Parameter(description = "Id do usuário")
            @PathVariable("userId") String userId
    ) {
        List<String> likes = publicationService.unlikePublication(publicationId, userId);
        return ResponseEntity.ok().body(likes);
    }
}
