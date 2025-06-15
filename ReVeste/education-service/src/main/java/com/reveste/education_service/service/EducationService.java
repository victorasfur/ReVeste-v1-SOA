package com.reveste.education_service.service;

import com.reveste.education_service.dto.EducationalContentDTO;
import com.reveste.education_service.model.EducationalContent;
import com.reveste.education_service.repository.EducationalContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EducationService {

    @Autowired
    private EducationalContentRepository contentRepository;

    public List<EducationalContentDTO> getAllContent() {
        return contentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EducationalContentDTO> getActiveContent() {
        return contentRepository.findByIsActive(true).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EducationalContentDTO> getContentByCategory(String category) {
        return contentRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<EducationalContentDTO> getContentByDifficulty(String difficulty) {
        return contentRepository.findByDifficulty(difficulty).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EducationalContentDTO getContentById(Long id) {
        EducationalContent content = contentRepository.findById(id).orElse(null);
        return content != null ? convertToDTO(content) : null;
    }

    public EducationalContentDTO createContent(EducationalContent content) {
        content.setCreatedDate(LocalDateTime.now());
        content.setIsActive(true);
        EducationalContent savedContent = contentRepository.save(content);
        return convertToDTO(savedContent);
    }

    public EducationalContentDTO updateContent(Long id, EducationalContent contentDetails) {
        EducationalContent content = contentRepository.findById(id).orElse(null);
        if (content != null) {
            content.setTitle(contentDetails.getTitle());
            content.setContent(contentDetails.getContent());
            content.setCategory(contentDetails.getCategory());
            content.setDifficulty(contentDetails.getDifficulty());
            content.setEstimatedReadTime(contentDetails.getEstimatedReadTime());
            content.setIsActive(contentDetails.getIsActive());
            EducationalContent updatedContent = contentRepository.save(content);
            return convertToDTO(updatedContent);
        }
        return null;
    }

    public boolean deleteContent(Long id) {
        if (contentRepository.existsById(id)) {
            contentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void initializeDefaultContent() {
        if (contentRepository.count() == 0) {
            createDefaultContent();
        }
    }

    private void createDefaultContent() {
        EducationalContent content1 = new EducationalContent();
        content1.setTitle("Introdução aos Investimentos");
        content1.setContent("Aprenda os conceitos básicos sobre investimentos e como começar a investir seu dinheiro de forma inteligente.");
        content1.setCategory("INVESTIMENTOS");
        content1.setDifficulty("BEGINNER");
        content1.setEstimatedReadTime(5);
        createContent(content1);

        EducationalContent content2 = new EducationalContent();
        content2.setTitle("Juros Compostos: O Poder do Tempo");
        content2.setContent("Entenda como os juros compostos podem multiplicar seu dinheiro ao longo do tempo e por que Einstein os chamou de 'oitava maravilha do mundo'.");
        content2.setCategory("EDUCACAO_FINANCEIRA");
        content2.setDifficulty("INTERMEDIATE");
        content2.setEstimatedReadTime(8);
        createContent(content2);

        EducationalContent content3 = new EducationalContent();
        content3.setTitle("Os Riscos das Apostas Online");
        content3.setContent("Conheça os riscos psicológicos e financeiros das apostas online e como elas podem impactar negativamente sua vida financeira.");
        content3.setCategory("PREVENCAO");
        content3.setDifficulty("BEGINNER");
        content3.setEstimatedReadTime(6);
        createContent(content3);
    }

    private EducationalContentDTO convertToDTO(EducationalContent content) {
        EducationalContentDTO dto = new EducationalContentDTO();
        dto.setId(content.getId());
        dto.setTitle(content.getTitle());
        dto.setContent(content.getContent());
        dto.setCategory(content.getCategory());
        dto.setDifficulty(content.getDifficulty());
        dto.setEstimatedReadTime(content.getEstimatedReadTime());
        dto.setCreatedDate(content.getCreatedDate());
        dto.setIsActive(content.getIsActive());
        return dto;
    }
}

