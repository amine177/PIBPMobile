package tn.esprit;


import static com.codename1.ui.CN.*;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Toolbar;
import tn.esprit.entite.Utilisateur;
import tn.esprit.profil.LoginForm;


/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class Main {

    
    private Form current;
    private Resources theme;
    private static Utilisateur loggedUser;

    public void init(Object context) {
        // use two network threads instead of one
        //updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");        
        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);
        Toolbar.setCenteredDefault(false);
        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        new LoginForm(theme).show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }
   

}
