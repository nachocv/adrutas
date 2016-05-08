package adrutas.com.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
import adrutas.com.dao.Salida;

import com.opensymphony.xwork2.ActionSupport;

public class ExcelContable extends ActionSupport {
    private static final long serialVersionUID = -5490825025776619653L;
    private static final String SPACES = "                              ";

    private int salida;

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public String execute() throws IOException {
        Cell cell;
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        style.setDataFormat(wb.createDataFormat().getFormat("dd/mm/yy h:mm:ss;@"));
        HSSFSheet sContable = wb.createSheet("Contabilidad");
        HSSFSheet sPersonas = wb.createSheet("Personas");
        Row row = sContable.createRow(0);
        HttpServletResponse res = ServletActionContext.getResponse();
        ServletOutputStream out = res.getOutputStream();
        Object object;
        int fil1 = 0;
        int fil2 = 0;
        int col = 0;
        res.setContentType("application/vnd.ms-excel");
        res.setHeader("Content-Disposition", "attachment;filename=contable_" + salida + ".xls");
        Map<String, Object> mSalida = Salida.getSalida(salida);
        String descripcion = ((String) mSalida.get("Descripcion") + SPACES).substring(0, 30);
        row.createCell(col++).setCellValue("fecha/hora");
        row.createCell(col++).setCellValue("titulo");
        row = sContable.createRow(++fil1);
        col = 0;
        cell = row.createCell(col++);
        cell.setCellValue((Timestamp) mSalida.get("fecha"));
        cell.setCellStyle(style);
        row.createCell(col++).setCellValue((String) mSalida.get("Descripcion"));
        fil1++;
        col = 0;
        row = sContable.createRow(++fil1);
        row.createCell(col++).setCellValue("bus");
        row.createCell(col++).setCellValue("asiento");
        row.createCell(col++).setCellValue("cuenta");
        row.createCell(col++).setCellValue("id_recibo");
        row.createCell(col++).setCellValue("fecha/hora");
        row.createCell(col++).setCellValue("importe");
        row.createCell(col++).setCellValue("seguro_dia");
        row.createCell(col++).setCellValue("bono");
        row.createCell(col++).setCellValue("estado");
        row.createCell(col++).setCellValue("ingreso");
        row.createCell(col++).setCellValue("pago");
        row.createCell(col++).setCellValue("observacion");
        row.createCell(col++).setCellValue("salida");
        row.createCell(col++).setCellValue("concepto");
        row = sPersonas.createRow(0);
        col = 0;
        row.createCell(col++).setCellValue("id_persona");
        row.createCell(col++).setCellValue("dni");
        row.createCell(col++).setCellValue("nombre");
        row.createCell(col++).setCellValue("apellido1");
        row.createCell(col++).setCellValue("apellido2");
        for (Map<String, Object> bean: Salida.getSalidaRecibos(salida)) {
            row = sContable.createRow(++fil1);
            col = 0;
            if ((object = bean.get("bus"))!=null) {
                row.createCell(col).setCellValue((Integer) object);
            }
            col++;
            if ((object = bean.get("asiento"))!=null) {
                row.createCell(col).setCellValue((Integer) object);
            }
            col++;
            row.createCell(col++).setCellValue("34" + Constante.nF2.format((Integer) bean.get("id_persona")));
            row.createCell(col++).setCellValue((Integer) bean.get("id_recibo"));
            cell = row.createCell(col++);
            cell.setCellValue((Timestamp) bean.get("fecha"));
            cell.setCellStyle(style);
            row.createCell(col++).setCellValue(((BigDecimal) bean.get("imp_auto")).doubleValue());
            row.createCell(col++).setCellValue((object = bean.get("seguro_dia"))==null? false: (boolean) object);
            if ((object = bean.get("bono"))!=null) {
                row.createCell(col).setCellValue((Integer) object);
            }
            col++;
            row.createCell(col++).setCellValue((String) bean.get("estado"));
            row.createCell(col++).setCellValue(((BigDecimal) bean.get("importe")).doubleValue());
            row.createCell(col++).setCellValue(((BigDecimal) bean.get("pago")).doubleValue());
            row.createCell(col++).setCellValue((String) bean.get("observacion"));
            row.createCell(col++).setCellValue(salida);
            row.createCell(col++).setCellValue(descripcion);
            row = sPersonas.createRow(++fil2);
            col = 0;
            row.createCell(col++).setCellValue(Constante.nF2.format((Integer) bean.get("id_persona")));
            row.createCell(col++).setCellValue((String) bean.get("dni"));
            row.createCell(col++).setCellValue((String) bean.get("nombre"));
            row.createCell(col++).setCellValue((String) bean.get("apellido1"));
            row.createCell(col++).setCellValue((String) bean.get("apellido2"));
        }
        try {
            wb.write(out);
        } finally {
            out.close();
        }
        return null;
	}
}
