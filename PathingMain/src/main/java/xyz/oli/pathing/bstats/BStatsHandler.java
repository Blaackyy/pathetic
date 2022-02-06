package xyz.oli.pathing.bstats;

import lombok.experimental.UtilityClass;
import org.bukkit.plugin.java.JavaPlugin;

@UtilityClass
public class BStatsHandler {
    
    private static int pathsCreated = 0;
    private static int failedPaths = 0;
    private static int lengthOfPaths = 0;
    
    public static void init(JavaPlugin plugin) {
        
        Metrics metrics = new Metrics(plugin, 14215);
    
        metrics.addCustomChart(new Metrics.SingleLineChart("paths_created", () -> pathsCreated));
        metrics.addCustomChart(new Metrics.SingleLineChart("failed_attempts", () -> failedPaths));
        metrics.addCustomChart(new Metrics.SingleLineChart("length_of_pathfinding_-_blocks", () -> lengthOfPaths));
    }
    
    public static void increasePathCount() {
        pathsCreated++;
    }
    
    public static void increaseFailedPathCount() {
        failedPaths++;
    }
    
    public static void addLength(final int value) {
        lengthOfPaths += value;
    }
}