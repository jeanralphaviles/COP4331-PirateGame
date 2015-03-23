package model.entity.occupation.ability;

import model.Level;
import model.entity.Entity;
import utility.decal.Decal;

public abstract class Ability {

    private int manaCost = 10;
    private String name = "Ability";
    private Decal icon = Decal.createDecal(Decal.level_up);
    //
    private int abilityLevel = 0;
    //
    public static int abilityLevelCost = 25;

    public Ability() {
    }

    public Ability(String name) {
        this.name = name;
    }

    public Ability(int manaCost, String name) {
        this(name);
        this.manaCost = manaCost;
    }

    public Ability(int manaCost, Decal icon) {
        this.manaCost = manaCost;
        this.icon = icon;
    }

    public Ability(int manaCost, String name, Decal icon) {
        this(manaCost, name);
        this.icon = icon;
    }

    public abstract void activate(Entity caster, Level level);

    @Override
    public boolean equals(Object object) {
        Ability ability = (Ability) object;
        boolean sameName = this.name.equals(ability.getName());
        return sameName;
    }

    public boolean entityHasMana(Entity entity) {
        return entity.getDerivedStatistics().getCurrentMana() >= getEffectiveManaCost();
    }

    public void removeEntityMana(Entity entity) {
        entity.getStatistics().changeCurrentMana(-1 * getEffectiveManaCost());
    }

    private int getManaCost() {
        return manaCost;
    }
    
    public int getEffectiveManaCost() {
        return manaCost - abilityLevel;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Decal getIcon() {
        return icon;
    }

    public void setIcon(Decal icon) {
        this.icon = icon;
    }

    public int getAbilityLevel() {
        return abilityLevel;
    }

    public void setAbilityLevel(int abilityLevel) {
        if (abilityLevel < 0) {
            return;
        }
        this.abilityLevel = abilityLevel;
    }

}
