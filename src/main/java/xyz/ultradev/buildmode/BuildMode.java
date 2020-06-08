package xyz.ultradev.buildmode;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.ultradev.buildmode.modules.PermissionModule;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BuildMode extends JavaPlugin {
    private List<UUID> enabled = new ArrayList<>();
    private List<BuildModeModule> availableModules = new ArrayList<>();
    private List<BuildModeModule> enabledModules = new ArrayList<>();

    public void onEnable() {
        availableModules.add(new PermissionModule(this));

        for (BuildModeModule module : availableModules) {
            if (module.shouldEnable()) {
                enabledModules.add(module);
            }
        }

        new BuildModeListener(this);
        getCommand("buildmode").setExecutor(new BuildModeCommand(this));
    }

    public void onDisable() {

    }

    public boolean isEnabled(UUID id) {
        return enabled.contains(id);
    }
    public boolean enable(UUID id) {
        if (isEnabled(id)) {
            return false;
        }
        enabled.add(id);
        return true;
    }
    public boolean disable(UUID id) {
        return enabled.remove(id);
    }
    public boolean toggle(UUID id) {
        if (isEnabled()) {
            enabled.remove(id);
            return false;
        } else {
            enabled.add(id);
            return true;
        }
    }

    public boolean canBuild(Player player, Location loc) {
        if (isEnabled(player.getUniqueId())) {
            return true;
        }
        for (BuildModeModule module : enabledModules) {
            if (module.canBuild(player, loc)) {
                return true;
            }
        }
        return false;
    }
}
