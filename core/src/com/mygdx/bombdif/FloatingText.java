package com.mygdx.bombdif;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FloatingText extends Actor {
    private final String text;
    private final long animationDuration;
    private BitmapFont fontw;
    private float deltaX;
    private float deltaY;
    private boolean animated = false;
    private long animationStart;
    private float ogTrans;
    private float ogPos;
    private boolean flag=true;

    public FloatingText(BitmapFont fontw, String text, long animationDuration) {
        this.fontw = fontw;
        this.text = text;
        this.animationDuration = animationDuration;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (animated) {
            /*// The component will auto-destruct when animation is finished.
            if (isDisposable()) {
                dispose();
                return;
            }*/

            float elapsed = System.currentTimeMillis() - animationStart;

            // The text will be fading.
            if (elapsed == 0) {
                fontw.setColor(getColor().r, getColor().g, getColor().b, ogTrans);
                fontw.draw(batch, text, getX() , ogPos);
            }else {
                fontw.setColor(getColor().r, getColor().g, getColor().b, parentAlpha * (1 - elapsed / animationDuration));
                fontw.draw(batch, text, getX() + deltaX * elapsed / 1000f, getY() + deltaY * elapsed / 1000f);
            }

            if (isFinished()){
                animated = false;
            }
        }
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    public void animate() {
        animated = true;
        animationStart = System.currentTimeMillis();
        if (flag){
            ogTrans = 1;
            ogPos = getY();
            flag = false;
        }
    }

    public boolean isAnimated() {
        return animated;
    }

    /**
     * @return true is the animation has finished.
     */
    private boolean isFinished() {
        return animationStart + animationDuration < System.currentTimeMillis();
    }

    /**
     * Dispose the component. <b>Note that all the children components also should
     * be disposed otherwise a memory leak will occur.</b>
     */
    public void dispose() {
        fontw.dispose();
        remove();
    }
}
