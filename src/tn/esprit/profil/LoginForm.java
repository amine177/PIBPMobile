/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.profil;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;




import tn.esprit.securite.FOSJCrypt;
import tn.esprit.securite.Sha512;
import tn.esprit.securite.User;



public class LoginForm extends Form {
    private  User last_auth=null;
    private Database db=null;
    private Cursor cur=null;
    public User getLast_auth() {
        return last_auth;
    }

    public void setLast_auth(User last_auth) {
        this.last_auth = last_auth;
    }

    
    
    public LoginForm(Resources theme)  {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        // install a default DB
        String path = Display.getInstance().getDatabasePath("MyDB.db");
        System.out.println(path);
        if(path != null && !FileSystemStorage.getInstance().exists(path)) {
            copyDb(path);
        
        }
        try {
            db = Display.getInstance().openOrCreate("MyDB.db");
                 System.out.println("database created");                
            
        } catch (IOException ex) {
            System.out.println("creation ex");
        }             
        try {
            if(db==null){
            db.execute(BaseDD.createUserTableDb());
            System.out.println("User table created");
            }
        } catch (IOException ex) {
            System.out.println("create user table failed");
        }       
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("Bienvenue", ""),
                new Label("", "WelcomeBlue")
        );

        getTitleArea().setUIID("Container");

        Image profilePic = theme.getImage("user-picture.jpg");
        System.out.println(profilePic==null);
        Image mask = theme.getImage("round-mask.png");
        System.out.println(mask==null);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());      
        /*UtilisateurService es=new UtilisateurService();
        ArrayList<Utilisateur> users = es.selectAllEnabled();*/
        try {
            cur = db.executeQuery("select * from User");
            if(cur!=null)
            while(cur.next()) {
               int idx = cur.getColumnIndex("id");
               int loginx = cur.getColumnIndex("username");
               int plainx = cur.getColumnIndex("plain");
                int idd=Integer.parseInt(cur.getRow().getString(idx));
                String loginn = cur.getRow().getString(loginx);
                String plainn = cur.getRow().getString(plainx);
                last_auth=new User(idd, loginn, plainn);
                System.out.println(last_auth);
            }
        } catch (IOException ex) {
            System.out.println("select stored user");
        }
         
         TextField login = new TextField("", "Login", 20,TextField.ANY) ;
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD) ;
        if(last_auth!=null)   {
            System.out.println("entrer car il ya stored user");
             login = new TextField(last_auth.getLogin(), "", 20,TextField.ANY) ;
             password = new TextField(last_auth.getPlain(), "", 20, TextField.PASSWORD) ;
        }        
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);

        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        
        

        Button createNewAccount = new Button("           S'inscrire");
       

        // We remove the extra space for low resolution devices so things fit better
         User toStore=new User(); 
        CheckBox spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new CheckBox("Remember Me");
            
        } else {
            spaceLabel = new CheckBox("Remember Me");
        } 
        if(spaceLabel.isSelected()&&!login.getText().isEmpty()&&!password.getText().isEmpty()) {                                                 
                        toStore.setLogin(login.getText());
                        toStore.setPlain(path);                        
            try {
                db.execute(BaseDD.insertUser(toStore));
                System.out.println("ajouter le utili saisie");
            } catch (IOException ex) {
                System.out.println("najouter pas le user car il remember pas");
                last_auth=null;
            }                        
         loginButton.addActionListener(e -> {
            
                // if true : can connect
                if(true)                {
                    
                     Toolbar.setGlobalToolbar(false);
                     new WalkthruForm(theme).show();
                     Toolbar.setGlobalToolbar(true);
                }       
            
        });
        Container by = BoxLayout.encloseY(
                welcome,
                profilePicLabel,
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);      
        by.setScrollableY(true);
        by.setScrollVisible(false);
        createNewAccount.setVisible(true);
        createNewAccount.setUIID("");
        createNewAccount.addActionListener(e -> Display.getInstance().execute("http://127.0.0.1:8001/register/"));
    }
    }
    private void copyDb(String path) {
        try(InputStream i = Display.getInstance().getResourceAsStream(getClass(), "/MyDB.db");
                OutputStream o = FileSystemStorage.getInstance().openOutputStream(path)) {
            Util.copy(i, o);
        } catch(IOException err) {
            Log.e(err);
        }
    }
    
    /*public boolean userLogging(String userIdentity, String password) throws Exception  {
    UtilisateurService us=new UtilisateurService();
    if (Authenticator.validate(userIdentity, password)) {
    return true;
    } else {
    return false;
    }
    }*/
}
