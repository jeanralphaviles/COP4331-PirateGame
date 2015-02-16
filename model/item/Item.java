package model.item;

import java.io.IOException;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;
import utility.decal.ItemDefaultDecal;

/**
* Encapsulates all item types. Allows for easy handling by map/entity
*/


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

        @Override
		public String toString() {
        	if (this.category == Category.TAKEABLE_ITEM) {
        		return ((TakeableItem)this).toString();
        	}
        	return "[" + category.toString() + "," + decal.toString() + "," + statistics.toString() + "," + name + "]";
        }
        
		public static Item fromString(String string) throws IOException {
			String stripped = string.substring(1, string.length() - 1);
			int bracketCount = 0;
			int start = 0;
			int itemCount = 0;
			Item item = new InteractiveItem();
			for (int j = 0; j < stripped.length(); ++j) {
				if (stripped.charAt(j) == ',') {
					Category category = Category.valueOf(stripped.substring(start, j));
					switch (category) {
						case ANY_ITEM:
							break;
						case INTERACTIVE_ITEM:
							item = new InteractiveItem();
						case OBSTACLE_ITEM:
							item = new ObstacleItem();
							break;
						case ONE_SHOT_ITEM:
							item = new OneShotItem();
							break;
						case TAKEABLE_ITEM:
							return TakeableItem.fromString(string);
						default:
					}
					start = j + 1;
					break;
				}
			}
			
			for (int i = start; i < stripped.length(); ++i) {
				if (bracketCount == 0 && stripped.charAt(i) == ',') {
					if (itemCount == 0) {
						Decal decal = Decal.fromString(stripped.substring(start, i));
						item.decal = decal;
					} else if (itemCount == 1) {
						Statistics statistics = Statistics.fromString(stripped.substring(start, i));
						item.statistics = statistics;
						break;
					} 
					++itemCount;
					start = i + 1;
				} else if (stripped.charAt(i) == '[') {
					++bracketCount;
				} else if (stripped.charAt(i) == ']') {
					--bracketCount;
				}
			}
			
			item.name = stripped.substring(start, stripped.length());
			return item;
		}
}
