package goblincwl.vexviewbag.command;

import goblincwl.vexviewbag.VexViewBag;
import goblincwl.vexviewbag.gui.BagGui;
import goblincwl.vexviewbag.gui.ShopGui;
import goblincwl.vexviewbag.gui.StandGui;
import lk.vexview.api.VexViewAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Set;


/**
 * @author ☪wl
 * @program VexViewBag
 * @description 指令处理
 * @create 2020-07-01 16:02
 */
public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        VexViewBag instance = VexViewBag.getInstance();

        if (command.getName().equals("vvb")) {
            if (args.length < 1) {
                return false;
            } else {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("此指令只能由玩家输入.");
                    return true;
                }
                Player player = (Player) sender;
                switch (args[0]) {
                    case "open":
                        //打开背包

                        VexViewAPI.openGui(player, new BagGui(player));
                        break;
                    case "stand":
                        //替身
                        try {
                            VexViewAPI.openGui(player, new StandGui(player));
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "shop":
                        //商店
                        VexViewAPI.openGui(player, new ShopGui());
                    case "reload":
                        //重载配置文件
                        VexViewBag.getInstance().reloadConfig();
                        sender.sendMessage("配置文件已重载.");
                        break;
                    case "saveItem":
                        try {
                            File shopConfigFile = new File(instance.getDataFolder(), "item.yml");
                            YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(shopConfigFile);
                            Set<String> keys = yamlConfiguration.getKeys(false);
                            yamlConfiguration.set(String.valueOf(keys.size() + 1)+".item", player.getInventory().getItemInMainHand());
                            yamlConfiguration.save(shopConfigFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    default:
                        return false;
                }
            }
            return true;
        }
        return false;
    }
}
