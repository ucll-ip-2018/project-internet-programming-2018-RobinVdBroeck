package be.ucll.runetracker.database;

public class SkillDatabaseFactory {
    public static SkillDatabase create() {
        return new SkillDatabaseStub();
    }
}
