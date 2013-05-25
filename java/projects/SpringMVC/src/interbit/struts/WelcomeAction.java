package interbit.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.BeanNameAware;

public class WelcomeAction extends Action implements BeanNameAware {
	private String beanName;

	@Override
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		System.out.println("in struts action - my name is " + beanName);
		return null;
	}

}
