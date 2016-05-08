package adrutas.com.action;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Map;

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

public class ListaAlfa extends ActionSupport {
    private static final long serialVersionUID = -5895086119656860023L;

    private String salida;

    public String execute() throws IOException, SQLException, XDocReportException {
        HttpServletResponse resp = ServletActionContext.getResponse();
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        resp.setHeader("Content-Disposition", "attachment;filename=lista_telefonos_" + salida + ".docx");
        InputStream in = null;
        Map<String, String> bean = Salida.get(salida);
        try {
            in = ListaAlfa.class.getResourceAsStream("lista_alfabetica.docx" );
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            IContext context = report.createContext();
            context.put("bus", 1);
            context.put("salida", salida);
            context.put("descripcion", bean.get("Descripcion"));
            context.put("fecha", Constante.dF4.format(bean.get("fecha")));
            context.put("list", Salida.getListaTelefonos(salida));
            report.process(context, out);
        } finally {
            in.close();
            out.close();
        }
        return null;
	}

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }
}
