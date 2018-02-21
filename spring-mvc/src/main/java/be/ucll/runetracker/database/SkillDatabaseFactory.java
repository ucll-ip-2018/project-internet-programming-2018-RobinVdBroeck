package be.ucll.runetracker.database;

public class SkillDatabaseFactory {
    private SkillDatabaseFactory() {
    }

    public static SkillDatabase create(DatabaseType type) {
        switch (type) {
            case STUB:
                return new SkillDatabaseStub();
            case RELATIONAL:
                throw new DatabaseException("Not yet implemented");
            default:
                throw new IllegalStateException("Unknown database type");
        }
    }
}
