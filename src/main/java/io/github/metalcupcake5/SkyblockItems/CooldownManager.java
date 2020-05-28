package io.github.metalcupcake5.SkyblockItems;

import java.util.HashMap;
import java.util.Map;

public class CooldownManager {

    private final Map<String, Long> cooldowns = new HashMap<>();

    public static final long YETI_COOLDOWN = 1;
    public static final long AOTE_COOLDOWN = 0;
    public static final long ENDER_BOW_COOLDOWN = 45;
    public static final long EXPLOSIVE_BOW_COOLDOWN = 0;
    public static final long LEAPING_COOLDOWN = 1;

    public void setCooldown(String player, long time){
        if(time < 1) {
            cooldowns.remove(player);
        } else {
            cooldowns.put(player, time);
        }
    }

    public long getCooldown(String player){
        return cooldowns.getOrDefault(player, (long) 0);
    }

}
