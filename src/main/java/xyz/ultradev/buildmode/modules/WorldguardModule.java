package xyz.ultradev.buildmode.modules;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.BukkitUtil;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.association.RegionAssociable;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.ultradev.buildmode.BuildMode;
import xyz.ultradev.buildmode.BuildModeModule;

public class WorldguardModule extends BuildModeModule {
    private WorldGuard worldGuard;

    public WorldguardModule(BuildMode parent) {
        super(parent, "worldguard");
    }

    @Override
    public boolean canEnable() {
        return Bukkit.getPluginManager().isPluginEnabled("WorldGuard");
    }

    @Override
    public void enable() {
        this.worldGuard = WorldGuard.getInstance();
    }

    @Override
    public boolean canBuild(Player pl, Location loc) {
        World world = BukkitAdapter.adapt(loc.getWorld());
        RegionManager manager = worldGuard.getPlatform().getRegionContainer().get(world); // Get region manager
        if (manager == null) {
            return false; // Cannot check regions
        }
        ApplicableRegionSet regs = manager.getApplicableRegions(BukkitAdapter.adapt(loc).toVector().toBlockPoint());
        StateFlag.State state = regs.queryState(null, Flags.BUILD);
        return state == StateFlag.State.ALLOW; // Return true if everyone is allowed to build
    }
}
