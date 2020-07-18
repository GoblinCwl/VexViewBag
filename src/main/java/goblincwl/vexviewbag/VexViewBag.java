package goblincwl.vexviewbag;

import goblincwl.vexviewbag.command.CommandHandler;
import goblincwl.vexviewbag.listener.VexViewEventListener;
import lk.vexview.api.VexViewAPI;
import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


public final class VexViewBag extends JavaPlugin {

    public static VexViewBag instance;
    public static Economy economy;
    public static PlayerPointsAPI playerPointsAPI;
    public static String messagePrefix;

    @Override
    public void onEnable() {
        ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
        consoleSender.sendMessage(ChatColor.AQUA + "VexViewBag插件启动中...");


        try {
            if (!Bukkit.getPluginManager().isPluginEnabled("VexView")) {
                throw new RuntimeException();
            }

            if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
                throw new RuntimeException();
            }

            if (!Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
                throw new RuntimeException();
            }

            //静态主类
            instance = this;
            //Vault Economy注入
            economy = getServer().getServicesManager().getRegistration(Economy.class).getProvider();
            //PlayerPoints注入
            Plugin playerPoints = getServer().getPluginManager().getPlugin("PlayerPoints");
            playerPointsAPI = ((PlayerPoints) playerPoints).getAPI();

            String version = VexViewAPI.getVexView().getVersion();
            consoleSender.sendMessage("已找到VexView前置，版本：" + version);

            //注册指令
            Bukkit.getPluginCommand("vvb").setExecutor(new CommandHandler());
            //注册事件
            Bukkit.getPluginManager().registerEvents(new VexViewEventListener(), this);

            //生成默认配置
            saveDefaultConfig();

            //消息提示头
            messagePrefix = getConfig().getString("messagePrefix");


            consoleSender.sendMessage(ChatColor.AQUA + "VexViewBag插件启动完成！");
        } catch (Exception e) {
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
