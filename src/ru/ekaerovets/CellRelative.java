package ru.ekaerovets;

/**
 * @author labirint
 *         date 21.06.17
 */
public class CellRelative {

    boolean isRay;
    int dist;

    public CellRelative(int dx, int dy) {
        if (dx < 0) {
            dx = -dx;
        }

        if (dx == 0) {
            isRay = true;
            dist = dy < 0 ? -dy : dy;
        } else if (dy == 0) {
            isRay = true;
            dist = dx;
        } else if (dx == dy) {
            isRay = true;
            dist = dx;
        } else {
            isRay = false;
            dist = dx;
            if (dy < 0) {
                dist -= dy;
            } else if (dy > dx) {
                dist += dy - dx;
            }
        }
    }

}
