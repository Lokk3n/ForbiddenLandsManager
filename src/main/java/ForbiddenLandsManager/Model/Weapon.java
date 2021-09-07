package ForbiddenLandsManager.Model;

public class Weapon extends Item{
    String name;
    int gearBonus;
    int damage;
    String features;
    String comment;

    public Weapon(String name, int gearBonus, int damage, String features, String comment) {
        this.name = name;
        this.gearBonus = gearBonus;
        this.damage = damage;
        this.features = features;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGearBonus() {
        return gearBonus;
    }

    public void setGearBonus(int gearBonus) {
        this.gearBonus = gearBonus;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
