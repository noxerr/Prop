/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion.controlador;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;


/**
 *
 * @author David
 */
public class Graphj extends JFrame{
    
    private mxGraph graph = new mxGraph();
    Object parent = graph.getDefaultParent();
    private String path;
    private HashMap<String, Object> vertices = new HashMap<String, Object>();
           
    public Graphj(HashMap<String, HashMap<String, Double>> g){
        Set<String> N = g.keySet();
        int x = 20;
        int y = 20;
        for(String n : N){
            Object v = graph.insertVertex(parent, n, n, x, y, 20, 20);
            vertices.put(n, v);
            x += 40;
            y += 40;
        }
        for(String n : N){
            try{            
                for(String n2 : g.get(n).keySet()){
                    Object e1 = graph.insertEdge(parent, null, g.get(n).get(n2), vertices.get(n), vertices.get(n2));
                }
            }
            catch(NullPointerException e){
                System.out.println("null");
            }
        }
        
        Map<String, Object> edge = new HashMap<String, Object>();
        edge.put(mxConstants.STYLE_ROUNDED, true); //Curva los bordes
        edge.put(mxConstants.STYLE_ORTHOGONAL, false);
        edge.put(mxConstants.STYLE_EDGE, "elbowEdgeStyle");
        edge.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CONNECTOR);
        edge.put(mxConstants.STYLE_FONTSIZE,"25" );
        edge.put(mxConstants.STYLE_ENDARROW, "none");
        edge.put(mxConstants.STYLE_VERTICAL_ALIGN, mxConstants.ALIGN_MIDDLE);
        edge.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_CENTER);
        edge.put(mxConstants.STYLE_STROKECOLOR, "#000000"); // default is #6482B9
        edge.put(mxConstants.STYLE_FONTCOLOR, "#446299");


        mxStylesheet edgeStyle = new mxStylesheet();
        edgeStyle.setDefaultEdgeStyle(edge);
        graph.setStylesheet(edgeStyle);
        
        final mxGraphComponent graphComponent = new mxGraphComponent(graph);
	getContentPane().add(graphComponent);
        
    }

    
    /*public void ExportarImagen(String path, String ext){
        try{
            this.path = path;
            FileOutputStream out = new FileOutputStream(path);
            Color bg = null;
            bg = graph.getBackground();
            Image imgs = graph.createImage(200,200);
            graph.refresh();
            BufferedImage img = graph.getImage(null, 0);
            ImageIO.write(img, ext, out);
            out.flush();
            out.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }*/
    
    
}

