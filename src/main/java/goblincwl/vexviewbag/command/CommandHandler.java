package goblincwl.vexviewbag.command;

import goblincwl.vexviewbag.VexViewBag;
import goblincwl.vexviewbag.gui.BagGui;
import goblincwl.vexviewbag.gui.StandGui;
import lk.vexview.api.VexViewAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


/**
 * @author ☪wl
 * @program VexViewBag
 * @description 指令处理
 * @create 2020-07-01 16:02
 */
public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("vvb")) {
            if (args.length < 1) {
                return false;
            } else {
                switch (args[0]) {
                    case "open":
                        //打开背包
                        if (!(sender instanceof Player)) {
                            sender.sendMessage("此指令只能由玩家输入.");
                            return true;
                        }
                        VexViewAPI.openGui((Player) sender, new BagGui((Player) sender));
                        break;
                    case "stand":
                        //替身
                        if (!(sender instanceof Player)) {
                            sender.sendMessage("此指令只能由玩家输入.");
                            return true;
                        }
                        try {
                            VexViewAPI.openGui((Player) sender, new StandGui((Player) sender));
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "reload":
                        //重载配置文件
                        VexViewBag.getInstance().reloadConfig();
                        sender.sendMessage("配置文件已重载.");
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
