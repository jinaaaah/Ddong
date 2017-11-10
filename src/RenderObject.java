import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public abstract class RenderObject {

    protected int mode;
    protected HashMap<Integer, PImage[]> imageMap;
    protected int x, y;
    protected PApplet pApplet;
    protected ResourceManager resourceManager;
    protected PImage pImage;

    protected PImage[] images;

    public RenderObject(PApplet pApplet, ResourceManager resourceManager) {
        this.pApplet = pApplet;
        this.resourceManager = resourceManager;
        this.imageMap = new HashMap<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //그리기 업데이트
    public void update(){

    }
    //상태 지정
    public void setMode(int mode){
        this.mode = mode;
        images = imageMap.get(mode);
    }

    //상태 추가
    public void addMode(int mode, String resourceId, int[] indices){
        PImage[] curImages = new PImage[indices.length];

        for(int i = 0; i< indices.length; i++){
            curImages[i] = resourceManager.getImage(resourceId,indices[i]);
        }
        imageMap.put(mode,curImages);
    }

    protected int tick = 0;
    //그리기
    public void render(){
        tick++;
        pImage = images[tick/ 8 % images.length];
        pApplet.image(pImage,x,y);
    }
}
