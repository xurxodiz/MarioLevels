package dk.itu.mario.scene;

import java.awt.Graphics;

public abstract class ViewDecorator extends Scene {
    protected Scene decoratedScene;

    public ViewDecorator(Scene dS) {
        this.decoratedScene = dS;
    }

    public void render(Graphics g, float alpha) {
        this.decoratedScene.render(g, alpha);
    }
}
