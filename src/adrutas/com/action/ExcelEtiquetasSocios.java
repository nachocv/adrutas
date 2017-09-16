package adrutas.com.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class ExcelEtiquetasSocios extends ActionSupport {
    private static final long serialVersionUID = -3975568617513321781L;

    private boolean no_email;

    public void setNo_email(boolean no_email) {
        this.no_email = no_email;
    }

    public String execute() throws IOException {
        Calendar cal = new GregorianCalendar();
        int fil = 1;
        HttpServletResponse res = ServletActionContext.getResponse();
        ServletOutputStream out = res.getOutputStream();
        String parameter1;
        String parameter2;
        Map<String, Object> map = new HashMap<String, Object>();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Socios");
        HSSFRow row = sheet.createRow(0);
        res.setContentType("application/vnd.ms-excel");
        res.setHeader("Content-Disposition", "attachment;filename=etiquetas_socios_"
                        + Constante.dF1.format(cal.getTime()) + ".xls");
        row.createCell(0).setCellValue("linea1");
        row.createCell(1).setCellValue("linea2");
        row.createCell(2).setCellValue("linea3");
        map.put("lAnyo", ExcelSocios.getYears(cal));
        map.put("no_email", no_email);
        for (Map<String, Object> bean: Persona.getSocios(map)) {
            (row = sheet.createRow(fil++)).createCell(0).setCellValue(bean.get("nombre") + ((parameter1 = (String)
                            bean.get("apellido1"))==null? "": (" " + parameter1)) + ((parameter1 = (String)
                            bean.get("apellido2"))==null? "": (" " + parameter1)));
            row.createCell(1).setCellValue((String) bean.get("domicilio"));
            row.createCell(2).setCellValue(((parameter1 = (String) bean.get("codigo_postal"))==null? "":
                (parameter1 + "-")) + ((parameter1 = (parameter1 = (String) bean.get("poblacion"))==null? "":
                parameter1).equalsIgnoreCase(((parameter2 = (parameter2 = (String) bean.get("provincia"))==null? "":
                parameter2)))? parameter1: (parameter1 + " (" + parameter2) + ")"));
        }
        try {
            workbook.write(out);
        } finally {
            out.close();
        }
        return null;
	}
}
