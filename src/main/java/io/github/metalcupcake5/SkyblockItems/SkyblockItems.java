package io.github.metalcupcake5.SkyblockItems;

import io.github.metalcupcake5.SkyblockItems.actionbar.Actionbar;
import io.github.metalcupcake5.SkyblockItems.actionbar.Actionbar_1_8_R3;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.*;

public final class SkyblockItems extends JavaPlugin implements Listener {
    private static Actionbar actionbar;
    public static final Integer yeti_sword_mana = 250;
    public static final Integer aote_mana = 50;
    public static final Integer ender_bow_mana = 50;
    public static final Integer leaping_sword_mana = 50;

    /*TODO
     *   List of things needed:
     *       -Make AOTE give +50 speed(3 seconds)
     *
     * DONE
     *       -Make ender bow deal 10% of current health.
     */

    public static HashMap<UUID, Integer> playerMana = new HashMap<UUID, Integer>();
    public static HashMap<UUID, Integer> playerMaxMana = new HashMap<>();
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        Boolean logging = this.getConfig().getBoolean("logging");
        if (setupActionbar()) {
            Bukkit.getPluginManager().registerEvents(this, this);
            if(logging) {
                getLogger().info("Preliminary setup(ActionBar) was successful!");
                getLogger().info("Preliminary setup complete!");
            }
        } else {
            getLogger().info("Preliminary setup(ActionBar) failed.");
            getLogger().severe("Server version is not supported by the current ActionBar versions.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            playerMana.put(player.getUniqueId(), 100);
            playerMaxMana.put(player.getUniqueId(), 100);
        }
        getCommand("giveItem").setExecutor(new giveItemCommand());
        getCommand("setMana").setExecutor(new setMana(this));
        getCommand("setMaxMana").setExecutor(new setMaxMana());
        getCommand("config").setExecutor(new configCommand(this));
        new PlayerListener(this);
        new EntityListener(this);
        if(logging) {
            getLogger().info("\n///////////\n*\n*\n*  SkyblockItems enabled!\n*\n*\n///////////");
        }

        BukkitScheduler scheduler = getServer().getScheduler();

        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                playerMana.forEach((uuid, value) -> {
                    Player player = Bukkit.getServer().getPlayer(uuid);
                    if(player == null) return;
                    int mana = value;
                    int maxMana = playerMaxMana.get(uuid);
                    if(mana<maxMana){
                        double regenValue = Math.floor(maxMana/50);
                        int totalmana = (int) (mana+regenValue);
                        if(totalmana > maxMana){
                            playerMana.put(uuid, maxMana);
                        }else {
                            playerMana.put(uuid, totalmana);
                        }
                    }
                    actionbar.sendActionbar(player, ChatColor.AQUA + Integer.toString(mana) + "/"+ maxMana + " mana");
                });
            }
        }, 0L, 20L);
    }

    @Override
    public void onDisable() {
        Boolean logging = this.getConfig().getBoolean("logging");
        if(logging) {
            getLogger().info("\n///////////\n*\n*\n*  SkyblockItems disabled!\n*\n*\n///////////");
        }
    }

    private static double distanceSquared(Vector from, Vector to) {
        double dx = to.getBlockX() - from.getBlockX();
        double dz = to.getBlockZ() - from.getBlockZ();
        return dx * dx + dz * dz;
    }

    public static Vector calculateVelocityBlock(Vector from, Vector to, int heightGain) {
        // Gravity of a falling block
        double gravity = 0.115;
        // Block locations
        int endGain = to.getBlockY() - from.getBlockY();
        double horizDist = Math.sqrt(distanceSquared(from, to));
        // Height gain
        int gain = heightGain;
        double maxGain = gain > (endGain + gain) ? gain : (endGain + gain);
        // Solve quadratic equation for velocity
        double a = -horizDist * horizDist / (4 * maxGain);
        double b = horizDist;
        double c = -endGain;
        double slope = -b / (2 * a) - Math.sqrt(b * b - 4 * a * c) / (2 * a);
        // Vertical velocity
        double vy = Math.sqrt(maxGain * gravity);
        // Horizontal velocity
        double vh = vy / slope;
        // Calculate horizontal direction
        int dx = to.getBlockX() - from.getBlockX();
        int dz = to.getBlockZ() - from.getBlockZ();
        double mag = Math.sqrt(dx * dx + dz * dz);
        double dirx = dx / mag;
        double dirz = dz / mag;
        // Horizontal velocity components
        double vx = vh * dirx;
        double vz = vh * dirz;
        return new Vector(vx, vy, vz);
    }

    public static Vector calculateVelocityPlayer(Vector from, Vector to, int heightGain) {
        // Gravity of a falling block
        double gravity = 0.867;
        // Block locations
        int endGain = to.getBlockY() - from.getBlockY();
        double horizDist = Math.sqrt(distanceSquared(from, to));
        // Height gain
        int gain = heightGain;
        double maxGain = gain > (endGain + gain) ? gain : (endGain + gain);
        // Solve quadratic equation for velocity
        double a = -horizDist * horizDist / (4 * maxGain);
        double b = horizDist;
        double c = -endGain;
        double slope = -b / (2 * a) - Math.sqrt(b * b - 4 * a * c) / (2 * a);
        // Vertical velocity
        double vy = Math.sqrt(maxGain * gravity);
        // Horizontal velocity
        double vh = vy / slope;
        // Calculate horizontal direction
        int dx = to.getBlockX() - from.getBlockX();
        int dz = to.getBlockZ() - from.getBlockZ();
        double mag = Math.sqrt(dx * dx + dz * dz);
        double dirx = dx / mag;
        double dirz = dz / mag;
        // Horizontal velocity components
        double vx = vh * dirx;
        double vz = vh * dirz;
        return new Vector(vx, vy, vz);
    }

    public static Object checkSame(List list){
        for(int i = 1; i < list.size(); i++){
            if(list.get(i-1) != list.get(i)){
                class Return{
                    Object type = null;
                    boolean same = false;
                }
                return false;
            }
        }
        class Return{
            Object type = list.get(1);
            boolean same = true;
        }

        return new Return();
    }

    public static void setPlayerMana(UUID uuid, Integer integer){
        playerMana.put(uuid, integer);
    }

    public static void setPlayerMaxMana(UUID uuid, Integer amount){
        playerMaxMana.put(uuid, amount);
    }

    public static Integer getPlayerMana(UUID uuid){
        Integer mana = playerMana.get(uuid);
        return mana;
    }

    private boolean setupActionbar() {

        String version;

        try {

            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

        } catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
            return false;
        }
        Boolean logging = this.getConfig().getBoolean("logging");
        if(logging) {
            getLogger().info("Server version is " + version);
        }

        if (version.equals("v1_8_R3")) {
            //server is running 1.8-1.8.1 so we need to use the 1.8 R1 NMS class
            actionbar = new Actionbar_1_8_R3();

        }
        // This will return true if the server version was compatible with one of our NMS classes
        // because if it is, our actionbar would not be null
        return actionbar != null;
    }

    public static Actionbar getActionbar() {
        return actionbar;
    }
}