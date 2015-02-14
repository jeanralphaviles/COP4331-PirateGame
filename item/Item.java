package item;

public abstact class Item {

        // Atrributes
        protected Decal groundDecal;
        protected Statistics statistics;

        // Default Constructor
        public Item(){

            groundDecal = null;
            statistics = null;

        }
        // Constructor I
        public Item( Decal groundDecal ){

            this.groundDecal = groundDecal;
            statistics = null;
        }
        // Constructor I
        public Item( Decal groundDecal, Statistics statistics){

            this.groundDecal = groundDecal;
            this.statistics = statistics;
        }

        // ---------- METHODS IMPLEMENTATION ---------
        // ----------                         ---------

        // Accessor Methods:
        // --------------------------------------------
        public Decal getGroundDecal(){ return groundDecal; }

        // --------------------------------------------
        public Statistics getAugmentStatistics( ){return statistics}

        // Mutator Methods:
        // --------------------------------------------
        public void setGroundDecal( Deacal decal){ this.groundDecal = groundDecal;}

        // --------------------------------------------
        public void setAugmentStatistics( Statistics statistics ){ this.statistics = statistics; }

        // Abstract Methods:
        // --------------------------------------------
        public abstract void triggerProximity( Entity entity );
}
