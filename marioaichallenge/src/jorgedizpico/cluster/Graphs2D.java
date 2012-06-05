package jorgedizpico.cluster;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graphs2D {

	public static void main(String[] args) throws IOException {
		String dataFileName = "data/logprob.csv";
		Double[][] dataValues = getData(dataFileName);
		
        XYSeriesCollection dataset01 = new XYSeriesCollection();
        XYSeries data01 = new XYSeries("data 0-1");
        for (Double[] d : dataValues)
        	data01.add(d[0], d[1]);
        
        dataset01.addSeries(data01);
        showGraph(dataset01);
        
        XYSeriesCollection dataset02 = new XYSeriesCollection();
        XYSeries data02 = new XYSeries("data 0-2");
        for (Double[] d : dataValues)
        	data02.add(d[0], d[2]);
        
        dataset02.addSeries(data02);
        showGraph(dataset02);
        
        XYSeriesCollection dataset12 = new XYSeriesCollection();
        XYSeries data12 = new XYSeries("data 1-2");
        for (Double[] d : dataValues)
        	data12.add(d[1], d[2]);
        
        dataset12.addSeries(data12);
        showGraph(dataset12);
	}
		
	public static Double[][] getData(String dataFileName) throws IOException {
		
		Double[][] data = new Double[97][3];
		
		BufferedReader bReader = new BufferedReader(new FileReader(dataFileName));
		String s;
		int i = 0;
		while ((s=bReader.readLine())!=null){
			String datavalue [] = s.split(",");
			data[i][0] = Double.parseDouble(datavalue[0]);
			data[i][1] = Double.parseDouble(datavalue[1]);
			data[i][2] = Double.parseDouble(datavalue[2]);
			
			if (data[i][0] < -75 || data[i][1] < -250)
				System.out.println(data[i][0] + "," + data[i][1] + "," + data[i][2]);
			else {

			
				data[i][0] = Math.pow(1.1,data[i][0]);
				data[i][1] = Math.pow(1.1,data[i][1]);
				data[i][2] = Math.pow(1.1,data[i][2]);
			
				i++;
			}

		}
		bReader.close();
		return data;
	}
        
    private static void showGraph(XYDataset dataset) {
        final JFreeChart chart = createChart(dataset);
        
        ChartFrame frameGeo=new ChartFrame("XYLine Chart", chart);
        frameGeo.setSize(700,500);
        frameGeo.setLocation(700,0);
        frameGeo.setVisible(true);
    }

    private static JFreeChart createChart(XYDataset dataset) {
    	
        JFreeChart chart = ChartFactory.createScatterPlot(
            "Title",                  // chart title
            "X",                      // x axis label
            "Y",                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        return chart;
    }
	
}
