package com.candaceBot.BotInit;

import com.candaceBot.Models.Household;
import com.candaceBot.Utils.MessageUtils;
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
    private JSONObject brain;

    public static JSONObject brainAlive(JDA bot){
        JSONObject brain = new JSONObject();
        if(!new File(brainPath).exists()){
            logger.info("No brain found, creating brain.");

            for (Guild g: bot.getGuilds()
            ) {
                Household h = new Household(g);
                brain.put(g.getId(), h.initiateHouseJson());
            }
            try (FileWriter file = new FileWriter(brainPath)) {
                file.write(brain.toString(4)); // Indent with four spaces
                logger.info("brain JSON file created: " + brainPath);
                return brain;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            logger.info("Brain exists! Checking if any added to any new servers");
            try {
                String content = new String(Files.readAllBytes(Paths.get(brainPath)));
                brain = new JSONObject(new JSONTokener(content));
                for (Guild g : bot.getGuilds()) {
                    if (!brain.has(g.getId())) {
                        logger.info(String.format("Missing %s server - Adding now.", g.getName()));
                        Household h = new Household(g);
                        brain.put(g.getId(), h.initiateHouseJson());
                    }
                }
                logger.info(String.format("Brain loaded! Found %s households.", brain.length()));
                return brain;
            } catch (IOException e) {
                logger.error("Brain file exists, but failed to load.", e);
                e.printStackTrace();
            }

        }
        return brain;
    }
    public void addHousehold(Household house){
        if(!brain.isEmpty()){
            if(brain.has(house.getId())){
                logger.error("%s server already in brain. Notifying owner.");
                MessageUtils.notifyOwnerBotExists(house.getOwner(), house.householdName);
            } else {
                brain.put(house.getId(), house.initiateHouseJson());
            }
        } else {
            logger.error("Head empty, no thoughts. Notifying server owner.");
            MessageUtils.notifyOwnerBrainError(house.getOwner(), house.householdName);
        }
    }

    public JSONObject getHousehold(String id){
        return brain.getJSONObject(id);
    }


}

