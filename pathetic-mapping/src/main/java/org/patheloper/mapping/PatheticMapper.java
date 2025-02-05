package org.patheloper.mapping;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.plugin.java.JavaPlugin;
import org.patheloper.Pathetic;
import org.patheloper.api.annotation.Experimental;
import org.patheloper.api.pathing.Pathfinder;
import org.patheloper.api.pathing.rules.PathingRuleSet;
import org.patheloper.model.pathing.pathfinder.AStarPathfinder;
import org.patheloper.util.ErrorLogger;

import java.util.Objects;

/**
 * PatheticMapper is a utility class that maps the Pathetic API to the Pathetic Implementation.
 */
@UtilityClass
public class PatheticMapper {

    /**
     * Initializes the Lib.
     * If the lib is not initialized yet but is used anyways, this will cause many things to break.
     *
     * @param javaPlugin the JavaPlugin which initializes the lib
     * @throws IllegalStateException If an attempt is made to initialize more than 1 time
     */
    public void initialize(JavaPlugin javaPlugin) {
        Pathetic.initialize(javaPlugin);
    }
    
    /**
     * Instantiates a new pathfinder object.
     *
     * @return The {@link Pathfinder} object
     * @throws IllegalStateException If the lib is not initialized yet
     */
    public @NonNull Pathfinder newPathfinder() {
        return newPathfinder(PathingRuleSet.createAsyncRuleSet());
    }
    
    /**
     * Instantiates a new A*-pathfinder.
     *
     * @param pathingRuleSet - The {@link PathingRuleSet}
     * @return The {@link Pathfinder}
     * @throws IllegalStateException If the lib is not initialized yet
     */
    public @NonNull Pathfinder newPathfinder(PathingRuleSet pathingRuleSet) {
        return newPathfinder(pathingRuleSet, PathfinderType.ASTAR);
    }
    
    /**
     * Instantiates a new pathfinder object.
     *
     * @param pathingRuleSet - The {@link PathingRuleSet}
     * @param pathfinderType - The {@link PathfinderType}
     * @return The {@link Pathfinder} object
     * @throws IllegalStateException If the lib is not initialized yet
     */
    @Deprecated
    public @NonNull Pathfinder newPathfinder(PathingRuleSet pathingRuleSet, PathfinderType pathfinderType) {
        if (Pathetic.isInitialized()) {
            if (Objects.requireNonNull(pathfinderType) == PathfinderType.ASTAR) {
                return new AStarPathfinder(pathingRuleSet);
            }
            throw ErrorLogger.logFatalError("Unknown pathfinder type: " + pathfinderType);
        }
        throw ErrorLogger.logFatalError("Pathetic is not initialized");
    }

    @Deprecated
    public enum PathfinderType {
        
        ASTAR;
    }

}
