package me.robinvdb.runetracker.services;

import me.robinvdb.runetracker.domain.DataPointEntry;

import java.util.List;

public interface HighScoresService {
    List<DataPointEntry> getStats(String username);
}
