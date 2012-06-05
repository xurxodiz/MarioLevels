package jorgedizpico.cluster;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileReader;
import java.io.IOException;

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
	
    public static String FILENAME = "data/logprob.csv";
    
   /* public static void main(String[] args) throws IOException {
    	
         
    	MultiColorScatter scatter = new MultiColorScatter( points, new ColorMapper( new ColorMapRainbow(), -0.5f, 0.5f ) );

    	Chart chart = new Chart();
    	chart.getAxeLayout().setMainColor(Color.BLACK);
    	chart.getView().setBackgroundColor(Color.WHITE);
    	chart.getScene().add(scatter);
    	scatter.setLegend( new ColorbarLegend(scatter, chart.getView().getAxe().getLayout(), Color.WHITE, null) );
    	scatter.setLegendDisplayed(true);

    	ChartLauncher.openChart(chart);
    }*/
    
    public static void main(String[] args) throws Exception {
        Plugs.frame(getChart(), new Rectangle(0,200,400,400), "Jzy3d Demo");
      }

      public static Chart getChart() throws Exception {
        InteractiveScatter scatter = readScatter();
          
          chart = new Chart("awt");
          chart.getScene().add( scatter );
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
              if(displayMessage && message!=null){
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

      protected static InteractiveScatter readScatter() throws Exception{
    	  
      	int size = 97;
      	double x, y, z;
      	int k = 0;

      	Coord3d[] points = new Coord3d[size];
      	Color[] colors = new Color[size];

      	CSVReader reader = new CSVReader(new FileReader(FILENAME));
        String [] nextLine;
      	
       // ColorMapper map = new ColorMapper( new ColorMapRainbow());
        
      	while ((nextLine = reader.readNext()) != null) {
      		x = Double.parseDouble(nextLine[0]);
      		y = Double.parseDouble(nextLine[1]);
      		z = Double.parseDouble(nextLine[2]);
      		
      		/*
      		 * 	x = Math.pow(10, x);
      			y = Math.pow(10, y);
      			z = Math.pow(10, z);
      		 */
      		points[k] = new Coord3d(x, y, z);
            colors[k] = Color.RED;
			if (x < -75 || y < -250)
				System.out.println(x + "," + y + "," + z);
			else
				k++;
      	}  
          InteractiveScatter dots = new InteractiveScatter(points, colors);
          dots.setWidth(1);
          return dots;
      }
      
      /***********************************************/
      
    //protected static SelectableScatter scatter;
      protected static Chart chart;
      protected static Renderer2d messageRenderer;

      protected static ChartThreadController threadCamera;
      protected static ChartMouseController mouseCamera;
      protected static AbstractChartMouseSelector mouseSelection;

      protected static boolean displayMessage = true;
      protected static String message;
      
      public static String MESSAGE_SELECTION_MODE = "Selection mode (hold 'c' to control camera)";
      public static String MESSAGE_ROTATION_MODE = "Rotation mode (release 'c' to make a selection)";
    
    protected static int readNLines(String filename) throws IOException {
            CSVReader reader = new CSVReader(new FileReader(filename));
            int n = 0;
            while(reader.readNext() != null){
                    n++;
            }
            reader.close();
            return n;
    }

}
