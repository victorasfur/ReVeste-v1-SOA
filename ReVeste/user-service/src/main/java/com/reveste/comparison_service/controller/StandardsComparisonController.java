package com.reveste.comparison_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/comparison")
@Tag(name = "Standards Comparison", description = "APIs para comparação de padrões REST, SOAP, JSON, XML, WSDL, UDDI")
@CrossOrigin(origins = "*")
public class StandardsComparisonController {

    @GetMapping("/standards")
    @Operation(summary = "Comparar padrões", description = "Retorna uma comparação detalhada entre os principais padrões utilizados")
    public ResponseEntity<Map<String, Object>> compareStandards() {
        Map<String, Object> comparison = new HashMap<>();
        
        // REST
        Map<String, Object> rest = new HashMap<>();
        rest.put("name", "REST (Representational State Transfer)");
        rest.put("description", "Arquitetura para sistemas distribuídos baseada em HTTP");
        rest.put("advantages", new String[]{
            "Simplicidade e facilidade de uso",
            "Suporte nativo a HTTP",
            "Cacheable",
            "Stateless",
            "Interface uniforme"
        });
        rest.put("disadvantages", new String[]{
            "Limitado aos métodos HTTP",
            "Menos segurança built-in",
            "Não possui padrão para descoberta de serviços"
        });
        rest.put("use_cases", "APIs web, aplicações móveis, microsserviços");
        
        // SOAP
        Map<String, Object> soap = new HashMap<>();
        soap.put("name", "SOAP (Simple Object Access Protocol)");
        soap.put("description", "Protocolo para troca de informações estruturadas em ambientes distribuídos");
        soap.put("advantages", new String[]{
            "Segurança robusta (WS-Security)",
            "Transações ACID",
            "Padrões bem definidos",
            "Independente de protocolo",
            "Descoberta de serviços via UDDI"
        });
        soap.put("disadvantages", new String[]{
            "Complexidade maior",
            "Overhead de XML",
            "Performance inferior ao REST",
            "Curva de aprendizado mais alta"
        });
        soap.put("use_cases", "Sistemas empresariais, transações financeiras, integração B2B");
        
        // JSON
        Map<String, Object> json = new HashMap<>();
        json.put("name", "JSON (JavaScript Object Notation)");
        json.put("description", "Formato leve de intercâmbio de dados");
        json.put("advantages", new String[]{
            "Leve e compacto",
            "Fácil de ler e escrever",
            "Suporte nativo em JavaScript",
            "Parsing rápido",
            "Amplamente suportado"
        });
        json.put("disadvantages", new String[]{
            "Menos expressivo que XML",
            "Sem validação de schema nativa",
            "Limitado a tipos de dados básicos"
        });
        json.put("use_cases", "APIs REST, aplicações web, configurações");
        
        // XML
        Map<String, Object> xml = new HashMap<>();
        xml.put("name", "XML (eXtensible Markup Language)");
        xml.put("description", "Linguagem de marcação para documentos estruturados");
        xml.put("advantages", new String[]{
            "Altamente estruturado",
            "Validação via XSD/DTD",
            "Suporte a namespaces",
            "Transformações via XSLT",
            "Padrão maduro e estável"
        });
        xml.put("disadvantages", new String[]{
            "Verboso e pesado",
            "Parsing mais lento",
            "Complexidade maior",
            "Maior uso de bandwidth"
        });
        xml.put("use_cases", "SOAP, configurações complexas, documentos estruturados");
        
        // WSDL
        Map<String, Object> wsdl = new HashMap<>();
        wsdl.put("name", "WSDL (Web Services Description Language)");
        wsdl.put("description", "Linguagem para descrever serviços web");
        wsdl.put("advantages", new String[]{
            "Descrição completa do serviço",
            "Geração automática de código",
            "Contratos bem definidos",
            "Descoberta de serviços",
            "Validação de interface"
        });
        wsdl.put("disadvantages", new String[]{
            "Complexidade alta",
            "Acoplamento forte",
            "Específico para SOAP",
            "Curva de aprendizado"
        });
        wsdl.put("use_cases", "Serviços SOAP, integração empresarial, contratos de serviço");
        
        // UDDI
        Map<String, Object> uddi = new HashMap<>();
        uddi.put("name", "UDDI (Universal Description, Discovery and Integration)");
        uddi.put("description", "Diretório para descoberta de serviços web");
        uddi.put("advantages", new String[]{
            "Descoberta automática de serviços",
            "Registro centralizado",
            "Metadados ricos",
            "Classificação de serviços",
            "Padrão da indústria"
        });
        uddi.put("disadvantages", new String[]{
            "Complexidade de implementação",
            "Overhead de manutenção",
            "Pouco usado na prática",
            "Alternativas mais simples disponíveis"
        });
        uddi.put("use_cases", "Descoberta de serviços, registros corporativos, SOA");
        
        comparison.put("REST", rest);
        comparison.put("SOAP", soap);
        comparison.put("JSON", json);
        comparison.put("XML", xml);
        comparison.put("WSDL", wsdl);
        comparison.put("UDDI", uddi);
        
        Map<String, String> summary = new HashMap<>();
        summary.put("recommendation", "Para aplicações modernas, recomenda-se REST com JSON para simplicidade e performance. SOAP com XML ainda é relevante para sistemas empresariais que requerem segurança robusta e transações complexas.");
        summary.put("trend", "REST/JSON dominam o mercado atual, enquanto SOAP/XML mantêm relevância em contextos específicos.");
        
        comparison.put("summary", summary);
        
        return ResponseEntity.ok(comparison);
    }

