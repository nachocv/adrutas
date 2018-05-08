package adrutas.com.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class ExcelGmail extends ActionSupport {
    private static final long serialVersionUID = 1243148523536604288L;

    public String execute() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Socios");
        Calendar cal = new GregorianCalendar();
        int anyo = cal.get(Calendar.YEAR);
        List<Map<String, Object>> reg = Persona.getDatosGMail(anyo);
        Row row = sheet.createRow(0);
        int fil = 1;
        int col = 0;
        HttpServletResponse res = ServletActionContext.getResponse();
        ServletOutputStream out = res.getOutputStream();
        res.setContentType("application/vnd.ms-excel");
        res.setHeader("Content-Disposition", "attachment;filename=google_" + Constante.dF1.format(cal.getTime()) + ".xls");
        row.createCell(col++).setCellValue("Name");
        row.createCell(col++).setCellValue("Given Name");
        row.createCell(col++).setCellValue("Family Name");
        row.createCell(col++).setCellValue("Birthday");
        row.createCell(col++).setCellValue("Group Membership");
        row.createCell(col++).setCellValue("E-mail 1 - Value");
        row.createCell(col++).setCellValue("E-mail 2 - Value");
        row.createCell(col++).setCellValue("E-mail 3 - Value");
        row.createCell(col++).setCellValue("Phone 1 - Value");
        row.createCell(col++).setCellValue("Phone 2 - Value");
        row.createCell(col++).setCellValue("Phone 3 - Value");
        row.createCell(col++).setCellValue("Address 1 - Street");
        row.createCell(col++).setCellValue("Address 1 - City");
        row.createCell(col++).setCellValue("Address 1 - Postal Code");
        for (Map<String, Object> bean: reg) {
            row = sheet.createRow(fil++);
            col = 0;
            row.createCell(col++).setCellValue((String) bean.get("Name"));
            row.createCell(col++).setCellValue((String) bean.get("Given Name"));
            row.createCell(col++).setCellValue((String) bean.get("Family Name"));
            row.createCell(col++).setCellValue((String) bean.get("Birthday"));
            row.createCell(col++).setCellValue((String) bean.get("Group Membership"));
            row.createCell(col++).setCellValue((String) bean.get("E-mail 1 - Value"));
            row.createCell(col++).setCellValue((String) bean.get("E-mail 2 - Value"));
            row.createCell(col++).setCellValue((String) bean.get("E-mail 3 - Value"));
            row.createCell(col++).setCellValue((String) bean.get("Phone 1 - Value"));
            row.createCell(col++).setCellValue((String) bean.get("Phone 2 - Value"));
            row.createCell(col++).setCellValue((String) bean.get("Phone 3 - Value"));
            row.createCell(col++).setCellValue((String) bean.get("Address 1 - Street"));
            row.createCell(col++).setCellValue((String) bean.get("Address 1 - City"));
            row.createCell(col++).setCellValue((String) bean.get("Address 1 - Postal Code"));
        }
        try {
            workbook.write(out);
        } finally {
            out.close();
        }
        return null;
	}
}
