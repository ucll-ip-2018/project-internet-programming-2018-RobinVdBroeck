package be.ucll.runetracker.web.config;

import be.ucll.runetracker.domain.DataPointEntry;
import be.ucll.runetracker.domain.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.logging.Logger;

public class HighScoresService {
    private static final Logger logger = Logger.getLogger(HighScoresService.class.toString());

    private Skill[] skillOrder = {
            Skill.TOTAL,
            Skill.ATTACK,
            Skill.DEFENCE,
            Skill.STRENGTH,
            Skill.HITPOINTS,
            Skill.RANGED,
            Skill.PRAYER,
            Skill.MAGIC,
            Skill.COOKING,
            Skill.WOODCUTTING,
            Skill.FLETCHING,
            Skill.FISHING,
            Skill.FIREMAKING,
            Skill.CRAFTING,
            Skill.SMITHING,
            Skill.MINING,
            Skill.HERBLORE,
            Skill.AGILITY,
            Skill.THIEVING,
            Skill.SLAYER,
            Skill.FARMING,
            Skill.RUNECRAFTING,
            Skill.HUNTER,
            Skill.CONSTRUCTION,
    };

    public List<DataPointEntry> getStats(String username) {
        try {
            URL url = new URL("http://services.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + username);
            URLConnection connection = url.openConnection();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()))) {
                List<DataPointEntry> entries = new ArrayList<>(skillOrder.length);
                String inputLine;
                for (int i = 0; (inputLine = in.readLine()) != null; i++) {
                    if (i >= skillOrder.length) {
                        break;
                    }
                    String[] parts = inputLine.split(",");
                    if (parts.length != 3) {
                        throw new InvalidPropertiesFormatException("Not all information is returned by the runescape api on line: " + i + " with data:" + inputLine);
                    }
                    int[] parsed = new int[parts.length];
                    for (int x = 0; x < parts.length; x++) {
                        parsed[x] = Integer.parseInt(parts[x]);
                    }
                    DataPointEntry entry = new DataPointEntry(skillOrder[i], parsed[0], (short) parsed[1], parsed[2]);
                    entries.add(entry);
                }
                StringBuilder builder = new StringBuilder();
                for(DataPointEntry stat : entries) {
                    builder.append(stat.toString());
                    builder.append("\n");
                }
                logger.info("Fetched stats of " + username);
                logger.info(builder.toString());
                return entries;
            }
        } catch (IOException e) {
            throw new HighscoreServiceException(e);
        }
    }
}
