import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.event.SwingPropertyChangeSupport;

public class GamePanel extends JPanel implements Runnable
{
  // SCREEN SETTINGS
  final int originalTileSize = 16; //16x16 tile, default size
  final int scale = 3;

  final int tileSize = originalTileSize * scale; //48x48 tile

  //Change this ratio - rn 3 x 4
  final int maxScreenCol = 12;
  final int maxScreenRow = 16;
  final int screenWidth = tileSize * maxScreenCol; // 768 pixels
  final int screenHeight = tileSize * maxScreenRow; // 576 
  
  //FPS
  int fps = 60;

  //KEY HANDLER
  KeyHandler keyH = new KeyHandler();

  //TIMER
  //Similar to a timer
  Thread gameThread;

  //PLAYER POS
  int playerX = 100;
  int playerY = 100;

  //Can update this later for a stat boost
  int playerSpeed = 4;

  //CONSTRUCTOR
  public GamePanel()
  {
    //maybe change this to this.setBounds if Dimension class isn't offering anything
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);

    // basically allows all the drawing in the component to be done in an offscreen painting buffer
    //basically improves the rendering performance so for our game we might not need but might as well keep
    this.setDoubleBuffered(true);

    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  //THREAD METHODS
  public void startGameThread()
  {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() 
  {
    //1 second
    //draws every 1/60 second
    double drawInterval = 1000000000/fps;
    double newDrawTime = System.nanoTime() + drawInterval;

    //while the gamethread exists it repeats
    while(gameThread != null)
    {
      System.out.println("The Game Loop is Running");

      //the current time in nanoseconds
      //allows to keep track of how long the loops runs
      long currentTime = System.nanoTime();

      //update information like charcter positions
      update();

      //draw the screen with the updated info 
      repaint();
     
     //pauses game loop for remainingTime 
     //will not do anything until sleep time is over
      try
      {
       double remainingTime = nextDrawTime - System.nanoTime();
       Thread.sleep((long) remainingTime);
       if(remainingTime < 0 )
       {
        remainingTime = 0;
       }
       nextDrawTime += drawInterval;
      }
      catch(InterruptedException e){
        e.printStackTrace();
      }
      
    }
  }

  public void update()
  {
    if(keyH.upPressed)
      playerY -= playerSpeed;
    if(keyH.downPressed)
      playerY += playerSpeed;
    if(keyH.rightPressed)
      playerX += playerSpeed;
    if(keyH.leftPressed)
      playerX += playerSpeed;

  }

  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    //graphics 2D has more functions
    Graphics2D g2 = (Graphics2D)g;

    g2.setColor(Color.white);

    g2.fillRect(playerX, playerY, tileSize, tileSize);

    //this line just helps save memory, not needed
    g2.dispose();
  }
}
