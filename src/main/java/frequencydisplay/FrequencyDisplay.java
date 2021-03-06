/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frequencydisplay;

import frequencydisplay.controller.DisplayController;
import frequencydisplay.data.Platform;
import frequencydisplay.model.AppModel;
import frequencydisplay.model.CsvPlatformModelLoader;
import frequencydisplay.model.Model;
import frequencydisplay.view.AppView;
import java.util.List;

/**
 *
 * @author Keith
 */
public class FrequencyDisplay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Model
        Model model = new AppModel();
        
        //View
        AppView view = new AppView();
        view.initialize();
        
        //Controller
        DisplayController controller = new DisplayController(model, view);
        controller.launch();
        
        List<Platform> database = CsvPlatformModelLoader.loadPlatformsFromFile("Sonar_Profiles_LWAMI_311.csv");
        for (Platform p : database) {
            model.addPlatform(p);
        }
        
    }
    
}
