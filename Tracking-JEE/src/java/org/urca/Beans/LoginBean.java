package org.urca.Beans;

import java.io.IOException;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class LoginBean {
    
    public void redirectToLoginPage() throws IOException {
           ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
           externalContext.redirect(externalContext.getRequestContextPath() + "/login.xhtml");
    }   
}