    @GetMapping("/rest-vs-soap")
    @Operation(summary = "REST vs SOAP", description = "Comparação detalhada entre REST e SOAP")
    public ResponseEntity<Map<String, Object>> compareRestVsSoap() {
        Map<String, Object> comparison = new HashMap<>();
        
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("Performance", "REST: Melhor (menos overhead) | SOAP: Menor (mais overhead XML)");
        criteria.put("Simplicidade", "REST: Muito simples | SOAP: Complexo");
        criteria.put("Segurança", "REST: Básica (HTTPS) | SOAP: Robusta (WS-Security)");
        criteria.put("Transações", "REST: Limitadas | SOAP: Completas (ACID)");
        criteria.put("Caching", "REST: Nativo | SOAP: Limitado");
        criteria.put("Descoberta", "REST: Manual | SOAP: Automática (UDDI)");
        criteria.put("Padrões", "REST: Poucos | SOAP: Muitos e bem definidos");
        criteria.put("Adoção", "REST: Muito alta | SOAP: Moderada");
        
        comparison.put("criteria", criteria);
        comparison.put("conclusion", "REST é preferível para APIs públicas e aplicações web modernas. SOAP é melhor para sistemas empresariais com requisitos rigorosos de segurança e transações.");
        
        return ResponseEntity.ok(comparison);
    }

    @GetMapping("/json-vs-xml")
    @Operation(summary = "JSON vs XML", description = "Comparação detalhada entre JSON e XML")
    public ResponseEntity<Map<String, Object>> compareJsonVsXml() {
        Map<String, Object> comparison = new HashMap<>();
        
        Map<String, Object> criteria = new HashMap<>();
        criteria.put("Tamanho", "JSON: Menor (menos verboso) | XML: Maior (mais verboso)");
        criteria.put("Parsing", "JSON: Mais rápido | XML: Mais lento");
        criteria.put("Legibilidade", "JSON: Boa | XML: Boa");
        criteria.put("Validação", "JSON: Schema JSON | XML: XSD/DTD (mais robusto)");
        criteria.put("Suporte a tipos", "JSON: Limitado | XML: Extensivo");
        criteria.put("Namespaces", "JSON: Não suporta | XML: Suporte completo");
        criteria.put("Comentários", "JSON: Não suporta | XML: Suporta");
        criteria.put("Transformações", "JSON: Limitadas | XML: XSLT");
        
        comparison.put("criteria", criteria);
        comparison.put("conclusion", "JSON é ideal para APIs REST e aplicações web. XML é melhor para documentos complexos e quando validação rigorosa é necessária.");
        
        return ResponseEntity.ok(comparison);
    }
}

