package com.candaceBot.BotInit;

import com.candaceBot.Models.Household;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Brain {
    static String brainPath = "src/main/resources/Jsons/brain.json";
    static final Logger logger = LoggerFactory.getLogger(Brain.class);
    public static void brainAlive(JDA bot){
        if(!new File(brainPath).exists()){
            logger.info("No brain found, creating brain.");
            JSONObject brain = new JSONObject();
            for (Guild g: bot.getGuilds()
            ) {
                Household h = new Household(g);
                brain.put(g.getId(), h.populateHouseJson());
            }
            try (FileWriter file = new FileWriter(brainPath)) {
                file.write(brain.toString(4)); // Indent with four spaces
                logger.info("brain JSON file created: " + brainPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            logger.info("Brain exists! Checking if any added to any new servers");
            try {
                String content = new String(Files.readAllBytes(Paths.get(brainPath)));
                JSONObject brain = new JSONObject(new JSONTokener(content));
                for (Guild g : bot.getGuilds()) {
                    if (!brain.has(g.getId())) {
                        logger.info(String.format("Missing %s server - Adding now.", g.getName()));
                        Household h = new Household(g);
                        brain.put(g.getId(), h.populateHouseJson());
                    }
                }
            } catch (IOException e) {
                logger.error("brainFile failed to load.", e);
                e.printStackTrace();
            }

        }
    }
}
