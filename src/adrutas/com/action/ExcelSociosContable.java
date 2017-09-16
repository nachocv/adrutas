package adrutas.com.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class ExcelSociosContable extends ActionSupport {
	private static final long serialVersionUID = 878672818194171342L;
	private static final String[] names = {"socio", "nombre", "apellido1", "apellido2", "domicilio", "codigo_postal",
		"poblacion", "provincia", "dni", "nacimiento", "cuota", "federación", "E-mail1", "Phone1", "Phone3", "Phone2"};

    public String execute() throws IOException {
    	Date nacimiento;
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Socios");
        Calendar cal = new GregorianCalendar();
        Map<String, Object> map = new HashMap<String, Object>();
        CellStyle style = wb.createCellStyle();
        style.setDataFormat(wb.createDataFormat().getFormat("dd/mm/yy;@"));
        map.put("lAnyo", ExcelSocios.getYears(cal));
        Cell cell;
        Row row = sheet.createRow(0);
        int fil = 1;
        int col = 0;
        HttpServletResponse res = ServletActionContext.getResponse();
        ServletOutputStream out = res.getOutputStream();
        res.setContentType("application/vnd.ms-excel");
        res.setHeader("Content-Disposition", "attachment;filename=contabilidad_socios_"
                        + Constante.dF1.format(cal.getTime()) + ".xls");
        for (String name: names) {
            cell = row.createCell(col++);
            cell.setCellValue(name);
        }
        for (Map<String, Object> bean: Persona.getFichas(map)) {
            row = sheet.createRow(fil++);
            col = 0;
            row.createCell(col++).setCellValue((Integer) bean.get("id_persona"));
            row.createCell(col++).setCellValue((String) bean.get("nombre"));
            row.createCell(col++).setCellValue((String) bean.get("apellido1"));
            row.createCell(col++).setCellValue((String) bean.get("apellido2"));
            row.createCell(col++).setCellValue((String) bean.get("domicilio"));
            row.createCell(col++).setCellValue((String) bean.get("codigo_postal"));
            row.createCell(col++).setCellValue((String) bean.get("poblacion"));
            row.createCell(col++).setCellValue((String) bean.get("provincia"));
            row.createCell(col++).setCellValue((String) bean.get("dni"));
            cell = row.createCell(col++);
            if ((nacimiento = (Date) bean.get("nacimiento"))!=null) {
                cell.setCellValue(nacimiento);
                cell.setCellStyle(style);
            }
            row.createCell(col++).setCellValue(((BigDecimal) bean.get("importecuota")).doubleValue());
            row.createCell(col++).setCellValue(((BigDecimal) bean.get("importelicencia")).doubleValue());
            row.createCell(col++).setCellValue((String) bean.get("E-mail1"));
            row.createCell(col++).setCellValue((String) bean.get("Phone1"));
            row.createCell(col++).setCellValue((String) bean.get("Phone2"));
            row.createCell(col++).setCellValue((String) bean.get("Phone3"));
        }
        try {
            wb.write(out);
        } finally {
            out.close();
        }
        return null;
	}
}
