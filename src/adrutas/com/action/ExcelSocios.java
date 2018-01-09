package adrutas.com.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

public class ExcelSocios extends ActionSupport {
    private static final long serialVersionUID = 1243148523536604288L;
    private static final String[] names = {"id_persona","socio","usuario","idPerfil","password","nombre","apellido1",
        "apellido2","domicilio","codigo_postal","poblacion","provincia","tipoIdentificacion","dni","nota","correo",
        "ingreso","nacimiento","sexo","DEUDA","BAJA","FECHA BAJA","JD","Poseconta","pz_castilla",
        "GratisNS","GratisExtras","Gratisnumsalidas","vetado","veto","pasaporte","cad_pasaporte","Talla",
        "GratisFotos","Colaborador","num_salidas","E-mail1","E-mail2","E-mail3","Phone1","Phone3",
        "Phone2","importelicencia","importecuota","tipo_licencia","id_recibo","fp","fecha","adulto",
        "club","anyo"};

    public static Integer getYears(Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        if (cal.get(Calendar.MONTH)<2) {
            return year-1;
        }
        return year;
    }
    public String execute() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Socios");
        Calendar cal = new GregorianCalendar();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("anyo", getYears(cal));
        List<Map<String, Object>> reg = Persona.getFichas(map);
        Cell cell;
        Row row = sheet.createRow(0);
        int fil = 1;
        int col = 0;
        HttpServletResponse res = ServletActionContext.getResponse();
        ServletOutputStream out = res.getOutputStream();
        Object obj;
        res.setContentType("application/vnd.ms-excel");
        res.setHeader("Content-Disposition", "attachment;filename=datos_socios_"
                        + Constante.dF1.format(cal.getTime()) + ".xls");
        for (String name: names) {
            cell = row.createCell(col++);
            cell.setCellValue(name);
        }
        for (Map<String, Object> bean: reg) {
            row = sheet.createRow(fil++);
            col = 0;
            for (String name: names) {
                obj = bean.get(name);
                cell = row.createCell(col++);
                if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                } else if (obj instanceof Integer) {
                    cell.setCellValue(((Integer) obj).intValue());
                } else if (obj instanceof Long) {
                    cell.setCellValue(((Long) obj).intValue());
                } else if (obj instanceof BigDecimal) {
                    cell.setCellValue(((BigDecimal) obj).doubleValue());
                }
            }
        }
        try {
            workbook.write(out);
        } finally {
            out.close();
        }
        return null;
	}
}
