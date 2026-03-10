package com.gestaoigrejaemcelula.demo.aplication.service;



import com.gestaoigrejaemcelula.demo.aplication.dto.RelatorioResponseDTO;

import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

// keep your other imports:
import com.gestaoigrejaemcelula.demo.aplication.dto.RelatorioResponseDTO;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class RelatorioPdfService {

    public byte[] gerarPdf(List<RelatorioResponseDTO> relatorios) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("RELATÓRIO DE CÉLULAS - IGREJA QUADRANGULAR")
                    .setBold()
                    .setFontSize(18));

            // Espaço
            document.add(new Paragraph(" "));

            // Tabela: Célula | Membros | Visitantes | Total
            Table table = new Table(UnitValue.createPercentArray(new float[]{4, 2, 2, 2}));
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell("Célula");
            table.addHeaderCell("Nome do LIder");
            table.addHeaderCell("Membros");
            table.addHeaderCell("Visitantes");
            table.addHeaderCell("Total");

            for (RelatorioResponseDTO r : relatorios) {

                int membros = r.getMembrosPresentes() != null
                        ? r.getMembrosPresentes().size()
                        : 0;

                int visitantes = r.getVisitantesPresentes() != null
                        ? r.getVisitantesPresentes().size()
                        : 0;

                int total = membros + visitantes;

                table.addCell(r.getNomeCelula() != null ? r.getNomeCelula() : "---");
                table.addCell(r.getNomeLider() !=null ? r.getNomeLider():"-----");
                table.addCell(String.valueOf(membros));
                table.addCell(String.valueOf(visitantes));
                table.addCell(String.valueOf(total));
            }

            document.add(table);
            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }

        return out.toByteArray();
    }
}