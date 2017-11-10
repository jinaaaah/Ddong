import processing.core.PApplet;

import java.util.Random;

public class Bomb extends RenderObject {

    public static final int VISIBLE = 0;


    Random random;
    private float velocity = 0;
    private float accel = 0;
    private boolean isScale;
    protected int level;

    public Bomb(PApplet pApplet, ResourceManager resourceManager) {
        super(pApplet, resourceManager);
        random = new Random();

        this.x = random.nextInt(750) + 24;
        this.y = -random.nextInt(1000) - 24;
        this.accel = (random.nextInt(6)+2) / 100f + 0.01f;
        this.isScale = false;

        addMode(VISIBLE, "bomb",new int[] {0,1,2});
        setMode(VISIBLE);

        level = 1;
    }
    public void updateLevel(){
        level++;
        System.out.println(level);
    }

    public void setScale(boolean scale) {
        isScale = scale;
    }

    @Override
    public void update() {
        super.update();

        if(mode == VISIBLE){
            if(y < 570){
                if(velocity < 10f){
                    this.velocity += this.accel;
                }
                y += velocity;
            }else {
                this.x = random.nextInt(750) + 24;
                this.y = -random.nextInt(1000);
                this.velocity = 0;
                this.accel = (random.nextInt(6)+2) / 100f  + 0.01f;
            }
        }

    }

    @Override
    public void render() {
        tick++;
        pImage = images[(tick/ 8) % images.length];
        //System.out.println(level + " , " + pImage.width);
        pApplet.pushMatrix();
        if(isScale){
            pApplet.scale((float)(1 +(level/2)));
        }
        pApplet.image(pImage,x,y);
        pApplet.popMatrix();
//        super.render();
    }
}
