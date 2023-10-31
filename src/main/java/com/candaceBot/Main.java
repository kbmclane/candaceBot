package com.candaceBot;

import com.candaceBot.BotInit.PostBotBuild;
import com.candaceBot.Listeners.EventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception{
        String token = new String(Files.readAllBytes(Paths.get("C:\\Users\\k\\IdeaProjects\\candaceBot\\src\\main\\resources\\token.txt")));
        JDABuilder builder = JDABuilder.createDefault(token,GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);

        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.customStatus("Managing your chores!"));
        JDA bot = builder.build();
        PostBotBuild.botRoutines(bot);
    }
}