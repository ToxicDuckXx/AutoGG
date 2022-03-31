package me.duck.autogg.gui;

import me.duck.autogg.AutoGg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraftforge.common.config.Configuration;

import java.io.IOException;

public class SettingsGui extends GuiScreen {

    private GuiTextField text;
    @Override
    public void initGui() {
        super.initGui();
        this.text = new GuiTextField(1, this.fontRendererObj, this.width / 2 - 160, this.height/2-46, 137, 20);
        this.text.setMaxStringLength(23);
        this.text.setEnableBackgroundDrawing(true);
        this.text.setVisible(true);
        this.text.setEnabled(true);
        this.text.setText(AutoGg.winMessage);

        buttonList.add(new GuiButton(2, width/2-80, 150, getEnabled()));
        buttonList.add(new GuiButton(3, width/2-10, this.height/2-46, "Set"));

    }

    private String getEnabled() {
        boolean enabled = AutoGg.enabled;
        if (enabled) {
            return "Enabled";
        }
        else {
            return "Disabled";
        }
    }

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {

        super.drawDefaultBackground();
        buttonList.get(0).drawButton(Minecraft.getMinecraft() , mouseX, mouseY);
        buttonList.get(1).drawButton(Minecraft.getMinecraft(), mouseX, mouseY);
        this.text.drawTextBox();

        super.drawScreen(mouseX, mouseY, partialTicks);


    }
    @Override
    public void mouseClicked(int x, int y, int btn) throws IOException {

        super.mouseClicked(x, y, btn);
        this.text.mouseClicked(x, y, btn);

    }
    @Override
    public void keyTyped(char par1, int par2) throws IOException {

        super.keyTyped(par1, par2);
        this.text.textboxKeyTyped(par1, par2);

    }
    @Override
    public void actionPerformed(GuiButton button) {

        if (button.id == 2) {
            if (button.displayString.equals("Enabled")) {
                button.displayString = "Disabled";
                changeConfig("enabled", false, "");

            }
            else {
                button.displayString = "Enabled";
                changeConfig("enabled", true, "");
            }
        }
        else if (button.id == 3) {
            changeConfig("winMessage", false, this.text.getText());
        }

    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    private void changeConfig(String key, boolean value, String message) {

        if (key.equals("winMessage")) {
            AutoGg.config.get(Configuration.CATEGORY_GENERAL, "winMessage", "gg", "Win Message").set(message);
            AutoGg.config.save();
            AutoGg.winMessage = message;
        }
        else if (key.equals("enabled")) {
            AutoGg.config.get(Configuration.CATEGORY_GENERAL, "enabled", true, "Enabled Auto GG").set(value);
            AutoGg.config.save();
            AutoGg.enabled = value;
        }

    }


}
