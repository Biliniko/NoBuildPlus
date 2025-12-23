package p1xel.nobuildplus.hook;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class HookedPlugins {

    private static Hooks hookedPlugin;

    public static void addHookPlugin(Hooks hook) {
        hookedPlugin = hook;
    }

    public static boolean cancel(Player player) {
        if (hookedPlugin == null) {
            return false;
        }
        boolean result = hookedPlugin.cancel(player);
        if (result) {
            return true;
        }
        return false;
    }

    public static boolean cancel(Block block) {
        if (hookedPlugin == null) {
            return false;
        }
        boolean result = hookedPlugin.cancel(block);
        if (result) {
            return true;
        }
        return false;
    }

    public static boolean cancel(Entity entity) {
        if (hookedPlugin == null) {
            return false;
        }
        boolean result = hookedPlugin.cancel(entity);
        if (result) {
            return true;
        }
        return false;
    }

    public static boolean cancel(Location loc) {
        if (hookedPlugin == null) {
            return false;
        }
        boolean result = hookedPlugin.cancel(loc);
        if (result) {
            return true;
        }
        return false;
    }


}
