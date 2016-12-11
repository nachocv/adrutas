package adrutas.com.action;

import com.opensymphony.xwork2.ActionSupport;

public class Init extends ActionSupport {
	private static final long serialVersionUID = 6991029774960809847L;

	@Override
	public String execute() throws Exception {
		new Inicio().init();
		return "";
	}
}
