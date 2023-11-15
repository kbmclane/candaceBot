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
    private static JSONObject brain;

    public static JSONObject brainAlive(JDA bot) {
        JSONObject fooBrain = new JSONObject();
        if (!new File(brainPath).exists()) {
            logger.info("No brain found, creating brain.");

            for (Guild g : bot.getGuilds()
            ) {
                Household h = new Household(g);
                fooBrain.put(g.getId(), h.initiateHouseJson());
            }
            try (FileWriter file = new FileWriter(brainPath)) {
                file.write(fooBrain.toString(4)); // Indent with four spaces
                logger.info("brain JSON file created: " + brainPath);
                brain = fooBrain;
                return fooBrain;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
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

    public void addHousehold(Household house) {
        if (brain.has(house.getId())) {
            logger.error("%s server already in Candace's brain. Notifying owner.");
            MessageUtils.notifyOwnerServerAlreadyExists(house.getOwner(), house.getHouseholdName());
        } else {
            brain.put(house.getId(), house.initiateHouseJson());
            try (FileWriter file = new FileWriter(brainPath)) {
                file.write(brain.toString(4)); // Indent with four spaces
                logger.info(String.format("%s server added to Candace's brain. Notifying owner.", house.getHouseholdName()));
                MessageUtils.notifyOwnerServerAdded(house.getOwner(), house.getHouseholdName());
            } catch (IOException e) {
                logger.error(String.format("Problem adding Candace to %s. Notifying owner to try again later.", house.getHouseholdName()));
                MessageUtils.notifyOwnerBrainError(house.getOwner(), house.getHouseholdName());
                e.printStackTrace();
            }
        }
    }

    public void removeHousehold(Guild g) {
        if (!brain.has(g.getId())) {
            logger.error("Cannot find %s server to remove in Candace's brain. Notifying owner.");
            MessageUtils.notifyOwnerServerAlreadyExists(g.getOwner(), g.getHouseholdName());
        } else {
            brain.remove(g.getId());
            try (FileWriter file = new FileWriter(brainPath)) {
                file.write(brain.toString(4)); // Indent with four spaces
                logger.info(String.format("%s server added to Candace's brain. Notifying owner.", g.getHouseholdName()));
                MessageUtils.notifyOwnerServerAdded(g.getOwner(), g.getHouseholdName());
            } catch (IOException e) {
                logger.error(String.format("Problem adding Candace to %s. Notifying owner to try again later.", g.getHouseholdName()));
                MessageUtils.notifyOwnerBrainError(g.getOwner(), g.getHouseholdName());
                e.printStackTrace();
            }
        }
    }

    public JSONObject getHousehold(String id) {
        return brain.getJSONObject(id);
    }


}

