package dk.itu.mario.scene;

import java.awt.event.MouseEvent;
import dk.itu.mario.engine.sprites.Mario;
import java.awt.Graphics;
import dk.itu.mario.engine.Art;

public class ScrollMessageDecorator extends ViewDecorator{

    private int tick;
    private boolean wasDown = true;

    private String scrollMessage = "";
    

    public ScrollMessageDecorator(Scene s) {
        super(s);
    }

    public void render(Graphics g, float alpha) {
        drawString(g, scrollMessage, 160 - scrollMessage.length() * 4, 160, 0);
    }

    private void drawString(Graphics g, String text, int x, int y, int c)
    {
        char[] ch = text.toCharArray();
        for (int i = 0; i < ch.length; i++)
        {
            g.drawImage(Art.font[ch[i] - 32][c], x + i * 8, y, null);
        }
    }
    
    public void setScrollMessage(String msg) {
        this.scrollMessage = msg;
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
