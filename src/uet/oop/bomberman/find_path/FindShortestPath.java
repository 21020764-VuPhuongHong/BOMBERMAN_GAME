package uet.oop.bomberman.find_path;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.control.CollisionHandle;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.still_block.Wall;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;

public class FindShortestPath {
    public static String hash(Rectangle2D rect) {
        int x = (int) rect.getMinX();
        int y = (int) rect.getMinY();

        return x + " " + y;
    }

    public static boolean checkCanMove(Rectangle2D rect) {
        Rectangle2D rect1 = new Rectangle2D(rect.getMinX(), rect.getMinY(), rect.getWidth(), rect.getHeight());
        for (Entity e : BombermanGame.stillObjects) {
            if (e instanceof Wall) {
                if (CollisionHandle.intersects(e, rect1)) {
                    return false;
                }
            }
        }

        for (Entity e : BombermanGame.entities) {
            if (e instanceof Brick) {
                if (CollisionHandle.intersects(e, rect1)) {
                    return false;
                }
            }
        }

        for (Bomb bomb : BombermanGame.listBombs) {
            if (CollisionHandle.intersects(bomb, rect1)) {
                return false;
            }
        }

        return true;
    }

    public static int find(Entity s, Entity t) {
        // 1 : up
        // 2: right
        // 3: down
        // 4: left

        int direct_res = -1;
        if (!CollisionHandle.intersects(s, t)) {
            try {
                int velocityX = 2;
                int velocityY = 2;

                int[] direct_x = {0, velocityX, 0, -velocityX};
                int[] direct_y = {-velocityY, 0, velocityY, 0};

                int max_cell = 9999;

                ArrayList<String> color = new ArrayList<>();
                int[] pre = new int[max_cell];
                int[] direct = new int[max_cell];
                Rectangle2D[] queue = new Rectangle2D[max_cell];

                for (int i = 0; i < max_cell; ++i) {
                    direct[i] = -1;
                }

                int L = 0, R = -1;
                queue[++R] = CollisionHandle.getBoundary(s);
                pre[R] = -1;
                direct[R] = -1;
                /**color.add(getBoundary(s).hashCode());*/
                color.add(hash(CollisionHandle.getBoundary(s)));

                int stop_point = R;
                boolean flag = false;


                while (L <= R) {
                    Rectangle2D v = queue[L++];
                    for (int i = 0; i < 4; ++i) {
                        Rectangle2D rect_move = new Rectangle2D(v.getMinX() + (double) direct_x[i], v.getMinY() + (double) direct_y[i], v.getWidth(), v.getHeight());
                        String h_rect_move = hash(rect_move);
                        if (color.contains(h_rect_move)) {
                            //System.out.println("visited " + color.contains(h_rect_move) + " " + h_rect_move);
                            continue;
                        }


                        if (!checkCanMove(rect_move)) {
                            //System.out.println("collision");
                            continue;
                        }

                        color.add(h_rect_move);
                        queue[++R] = rect_move;
                        direct[R] = i + 1;
                        pre[R] = L - 1;

                        if (CollisionHandle.intersects(t, rect_move)) {
                            stop_point = R;
                            //System.out.println("stop_point" + stop_point);
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }

                //trace
                while (true) {
                    if (stop_point == -1) {
                        break;
                    }

                    if (direct[stop_point] != -1) {
                        direct_res = direct[stop_point];
                    }
                    stop_point = pre[stop_point];
                }


                //System.out.println("right: " + R);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        //System.out.println("trace: " + direct_res);
        return direct_res;
    }
}