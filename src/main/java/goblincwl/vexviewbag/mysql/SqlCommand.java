package goblincwl.vexviewbag.mysql;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description mysql指令枚举
 * @create 2020-07-19 18:10
 */
public enum SqlCommand {

    //数据库表创建
    CREATE_TABLE("CREATE TABLE IF NOT EXISTS VVB_PLAYERDATA (\n" +
            "    ID           INT AUTO_INCREMENT\n" +
            "        PRIMARY KEY,\n" +
            "    PLAYER_UUID  VARCHAR(36) NOT NULL,\n" +
            "    ONLINE_DATE  VARCHAR(36) NULL,\n" +
            "    ONLINE_TIME  BIGINT      NULL DEFAULT 0,\n" +
            "    ACTIVE_POINT BIGINT      NULL DEFAULT 0,\n" +
            "    SIGN_MONTH   INT(2)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY1    INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY2    INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY3    INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY4    INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY5    INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY6    INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY7    INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY8    INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY9    INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY10   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY11   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY12   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY13   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY14   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY15   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY16   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY17   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY18   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY19   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY20   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY21   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY22   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY23   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY24   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY25   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY26   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY27   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY28   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY29   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY30   INT(1)      NULL DEFAULT 0,\n" +
            "    SIGN_DAY31   INT(1)      NULL DEFAULT 0\n" +
            ")\n" +
            "    ENGINE = InnoDB\n" +
            "    CHARSET = UTF8;"),

    //插入一行
    INSERT_ONE("INSERT INTO VVB_PLAYERDATA (PLAYER_UUID)\n" +
            "VALUES (?)"),

    //查询一行
    SELECT_ONE("SELECT VP.ID, PLAYER_UUID, ONLINE_DATE, ONLINE_TIME, ACTIVE_POINT, SIGN_MONTH, SIGN_DAY1, SIGN_DAY2, SIGN_DAY3,\n" +
            "    SIGN_DAY4, SIGN_DAY5, SIGN_DAY6, SIGN_DAY7, SIGN_DAY8, SIGN_DAY9, SIGN_DAY10, SIGN_DAY11, SIGN_DAY12, SIGN_DAY13,\n" +
            "    SIGN_DAY14, SIGN_DAY15, SIGN_DAY16, SIGN_DAY17, SIGN_DAY18, SIGN_DAY19, SIGN_DAY20, SIGN_DAY21, SIGN_DAY22,\n" +
            "    SIGN_DAY23, SIGN_DAY24, SIGN_DAY25, SIGN_DAY26, SIGN_DAY27, SIGN_DAY28, SIGN_DAY29, SIGN_DAY30, SIGN_DAY31\n" +
            "FROM VVB_PLAYERDATA VP\n" +
            "WHERE VP.PLAYER_UUID = ?"),

    //更新一行
    UPDATE_ONE("UPDATE VVB_PLAYERDATA\n" +
            "SET ONLINE_DATE=?,\n" +
            "    ONLINE_TIME=?,\n" +
            "    ACTIVE_POINT =?,\n" +
            "    SIGN_MONTH =?,\n" +
            "    SIGN_DAY1 =?,\n" +
            "    SIGN_DAY2 =?,\n" +
            "    SIGN_DAY3 =?,\n" +
            "    SIGN_DAY4 =?,\n" +
            "    SIGN_DAY5 =?,\n" +
            "    SIGN_DAY6 =?,\n" +
            "    SIGN_DAY7 =?,\n" +
            "    SIGN_DAY8 =?,\n" +
            "    SIGN_DAY9 =?,\n" +
            "    SIGN_DAY10 =?,\n" +
            "    SIGN_DAY11 =?,\n" +
            "    SIGN_DAY12 =?,\n" +
            "    SIGN_DAY13 =?,\n" +
            "    SIGN_DAY14 =?,\n" +
            "    SIGN_DAY15 =?,\n" +
            "    SIGN_DAY16 =?,\n" +
            "    SIGN_DAY17 =?,\n" +
            "    SIGN_DAY18 =?,\n" +
            "    SIGN_DAY19 =?,\n" +
            "    SIGN_DAY20 =?,\n" +
            "    SIGN_DAY21 =?,\n" +
            "    SIGN_DAY22 =?,\n" +
            "    SIGN_DAY23 =?,\n" +
            "    SIGN_DAY24 =?,\n" +
            "    SIGN_DAY25 =?,\n" +
            "    SIGN_DAY26 =?,\n" +
            "    SIGN_DAY27 =?,\n" +
            "    SIGN_DAY28 =?,\n" +
            "    SIGN_DAY29 =?,\n" +
            "    SIGN_DAY30 =?,\n" +
            "    SIGN_DAY31 =?\n" +
            "WHERE PLAYER_UUID = ?"),

    UPDATE_ONLINE_TIME("UPDATE VVB_PLAYERDATA\n" +
            "SET ONLINE_DATE=?,\n" +
            "    ONLINE_TIME=?\n" +
            "WHERE PLAYER_UUID = ?"),

    UPDATE_ACTIVE_POINT("UPDATE VVB_PLAYERDATA\n" +
            "SET ACTIVE_POINT = ?\n" +
            "WHERE PLAYER_UUID = ?");

    private String command;

    SqlCommand(String command) {
        this.command = command;
    }

    public String commandToString() {
        return command;
    }
}
