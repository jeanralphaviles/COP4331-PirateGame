package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;

public abstract class Item {

        // Atrributes
        protected Decal decal;
        protected Statistics statistics;

        // Default Constructor
        public Item(){

            decal = null;
            statistics = null;

        }
        // Constructor I
        public Item( Decal decal){

            this.decal = decal;
            statistics = null;
        }
        // Constructor I
        public Item( Decal decal, Statistics statistics){

            this.decal = decal;
            this.statistics = statistics;
        }

        // ---------- METHODS IMPLEMENTATION ---------
        // ----------                         ---------

        // Accessor Methods:
        // --------------------------------------------
        public Decal getDecal(){ return decal; }

        // --------------------------------------------
        public Statistics getAugmentStatistics( ){return statistics;}

        // Mutator Methods:
        // --------------------------------------------
        public void setDecal(Decal decal){ this.decal = decal;}

        // --------------------------------------------
        public void setAugmentStatistics( Statistics statistics ){ this.statistics = statistics; }

        // Abstract Methods:
        // --------------------------------------------
        public abstract void triggerProximity( Entity entity );
}
