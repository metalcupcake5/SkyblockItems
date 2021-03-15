package io.github.metalcupcake5.SkyblockItems;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;


import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.github.metalcupcake5.SkyblockItems.SkyblockItems.*;
import static org.bukkit.plugin.java.JavaPlugin.getPlugin;

public class PlayerListener implements Listener{

    private final SkyblockItems plugin;

    public PlayerListener(SkyblockItems plugin){
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }

    private final CooldownManager cooldownManager = new CooldownManager();

    private int taskID = 0;

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        setPlayerMana(player.getUniqueId(), 100);
        setPlayerMaxMana(player.getUniqueId(), 100);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if(playerMana.get(uuid) == null){
            return;
        }
        playerMana.remove(uuid);
        playerMaxMana.remove(uuid);
    }

    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        ArrayList<Material> materials = new ArrayList<>(
                Arrays.asList(
                        Material.CHEST,
                        Material.ENDER_CHEST,
                        Material.STONE_BUTTON,
                        Material.WOOD_BUTTON,
                        Material.WOOD_DOOR,
                        Material.WOODEN_DOOR,
                        Material.ACACIA_DOOR,
                        Material.BIRCH_DOOR,
                        Material.DARK_OAK_DOOR,
                        Material.JUNGLE_DOOR,
                        Material.SPRUCE_DOOR,
                        Material.TRAP_DOOR,
                        Material.FENCE_GATE,
                        Material.DARK_OAK_FENCE_GATE,
                        Material.BIRCH_FENCE_GATE,
                        Material.SPRUCE_FENCE_GATE,
                        Material.JUNGLE_FENCE_GATE,
                        Material.ACACIA_FENCE_GATE
                        ));

        if(clickedBlock != null && materials.contains(clickedBlock.getType())){
            return;
        }
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();
        if (item == null) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasDisplayName()) {
            return;
        }
        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {

            String name = meta.getDisplayName();
            if (name.contains(ChatColor.BLUE + "Aspect of the End")) {
                Integer cost = aote_mana;
                Integer mana = getPlayerMana(player.getUniqueId());
                if(mana < cost){
                    player.sendMessage(ChatColor.RED + "You don't have enough mana to do this!");
                    return;
                }

                Block b = player.getTargetBlock((Set<Material>) null, 8);
                Location location = b.getLocation();
                location.setPitch(player.getLocation().getPitch());
                location.setYaw(player.getLocation().getYaw());
                player.teleport(location.add(0,1,0));
                setPlayerMana(player.getUniqueId(), mana-cost);
                Float currentSpeed = player.getWalkSpeed();
                player.setWalkSpeed((float) (currentSpeed + 0.1));
                BukkitScheduler scheduler = player.getServer().getScheduler();
                scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        Float speed = player.getWalkSpeed();
                        player.setWalkSpeed((float) (speed - 0.1));
                    }
                }, 60L);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + "Instant Transmission" + ChatColor.GREEN + "!" + ChatColor.AQUA + "(" + cost+" mana)");
            }
            if (name.contains(ChatColor.GOLD + "Yeti Sword")) {
                Integer cost = yeti_sword_mana;
                Integer mana = getPlayerMana(player.getUniqueId());
                if(mana < cost){
                    player.sendMessage(ChatColor.RED + "You don't have enough mana to do this!");
                    return;
                }

                long timeLeft = System.currentTimeMillis() - cooldownManager.getCooldown(player.getUniqueId()+"-YETI");
                if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) <= CooldownManager.YETI_COOLDOWN){
                    long time =  CooldownManager.YETI_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft)*-1;
                    player.sendMessage(ChatColor.RED + "This ability is currently on cooldown for "+time+" more seconds.");
                    return;
                }
                Block b = player.getTargetBlock((Set<Material>) null, 32);
                World world = player.getWorld();
                Location startBlock = player.getLocation();

                List<Location> locationList = new ArrayList<>();
                List<Location> endList = new ArrayList<>();
                List<Material> blockTypes = new ArrayList<>();
                List<Material> launchTypes = new ArrayList<>();

                //Get blocks 1 layer below player, 3x3 square
                for (int length = -1; length < 2; length++) {
                    for (int height = -1; height < 2; height++) {
                        Location loc = startBlock.clone().add(length, 0, height);
                        Location end = b.getLocation().clone().add(length, 0, height);
                        locationList.add(loc);
                        endList.add(end);
                    }
                }

                //The 4 outer blocks of top layer
                locationList.add(startBlock.clone().add(0,0,2));
                locationList.add(startBlock.clone().add(0,0,-2));
                locationList.add(startBlock.clone().add(2,0,0));
                locationList.add(startBlock.clone().add(-2,0,0));
                endList.add(b.getLocation().clone().add(0, 0, 2));
                endList.add(b.getLocation().clone().add(0, 0, -2));
                endList.add(b.getLocation().clone().add(2, 0, 0));
                endList.add(b.getLocation().clone().add(-2, 0, 0));

                //Lower layer
                locationList.add(startBlock.clone().add(0,-1,0));
                locationList.add(startBlock.clone().add(1,-1,0));
                locationList.add(startBlock.clone().add(-1,-1,0));
                locationList.add(startBlock.clone().add(0,-1,1));
                locationList.add(startBlock.clone().add(0,-1,-1));
                endList.add(b.getLocation().clone().add(0, -1, 0));
                endList.add(b.getLocation().clone().add(1, -1, 0));
                endList.add(b.getLocation().clone().add(-1, -1, 0));
                endList.add(b.getLocation().clone().add(0, -1, 1));
                endList.add(b.getLocation().clone().add(0, -1, -1));

                Byte blockData = 0x0;

                locationList.forEach(block -> {
                    Location loc = block.getBlock().getLocation().clone().subtract(0,1,0);
                    Material mat = loc.getBlock().getType();
                    launchTypes.add(mat);
                    blockTypes.add(block.getBlock().getType());
                });

                //this.plugin.getLogger().info(blockTypes.toString());

                if(checkSame(blockTypes).getSame() && checkSame(blockTypes).getMaterial() == Material.AIR){
                    locationList.clear();
                    endList.clear();
                    launchTypes.clear();
                    locationList.add(startBlock);
                    launchTypes.add(Material.ICE);
                    endList.add(b.getLocation());

                }

                //go through list and launch block
                locationList.forEach(location -> {
                    //Location material = location.clone().subtract(0,1,0);
                    Material material = launchTypes.get(locationList.indexOf(location));
                    Location origin = location.clone().add(0,2,0);
                    int pos = locationList.indexOf(location);
                    Location endPos = endList.get(pos);

                    FallingBlock block = world.spawnFallingBlock(origin, material, blockData);
                    block.setDropItem(false);
                    block.setMetadata("yeti", new FixedMetadataValue(getPlugin(SkyblockItems.class), true));
                    block.setVelocity(calculateVelocityBlock(origin.toVector(), endPos.toVector(), 3));
                });
                setPlayerMana(player.getUniqueId(), mana-cost);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + "Terrain Toss" + ChatColor.GREEN + "!" + ChatColor.AQUA + "(" + cost +" mana)");
                cooldownManager.setCooldown(player.getUniqueId()+"-YETI", System.currentTimeMillis());
            }
            if (name.contains(ChatColor.DARK_PURPLE + "Leaping Sword")){
                Integer cost = leaping_sword_mana;
                Integer mana = getPlayerMana(player.getUniqueId());
                if(mana < cost){
                    player.sendMessage(ChatColor.RED + "You don't have enough mana to do this!");
                    return;
                }

                long timeLeft = System.currentTimeMillis() - cooldownManager.getCooldown(player.getUniqueId()+"-LEAPING");
                if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) <= CooldownManager.LEAPING_COOLDOWN){
                    long time =  CooldownManager.LEAPING_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft)*-1;
                    player.sendMessage(ChatColor.RED + "This ability is currently on cooldown for "+time+" more seconds.");
                    return;
                }

                Block b = player.getTargetBlock((Set<Material>) null, 10);
                World world = player.getWorld();
                Location startBlock = player.getLocation();
                Location target = b.getLocation();

                player.setVelocity(calculateVelocityPlayer(startBlock.toVector(), target.toVector(), 3));

                setPlayerMana(player.getUniqueId(), mana-cost);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + "Leap" + ChatColor.GREEN + "!" + ChatColor.AQUA + "(" + cost +" mana)");
                cooldownManager.setCooldown(player.getUniqueId()+"-LEAPING", System.currentTimeMillis());

            }
            if (name.contains(ChatColor.DARK_PURPLE + "Ember Rod")){
                Integer cost = ember_rod_mana;
                Integer mana = getPlayerMana(player.getUniqueId());
                if(mana < cost){
                    player.sendMessage(ChatColor.RED + "You don't have enough mana to do this!");
                    return;
                }

                Fireball f1 = player.launchProjectile(Fireball.class);
                f1.setIsIncendiary(false);

                BukkitScheduler scheduler = player.getServer().getScheduler();
                scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        Fireball f2 = player.launchProjectile(Fireball.class);
                        f2.setIsIncendiary(false);
                    }
                }, 10L);
                scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        Fireball f3 = player.launchProjectile(Fireball.class);
                        f3.setIsIncendiary(false);
                    }
                }, 20L);
                setPlayerMana(player.getUniqueId(), mana-cost);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + "Fire Blast" + ChatColor.GREEN + "!" + ChatColor.AQUA + "(" + cost +" mana)");

            }
            if (name.contains(ChatColor.DARK_PURPLE + "Ink Wand")){
                Integer cost = ink_wand_mana;
                Integer mana = getPlayerMana(player.getUniqueId());
                if(mana < cost) {
                    player.sendMessage(ChatColor.RED + "You don't have enough mana to do this!");
                    return;
                }

                Block b = player.getTargetBlock((Set<Material>) null, 16);

                Location start = player.getLocation().clone().add(0,1,0);

                Item i = player.getWorld().dropItem(start, new ItemStack((Material.INK_SACK)));

                i.setPickupDelay(20000);

                i.setVelocity(calculateVelocityBlock(start.toVector(), b.getLocation().toVector(), 1));

                BukkitScheduler scheduler = player.getServer().getScheduler();
                taskID = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        double velocity = i.getVelocity().getY();
                        if(velocity == 0.0){
                            Location loc = i.getLocation();
                            i.getWorld().playEffect(loc, Effect.SMOKE, 0);
                            List<Entity> entityList = i.getNearbyEntities(0.5,1,0.5);
                            entityList.forEach(entity -> {
                                if(entity instanceof LivingEntity){
                                    double health = ((LivingEntity) entity).getHealth();
                                    ((LivingEntity) entity).damage(50000);
                                    return;
                                }
                            });
                            i.remove();
                            scheduler.cancelTask(taskID);
                        }
                    }
                }, 0L, 1L);


                setPlayerMana(player.getUniqueId(), mana-cost);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + "Ink Bomb" + ChatColor.GREEN + "!" + ChatColor.AQUA + "(" + cost +" mana)");

            }
        }
        if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {
            String name = meta.getDisplayName();
            if (name.contains(ChatColor.BLUE + "Ender Bow")) {
                Integer cost = ender_bow_mana;
                Integer mana = getPlayerMana(player.getUniqueId());
                if(mana < cost){
                    player.sendMessage(ChatColor.RED + "You don't have enough mana to do this!");
                    return;
                }
                long timeLeft = System.currentTimeMillis() - cooldownManager.getCooldown(player.getUniqueId()+"-ENDER_BOW");
                if(TimeUnit.MILLISECONDS.toSeconds(timeLeft) <= CooldownManager.ENDER_BOW_COOLDOWN){
                    long time = CooldownManager.ENDER_BOW_COOLDOWN - TimeUnit.MILLISECONDS.toSeconds(timeLeft)*-1;
                    player.sendMessage(ChatColor.RED + "This ability is currently on cooldown for "+time+" more seconds.");
                    return;
                }
                Projectile projectile = player.launchProjectile(EnderPearl.class);
                projectile.setMetadata("enderbow", new FixedMetadataValue(getPlugin(SkyblockItems.class), true));
                setPlayerMana(player.getUniqueId(), mana-cost);
                player.sendMessage(ChatColor.GREEN + "Used " + ChatColor.GOLD + "Ender Warp" + ChatColor.GREEN + "!" + ChatColor.AQUA + "(" + cost +" mana)");
                cooldownManager.setCooldown(player.getUniqueId()+"-ENDER_BOW", System.currentTimeMillis());
            }

        }
    }

    /*
     * Grappling Hook code pulled from
     *   https://github.com/BenBHG/GrapplingRod/blob/master/src/com/boothousegaming/grapplingrod/GrapplingRod.java
     *
     */

    @EventHandler
    public void playerFishEvent(PlayerFishEvent event){

        Player player = event.getPlayer();


        ItemStack item = player.getItemInHand();
        if (item == null) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (!meta.hasDisplayName()) {
            return;
        }

        String name = meta.getDisplayName();
        if (name.contains(ChatColor.GREEN + "Grappling Hook")) {

            Vector vector3;
            Entity entity;
            Block block;
            double d;
            double hookThreshold = 0.25;
            double hForceMult = 0.3;
            double hForceMax = 7.5;
            double vForceMult = 0.7;
            double vForceBonus = 0.5;
            double vForceMax = 1.5;

            if (event.getState() != PlayerFishEvent.State.FISHING) {
                entity = event.getHook();
                block = entity.getWorld().getBlockAt(entity.getLocation().add(0.0, -hookThreshold, 0.0));


                vector3 = entity.getLocation().subtract(player.getLocation()).toVector();

                if (vector3.getY() < 0.0)
                    vector3.setY(0.0);

                vector3.setX(vector3.getX() * hForceMult);
                vector3.setY(vector3.getY() * vForceMult + vForceBonus);
                vector3.setZ(vector3.getZ() * hForceMult);

                d = hForceMax * hForceMax;
                if (vector3.clone().setY(0.0).lengthSquared() > d) {
                    d = d / vector3.lengthSquared();
                    vector3.setX(vector3.getX() * d);
                    vector3.setZ(vector3.getZ() * d);
                }

                if (vector3.getY() > vForceMax)
                    vector3.setY(vForceMax);

                player.setVelocity(vector3);
            }
        }
    }
}
