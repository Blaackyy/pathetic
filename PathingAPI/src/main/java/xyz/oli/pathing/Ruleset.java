package xyz.oli.pathing;

import java.util.Set;

public interface Ruleset {
    
    void addRule(Rule rule); // lambda
    
    void applyOn(Path path);
    
    Set<Rule> getRules();
    
}
