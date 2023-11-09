package com.candaceBot;

import com.candaceBot.BotInit.PostBotBuild;
import com.candaceBot.Models.Household;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.*;

import java.nio.file.*;

public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws Exception{
        String token = new String(Files.readAllBytes(Paths.get("src/main/resources/token.txt")));
        JDABuilder builder = JDABuilder.createDefault(token,GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS);
        //builder.addEventListeners(new ReadyListener());

        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.customStatus("Managing your chores!"));
        JDA candace = builder.build();
        candace.awaitReady();
        PostBotBuild.botRoutines(candace);
        logger.info(String.format("Candace is ready on %s servers", candace.getGuilds().size()));


    }
}














































































































