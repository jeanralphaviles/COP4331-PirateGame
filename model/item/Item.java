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

        // Default Constructor
        public Item(){

            decal =  new ItemDefaultDecal();
            statistics = new Statistics();
            category = Category.ANY_ITEM;

        }
        // Constructor I
        public Item( Decal decal){

            this.decal = decal;
            category = Category.ANY_ITEM;
            statistics = new Statistics();
        }
        // Constructor II
        public Item( Decal decal, Category category ){

            this.decal = decal;
            this.category = category;
            statistics = new Statistics();
        }
        // Constructor III
        public Item( Decal decal, Category category, Statistics statistics){

            this.decal = decal;
            this.category = category;
            this.statistics = statistics;
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
        public Statistics getAugmentStatistics( ){return statistics;}

        // Mutator Methods:
        // --------------------------------------------
        public void setDecal(Decal decal){

            this.decal = decal;

        }
        // --------------------------------------------
        public void setAugmentStatistics( Statistics statistics ){

                this.statistics = statistics;
        }

        // Abstract Methods:
        // --------------------------------------------
        public abstract void triggerProximity( Entity entity );
}
