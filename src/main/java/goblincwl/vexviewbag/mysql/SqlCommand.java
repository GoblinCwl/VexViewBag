package goblincwl.vexviewbag.mysql;

/**
 * @author ☪wl
 * @program MinecrafProject
 * @description mysql指令枚举
 * @create 2020-07-19 18:10
 */
public enum SqlCommand {

    ;

    private String command;

    SqlCommand(String command) {
        this.command = command;
    }

    public String commandToString() {
        return command;
    }
}
