package jorgedizpico.cluster;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import au.com.bytecode.opencsv.CSVReader;

public class Graphs2D {
	
	protected static boolean exp = false;

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.out.println("Usage: Graph2D <csvInput> [log]");
		}
		
		try {
			
			if (2 <= args.length)
				exp = (args[1].equals("exp"));
			
			String csvFile = args[0];
			ArrayList<Double[]> dataValues = getData(csvFile);
			
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
	        
		} catch (Exception e) {
			System.out.println("Unable to draw 2D plots: " + e.getMessage());
		}
	}
		
	public static ArrayList<Double[]> getData(String csvFile) throws IOException {
		
		ArrayList<Double[]> list = new ArrayList<Double[]>();
		double x, y, z;
		int i = 0;
		
      	CSVReader reader = new CSVReader(new FileReader(csvFile));
        String [] nextLine;
        
      	while (null != (nextLine = reader.readNext())) {
      		x = Double.parseDouble(nextLine[0]);
      		y = Double.parseDouble(nextLine[1]);
      		z = Double.parseDouble(nextLine[2]);
			
			if (Filters.isOutlierPoint(x,y,z))
				System.out.println("outlier [" + i + "]: " + x + "," + y + "," + z + " (skipped)");
			
			else {
				if (exp) {
					x = Math.pow(1.1,x);
					y = Math.pow(1.1,y);
					z = Math.pow(1.1,z);
				}
				list.add(new Double[]{x,y,z});
			}
			i++;

		}
		reader.close();
    	System.out.println("Total points plotted: " + list.size());
		return list;
	}
        
    protected static void showGraph(XYDataset dataset) {
        final JFreeChart chart = createChart(dataset);
        
        ChartFrame frameGeo = new ChartFrame("XYLine Chart", chart);
        frameGeo.setSize(700,500);
        frameGeo.setLocation(700,0);
        frameGeo.setVisible(true);
    }

    protected static JFreeChart createChart(XYDataset dataset) {
    	
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
