package me.duck.autogg;

import me.duck.autogg.gui.SettingsGui;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import scala.collection.parallel.ParIterableLike;

public class AutoGgCommand extends CommandBase implements ICommand {

    private AutoGg mod;

    public AutoGgCommand(AutoGg mod) {
        this.mod = mod;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public String getCommandName() {
        return "autogg";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "autogg";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        mod.setGui();

    }
}
