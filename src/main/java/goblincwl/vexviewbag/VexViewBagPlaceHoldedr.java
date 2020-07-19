package goblincwl.vexviewbag;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description PlaceHolderAPI集成
 * @create 2020-07-19 17:27
 */
public class VexViewBagPlaceHoldedr extends PlaceholderExpansion {

    private VexViewBag plugin;

    public VexViewBagPlaceHoldedr(VexViewBag plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String getIdentifier() {
        return "vexViewBag";
    }

    @Override
    public String getAuthor() {
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        try {
            //玩家数据文件
            File file = new File(this.plugin.getDataFolder(), "/playerData/" + player.getName() + ".yml");
            if (!file.exists()) {
                file.createNewFile();
            }
            YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
            switch (identifier) {
                case "activePoint":
                    return String.valueOf(configuration.getLong("active.activePoint"));
                case "onlineTime":
                    return String.valueOf(configuration.getLong("onlineTime.todayTime"));
                default:
                    return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
}
