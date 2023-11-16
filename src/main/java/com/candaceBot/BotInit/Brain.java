package com.candaceBot.BotInit;

import com.candaceBot.Models.Assignee;
import com.candaceBot.Models.Household;
import com.candaceBot.Utils.MessageUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Brain {
    static String brainPath = "src/main/resources/Jsons/%s.json";
    static final Logger logger = LoggerFactory.getLogger(Brain.class);

    public static void populateBrain(JDA bot) {
        for (Guild g : bot.getGuilds()
        ) {
            String path = String.format(brainPath, g.getId());
            JSONObject fooBrain = new JSONObject();
            if (!new File(path).exists()) {
                logger.info(String.format("No file found for %s server, creating file.", g.getName()));
                Household h = new Household(g);
                fooBrain.put("household", h.toJson());
                Assignee a = new Assignee(h.getOwner().getUser(), true);
                JSONObject aSec = new JSONObject();
                aSec.put(a.getId(), a.toJson());
                fooBrain.put("assignees", aSec);
                fooBrain.put("chores", new JSONObject());
                try (FileWriter file = new FileWriter(path)) {
                    file.write(fooBrain.toString(4)); // Indent with four spaces
                    logger.info(String.format("%s JSON file created: " + path, g.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else{
                try (FileWriter file = new FileWriter(path)) {
                    file.write(fooBrain.toString(4)); // Indent with four spaces
                    logger.info(String.format("%s JSON file created: " + path, g.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addHousehold(Household h) {
        String path = String.format(brainPath, h.getId());
        if (new File(path).exists()) {
            logger.error(String.format("'%s' server already in Candace's brain. Notifying owner.", h.getHouseholdName()));
            MessageUtils.notifyOwnerServerAlreadyExists(h.getOwner(), h.getHouseholdName());
        } else {
            JSONObject brain = new JSONObject();
            brain.put("household", h.toJson());
            Assignee a = new Assignee(h.getOwner().getUser(), true);
            JSONObject aSec = new JSONObject();
            aSec.put(a.getId(), a.toJson());
            brain.put("assignees", aSec);
            brain.put("chores", new JSONObject());
            try (FileWriter file = new FileWriter(path)) {
                file.write(brain.toString(4)); // Indent with four spaces
                logger.info(String.format("'%s' JSON file created: " + path, h.getHouseholdName()));
                MessageUtils.notifyOwnerServerAdded(h.getOwner(), h.getHouseholdName());
                file.close();
            } catch (IOException e) {
                logger.error(String.format("Problem adding Candace to '%s'. Notifying owner to try again later.", h.getHouseholdName()));
                MessageUtils.notifyOwnerBrainError(h.getOwner(), h.getHouseholdName());
                e.printStackTrace();
            }
        }
    }

    public void removeHousehold(Guild g) {
        String path = String.format(brainPath, g.getId());
        Member owner = g.retrieveOwner().submit().join();
        if (!new File(path).exists()) {
            logger.error("Cannot find %s server to remove in Candace's brain. Notifying owner.");
            MessageUtils.notifyOwnerServerDoesNotExist(owner, g.getName());
        } else {
            Path p = Paths.get(path);
            try {
                Files.delete(p); // Indent with four spaces
                logger.info(String.format("%s server removed from Candace's brain.", g.getName()));
            } catch (IOException e) {
                logger.error(String.format("Problem removing Candace to %s. Notifying owner to try again later.", g.getName()));
                MessageUtils.notifyOwnerBrainError(owner, g.getName());
                e.printStackTrace();
            }
        }
    }

    public void updateHouseholdName(Guild g, String oldName) {
        String path = String.format(brainPath, g.getId());
        Member owner = g.retrieveOwner().complete();
        File file = new File(path);
        if (!file.exists()) {
            logger.error("Cannot find '%s' server to remove in Candace's brain. Notifying owner.");
            MessageUtils.notifyOwnerServerDoesNotExist(owner, g.getName());
        }  else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode o = mapper.readTree(file);
                JsonNode i = o.path("household");
                ((ObjectNode)i).put("name",g.getName());
                ((ObjectNode)o).put("household",i);
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, o);
                logger.info(String.format("'%s' server updated to '%s' in Candace's brain.", oldName, g.getName()));
            } catch (Exception e) {
                logger.error(String.format("Problem updating '%s' server name to '%s'. Notifying owner to try again later.", oldName, g.getName()));
                MessageUtils.notifyOwnerBrainError(owner, g.getName());
                e.printStackTrace();
            }
        }
    }
    public void removeMembership(Guild g, User u) {
        String path = String.format(brainPath, g.getId());
        Member owner = g.retrieveOwner().complete();
        File file = new File(path);
        if (!file.exists()) {
            logger.error("Cannot find %s server to remove in Candace's brain. Notifying owner.");
            MessageUtils.notifyOwnerServerDoesNotExist(owner, g.getName());
        }  else {
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode o = mapper.readTree(file);
                JsonNode i = o.path("assignees");
                ((ObjectNode)i).put("name",u.getEffectiveName());
                ((ObjectNode)o).put("assignees",i);
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, o);
                logger.info(String.format("%s server updated to %s in Candace's brain.", g.getName()));
            } catch (Exception e) {
                logger.error(String.format("Problem removing user %s from %s server. Notifying owner to manually opt them out later.", g.getName()));
                MessageUtils.notifyOwnerBrainError(owner, g.getName());
                e.printStackTrace();
            }
        }
    }
}

