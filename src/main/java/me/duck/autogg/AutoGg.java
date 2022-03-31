package me.duck.autogg;

import me.duck.autogg.gui.SettingsGui;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = AutoGg.MODID, version = AutoGg.VERSION, clientSideOnly = true)
public class AutoGg
{
    public static final String MODID = "autogg";
    public static final String VERSION = "1.0";
    public static Configuration config;
    private boolean openConfig;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        config = new Configuration(e.getSuggestedConfigurationFile());
        syncConfig(true);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        ClientCommandHandler.instance.registerCommand(new AutoGgCommand(this));
    }

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent e) {
        if (!enabled) {
            return;
        }
        String message = e.message.getUnformattedText();
        message = EnumChatFormatting.getTextWithoutFormattingCodes(message);
        if (GetTriggers.checkMessage(message)) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(winMessage);
        }
    }

    @SubscribeEvent
    public void onRender(TickEvent.RenderTickEvent e) {
        if (openConfig) {
            Minecraft.getMinecraft().displayGuiScreen(new SettingsGui());
            openConfig = false;
        }

    }

    public static boolean enabled = true;
    public static String winMessage = "gg";
    public static void syncConfig(boolean reload) {

        if (reload) {
            config.load();
        }

        Property enabledProp = config.get(Configuration.CATEGORY_GENERAL, "enabled", true, "Enabled Auto GG");
        enabled = enabledProp.getBoolean();
        Property winMessageProp = config.get(Configuration.CATEGORY_GENERAL, "winMessage", "gg", "Win Message");
        winMessage = winMessageProp.getString();

        if (config.hasChanged()) {
            config.save();
        }


    }

    public void setGui() {
        openConfig = true;
    }


}
