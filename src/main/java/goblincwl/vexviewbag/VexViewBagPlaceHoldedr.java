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
            //玩家数据
            VexViewBagPlayer vexViewBagPlayer = VexViewBag.mySqlManager.selectData(player.getUniqueId().toString());
            if (vexViewBagPlayer == null) {
                vexViewBagPlayer = VexViewBag.mySqlManager.insertData(player.getUniqueId().toString());
            }
            switch (identifier) {
                case "activePoint":
                    return String.valueOf(vexViewBagPlayer.getActivePoint());
                case "onlineTime":
                    return String.valueOf(vexViewBagPlayer.getOnlineTime());
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
}
