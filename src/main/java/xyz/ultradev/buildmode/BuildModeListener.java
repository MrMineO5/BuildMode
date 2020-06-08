package xyz.ultradev.buildmode;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildModeListener implements Listener {
    private BuildMode parent;

    public BuildModeListener(BuildMode parent) {
        this.parent = parent;
        Bukkit.getPluginManager().registerEvents(this, parent);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!parent.canBuild(e.getPlayer(), e.getBlock().getLocation())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!parent.canBuild(e.getPlayer(), e.getBlock().getLocation())) {
            e.setCancelled(true);
        }
    }
}
