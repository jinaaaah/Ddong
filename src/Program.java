import processing.core.PApplet;
import processing.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;


public class Program extends PApplet {
    private ResourceManager resourceManager;
    private Player player;
    private boolean playing;

    private List<Bomb> bombs;

    private int bombNum;
    private int tick;
    @Override
    public void keyPressed(KeyEvent event) {
        //37:Left 39: Right
        int key = event.getKeyCode();
        switch (key){
            case 37:
                player.setMode(Player.WALK_LEFT);
                break;
            case 39:
                player.setMode(Player.WALK_RIGHT);
                break;
            case 82:
                reset();
        }

    }

    @Override
    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        switch (key){
            case 37:
                player.setMode(Player.STOP_LEFT);
                break;
            case 39:
                player.setMode(Player.STOP_RIGHT);
                break;
        }
    }



    @Override
    public void draw() {

        if(playing)
            displayLose();
        else{
            background(0);
            tick++;
            if(tick % 150 == 0){
                bombNum  += 1;
            }

            player.update();
            player.render();

            for(Bomb bomb: bombs){
                bomb.update();
            }
            for(Bomb bomb : bombs){
                if(tick % 450 == 0){
                    bomb.updateLevel();
                }
                bomb.render();
            }
            for(Bomb bomb: bombs){
                if(player.checkCollision(bomb.getX(),bomb.getY())){
                    playing =true;
                }
            }
        }

    }

    public void displayLose(){
        background(0);
        text("GoodBye",100 , 100);
    }

    public void reset(){
        settings();
        draw();
    }
    @Override
    public void settings() {
        size(800, 600);
        resourceManager = new ResourceManager();
        resourceManager.init(this);
        resourceManager.cropImage("bomb","img/bomberman-effect.png",24,24,3,1);
        resourceManager.cropImage("character_Move","img/bomberman-movement.png",21,32,5,4);
        resourceManager.cropImage("character_Stay","img/bomberman-stay.png",21,32,3,4);

        tick = 1;
        player = new Player(this, resourceManager);
        playing = false;
        bombNum = 30;
        bombs = new ArrayList<>();

        createBomb();

    }

    private void createBomb(){


        for(int i = 0; i < bombNum; i++){
            Bomb bomb = new Bomb(this, resourceManager);
            if(i % 10 == 0){

                bomb.setScale(true);
            }else{
                bomb.setScale(false);
            }
            bombs.add(bomb);
        }

    }

    @Override
    public void setup() {
        background(0);
    }

    public static void main(String[] args) {
        PApplet.main("Program");
    }
}
