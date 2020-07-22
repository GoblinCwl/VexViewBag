package goblincwl.vexviewbag.mysql;

import goblincwl.vexviewbag.DataAnnotation;
import goblincwl.vexviewbag.VexViewBag;
import goblincwl.vexviewbag.VexViewBagPlayer;
import org.bukkit.configuration.file.FileConfiguration;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description Sql管理类
 * @create 2020-07-20 10:18
 */
public class MySqlManager {

    public static MySqlManager instance = null;
    private String ip;
    private String databaseName;
    private String userName;
    private String userPassword;
    private String port;
    private Connection connection;

    public static MySqlManager get() {
        return instance == null ? instance = new MySqlManager() : instance;
    }

    public void enableMySql() {
        FileConfiguration config = VexViewBag.getInstance().getConfig();

        ip = config.getString("mysql.ip");
        databaseName = config.getString("mysql.dataBase");
        userName = config.getString("mysql.userName");
        userPassword = config.getString("mysql.password");
        port = config.getString("mysql.port");
        connectMySQL();
        String cmd = SqlCommand.CREATE_TABLE.commandToString();
        try {
            PreparedStatement ps = connection.prepareStatement(cmd);
            doCommand(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void connectMySQL() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + databaseName + "?autoReconnect=true", userName, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doCommand(PreparedStatement ps) {
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("执行指令失败，以下为错误提示");
            e.printStackTrace();
        }
    }

    /**
     * 插入新数据
     *
     * @param UUID 玩家UUID
     * @return goblincwl.vexviewbag.VexViewBagPlayer
     * @create 2020/7/22 10:38
     * @author ☪wl
     */
    public VexViewBagPlayer insertData(String UUID) throws SQLException, IllegalAccessException {
        //先查询UUID是否已经拥有
        VexViewBagPlayer vexViewBagPlayer = selectData(UUID);
        if (vexViewBagPlayer != null) {
            return vexViewBagPlayer;
        } else {
            PreparedStatement ps;
            ps = connection.prepareStatement(SqlCommand.INSERT_ONE.commandToString());
            ps.setString(1, UUID);
            doCommand(ps);
        }
        return selectData(UUID);
    }

    /**
     * 查询玩家数据
     *
     * @param UUID 玩家UUID
     * @return goblincwl.vexviewbag.VexViewBagPlayer
     * @create 2020/7/22 10:38
     * @author ☪wl
     */
    public VexViewBagPlayer selectData(String UUID) throws SQLException, IllegalAccessException {
        PreparedStatement ps;
        ps = connection.prepareStatement(SqlCommand.SELECT_ONE.commandToString());
        ps.setString(1, UUID);
        VexViewBagPlayer resultVBP = new VexViewBagPlayer();
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Field[] declaredFields = resultVBP.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                field.set(resultVBP, resultSet.getObject(field.getAnnotation(DataAnnotation.class).value()));
            }

        }
        return resultVBP.getId() == null ? null : resultVBP;
    }

    /**
     * 更新在线时间
     *
     * @param UUID       玩家UUID
     * @param onlineDate 统计日期
     * @param onlineTime 当日变动时间
     * @return void
     * @create 2020/7/22 15:17
     * @author ☪wl
     */
    public void updateOnline(String UUID, String onlineDate, Long onlineTime) throws SQLException, IllegalAccessException {
        //先查询UUID是否存在
        VexViewBagPlayer vexViewBagPlayer = selectData(UUID);
        if (vexViewBagPlayer == null) {
            insertData(UUID);
        }
        PreparedStatement ps;
        ps = connection.prepareStatement(SqlCommand.UPDATE_ONLINE_TIME.commandToString());
        ps.setString(1, onlineDate);
        ps.setLong(2, onlineTime);
        ps.setString(3, UUID);
        doCommand(ps);
    }

    /**
     * 更新活跃值
     *
     * @param UUID  玩家UUID
     * @param point 变动活跃值
     * @return void
     * @create 2020/7/22 15:17
     * @author ☪wl
     */
    public void updateActivePoint(String UUID, Long point) throws SQLException, IllegalAccessException {
        //先查询UUID是否存在
        VexViewBagPlayer vexViewBagPlayer = selectData(UUID);
        if (vexViewBagPlayer == null) {
            throw new RuntimeException("玩家不存在");
        }
        PreparedStatement ps;
        ps = connection.prepareStatement(SqlCommand.UPDATE_ACTIVE_POINT.commandToString());
        ps.setLong(1, point);
        ps.setString(2, UUID);
        doCommand(ps);
    }
}
