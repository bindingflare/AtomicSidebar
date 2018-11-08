package com.gmail.flintintoe.command.subcommand;

import com.gmail.flintintoe.Sendable;
import org.bukkit.command.CommandSender;

public abstract class Subcommand extends Sendable {
    private static final String uniuserpermission = "atomicsidebar.*";
    private static final String uniadminpermission = "atomicsidebar.admin.*";
    private String subcommandlabel;
    private String permission;
    private String adminpermission;

    public Subcommand(String subcommandlabel, String permission, String adminpermission) {
        this.subcommandlabel = subcommandlabel;
        this.permission = permission;
        this.adminpermission = adminpermission;
    }

    public String getLabel() {
        return subcommandlabel;
    }

    public String getPermission() {
        return permission;
    }

    public String getAdminpermission() {
        return adminpermission;
    }

    public boolean checkIfUser(CommandSender sender) {
        return sender.hasPermission(getPermission()) || sender.hasPermission(getAdminpermission()) || hasUniversalPermission(sender);
    }

    public boolean checkIfAdmin(CommandSender sender) {
        return sender.hasPermission(getAdminpermission()) || hasUniversalPermission(sender);
    }

    public boolean hasUniversalPermission(CommandSender sender) {
        return sender.hasPermission(uniadminpermission) || sender.hasPermission(uniuserpermission);
    }

    public void sendPermissionError(CommandSender sender) {
        send(sender, "You do not have enough permissions to execute this command");
    }

    public void sendManyArgumentsError(CommandSender sender) {
        send(sender, "There are too many arguments in your command");
    }

    public abstract void execute(CommandSender sender, String[] args);
}
