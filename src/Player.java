import processing.core.PApplet;

public class Player extends RenderObject {

    public static final int STOP_LEFT = -1;
    public static final int WALK_LEFT = 0;
    public static final int STOP_RIGHT = -2;
    public static final int WALK_RIGHT = 1;

    private float velocity = 0;
    private float accel = 0.1f;

    public Player(PApplet pApplet, ResourceManager resourceManager) {
        super(pApplet, resourceManager);

        this.x = 10;
        this.y = 450;

        addMode(STOP_LEFT, "character_Stay",new int[] {9,10,11});
        addMode(WALK_RIGHT, "character_Move",new int[] {9,8,7,6,5});
        addMode(STOP_RIGHT, "character_Stay",new int[] {3,4,5});
        addMode(WALK_LEFT, "character_Move",new int[] {19,18,17,16,15});

        setMode(STOP_LEFT);
    }

    @Override
    public void update() {
        super.update();
        //키 입력에 따른 render

        if(mode == WALK_LEFT){
            if(x - 5 > 0){
                this.velocity -= this.accel;
            }else{
                this.velocity = 0;
            }
        }
        else if(mode == WALK_RIGHT){
            if(x + 26 < 800){
                this.velocity += this.accel;
            }else{
                this.velocity = 0;
            }
        }
        else{
            if(this.velocity > 0) this.velocity -= this.accel;
            if(this.velocity < 0) this.velocity += this.accel;
        }

        if(Math.abs(this.velocity) > 5){
            if(this.velocity > 0) this.velocity = 5;
            if(this.velocity < 0) this.velocity = -5;
         }
        this.x += velocity;
    }

    public boolean checkCollision(int bombX, int bombY){
        return (bombX + 24 > this.x && bombX < this.x + 21
                && bombY + 24 > this.y && bombY < this.y + 32);
    }

}
