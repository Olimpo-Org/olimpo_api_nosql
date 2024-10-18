package com.example.olimpo_api_nosql.controller.negotiationFlow;

import com.example.olimpo_api_nosql.exception.ExceptionThrower;
import com.example.olimpo_api_nosql.model.mongo.Announcement;
import com.example.olimpo_api_nosql.service.negotiationFlow.AnnouncementService;
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
@Tag(name = "Announcement", description = "Endpoints de anuncio")
@RequestMapping("/v1/announcement")
public class AnnouncementController {
    private final AnnouncementService announcementService;
    @Autowired
    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @Operation(summary = "Criar novo anuncio", description = "Endpoint cria uma nova a partir de um objeto JSON")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anuncio criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping("/create")
    public ResponseEntity<Announcement> create(
            @Parameter(description = "Anuncio a ser criado")
            @Valid @RequestBody Announcement announcement, BindingResult result
    ) {
        if (result.hasErrors()) {
            ExceptionThrower.throwBadRequestException(result.getAllErrors().get(0).getDefaultMessage());
        }
        Announcement createdAnnouncement = announcementService.create(announcement);
        return ResponseEntity.ok().body(createdAnnouncement);
    }

    @Operation(
            summary = "Listar todos os servicos de uma comunidade",
            description = "Endpoint lista todos os anuncios filtrados por servicos"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anuncios retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Anuncios não encontrados")
    })
    @GetMapping("/get/services/{communityId}")
    public ResponseEntity<List<Announcement>> getAllServices(
            @PathVariable("communityId") String communityId
    ) {
        List<Announcement> announcements = announcementService.getAllServices(communityId);
        return ResponseEntity.ok().body(announcements);
    }


    @Operation(
            summary = "Listar todos os anuncios de um usuário",
            description = "Endpoint lista todos os anuncios filtrados por um usuário"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anuncios retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Anuncios não encontrados")
    })
    @GetMapping("/get/{communityId}/{userId}")
    public ResponseEntity<List<Announcement>> getAllOfUser(
            @PathVariable("communityId") String communityId,
            @PathVariable("userId") String userId
    ) {
        List<Announcement> announcements = announcementService.getAllOfUser(communityId, userId);
        return ResponseEntity.ok().body(announcements);
    }

    @Operation(
            summary = "Listar todos os vendas de uma comunidade",
            description = "Endpoint lista todos os anuncios filtrados por vendas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anuncios retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Anuncios não encontrados")
    })
    @GetMapping("/get/sales/{communityId}")
    public ResponseEntity<List<Announcement>> getAllSales(
            @PathVariable("communityId") String communityId
    ) {
        List<Announcement> announcements = announcementService.getAllSales(communityId);
        return ResponseEntity.ok().body(announcements);
    }

    @Operation(
            summary = "Listar todos as doações de uma comunidade",
            description = "Endpoint lista todos os anuncios filtrados por doações"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anuncios retornados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Anuncios não encontrados")
    })
    @GetMapping("/get/donations/{communityId}")
    public ResponseEntity<List<Announcement>> getAllDonations(
            @PathVariable("communityId") String communityId
    ) {
        List<Announcement> announcements = announcementService.getAllDonations(communityId);
        return ResponseEntity.ok().body(announcements);
    }

//    @Operation(summary = "Listar todos os anuncios de uma comunidade", description = "Endpoint lista todos os anuncios")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Anuncios retornados com sucesso"),
//            @ApiResponse(responseCode = "404", description = "Anuncios não encontrados")
//    })
//    @GetMapping("/get/{communityId}")
//    public ResponseEntity<String> getAllOfCommunity(
//            @Parameter(description = "Id da comunidade")
//            @PathVariable("communityId") String communityId
//    ) {
//        List<Announcement> announcements = announcementService.getAllOfCommunity(communityId);
//        return ResponseEntity.ok().body(gsonUtils.toJson(announcements));
//    }
}
