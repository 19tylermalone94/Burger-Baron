package main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class KeyHandler implements KeyListener{

    // movement variables
    public boolean upPressed = false;
    public boolean downPressed = false;
    public boolean leftPressed = false;
    public boolean rightPressed = false;
    
    // action variables
    public boolean gKeyPressed = false;
    public boolean xKeyPressed = false;
    public boolean mKeyPressed = false;
    public boolean sKeyPressed = false;
    public boolean oKeyPressed = false;
    public boolean rKeyPressed = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;   
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;   
        }
        if (code == KeyEvent.VK_G) {
            gKeyPressed = true;
        }
        if (code == KeyEvent.VK_X) {
            xKeyPressed = true;
        }
        if (code == KeyEvent.VK_M) {
            mKeyPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            sKeyPressed = true;
        }
        if (code == KeyEvent.VK_O) {
            oKeyPressed = true;
        }
        if (code == KeyEvent.VK_R) {
            rKeyPressed = true;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;   
        }
        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;   
        }
        if (code == KeyEvent.VK_G) {
            gKeyPressed = false;
        }
        if (code == KeyEvent.VK_X) {
            xKeyPressed = false;
        }
        if (code == KeyEvent.VK_M) {
            mKeyPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            sKeyPressed = false;
        }
        if (code == KeyEvent.VK_O) {
            oKeyPressed = false;
        }
        if (code == KeyEvent.VK_R) {
            rKeyPressed = false;
        }
        
    }
    
}
