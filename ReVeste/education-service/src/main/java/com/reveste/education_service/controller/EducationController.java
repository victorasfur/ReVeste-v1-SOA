package com.reveste.education_service.controller;

import com.reveste.education_service.dto.EducationalContentDTO;
import com.reveste.education_service.model.EducationalContent;
import com.reveste.education_service.service.EducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
@Tag(name = "Education", description = "APIs para conteúdo educativo e chatbot")
@CrossOrigin(origins = "*")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @GetMapping("/content")
    @Operation(summary = "Listar todo o conteúdo", description = "Retorna uma lista de todo o conteúdo educativo")
    public ResponseEntity<List<EducationalContentDTO>> getAllContent() {
        List<EducationalContentDTO> content = educationService.getAllContent();
        return ResponseEntity.ok(content);
    }

    @GetMapping("/content/active")
    @Operation(summary = "Listar conteúdo ativo", description = "Retorna uma lista do conteúdo educativo ativo")
    public ResponseEntity<List<EducationalContentDTO>> getActiveContent() {
        List<EducationalContentDTO> content = educationService.getActiveContent();
        return ResponseEntity.ok(content);
    }

    @GetMapping("/content/category/{category}")
    @Operation(summary = "Buscar conteúdo por categoria", description = "Retorna conteúdo de uma categoria específica")
    public ResponseEntity<List<EducationalContentDTO>> getContentByCategory(@PathVariable String category) {
        List<EducationalContentDTO> content = educationService.getContentByCategory(category);
        return ResponseEntity.ok(content);
    }

    @GetMapping("/content/difficulty/{difficulty}")
    @Operation(summary = "Buscar conteúdo por dificuldade", description = "Retorna conteúdo de uma dificuldade específica")
    public ResponseEntity<List<EducationalContentDTO>> getContentByDifficulty(@PathVariable String difficulty) {
        List<EducationalContentDTO> content = educationService.getContentByDifficulty(difficulty);
        return ResponseEntity.ok(content);
    }

    @GetMapping("/content/{id}")
    @Operation(summary = "Buscar conteúdo por ID", description = "Retorna um conteúdo específico pelo ID")
    public ResponseEntity<EducationalContentDTO> getContentById(@PathVariable Long id) {
        EducationalContentDTO content = educationService.getContentById(id);
        if (content != null) {
            return ResponseEntity.ok(content);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/content")
    @Operation(summary = "Criar novo conteúdo", description = "Cria um novo conteúdo educativo")
    public ResponseEntity<EducationalContentDTO> createContent(@RequestBody EducationalContent content) {
        EducationalContentDTO createdContent = educationService.createContent(content);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContent);
    }

    @PutMapping("/content/{id}")
    @Operation(summary = "Atualizar conteúdo", description = "Atualiza os dados de um conteúdo existente")
    public ResponseEntity<EducationalContentDTO> updateContent(@PathVariable Long id, @RequestBody EducationalContent contentDetails) {
        EducationalContentDTO updatedContent = educationService.updateContent(id, contentDetails);
        if (updatedContent != null) {
            return ResponseEntity.ok(updatedContent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/content/{id}")
    @Operation(summary = "Deletar conteúdo", description = "Remove um conteúdo do sistema")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        boolean deleted = educationService.deleteContent(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/initialize")
    @Operation(summary = "Inicializar conteúdo padrão", description = "Inicializa o sistema com conteúdo educativo padrão")
    public ResponseEntity<String> initializeDefaultContent() {
        educationService.initializeDefaultContent();
        return ResponseEntity.ok("Conteúdo padrão inicializado com sucesso");
    }
}

