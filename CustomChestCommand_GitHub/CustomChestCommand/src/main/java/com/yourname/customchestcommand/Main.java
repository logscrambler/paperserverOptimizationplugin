package com.yourname.customchestcommand;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("chest").setExecutor(new ChestCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("CustomChestCommand 플러그인 비활성화");
    }
}
