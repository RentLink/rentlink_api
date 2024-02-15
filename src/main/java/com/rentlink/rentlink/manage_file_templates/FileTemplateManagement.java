package com.rentlink.rentlink.manage_file_templates;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FileTemplateManagement implements FileTemplatesInternalAPI {

    @Override
    public Object generateFromTemplate(Map<String, String> dataContext, DocTemplate template) {
        String filePath = "/Users/szasiii/Projekty/rentlink/NEW/rentlink_api/src/main/resources/test_doc.docx";
        String finalPath = "/Users/szasiii/Projekty/rentlink/NEW/rentlink_api/src/main/resources/test_doc2.docx";
        POIFSFileSystem fs = null;
        try {
            FileInputStream fis = new FileInputStream(filePath);
            XWPFDocument doc = new XWPFDocument(fis);
            doc = replaceText(doc, "${current_city}", "Warsaw");
            doc = replaceText(doc, "${current_date}", "2024-01-01");
            saveWord(finalPath, doc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static XWPFDocument replaceText(XWPFDocument doc, String findText, String replaceText) {
        for (XWPFParagraph p : doc.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains(findText)) {
                        text = text.replace(findText, replaceText);
                        r.setText(text, 0);
                    }
                }
            }
        }
        for (XWPFTable tbl : doc.getTables()) {
            for (XWPFTableRow row : tbl.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            if (text != null && text.contains(findText)) {
                                text = text.replace(findText, replaceText);
                                r.setText(text, 0);
                            }
                        }
                    }
                }
            }
        }
        return doc;
    }

    private static void saveWord(String filePath, XWPFDocument doc) throws FileNotFoundException, IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath);
            doc.write(out);
        } finally {
            out.close();
        }
    }
}
