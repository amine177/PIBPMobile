/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.profil;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import tn.esprit.entite.Etablissement;
import tn.esprit.services.EtablissementService;
import tn.esprit.widgets.SideMenuBaseForm;


import com.codename1.crypto.EncryptedStorage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;



/**
 *
 * @author ASUS
 */
public class BonPlan extends SideMenuBaseForm{
    EtablissementService etbsev=new EtablissementService();
    ArrayList<Etablissement> etabs=new ArrayList<>();
    public BonPlan(Resources res) {
        super(BoxLayout.y());
        setTitle("Etablissements");
        //Show list etabs here
        etabs=etbsev.selectAllEnabled();
        for(int i=0;i<etabs.size();i++)
        etabContainer(res,etabs.get(i))  ;
        EncryptedStorage.getInstance();
        setupSideMenu(res);
         
          
        
    }
    
    private void etabContainer(Resources res,Etablissement etab) {
        int height = Display.getInstance().convertToPixels(15f);
        int width = Display.getInstance().convertToPixels(25f);
        
        Container cnt = new Container(BorderLayout.absolute());                      
        Label nomlbl = new Label(etab.getNom());
        //Label image=new Label();
        
        if(etab.getPhoto()!=null){
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(80, 80), true);
 URLImage background = URLImage.createToStorage(placeholder, "http://127.0.0.1:8000/web/bundles/blog/template/images/" + etab.getPhoto(),
        "http://127.0.0.1:8000/web/bundles/blog/template/images/" + etab.getPhoto());
background.fetch();
//         EncodedImage imge = EncodedImage.createFromImage(Image.createImage(80,80), true);
//        URLImage imgg = URLImage.createToStorage(imge,"Medium_" + etab.getPhoto(), "http://127.0.0.1:8000/web/bundles/blog/template/images/" + etab.getPhoto(), URLImage.RESIZE_SCALE);
//        imgg.fetch();        
//         image= new Label(imgg.fill(width, height));
        
        cnt.add(BorderLayout.EAST,background);
    }
         cnt.add(BorderLayout.WEST,nomlbl);        
        add(cnt);
        setScrollableY(true);
        setScrollableX(true);
        nomlbl.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new DetailForm(res, etab).show();
            }
        });        
    }
    @Override    
    public void setupSideMenu(Resources res) {
        Toolbar b = getToolbar();
        b.setGlobalToolbar(true);
        b.removeAll();
        // Ajouter boutton a son container
        Button menuButton = new Button("");
        Container titleCmp = BoxLayout.encloseY(
                FlowLayout.encloseIn(menuButton)
        );
       
        titleCmp.setScrollableY(false);
        titleCmp.setScrollableX(false);       
        b.setTitleComponent(titleCmp);
        menuButton.setUIID("Title");
        b.addSearchCommand(e -> {
            
        });  
        menuButton.addActionListener((e) -> b.openSideMenu());
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        b.addMaterialCommandToSideMenu("  Profil", FontImage.MATERIAL_ARCHIVE, e -> gotoProfile(res));
        b.addMaterialCommandToSideMenu("  Evenements", FontImage.MATERIAL_ACCESS_TIME, e -> {
            try {
                gotoEvents(res);
            } catch (IOException ex) {
                System.out.println("");
            }
        });
        b.addMaterialCommandToSideMenu("  Blog", FontImage.MATERIAL_BOOK, e -> gotoBlog(res));
        b.addMaterialCommandToSideMenu("  Paramétres", FontImage.MATERIAL_SETTINGS, e -> gotoStats(res));
        b.addMaterialCommandToSideMenu("  Déconnecter", FontImage.MATERIAL_EXIT_TO_APP, e -> gotoLogin(res));
        
    }
    
    
}
