package ru.ekaerovets;

import ru.ekaerovets.items.Hoplite;
import ru.ekaerovets.items.Magma;
import ru.ekaerovets.items.Wizard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author labirint
 *         date 21.06.17
 */
public class Main extends JFrame {

    private Board board = new Board();

    public static void main(String args[]) {
        new Main();
    }

    public Main() {
        super("My Frame");
        setContentPane(new DrawPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //create a component that you can actually draw on.
    class DrawPane extends JPanel {

        String itemType = "";

        {
            addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {

                }

                @Override
                public void mousePressed(MouseEvent e) {
                    onClick(e);
                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });

            setFocusable(true);

            addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {
                    System.out.println(e.getKeyCode());
                    switch (e.getKeyCode()) {
                        case 87: itemType = "W"; break;
                        case 72: itemType = "H"; break;
                        case 66: itemType = "B"; break;
                        case 65: itemType = "A"; break;
                        case 77: itemType = "M"; break;
                        case 75: itemType = "K"; break;
                        case 76: itemType = "L"; break;
                    }
                    repaint();
                }

                @Override
                public void keyReleased(KeyEvent e) {

                }
            });
        }

        int bases[] = {0, 7, 15, 24, 34, 44, 53, 61, 68};

        void onClick(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            x -= 50;
            y -= 80;
            int xx = x / 20;
            y += x / 2 + xx;
            int yy = y / 22;

            if (xx < 0 || xx > 8) {
                return;
            }

            if (yy < 0 || yy < xx - 4 || yy > 10 || yy > xx + 6) {
                return;
            }

            handleCellClick(yy + bases[xx]);

        }

        void handleCellClick(int index) {
            if (board.cells[index].item == null) {
                switch (itemType) {
                    case "W": board.cells[index].item = new Wizard(); break;
                    case "M": board.cells[index].item = new Magma(); break;
                    case "H": board.cells[index].item = new Hoplite(); break;
                }
            } else {
                board.cells[index].item = null;
            }
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            for (Cell cell : board.cells) {
                drawCell(g, cell);
            }

            g.setColor(Color.black);
            g.drawString(itemType, 10, 10);

        }

        private void drawCell(Graphics g, Cell cell) {
            int x = cell.x * 20 + 50;
            int y = cell.y * 22 - cell.x * 11 + 80;
            Polygon p = new Polygon(new int[]{x, x + 7, x + 20, x + 27, x + 20, x + 7},
                    new int[]{y, y - 11, y - 11, y, y + 11, y + 11}, 6);

            if (cell.item instanceof Magma) {
                g.setColor(new Color(114, 0, 0));
            } else {
                g.setColor(Color.gray);
            }

            g.fillPolygon(p);
            g.setColor(Color.black);
            g.drawPolygon(p);
            if (cell.item != null) {
                g.setColor(Color.yellow);
                g.drawString(cell.item.getSymbol(), x + 10, y + 5);
            }
        }

    }

}