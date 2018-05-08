package adrutas.com.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import adrutas.com.Constante;
import adrutas.com.dao.Salida;

import com.opensymphony.xwork2.ActionSupport;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

public class ListaAsientos extends ActionSupport {
    private static final long serialVersionUID = 3517825207553161674L;

    private String salida;

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public String execute() throws IOException, XDocReportException, SQLException {
        ZipEntry zipEntry;
        ByteArrayOutputStream bAOS;
        HttpServletResponse resp = ServletActionContext.getResponse();
        ServletOutputStream oS = resp.getOutputStream();
        Map<String, String> bean = Salida.get(salida);
        ZipOutputStream zos = new ZipOutputStream(oS);
        InputStream in = ListaAsientos.class.getResourceAsStream("lista_asientos.docx" );
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
        IContext context = report.createContext();
        resp.setContentType("application/x-zip-compressed");
        resp.setHeader("Content-Disposition", "attachment;filename=lista_asientos_" + salida + ".zip");
        try {
            for (Entry<Integer, List<Map<String, Object>>> entry: Salida.getMapAsientos(salida).entrySet()) {
                context.put("bus", entry.getKey());
                context.put("salida", salida);
                context.put("descripcion", bean.get("Descripcion"));
                context.put("fecha", Constante.dF4.format(bean.get("fecha")));
                context.put("list", entry.getValue());
                report.process(context, bAOS = new ByteArrayOutputStream());
                zipEntry = new ZipEntry("lista_asientos_" + salida + "_" + entry.getKey() + ".docx");
                zos.putNextEntry(zipEntry);
                zos.write(bAOS.toByteArray());
                bAOS.close();
            }
        } finally {
            zos.close();
            in.close();
            oS.close();
        }
        return null;
	}
}
