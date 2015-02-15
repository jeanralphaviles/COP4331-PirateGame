package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;
import utility.decal.ItemDefaultDecal;

public abstract class Item {

        // Atrributes
        protected Decal decal;
        protected Statistics statistics;
        protected Category category;
        protected String name;
		protected final String DEFAULT_NAME = "Default Name";

        // Default Constructor
        public Item(){

            decal =  new ItemDefaultDecal();
            statistics = new Statistics();
            category = Category.ANY_ITEM;
            name = DEFAULT_NAME;

        }
        // Constructor I
        public Item( Decal decal){

            this.decal = decal;
            category = Category.ANY_ITEM;
            statistics = new Statistics();
            name = DEFAULT_NAME;
        }
        // Constructor II
        public Item( Decal decal, Category category ){

            this.decal = decal;
            this.category = category;
            statistics = new Statistics();
            name = DEFAULT_NAME;

        }
        // Constructor III
        public Item( Decal decal, Category category, String name){

            this.decal = decal;
            this.category = category;
            this.statistics = new Statistics();
            this.name = name;
        }
        // Constructor IV
        public Item( Decal decal, Category category, String name, Statistics statistics){

            this.decal = decal;
            this.category = category;
            this.statistics = statistics;
            this.name = name;
        }

        // ---------- METHODS IMPLEMENTATION ---------
        // ----------                         ---------

        // Accessor Methods:
        // --------------------------------------------
        public Decal getDecal(){

            return decal;
        }

        // --------------------------------------------
        public Category getCategory(){

            return category;
        }
        // --------------------------------------------
        public String getName(){return name;}

        // --------------------------------------------
        public Statistics getAugmentStatistics( ){return statistics;}

        // Mutator Methods:
        // --------------------------------------------
        public void getName(String name){ this.name = name;}

        // --------------------------------------------
        public void setDecal(Decal decal){

            this.decal = decal;

        }
       
        public void setName(String name) {
			this.name = name;
		}

        // --------------------------------------------

		public final void setAugmentStatistics(Statistics statistics) {
			this.statistics = statistics;
		}

		public void augmentStatistics(Statistics statistics) {

            statistics.changeAgility(statistics.getAgility());
			statistics.changeHardiness(statistics.getHardiness());
			statistics.changeIntellect(statistics.getIntellect());
			statistics.changeMaxHealth(statistics.getMaxHealth());
			statistics.changeStrength(statistics.getStrength());
		}



        // Abstract Methods:
        // --------------------------------------------
        public abstract void triggerProximity( Entity entity );
}
