package xyz.ultradev.buildmode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Getter
@AllArgsConstructor
public abstract class BuildModeModule {
    private BuildMode parent;
    private String name;

    public boolean shouldEnable() {
        return getParent().getConfig()
                .getBoolean("integrations." + getName(), false);
    }
    public abstract boolean canEnable();

    public void enable() {};

    public abstract boolean canBuild(Player pl, Location loc);

}
