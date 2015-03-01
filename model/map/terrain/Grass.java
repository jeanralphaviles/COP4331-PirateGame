package model.map.terrain;

import utility.decal.Decal;

public final class Grass extends Terrain {

    public Grass() {
        super(new Decal(Decal.grass));
    }

    public Grass(Decal decal) {
        super(decal);
    }

    @Override
    public boolean isPassable() {
        return true;
    }
}
