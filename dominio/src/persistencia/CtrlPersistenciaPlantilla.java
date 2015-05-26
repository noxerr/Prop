/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.io.IOException;
import java.util.List;


/**
 *
 * @author dani__000
 */
public class CtrlPersistenciaPlantilla {

    public static void guardarPlantillas(List<List<String>> list) throws IOException{
        FileManager.WriteFile(list, "plantillas", "plantilla");
    }
    
    
    public static List<List<String>> cargarPlantillas(){
        /*ArrayList<ArrayList<String>> list = new ArrayList();
        ArrayList<String> ret = new ArrayList<>(
            Arrays.asList("una", "0", "2", "3", "4", "5", "6", "7", "8", "9"));
        list.add(ret);*/
        return FileManager.read("plantillas", "plantilla");
    }
}
