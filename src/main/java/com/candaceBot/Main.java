package com.candaceBot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception{
        String token = new String(Files.readAllBytes(Paths.get("C:\\Users\\k\\IdeaProjects\\candaceBot\\src\\main\\resources\\token.txt")));
        System.out.println("Hello world!");
        JDABuilder builder = JDABuilder.createDefault(token);

        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.watching("TV"));

        builder.build();
    }
}