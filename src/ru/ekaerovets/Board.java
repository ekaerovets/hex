package ru.ekaerovets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author labirint
 *         date 21.06.17
 */
public class Board {

    private Cell[] cells;

    private int[][] neighbours = new int[79][];

    private int[] xCache = new int[79];
    private int[] yCache = new int[79];
    private int[] xyCache = new int[121];

    {
        cells = new Cell[79];
        Arrays.fill(xyCache, -1);

        int index = 0;
        for (int i = 0; i < 9; i++) {
            int start = i - 4 > 0 ? i - 4 : 0;
            int stop = 7 + i > 11 ? 11 : 7 + i;
            for (int j = start; j < stop; j++) {
                xCache[index] = i;
                yCache[index] = j;
                xyCache[i + j * 11] = index;
                cells[index++] = new Cell(i, j);
            }
        }

    }

    public Cell cellAt(int id) {
        return cells[id];
    }

    public int[] getNeighbours(int id) {
        if (neighbours[id] == null) {
            neighbours[id] = calcNeighboursInternal(id);
        }
        return neighbours[id];
    }

    private void addIfValid(int x, int y, List<Integer> dest) {
        int id = xy2id(x, y);
        if (id >= 0) {
            dest.add(id);
        }
    }
    private int[] calcNeighboursInternal(int id) {
        List<Integer> values = new ArrayList<>();

        int x = xCache[id];
        int y = yCache[id];

        addIfValid(x - 1, y - 1, values);
        addIfValid(x - 1, y, values);
        addIfValid(x, y - 1, values);
        addIfValid(x, y + 1, values);
        addIfValid(x + 1, y, values);
        addIfValid(x + 1, y + 1, values);

        int[] res = new int[values.size()];
        for (int i = 0; i < values.size(); i++) {
            res[i] = values.get(i);
        }
        return res;
    }

    // returns -1 if coordinates are invalid
    public int xy2id(int x, int y) {
        if (x < 0 || x > 8) {
            return -1;
        }
        if (y < 0 || y > 10) {
            return -1;
        }
        return xyCache[x + y * 11];
    }

}
