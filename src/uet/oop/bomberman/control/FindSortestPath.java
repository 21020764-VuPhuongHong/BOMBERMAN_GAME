package uet.oop.bomberman.control;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Block.Brick;
import uet.oop.bomberman.entities.Block.Wall;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;

public class FindSortestPath extends CollisionHandle{
    public static int mod = 99999;
    public static String hash(Rectangle2D rect) {
        Integer x = (int)rect.getMinX();
        Integer y = (int)rect.getMinY();

        return x.toString() + " " + y.toString();
    }

    public static boolean checkCanMove(Rectangle2D rect) {
        Rectangle2D rect1 = new Rectangle2D(rect.getMinX(), rect.getMinY(), rect.getWidth(), rect.getHeight());
        for(Entity e: BombermanGame.stillObjects) {
            if(e instanceof Wall)
            {
                if(intersects(e, rect1)) {
                    //System.out.println(e.getX() + " " + e.getVelocityY() + " " + e.getWidth() + "Wall");
                    return false;
                }
            }
        }

        for(Entity e: BombermanGame.entities)
        {
            if(e instanceof Brick) {
                if(intersects(e, rect1)) {
                    //++System.out.println(e.getX() + " " + e.getVelocityY() + " " + e.getWidth() + "Brick");
                    return false;
                }
            }
        }

        return true;
    }

    public static int Find(Entity s, Entity t) {
        // 1 : up
        // 2: right
        // 3: down
        // 4: left

        int direct_res = -1;

        try {

            int velocityX = 2;//Sprite.wall.SIZE;
            int velocityY = 2;//Sprite.wall.SIZE;

            int[] direct_x = {0, velocityX, 0, -velocityX};
            int[] direct_y = {-velocityY, 0, velocityY, 0};

            int max_cell = 100000;

            ArrayList <String> color = new ArrayList<>();
            int[] pre = new int[max_cell];
            int[] direct = new int[max_cell];
            Rectangle2D[] queue = new Rectangle2D[max_cell];

            for(int i = 0; i < max_cell; ++i) {
                direct[i] = -1;
            }

            int L = 0, R = -1;
            queue[++R] = getBoundary(s);
            pre[R] = -1;
            direct[R] = -1;
            /**color.add(getBoundary(s).hashCode());*/
            color.add(hash(getBoundary(s)));

            System.out.println("s: " + hash(getBoundary(s)));
            int stop_point = R;
            boolean flag = false;

            while (L <= R) {
                Rectangle2D v = queue[L++];
                for(int i = 0;i < 4; ++i) {
                    //System.out.println("vel: " + s.getVelocityX() + " " + s.getVelocityY());
                    Rectangle2D rect_move = new Rectangle2D(v.getMinX() + (double)direct_x[i], v.getMinY() + (double)direct_y[i], v.getWidth(), v.getHeight());
                    //int h_rect_move = rect_move.hashCode();
                    String h_rect_move = hash(rect_move);
                    //System.out.println("v: " + v.getMinX() + " " + v.getMinY());
                    ///System.out.println("rect: "+ rect_move.getMinX() + " " + rect_move.getMinY());
                    ///System.out.println("rect_move" + (v.getMinX() + (double)direct_x[i]) + " " +  (v.getMinY() + (double)direct_y[i]));
                    if(color.contains(h_rect_move)) {
                        //System.out.println("visited" + color.contains(h_rect_move) + " " + h_rect_move);
                        continue;
                    }


                    if(!checkCanMove(rect_move)) {
                       // System.out.println("collision");
                        continue;
                    }

                    color.add(h_rect_move);
                    queue[++R] = rect_move;
                    direct[R] = i + 1;
                    pre[R] = L-1;

                    if(intersects(t, rect_move)) {
                        stop_point = R;
                        System.out.println("stop_point" + stop_point);
                        flag = true;
                        break;
                    }
                }
                if(flag) {break;}
            }

            //trace
            while(true) {
                if(stop_point == -1) {
                    break;
                }

                if(direct[stop_point] != -1) {
                    direct_res = direct[stop_point];
                }
                stop_point = pre[stop_point];
            }

            System.out.println("right: " + R);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("trace: " + direct_res);
        return direct_res;
    }
}