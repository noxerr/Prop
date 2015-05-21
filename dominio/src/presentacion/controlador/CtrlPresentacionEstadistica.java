package presentacion.controlador;

import dominio.controlador.CtrlEstadistica;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.DeviationRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.YIntervalSeries;
import org.jfree.data.xy.YIntervalSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class CtrlPresentacionEstadistica {

    private static final long serialVersionUID = 1L;

    private static XYDataset createDataset() throws Exception {
        YIntervalSeries series1 = new YIntervalSeries("Newman");
        YIntervalSeries series2 = new YIntervalSeries("Clicke");
        YIntervalSeries series3 = new YIntervalSeries("Louvain");
        ArrayList<Double>[] newman = CtrlEstadistica.getXYAlg(0);
        if (newman == null) System.out.println("es null");
        ListIterator l1, l2, l3, l4;
        double d = 100D;
        double d1 = 100D;
        /*for (int i = 0; i <= 100; i++) {
            d = (d + Math.random()) - 0.47999999999999998D;
            double d2 = 0.050000000000000003D * (double) i;
            series1.add(i, d, d - d2, d + d2);
            d1 = (d1 + Math.random()) - 0.5D;
            double d3 = 0.070000000000000007D * (double) i;
            series3.add(i, d1, d1 - d3, d1 + d3);
        }*/
        l1 = newman[0].listIterator(); l2 = newman[1].listIterator();
        l3 = newman[2].listIterator(); l4 = newman[3].listIterator();
        while (l1.hasNext()){
            series1.add((double) l1.next(), (double) l2.next(), 
                    (double) l3.next(), (double) l4.next());
        }

        YIntervalSeriesCollection dataset = new YIntervalSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        return dataset;
    }

    private static JFreeChart createChart(XYDataset xydataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("Eficiència D'Algorismes", "Tamany (nodes)", "Temps (ms)", xydataset, PlotOrientation.VERTICAL, true, true, false);
        chart.setBackgroundPaint(Color.white);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        DeviationRenderer renderer = new DeviationRenderer(true, false);
        renderer.setSeriesStroke(0, new BasicStroke(3F, 1, 1));
        renderer.setSeriesStroke(1, new BasicStroke(3F, 1, 1));
        renderer.setSeriesStroke(2, new BasicStroke(3F, 1, 1));
        renderer.setSeriesFillPaint(0, new Color(255, 200, 200));
        renderer.setSeriesFillPaint(1, new Color(200, 200, 255));
        renderer.setSeriesFillPaint(2, new Color(200, 255, 200));
        plot.setRenderer(renderer);
        //
        NumberAxis valueAxis = (NumberAxis) plot.getRangeAxis();
        valueAxis.setAutoRangeIncludesZero(false);
        valueAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        return chart;
    }
    
    
    public static void crearGrafico(JPanel GraficoLineas) throws Exception{
        ChartPanel panel;
        GraficoLineas.removeAll();
        panel = new ChartPanel(createChart(createDataset()));
        panel.setBounds(0, 0, 550, 440);
        GraficoLineas.add(panel);
        GraficoLineas.repaint();

        try {
            ChartUtilities.saveChartAsJPEG(new File("grafico2.jpg"), createChart(createDataset()), 550, 440);
        } catch (IOException ex) {
            Logger.getLogger(CtrlPresentacionEstadistica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}