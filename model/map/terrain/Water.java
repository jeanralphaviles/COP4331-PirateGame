package model.map.terrain;

import utility.decal.Decal;

public final class Water extends Terrain {

    public Water() {
        super(new Decal(Decal.water));
    }

    public Water(Decal decal) {
        super(decal);
    }

    @Override
    public boolean isPassable() {
        return false;
    }
}
