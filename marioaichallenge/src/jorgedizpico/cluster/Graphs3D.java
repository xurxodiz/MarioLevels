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
	
	
    protected static Chart chart;
    protected static Renderer2d messageRenderer;

    protected static ChartThreadController threadCamera;
    protected static ChartMouseController mouseCamera;
    protected static AbstractChartMouseSelector mouseSelection;

    protected static boolean displayMessage = true;
    protected static String message;
    
    public static String MESSAGE_SELECTION_MODE = "Selection mode (hold 'c' to control camera)";
    public static String MESSAGE_ROTATION_MODE = "Rotation mode (release 'c' to make a selection)";
    
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Usage: Graph3D <csvInput>");
		}
		
		if (2 <= args.length)
			exp = (args[1].equals("exp"));
		
		try {
			Plugs.frame(getChart(args[0]), new Rectangle(0,200,400,400), "Jzy3d Demo");
		} catch (Exception e) {
			System.out.println("Unable to draw 3D plot: " + e.getMessage());
		}
	}
    
	protected static InteractiveScatter readScatter(String csvFile) throws Exception{
	  
		double x, y, z;
		int i = 0;
	
		ArrayList<Coord3d> points = new ArrayList<Coord3d>();
		ArrayList<Color> colors = new ArrayList<Color>();
	
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
			  		points.add(new Coord3d(x, y, z));
			        colors.add(Color.RED);
				}
				i++;
			
			}  
	    	reader.close();
	    	System.out.println("Total points plotted: " + points.size());
	    	
	       InteractiveScatter dots = new InteractiveScatter(points.toArray(new Coord3d[]{}),
	      		 										  colors.toArray(new Color[]{}));
	       dots.setWidth(5);
	       return dots;
	  }

	  public static Chart getChart(String csvFile) throws Exception {
		  
		  InteractiveScatter scatter = readScatter(csvFile);
	      
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
  
	protected static void useCam(){
		mouseSelection.releaseChart();
		chart.addController(mouseCamera);
	}
	  
	protected static void releaseCam(){
		chart.removeController(mouseCamera);
		mouseSelection.attachChart(chart);
	}

}
