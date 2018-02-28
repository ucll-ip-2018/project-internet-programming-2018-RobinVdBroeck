package be.ucll.runetracker.database;

import be.ucll.runetracker.domain.DataPoint;
import be.ucll.runetracker.domain.Skill;
import be.ucll.runetracker.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class DataPointService {
    private UserDatabase userDatabase;
    private DataPointDatabase dataPointDatabase;
    private SkillDatabase skillDatabase;

    private DataPointService() {
    }

    public DataPointService(UserDatabase userDatabase, DataPointDatabase dataPointDatabase, SkillDatabase skillDatabase) {
        setUserDatabase(userDatabase);
        setDataPointDatabase(dataPointDatabase);
        setSkillDatabase(skillDatabase);
    }

    private UserDatabase getUserDatabase() {
        return userDatabase;
    }

    private void setUserDatabase(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    private DataPointDatabase getDataPointDatabase() {
        return dataPointDatabase;
    }

    private void setDataPointDatabase(DataPointDatabase dataPointDatabase) {
        this.dataPointDatabase = dataPointDatabase;
    }

    private SkillDatabase getSkillDatabase() {
        return skillDatabase;
    }

    private void setSkillDatabase(SkillDatabase skillDatabase) {
        this.skillDatabase = skillDatabase;
    }

    // USERS
    public Optional<User> getUser(int id) {
        return userDatabase.get(id);
    }

    public List<User> getAllUsers() {
        return userDatabase.all();
    }

    public void addUser(User user) {
        userDatabase.add(user);
    }


    public void deleteUser(User user) {
        userDatabase.delete(user);
    }

    // DATAPOINTS
    public Optional<DataPoint> getDatapoint(int id) {
        return dataPointDatabase.get(id);
    }

    public List<DataPoint> getAllDatapoints() {
        return dataPointDatabase.all();
    }

    public void addDatapoint(DataPoint dataPoint) {
        dataPointDatabase.add(dataPoint);
    }

    public void deletedDataPoint(DataPoint dataPoint) {
        dataPointDatabase.delete(dataPoint);
    }

    // Skills
    public Optional<Skill> getSkill(String name) {
        return skillDatabase.get(name);
    }

    public List<Skill> getAllSkills() {
        return skillDatabase.all();
    }

    public void addSkill(Skill skill) {
        skillDatabase.add(skill);
    }

    public void deleteSkill(Skill skill) {
        skillDatabase.delete(skill);
    }
}
