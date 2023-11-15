package com.candaceBot;

import com.candaceBot.BotInit.PostBotBuild;
import com.candaceBot.Listeners.CommandListener;
import com.candaceBot.Listeners.EventListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.*;

public class Main {
    static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws Exception{
        String path = System.getProperty("TOKEN_PATH", "wrong");
        if(path.equals("wrong")){
            logger.error("Token path unable to be retrieved. Throwing!");
            throw new Exception("Path to bot token unavailable.");
        }
        String token = new String(Files.readAllBytes(Paths.get(path)));
        JDA candace = JDABuilder.createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_MEMBERS)
                .setActivity(Activity.customStatus("Managing your chores!"))
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .setBulkDeleteSplittingEnabled(false)
                .addEventListeners(new EventListener(), new CommandListener())
                .build();
        candace.awaitReady();
        PostBotBuild.botRoutines(candace);
        logger.info(String.format("Candace is ready on %s servers", candace.getGuilds().size()));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if(candace != null){
                candace.shutdownNow();
            }
        }));
    }
}