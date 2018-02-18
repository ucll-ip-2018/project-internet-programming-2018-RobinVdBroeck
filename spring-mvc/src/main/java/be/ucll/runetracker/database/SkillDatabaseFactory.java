package be.ucll.runetracker.database;

public class SkillDatabaseFactory {
    private SkillDatabaseFactory() {
    }

    public static SkillDatabase create() {
        return new SkillDatabaseStub();
    }
}
