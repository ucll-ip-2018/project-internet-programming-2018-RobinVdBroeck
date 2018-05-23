package be.ucll.runetracker.services;

import be.ucll.runetracker.domain.DataPointEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public interface HighScoresService {
    List<DataPointEntry> getStats(String username);
}
