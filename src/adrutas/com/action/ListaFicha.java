package adrutas.com.action;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import adrutas.com.dao.Licencia;
import adrutas.com.dao.Persona;

import com.opensymphony.xwork2.ActionSupport;

import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

public class ListaFicha extends ActionSupport {
    private static final long serialVersionUID = -262118787360537701L;
    private static final Logger log = Logger.getLogger(ListaFicha.class.getName());

    private String anyo;
    private Integer id_persona;

    public void setAnyo(String anyo) {
        this.anyo = anyo;
    }

    public void setId_persona(Integer id_persona) {
        this.id_persona = id_persona;
    }

    public String execute() throws IOException {
        HttpServletResponse res = ServletActionContext.getResponse();
        ServletOutputStream out = res.getOutputStream();
        InputStream in = null;
        Integer intAnyo = Integer.valueOf(anyo);
        res.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        res.setHeader("Content-Disposition", "attachment;filename=ficha_" + intAnyo + "_" + id_persona + ".docx");
        try {
            in = ListaFicha.class.getResourceAsStream("lista_ficha_2016.docx" );
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);
            IContext context = report.createContext();
            Map<String, Object> bean = new HashMap<String, Object>();
            bean.put("anyo", intAnyo);
            bean.put("id_persona", id_persona);
            Map<String, Object> map = Persona.getFicha(bean);
            context.put("importeseguro", ((BigDecimal) map.get("importe")).subtract((BigDecimal) map.get("importecuota")));
            for (Entry<String, Object> entry: map.entrySet()) {
                context.put(entry.getKey(), entry.getValue());
            }
            context.put("AU_men", Licencia.getMlicencias().get("AU").get("importe_menor"));
            context.put("AU_adl", Licencia.getMlicencias().get("AU").get("importe"));
            context.put("A_men", Licencia.getMlicencias().get("A").get("importe_menor"));
            context.put("A_adl", Licencia.getMlicencias().get("A").get("importe"));
            context.put("B_men", Licencia.getMlicencias().get("B").get("importe_menor"));
            context.put("B_adl", Licencia.getMlicencias().get("B").get("importe"));
            context.put("C_men", Licencia.getMlicencias().get("C").get("importe_menor"));
            context.put("C_adl", Licencia.getMlicencias().get("C").get("importe"));
            context.put("D_men", Licencia.getMlicencias().get("D").get("importe_menor"));
            context.put("D_adl", Licencia.getMlicencias().get("D").get("importe"));
            context.put("E_men", Licencia.getMlicencias().get("E").get("importe_menor"));
            context.put("E_adl", Licencia.getMlicencias().get("E").get("importe"));
//            context.put("OT1_men", Licencia.getMlicencias().get("OT1").get("importe_menor"));
//            context.put("OT1_adl", Licencia.getMlicencias().get("OT1").get("importe"));
            context.put("INT_CR_CA", Licencia.getMlicencias().get("INT CR CA").get("importe"));
            context.put("INT_CR_SA", Licencia.getMlicencias().get("INT CR SA").get("importe"));
            context.put("INT_SR_CA", Licencia.getMlicencias().get("INT SR CA").get("importe"));
            context.put("INT_SR_SA", Licencia.getMlicencias().get("INT SR SA").get("importe"));
            for (Map<String, Object> opcion: (List<Map<String, Object>>) Licencia.getMlicencias().get("B").get("opciones")) {
                if ("BTT".equals(opcion.get("tipo_opcion"))) {
                    context.put("BTT", opcion.get("importe"));
                    context.put("BTTc", '\u2610');
                }
                if ("EA".equals(opcion.get("tipo_opcion"))) {
                    context.put("EA", opcion.get("importe"));
                    context.put("EAc", '\u2610');
                }
                if ("EF".equals(opcion.get("tipo_opcion"))) {
                    context.put("EF", opcion.get("importe"));
                    context.put("EFc", '\u2610');
                }
                if ("SNW".equals(opcion.get("tipo_opcion"))) {
                    context.put("SNW", opcion.get("importe"));
                    context.put("SNWc", '\u2610');
                }
            }
            for (Map<String, Object> opcion: (List<Map<String, Object>>) map.get("opciones")) {
                context.put(opcion.get("tipo_opcion") + "c", '\u2611');
            }
            StringBuffer sB = new StringBuffer();
            for (Map<String, Object> telefono: Persona.getTelefonos(id_persona)) {
                sB.append(telefono.get("telefono") + ", ");
            }
            context.put("telefonos", sB.length()==0? "": sB.substring(0, sB.length()-2));
            sB = new StringBuffer();
            for (Map<String, Object> email: Persona.getEmails(id_persona)) {
                sB.append(email.get("email") + ", ");
            }
            context.put("emails", sB.length()==0? "": sB.substring(0, sB.length()-2));
            report.process(context, out);
        } catch (Exception e) {
            log.log(Level.SEVERE, "No genera la ficha", e);
        } finally {
            in.close();
            out.close();
        }
        return null;
	}
}
