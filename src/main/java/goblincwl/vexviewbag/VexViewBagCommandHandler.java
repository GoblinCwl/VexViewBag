package goblincwl.vexviewbag;

import goblincwl.vexviewbag.gui.BagGui;
import goblincwl.vexviewbag.gui.ShopGui;
import goblincwl.vexviewbag.gui.StandGui;
import lk.vexview.api.VexViewAPI;
import org.bukkit.Bukkit;
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
public class VexViewBagCommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        VexViewBag vexViewBag = VexViewBag.getInstance();

        if (command.getName().equals("vvb")) {
            if (args.length < 1) {
                return false;
            } else {
                if (!(sender instanceof Player)) {
                    switch (args[0]) {
                        case "addActivePoint":
                            return addActivePoint(args, sender);
                        default:
                            break;
                    }
                } else {
                    Player player = (Player) sender;
                    switch (args[0]) {
                        case "open":
                            //打开背包

                            VexViewAPI.openGui(player, new BagGui(player));
                            break;
                        case "stand":
                            //替身
                            VexViewAPI.openGui(player, new StandGui(player));
                            break;
                        case "shop":
                            //商店
                            VexViewAPI.openGui(player, new ShopGui());
                            break;
                        case "reload":
                            //重载配置文件
                            VexViewBag.getInstance().reloadConfig();
                            VexViewBag.configurationMap = VexViewBagUtils.loadAllConfig();
                            sender.sendMessage("配置文件已重载.");
                            break;
                        case "saveItem":
                            try {
                                File shopConfigFile = new File(vexViewBag.getDataFolder(), "item.yml");
                                YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(shopConfigFile);
                                Set<String> keys = yamlConfiguration.getKeys(false);
                                yamlConfiguration.set(keys.size() + ".item", player.getInventory().getItemInMainHand());
                                yamlConfiguration.save(shopConfigFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "addActivePoint":
                            return addActivePoint(args, sender);
                        default:
                            return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 添加活跃值
     *
     * @param args   指令参数
     * @param sender 指令发送者
     * @return boolean
     * @create 2020/7/22 15:32
     * @author ☪wl
     */
    private boolean addActivePoint(String[] args, CommandSender sender) {
        if (args.length > 2) {
            String playerName = args[1];
            String point = args[2];
            try {
                Player targetPlayer = Bukkit.getPlayer(playerName);
                VexViewBagPlayer vexViewBagPlayer = VexViewBag.mySqlManager.selectData(targetPlayer.getUniqueId().toString());
                if (vexViewBagPlayer == null) {
                    sender.sendMessage(VexViewBag.messagePrefix + "玩家" + playerName + "不存在");
                    return false;
                }
                VexViewBag.mySqlManager.updateActivePoint(targetPlayer.getUniqueId().toString(), vexViewBagPlayer.getActivePoint() + Long.parseLong(point));
                sender.sendMessage(VexViewBag.messagePrefix + "玩家" + playerName + "活跃值+" + point);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
        }
    }
}
