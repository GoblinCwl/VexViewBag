package goblincwl.vexviewbag;

import goblincwl.vexviewbag.command.CommandHandler;
import goblincwl.vexviewbag.listener.VexViewEventListener;
import lk.vexview.api.VexViewAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;


public final class VexViewBag extends JavaPlugin {

    public static VexViewBag instance;
    public static String messagePrefix;

    @Override
    public void onEnable() {
        ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
        consoleSender.sendMessage(ChatColor.AQUA + "VexViewBag插件启动中...");
        if (Bukkit.getPluginManager().isPluginEnabled("VexView")) {
            instance = this;

            String version = VexViewAPI.getVexView().getVersion();
            consoleSender.sendMessage("已找到VexView前置，版本：" + version);

            //注册指令
            Bukkit.getPluginCommand("vvb").setExecutor(new CommandHandler());
            //注册事件
            Bukkit.getPluginManager().registerEvents(new VexViewEventListener(), this);

            //生成默认配置
            saveDefaultConfig();
            messagePrefix = getConfig().getString("messagePrefix");

            consoleSender.sendMessage(ChatColor.AQUA + "VexViewBag插件启动完成！");
        } else {
            consoleSender.sendMessage(ChatColor.RED + "VexViewBag插件启动失败！");
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("VexViewBag插件已关闭。");
    }

    public static VexViewBag getInstance() {
        return instance;
    }
}
