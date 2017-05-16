import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import com.jhlabs.image.KaleidoscopeFilter;
import com.jhlabs.image.PointillizeFilter;;

public class Img_Filter {

	JFrame frame;
    JLabel lbIm1;
    JLabel lbIm2;
    BufferedImage srcimg;
    
    public Img_Filter() {
		// TODO Auto-generated constructor stub
	}

    public Img_Filter(String ss,String ss1) throws Exception, InterruptedException
    {
      Player(ss,ss1);
    }
    
	public void Player(String args,String s) throws InterruptedException{
            
            
            try {
                    File file = new File(args);
                    InputStream is = new FileInputStream(file);
                    int sel = Integer.parseInt(s);
                    srcimg = ImageIO.read(file);
                    boolean alpha = srcimg.getAlphaRaster() != null;
                    BufferedImage bufimg , dst;
                    if(!alpha)
                    {
                    bufimg= new BufferedImage(srcimg.getWidth(), srcimg.getHeight(), BufferedImage.TYPE_INT_RGB);
                    dst = new BufferedImage(srcimg.getWidth(), srcimg.getHeight(), BufferedImage.TYPE_INT_RGB);
                    }
                    else  {
                    	bufimg = new BufferedImage(srcimg.getWidth(), srcimg.getHeight(), BufferedImage.TYPE_INT_ARGB);
                        dst = new BufferedImage(srcimg.getWidth(), srcimg.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    }
                    	
                    
                    Graphics2D g = bufimg.createGraphics();
                    g.drawImage(srcimg, 0, 0, null);
                    g.dispose();
                    g = dst.createGraphics();
                    g.drawImage(srcimg, 0, 0, null);
                    g.dispose();
                    int width = srcimg.getWidth();
                    int height = srcimg.getHeight();
                   /* int r = 255;
                    int g = 10;
                    int b = 57;
                    int alpha = 255;
                    int col = (alpha << 24) | (r << 16) | (g << 8) | b;
                    for(int x = 0; x < bufimg.getWidth(); x++){
                        for(int y = 20; y < 30; y++){
                            img.setRGB(x, y, col);
                        }
                    }*/
                    /*long len = file.length();
                    byte[] bytes = new byte[(int)len];

                    int offset = 0;
                    int numRead = 0;
                    while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                            offset += numRead;
                    }
                    String vidinfo = String.format("Video height: %d, width: %d", height, width);
                    JLabel lbText1 = new JLabel(vidinfo);
                    lbText1.setHorizontalAlignment(SwingConstants.CENTER);          
*/
                    frame = new JFrame();
                    GridBagLayout gLayout = new GridBagLayout();
                    frame.setLayout(gLayout);
                    
                    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);                  
                    //System.out.println("Size of bytes is " +);
                    int index = 0;
                    
                   // get the dimensions of the screen: 
                    KaleidoscopeFilter Kfilt = new KaleidoscopeFilter();
                    //PointillizeFilter pfilt = new PointillizeFilter();
                   int n =  width* height;
                   /*int[] buf = new int[n], linebuf= new int[n];
                   int x,y,i=0;
                   for ( y = 0; y < srcimg.getWidth(); y++) {
                	   for ( x = 0; x < srcimg.getHeight(); x++) {
                		   linebuf[i]= bufimg.getRGB(x, y); i++;
                	   }
                   }*/
                   
                   if (sel==0) {
                	   Kfilt.setSides(5);
                	   dst = Kfilt.filter(bufimg, dst);
                   }
                   else
                   {   
                	   if(!alpha)
                       {
                       bufimg= new BufferedImage(srcimg.getWidth(), srcimg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
                       dst = new BufferedImage(srcimg.getWidth(), srcimg.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
                       }
                       else  {
                       	bufimg = new BufferedImage(srcimg.getWidth(), srcimg.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                           dst = new BufferedImage(srcimg.getWidth(), srcimg.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                       }
                	   g= bufimg.createGraphics();
                	   g.drawImage(srcimg, 0, 0, null);
                       g.dispose();
                       g = dst.createGraphics();
                       g.drawImage(srcimg, 0, 0, null);
                      
                	   /*byte[] pixels = ((DataBufferByte) bufimg.getRaster().getDataBuffer()).getData();
                	   int[][] result = new int[height][width];
                	   int[] res = new int[height*width];*/
                	   /*if (alpha) {
                		   //System.out.println("Alpha1");
                	         final int pixelLength = 4;
                	         for (int pixel = 0, row = 0, col = 0, i=0, j=0; pixel < pixels.length; pixel += pixelLength) {
                	            int argb = 0;
                	            argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                	            //argb += ((int) pixels[pixel + 1] & 0xff); // blue
                	            //argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                	            argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                	            result[row][col] = argb;
                	            dst.setRGB(i, j, argb);
                	            col++;
                	            if (col == width) {
                	               col = 0;
                	               row++;
                	            }
                	         }
                	      } 
                	   else {
                		  // System.out.println("Alpha2");
                		   
                	         final int pixelLength = 3;
                	         for (int pixel = 0, row = 0, col = 0,i=0,j=0; pixel < pixels.length; pixel += pixelLength) {
                	            int argb = 0;
                	            
                	            argb += -16777216; // 255 alpha
                	            argb += ((int) pixels[pixel] & 0xff); // blue
                	            //argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
                	            //argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
                	            result[row][col] = argb;
                	            //dst.setRGB(i, j, argb);
                	            col++;
                	            if (col == width) {
                	               col = 0;
                	               row++;
                	            }
                	         }
                	      }
                   
                	   int pixelLength = 3; int ii=0;
                	   for (int i=0; i<height; i ++) {
                		   for (int j=0; j<width; j ++)
                		   res[ii]= result[i][j];
                	   }
                	   */
                	  // System.out.println("Buf size is "+res.length);
                	   
                	 /*  for (int i=0; i<600; i ++) {
                		   for (int j=0; j<600; j ++) {
                			   g2.setPaint(Color.GREEN);
                		       g2.fill(new Ellipse2D.Double(i, j, 2, 2)); 
                		       //g.setColor(Color.WHITE)3
                		      // g.fillOval(i, j, 1,2);
                		       //if(i+15<600 && j+15<600) {
                	   //result[i][j]=pfilt.getPixel(i, j, res, width, height);
                	    // dst.setRGB(i, j, result[i][j]);
                		   }
                	   }*/
                	 //  dst = Kfilt.filter(bufimg, dst);
                   File f = new File("Output_Img_Pointillism.jpg");
                   //dst = Hfilt.filter(bufimg, dst);
                   //DiffusionFilter dfilt = new DiffusionFilter(); 
                   PointillizeFilter pfilt = new PointillizeFilter(); 
                   //DitherFilter difilt = new DitherFilter();
                   pfilt.setScale(8);
                   pfilt.setEdgeThickness((float) 0.4);
                   pfilt.setFuzziness((float) 0.8);
                   dst = pfilt.filter(bufimg, dst);
                   ImageIO.write(dst, "jpg", f); 
                   is.close();
                   System.exit(0);
                   }                  
                   
                    /* int Width = dummyCanvas.getWidth();
                    	    int Height = dummyCanvas.getHeight();*/
                    	    //System.out.println("width is "+dummyCanvas.toString());
                    	    
                                    /*GridBagConstraints c = new GridBagConstraints();
                                   // frame.getContentPane().add(lbText1, c);
                                    lbIm1 = new JLabel(new ImageIcon(dst));
                                    /*Graphics2D g = bufimg.createGraphics();
                                    };*/
                                    		
                                    //System.out.println(g.drawImage(bufimg, 0, 0, null));
                                    //g.dispose();
                                   /* c.fill = GridBagConstraints.HORIZONTAL;
                                    c.gridx = 0;
                                    c.gridy = 1;*/
                                    //System.out.println();
                                    /*frame.getContentPane().add(lbIm1,c);
                                    frame.pack();*/
                                   // frame.add(gpanel);
                                    /*frame.setVisible(true); */ 
                                    File f = new File("OutputImg_Kaleidoscope.jpg");
                                    ImageIO.write(dst, "jpg", f); 
                                    is.close();

            } catch (FileNotFoundException e) {
                    e.printStackTrace();
            } catch (IOException e) {
                    e.printStackTrace();
            }catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
            }
            // Use labels to display the images
    }

    public static void main(String args[]) throws Exception {
            //ImageReaderOrg play = new ImageReaderOrg();
    	     //System.out.println("Enter 0 for Kaleido, 1 for pointillism");
    		if(args.length < 2 || Integer.parseInt(args[1])>2 || Integer.parseInt(args[1]) < 0 ) {
    		System.out.println("Please enter path and 0 or 1 as argument. Restart the program.");
    		System.exit(0);
    		}
            new Img_Filter(args[0],args[1]);
    }
}

