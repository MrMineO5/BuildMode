package xyz.ultradev.buildmode.modules;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import xyz.ultradev.buildmode.BuildMode;
import xyz.ultradev.buildmode.BuildModeModule;

public class PermissionModule extends BuildModeModule {
    public PermissionModule(BuildMode parent) {
        super(parent, "permission");
    }

    @Override
    public boolean canEnable() {
        return true;
    }

    @Override
    public boolean canBuild(Player pl, Location loc) {
        return !pl.hasPermission("buildmode.use"); // If the player does not have this permission, do not use buildmode
    }
}
