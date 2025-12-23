package p1xel.nobuildplus.api;

import p1xel.nobuildplus.NoBuildPlus;
import p1xel.nobuildplus.Flags;
import p1xel.nobuildplus.storage.FlagsManager;
import p1xel.nobuildplus.storage.Settings;
import p1xel.nobuildplus.storage.Worlds;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class NBPAPI {

    private String info;
    private String version;

    public NBPAPI() {
        this.info = "This is a testing API at ";
        this.version = "v0.1";
    }

    public String getInfo() {
        return this.info;
    }

    public String getVersion() {
        return this.version;
    }

    private void saveAndReload() {
        File flags = new File(NoBuildPlus.getInstance().getDataFolder(), "flags.yml");
        File settings = new File(NoBuildPlus.getInstance().getDataFolder(), "settings.yml");
        File worlds = new File(NoBuildPlus.getInstance().getDataFolder(), "worlds.yml");

        try {
            FlagsManager.yaml.save(flags);
            Settings.yaml.save(settings);
            Worlds.yaml.save(worlds);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        FlagsManager.upload(flags);
        Settings.upload(settings);
        Worlds.upload(worlds);

        FlagsManager.defaultFlagList();
        Settings.defaultList();
        Flags.refreshMap();
    }

    // Add flag to NoBuildPlus (with no type: option)
    public void addFlag(String flag, String item, int slot, boolean def) {
        if (FlagsManager.yaml.get("flags." + flag + ".enable") == null) {
            FlagsManager.yaml.set("flags." + flag + ".enable", true);
            FlagsManager.yaml.set("flags." + flag + ".show-item", item);
            FlagsManager.yaml.set("flags." + flag + ".slot", slot);
            FlagsManager.defaultFlagList();
        }
        if (Settings.yaml.get("global-settings.flags." + flag) == null) {
            Settings.yaml.set("global-settings.flags." + flag, def);
        }
        for (String world : Settings.getEnableWorldList()) {

            if (Worlds.yaml.get(world + ".flags." + flag) == null) {

                Worlds.yaml.set(world + ".flags." + flag, def);

            }

        }

        saveAndReload();

    }

    // Add flag to NoBuildPlus (with type all/list option)
    public void addFlag(String flag, String item, int slot, String type, List<String> list, boolean def) {

        if (FlagsManager.yaml.get("flags." + flag + ".enable") == null) {
            FlagsManager.yaml.set("flags." + flag + ".enable", true);
            FlagsManager.yaml.set("flags." + flag + ".show-item", item);
            FlagsManager.yaml.set("flags." + flag + ".slot", slot);
            FlagsManager.yaml.set("flags." + flag + ".type", type);
            FlagsManager.yaml.set("flags." + flag + ".list", list);
            FlagsManager.defaultFlagList();
        }
        if (Settings.yaml.get("global-settings.flags." + flag) == null) {
            Settings.yaml.set("global-settings.flags." + flag, def);
        }
        for (String world : Settings.getEnableWorldList()) {

            if (Worlds.yaml.get(world + ".flags." + flag) == null) {

                Worlds.yaml.set(world + ".flags." + flag, def);

            }

        }

        saveAndReload();

    }

    // Get if the world is enabled for NoBuildPlus
    public boolean isWorldEnabled(String world) {
        return Settings.getEnableWorldList().contains(world);
    }

    // Get if the flag is enabled in Flags Manager for NoBuildPlus.
    public boolean isFlagEnabled(String flag) {
        if ( FlagsManager.isInTheFlagsList(flag) || FlagsManager.getFlagsIsEnabled(flag)) {
            return true;
        }
        return false;
    }

    // Get flag boolean for the enabled world
    public boolean getFlag(String world, String flag) {
        return Worlds.getFlag(world, flag);
    }

    // Get if the flag is able to be executed in the world.
    // TRUE == start to prevent
    // FALSE == no need to prevent
    public boolean canExecute(String world, Flags flag) {
        return flag.isEnabled(world);
    }

}
