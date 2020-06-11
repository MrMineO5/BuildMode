package xyz.ultradev.buildmode.modules;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
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
import org.codemc.worldguardwrapper.WorldGuardWrapper;
import org.codemc.worldguardwrapper.flag.IWrappedFlag;
import org.codemc.worldguardwrapper.flag.IWrappedStatusFlag;
import org.codemc.worldguardwrapper.flag.WrappedState;
import org.codemc.worldguardwrapper.implementation.v6.flag.WrappedPrimitiveFlag;
import org.codemc.worldguardwrapper.region.IWrappedRegion;
import xyz.ultradev.buildmode.BuildMode;
import xyz.ultradev.buildmode.BuildModeModule;

import java.util.List;
import java.util.Set;

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
        IWrappedFlag<WrappedState> flag = WorldGuardWrapper.getInstance().getFlag("build", WrappedState.class).orElse(null);
        WrappedState state = WorldGuardWrapper.getInstance().queryFlag(null, loc, flag).orElse(null);
        return state == WrappedState.ALLOW; // Return true if everyone is allowed to build
    }
}
