/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.cofar.administrador;

/**
 *
 * @author Jaime Chura
 * @version BACO 2.0 date 05-08-2016 time 04:43:23 PM
 */
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.imageio.ImageIO;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean
public class ChartView implements Serializable {

    private String base64Str;

    public String getBase64Str() {
        return base64Str;
    }

    public void setBase64Str(String base64Str) {
        this.base64Str = base64Str;
    }

    private LineChartModel lineModel1;
    private LineChartModel lineModel2;

    @PostConstruct
    public void init() {
        createLineModels();
    }

    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Linear Chart");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(10);

        lineModel2 = initCategoryModel();
        lineModel2.setTitle("Resultado Evaluación");
        lineModel2.setLegendPosition("e");
        lineModel2.setShowPointLabels(false);
        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Variables"));
        yAxis = lineModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Valores");

        yAxis.setMin(0);
        yAxis.setMax(10);
    }

    private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();

        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");

        series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");

        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);

        model.addSeries(series1);
        model.addSeries(series2);

        return model;
    }

    private LineChartModel initCategoryModel() {
        LineChartModel model = new LineChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Perfil Requerido");
        boys.set("Tol", 5);
        boys.set("Aut", 4);
        boys.set("Efi", 4);
        boys.set("TDeci", 4);
        boys.set("DomExp", 3);
        boys.set("CtrolCog", 3);
        boys.set("TolFrus", 4);
        boys.set("AdapCamb", 5);
        boys.set("FormDisc", 5);
        boys.set("Lid", 4);
        boys.set("EspSoc", 3);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Evaluación");
        girls.set("Tol", 4);
        girls.set("Aut", (int) (Math.random() * 4));
        girls.set("Efi", (int) (Math.random() * 4));
        girls.set("TDeci", 5);
        girls.set("DomExp", (int) (Math.random() * 4));
        girls.set("CtrolCog", 1);
        girls.set("TolFrus", (int) (Math.random() * 7));
        girls.set("AdapCamb", 4);
        girls.set("FormDisc", 5);
        girls.set("Lid", (int) (Math.random() * 5));
        girls.set("EspSoc", 1);
        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    public void submittedBase64Str() {
        // You probably want to have a more comprehensive check here. 
        // In this example I only use a simple check
        System.out.println("click submitted");
        if (base64Str.split(",").length > 1) {
            System.out.println("base64 is true");
            String encoded = base64Str.split(",")[1];
            byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(encoded.getBytes());
            // Write to a .png file
            System.out.println("Write to a .png file");
            try {
                File imageFile = new File("D:\\out.png");
                RenderedImage renderedImage = ImageIO.read(new ByteArrayInputStream(decoded));
                System.out.println("test " + imageFile.getAbsolutePath());
                ImageIO.write(renderedImage, "png", imageFile); // use a proper path & file name here.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
