package jorgedizpico.cluster;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.util.ArrayList;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.controllers.mouse.AbstractChartMouseSelector;
import org.jzy3d.chart.controllers.mouse.ChartMouseController;
import org.jzy3d.chart.controllers.mouse.interactives.ScatterMouseSelector;
import org.jzy3d.chart.controllers.thread.ChartThreadController;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.interactive.InteractiveScatter;
import org.jzy3d.plot3d.rendering.view.Renderer2d;
import org.jzy3d.ui.Plugs;

import au.com.bytecode.opencsv.CSVReader;

public class Graphs3D {
	
    protected static boolean exp = false;
	
	
    protected InteractiveScatter scatter;
    protected Chart chart;
    protected Renderer2d messageRenderer;

    protected ChartThreadController threadCamera;
    protected ChartMouseController mouseCamera;
    protected AbstractChartMouseSelector mouseSelection;

    protected boolean displayMessage = true;
    protected String message;
    
    public String MESSAGE_SELECTION_MODE = "Selection mode (hold 'c' to control camera)";
    public String MESSAGE_ROTATION_MODE = "Rotation mode (release 'c' to make a selection)";
    
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: Graph3D <csvInput>");
		}
		
		if (2 <= args.length)
			exp = (args[1].equals("exp"));
		
		try {
			ArrayList<Double[]> points = readPoints(args[0]);
			
			Graphs3D graphXYZ = new Graphs3D(points, new int[]{0,1,2});
			Chart chartXYZ = graphXYZ.newChart();
			Plugs.frame(chartXYZ, new Rectangle(0,400,800,800), "X-Y-Z");
			
			Graphs3D graphXYT = new Graphs3D(points, new int[]{0,1,3});
			Chart chartXYT = graphXYT.newChart();
			Plugs.frame(chartXYT, new Rectangle(0,400,800,800), "X-Y-T");
			
			Graphs3D graphXZT = new Graphs3D(points, new int[]{0,2,3});
			Chart chartXZT = graphXZT.newChart();
			Plugs.frame(chartXZT, new Rectangle(0,400,800,800), "X-Z-T");
			
			Graphs3D graphYZT = new Graphs3D(points, new int[]{1,2,3});
			Chart chartYZT = graphYZT.newChart();
			Plugs.frame(chartYZT, new Rectangle(0,400,800,800), "Y-Z-T");

		} catch (Exception e) {
			System.out.println("Unable to draw 3D plot: " + e.getMessage());
		}
	}
	
	protected Graphs3D(ArrayList<Double[]> points, int[] indexes) {
		Coord3d[] coords = new Coord3d[points.size()];
		Color[] colors = new Color[points.size()];
		double[] c = new double[3];
		
		for (int i = 0; i < coords.length; i++) {
			for (int j = 0; j < indexes.length; j++)
				c[j] = points.get(i)[indexes[j]];
			coords[i] = new Coord3d(c[0], c[1], c[2]);
			colors[i] = Color.RED;
		}
		scatter = new InteractiveScatter(coords,colors);
		scatter.setWidth(5);
	}
    
	protected static ArrayList<Double[]> readPoints(String csvFile) throws Exception{
	  
		double x, y, z, t;
		int i = 0;
	
		ArrayList<Double[]> points = new ArrayList<Double[]>();
	
		CSVReader reader = new CSVReader(new FileReader(csvFile));
		String [] nextLine;
	  
		while (null != (nextLine = reader.readNext())) {
			x = Double.parseDouble(nextLine[0]);
			y = Double.parseDouble(nextLine[1]);
			z = Double.parseDouble(nextLine[2]);
			t = Double.parseDouble(nextLine[3]);
			
			if (Filters.isOutlierPointXYZT(x,y,z,t))
				System.out.println("outlier [" + i + "]: " + x + ","
						+ y + "," + z + "," + t +
						" (skipped)");
				
				else {
					if (exp) {
						x = Math.pow(1.1,x);
						y = Math.pow(1.1,y);
						z = Math.pow(1.1,z);
						t = Math.pow(1.1,t);
					}
			  		points.add(new Double[]{x, y, z, t});
				}
				i++;
			
			}  
	    	reader.close();
	    	System.out.println("Total points added: " + points.size());
	       return points;
	  }

	  public Chart newChart() {
	      
	      chart = new Chart("awt");
	      chart.getScene().add(scatter);
	      chart.getView().setMaximized(true);
	      
	      // Create and add controllers
	      threadCamera = new ChartThreadController(chart);
	      mouseCamera = new ChartMouseController();
	      mouseCamera.addSlaveThreadController(threadCamera);
	      mouseSelection = new ScatterMouseSelector(scatter);
	      chart.getCanvas().addKeyListener(new KeyListener(){
	    	  	public void keyPressed(KeyEvent e) {}
				public void keyReleased(KeyEvent e) {
	    	  		switch( e.getKeyChar()){
	    	  			case 'c': releaseCam(); break;        
	    	  			default: break;
	    	  		}
	    	  		holding = false;
	    	  		message = MESSAGE_SELECTION_MODE;
	    	  		chart.updateProjectionsAndRender(); // update message display
	    	  	}
	    	  	public void keyTyped(KeyEvent e) {
	    	  		if(!holding){
	    	  			switch( e.getKeyChar()){
	    	  				case 'c': useCam(); break;        
	    	  				default: break;
	    	  			}
	    	  		holding = true;
	    	  		message = MESSAGE_ROTATION_MODE;
	    	  		chart.updateProjectionsAndRender();
	    	  		}
	    	  	}
	    	  	protected boolean holding = false;
	      });
	      releaseCam();
	      
	      message = MESSAGE_SELECTION_MODE;
	      messageRenderer = new Renderer2d(){
	        public void paint(Graphics g){
	          if(displayMessage && message != null){
	            g.setColor(java.awt.Color.RED);
	            g.drawString(message, 10, 30);
	          }
	            
	        }
	      };
	      chart.addRenderer(messageRenderer);
	      return chart;
	  }
  
	protected void useCam(){
		mouseSelection.releaseChart();
		chart.addController(mouseCamera);
	}
	  
	protected void releaseCam(){
		chart.removeController(mouseCamera);
		mouseSelection.attachChart(chart);
	}

}
