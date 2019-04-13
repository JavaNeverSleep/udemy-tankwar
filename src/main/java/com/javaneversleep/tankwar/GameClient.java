package com.javaneversleep.tankwar;

import com.alibaba.fastjson.JSON;
import com.javaneversleep.tankwar.Save.Position;
import org.apache.commons.io.FileUtils;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class GameClient extends JComponent {

    static final int WIDTH = 1366, HEIGHT = 768;

    private static final GameClient INSTANCE = new GameClient();

    private static final String GAME_SAVE = "game.sav";

    static GameClient getInstance() {
        return INSTANCE;
    }

    private Tank playerTank;

    private List<Tank> enemyTanks;

    private final AtomicInteger enemyKilled = new AtomicInteger(0);

    private List<Wall> walls;

    private List<Missile> missiles;

    private List<Explosion> explosions;

    private Blood blood;

    Blood getBlood() {
        return blood;
    }

    void addExplosion(Explosion explosion) {
        explosions.add(explosion);
    }

    void add(Missile missile) {
        missiles.add(missile);
    }

    Tank getPlayerTank() {
        return playerTank;
    }

    List<Tank> getEnemyTanks() {
        return enemyTanks;
    }

    List<Wall> getWalls() {
        return walls;
    }

    private GameClient() {
        this.playerTank = new Tank(400, 100, Direction.DOWN);
        this.missiles = new CopyOnWriteArrayList<>();
        this.explosions = new ArrayList<>();
        this.blood = new Blood(400, 250);
        this.walls = Arrays.asList(
            new Wall(280, 140, true, 12),
            new Wall(280, 540, true, 12),
            new Wall(100, 160, false, 12),
            new Wall(700, 160, false, 12)
        );
        this.initEnemyTanks();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void initEnemyTanks() {
        this.enemyTanks =  new CopyOnWriteArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                this.enemyTanks.add(new Tank(200 + j * 120, 400 + 40 * i, true, Direction.UP));
            }
        }
    }

    private final static Random RANDOM = new Random();

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (!playerTank.isLive()) {
            g.setColor(Color.RED);
            g.setFont(new Font(null, Font.BOLD, 100));
            g.drawString("GAME OVER", 100, 200);
            g.setFont(new Font(null, Font.BOLD, 60));
            g.drawString("PRESS F2 TO RESTART", 60, 360);
        } else {
            g.setColor(Color.WHITE);
            g.setFont(new Font(null, Font.BOLD, 16));
            g.drawString("Missiles: " + missiles.size(), 10, 50);
            g.drawString("Explosions: " + explosions.size(), 10, 70);
            g.drawString("Player Tank HP: " + playerTank.getHp(), 10, 90);
            g.drawString("Enemy Left: " + enemyTanks.size(), 10, 110);
            g.drawString("Enemy Killed: " + enemyKilled.get(), 10, 130);
            g.drawImage(Tools.getImage("tree.png"), 720, 10, null);
            g.drawImage(Tools.getImage("tree.png"), 10, 520, null);

            playerTank.draw(g);
            if (playerTank.isDying() && RANDOM.nextInt(3) == 2) {
                blood.setLive(true);
            }
            if (blood.isLive()) {
                blood.draw(g);
            }

            int count = enemyTanks.size();
            enemyTanks.removeIf(t -> !t.isLive());
            enemyKilled.addAndGet(count - enemyTanks.size());
            if (enemyTanks.isEmpty()) {
                this.initEnemyTanks();
            }
            for (Tank tank : enemyTanks) {
                tank.draw(g);
            }
            for (Wall wall : walls) {
                wall.draw(g);
            }

            missiles.removeIf(m -> !m.isLive());
            for (Missile missile : missiles) {
                missile.draw(g);
            }

            explosions.removeIf(e -> !e.isLive());
            for (Explosion explosion : explosions) {
                explosion.draw(g);
            }
        }
    }

    public static void main(String[] args) {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
        JFrame frame = new JFrame();
        frame.setTitle("史上最无聊的坦克大战！");
        frame.setIconImage(new ImageIcon("assets/images/icon.png").getImage());
        final GameClient client = GameClient.getInstance();
        frame.add(client);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    client.save();
                    System.exit(0);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Failed to save current game!",
                        "Oops! Error Occurred", JOptionPane.ERROR_MESSAGE);
                    System.exit(4);
                }
            }
        });
        frame.pack();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                client.playerTank.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                client.playerTank.keyReleased(e);
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        try {
            client.load();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load previous game!",
                "Oops! Error Occurred", JOptionPane.ERROR_MESSAGE);
        }
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                client.repaint();
                if (client.playerTank.isLive()) {
                    for (Tank tank : client.enemyTanks) {
                        tank.actRandomly();
                    }
                }
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void load() throws IOException {
        File file = new File(GAME_SAVE);
        if (file.exists() && file.isFile()) {
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            Save save = JSON.parseObject(json, Save.class);
            if (save.isGameContinued()) {
                this.playerTank = new Tank(save.getPlayerPosition(), false);

                this.enemyTanks.clear();
                List<Position> enemyPositions = save.getEnemyPositions();
                if (enemyPositions != null && !enemyPositions.isEmpty()) {
                    for (Position position : enemyPositions) {
                        this.enemyTanks.add(new Tank(position, true));
                    }
                }
            }
        }
    }

    void save(String destination) throws IOException {
        Save save = new Save(playerTank.isLive(), playerTank.getPosition(),
            enemyTanks.stream().filter(Tank::isLive)
                .map(Tank::getPosition).collect(Collectors.toList()));
        FileUtils.write(new File(destination), JSON.toJSONString(save, true), StandardCharsets.UTF_8);
    }

    void save() throws IOException {
        this.save(GAME_SAVE);
    }

    void restart() {
        if (!playerTank.isLive()) {
            playerTank = new Tank(400, 100, Direction.DOWN);
        }
        this.initEnemyTanks();
    }
}
