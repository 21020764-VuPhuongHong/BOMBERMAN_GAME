package uet.oop.bomberman.control;

import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.enemies.Kondoria;
import uet.oop.bomberman.entities.enemies.Ovapi;
import uet.oop.bomberman.entities.enemies.Pontan;
import uet.oop.bomberman.entities.items.BombPassItem;
import uet.oop.bomberman.entities.items.WallPassItem;

import java.security.Key;

public class Move {
    public static void moveLeft(Entity e) {
        if (canMoveLeft(e)) {
            e.setX(e.getX() - e.getVelocityX());
        }
    }

    public static void moveRight(Entity e) {
        if (canMoveRight(e)) {
            e.setX(e.getX() + e.getVelocityX());
        }
    }

    public static void moveUp(Entity e) {
        if (canMoveUp(e)) {
            e.setY(e.getY() - e.getVelocityY());
        }
    }

    public static void moveDown(Entity e) {
        if (canMoveDown(e)) {
            e.setY(e.getY() + e.getVelocityY());
        }
    }

    public static boolean canMoveLeft(Entity e) {
        if (CollisionHandle.checkWall(e.getX() - e.getVelocityX(), e.getY())) {
            return false;
        }

        if (CollisionHandle.checkBomb(e.getX() - e.getVelocityX(), e.getY())) {
            if (!(e instanceof Bomber)) {
                return false;
            } else {
                boolean bomberCanMove = false;

                if (BombPassItem.canPassBomb) {
                    bomberCanMove = true;
                }

                if (Bomb.bomberFirstGoLeftThroughBomb) {
                    Bomb.bomberFirstGoRightThroughBomb = false;
                    Bomb.bomberFirstGoUpThroughBomb = false;
                    Bomb.bomberFirstGoDownThroughBomb = false;
                    bomberCanMove = true;
                }

                if (!bomberCanMove) {
                    return false;
                }
            }
        }

        if (CollisionHandle.checkBrick(e.getX() - e.getVelocityX(), e.getY())) {
            if (e instanceof Bomber && WallPassItem.canPassBrick) {
                return true;
            }

            if (e instanceof Ovapi || e instanceof Kondoria || e instanceof Pontan) {
                return true;
            }

            return false;
        }

        return true;
    }

    public static boolean canMoveRight(Entity e) {
        if (CollisionHandle.checkWall(e.getX() + e.getVelocityX(), e.getY())) {
            return false;
        }

        if (CollisionHandle.checkBomb(e.getX() + e.getVelocityX(), e.getY())) {
            if (!(e instanceof Bomber)) {
                return false;
            } else {
                boolean bomberCanMove = false;

                if (BombPassItem.canPassBomb) {
                    bomberCanMove = true;
                }

                if (Bomb.bomberFirstGoRightThroughBomb) {
                    Bomb.bomberFirstGoLeftThroughBomb = false;
                    Bomb.bomberFirstGoUpThroughBomb = false;
                    Bomb.bomberFirstGoDownThroughBomb = false;
                    bomberCanMove = true;
                }

                if (!bomberCanMove) {
                    return false;
                }
            }
        }

        if (CollisionHandle.checkBrick(e.getX() + e.getVelocityX(), e.getY())) {
            if (e instanceof Bomber && WallPassItem.canPassBrick) {
                return true;
            }

            if (e instanceof Ovapi || e instanceof Kondoria || e instanceof Pontan) {
                return true;
            }

            return false;
        }

        return true;
    }

    public static boolean canMoveUp(Entity e) {
        if (CollisionHandle.checkWall(e.getX(), e.getY() - e.getVelocityY())) {
            return false;
        }

        if (CollisionHandle.checkBomb(e.getX(), e.getY() - e.getVelocityY())) {
            if (!(e instanceof Bomber)) {
                return false;
            } else {
                boolean bomberCanMove = false;

                if (BombPassItem.canPassBomb) {
                    bomberCanMove = true;
                }

                if (Bomb.bomberFirstGoUpThroughBomb) {
                    Bomb.bomberFirstGoLeftThroughBomb = false;
                    Bomb.bomberFirstGoRightThroughBomb = false;
                    Bomb.bomberFirstGoDownThroughBomb = false;
                    bomberCanMove = true;
                }

                if (!bomberCanMove) {
                    return false;
                }
            }
        }

        if (CollisionHandle.checkBrick(e.getX(), e.getY() - e.getVelocityY())) {
            if (e instanceof Bomber && WallPassItem.canPassBrick) {
                return true;
            }

            if (e instanceof Ovapi || e instanceof Kondoria || e instanceof Pontan) {
                return true;
            }

            return false;
        }

        return true;
    }

    public static boolean canMoveDown(Entity e) {
        if (CollisionHandle.checkWall(e.getX(), e.getY() + e.getVelocityY())) {
            return false;
        }

        if (CollisionHandle.checkBomb(e.getX(), e.getY() + e.getVelocityY())) {
            if (!(e instanceof Bomber)) {
                return false;
            } else {
                boolean bomberCanMove = false;

                if (BombPassItem.canPassBomb) {
                    bomberCanMove = true;
                }

                if (Bomb.bomberFirstGoDownThroughBomb) {
                    Bomb.bomberFirstGoLeftThroughBomb = false;
                    Bomb.bomberFirstGoRightThroughBomb = false;
                    Bomb.bomberFirstGoUpThroughBomb = false;
                    bomberCanMove = true;
                }

                if (!bomberCanMove) {
                    return false;
                }
            }
        }

        if (CollisionHandle.checkBrick(e.getX(), e.getY() + e.getVelocityY())) {
            if (e instanceof Bomber && WallPassItem.canPassBrick) {
                return true;
            }

            if (e instanceof Ovapi || e instanceof Kondoria || e instanceof Pontan) {
                return true;
            }

            return false;
        }

        return true;
    }
}
