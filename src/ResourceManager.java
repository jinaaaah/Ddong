import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class ResourceManager {

    private HashMap<String, PImage[]> hashMap;
    private PApplet pApplet;

    //초기화
    public void init(PApplet p){
        pApplet = p;
        hashMap = new HashMap<>();
    }

    //이미지를 불러내 크롭
    public void cropImage(String name, String path, int width, int height, int countX, int countY){
        PImage[] images = new PImage[countX * countY];
        PImage imgSrc = pApplet.loadImage(path);

        for(int i = 0; i<countX; i++){
            for(int j = 0; j<countY; j++){
                images[(j * countX) + i] = imgSrc.get(width * i, height * j, width,height);
            }
        }

        hashMap.put(name,images);
    }

    //이미지 불러내기
    public PImage getImage(String name, int index){
        return hashMap.get(name)[index];
    }

}
