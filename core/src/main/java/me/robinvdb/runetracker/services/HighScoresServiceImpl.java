package me.robinvdb.runetracker.services;

import me.robinvdb.runetracker.domain.DataPointEntry;
import me.robinvdb.runetracker.domain.Skill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class HighScoresServiceImpl implements HighScoresService {
    private static final Logger logger = Logger.getLogger(HighScoresServiceImpl.class.toString());

    private final Skill[] skillOrder = {
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
            logger.info("Fetching stats of " + username);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()))) {
                final AtomicInteger atomicInteger = new AtomicInteger();

                return in.lines()
                        .limit(skillOrder.length)
                        .map(line -> line.split(","))
                        .map(Arrays::asList)
                        .map(this::convertStringsToIntegers)
                        .map(parsed -> new DataPointEntry(skillOrder[atomicInteger.getAndIncrement()], parsed.get(0), parsed.get(1).shortValue(), parsed.get(2)))
                        .collect(Collectors.toList());

            }
        } catch (IOException e) {
            throw new HighscoreServiceException(e);
        }
    }


    private List<Integer> convertStringsToIntegers(Collection<String> strings) {
        List<Integer> parsed = new ArrayList<>(strings.size());
        for(String string : strings) {
            int parsedInt = Integer.parseInt(string);
            parsed.add(parsedInt);
        }
        return parsed;
    }
}
