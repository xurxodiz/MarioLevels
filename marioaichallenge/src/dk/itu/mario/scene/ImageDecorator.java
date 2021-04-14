package dk.itu.mario.scene;
import java.awt.Color;
import java.awt.event.MouseEvent;
import dk.itu.mario.engine.sprites.Mario;
import java.awt.Graphics;
import dk.itu.mario.engine.Art;
import java.awt.Image;

public class ImageDecorator extends ViewDecorator {
    private int tick;
    private boolean wasDown = true;

    private String colour = "";
    private static Image image;
    private int param;
    
    public ImageDecorator(Scene s) {
        super(s);
    }

    public void render(Graphics g, float alpha) {
        g.setColor(Color.decode(colour));
        g.fillRect(0, 0, 320, 240);
        g.drawImage(image, 160-48, param, null);
    }
    
    public void setColour(String clr) {
        this.colour = clr;
    }

    public void setImage(String status) {
        if (status.equals("WIN")) {
            image = Art.endScene[tick / 24 % 2][0];
            param = 52;
        }
        else if (status.equals("LOSE")) {
            int f = tick/3%10;
            if (f >= 6)
                f = 10 - f;
            image = Art.gameOver[f][0];
            param = 68;
        }
    }

    @Override
    public float getX(float alpha) {
        return 0;
    }

    @Override
    public float getY(float alpha) {
        return 0;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        return;
    }

    @Override
    public void init() {
        return;
    }

    @Override
    public void tick()
    {
        tick++;
        if (!wasDown && keys[Mario.KEY_JUMP])
        {
            //component.toTitle();
        }
        if (keys[Mario.KEY_JUMP])
        {
            wasDown = false;
        }
    }
}
