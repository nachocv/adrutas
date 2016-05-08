package adrutas.com.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class RolesInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 6247310487338827351L;

    protected List<Integer> allowedRoles = Collections.emptyList();
    protected List<Integer> disallowedRoles = Collections.emptyList();

    public void setAllowedRoles(String roles) {
        allowedRoles = stringToList(roles);
    }

    public void setDisallowedRoles(String roles) {
        disallowedRoles = stringToList(roles);
    }

    public String intercept(ActionInvocation invocation) throws Exception {
        if (isAllowed(ServletActionContext.getRequest(), invocation.getAction())) {
            return invocation.invoke();
        } else {
            ServletActionContext.getResponse().sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    /**
     * Splits a string into a List
     */
    protected List<Integer> stringToList(String val) {
        List<Integer> list = new ArrayList<>();
        if (val != null) {
            for (String rol: val.split("[ ]*,[ ]*")) {
                list.add(Integer.valueOf(rol));
            }
        }
        return list;
    }

    /**
     * Determines if the request should be allowed for the action
     *
     * @param request The request
     * @param action The action object
     * @return True if allowed, false otherwise
     */
    protected boolean isAllowed(HttpServletRequest request, Object action) {
        Map<String, Object> bean = (Map<String, Object>) request.getSession().getAttribute("yo");
        if (bean==null) {
            return false;
        }
        Integer rol = (Integer) bean.get("rol");
        return !disallowedRoles.contains(rol) && (allowedRoles.contains(rol) || allowedRoles.isEmpty());
    }
}
