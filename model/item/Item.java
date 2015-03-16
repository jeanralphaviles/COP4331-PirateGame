package model.item;

import model.entity.Entity;
import model.entity.Statistics;
import utility.decal.Decal;

/**
 * Encapsulates all item types. Allows for easy handling by map/entity
 */
public abstract class Item implements Cloneable {
    protected Decal decal;
    protected Statistics statistics;
    protected Category category;
    protected String name;
    protected final String DEFAULT_NAME = "Default Name";
    protected boolean isVisibile = true;

    public Item() {
        decal = new Decal(Decal.item_default);
        statistics = new Statistics();
        category = Category.ONE_SHOT_ITEM;
        name = DEFAULT_NAME;
    }

    public Item(Decal decal) {
    	this();
        this.decal = decal;
    }

    public Item(Decal decal, Category category) {
    	this(decal);
        this.category = category;
    }

    public Item(Decal decal, Category category, String name) {
    	this(decal, category);
        this.name = name;
    }

    public Item(Decal decal, Category category, String name, Statistics statistics) {
    	this(decal, category, name);
        this.statistics = statistics;
    }

    public abstract void triggerProximity(Entity entity);

	public void augmentStatistics(Statistics statistics) {
	    statistics.changeAgility(statistics.getAgility());
	    statistics.changeHardiness(statistics.getHardiness());
	    statistics.changeIntellect(statistics.getIntellect());
	    statistics.changeMaxHealth(statistics.getMaxHealth());
	    statistics.changeStrength(statistics.getStrength());
	}
	
	@Override
	public abstract Item clone();

	@Override
	public String toString() {
	    if (this.category == Category.TAKEABLE_ITEM) {
	        return ((TakeableItem) this).toString();
	    }
	    int visibility = this.isVisibile ? 1 : 0;
	    return "[" + category.toString() + "," + decal.toString() + "," + statistics.toString() + "," + name + "," + visibility + "]";
	}

	public static Item fromString(String string) {
	    String stripped = string.trim().substring(1, string.length() - 1);
	    int bracketCount = 0;
	    int start = 0;
	    int itemCount = 0;
	    Item item = new InteractiveItem();
	    for (int j = 0; j < stripped.length(); ++j) {
	        if (stripped.charAt(j) == ',') {
	            Category category = Category.valueOf(stripped.substring(start, j));
	            switch (category) {
	                case INTERACTIVE_ITEM:
	                    item = new InteractiveItem();
	                    break;
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
	                start = i + 1;
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
	    item.name = stripped.substring(start, stripped.length()).split(",")[0];
	    item.isVisibile = Integer.parseInt(stripped.substring(start, stripped.length()).split(",")[1]) == 1;
	    return item;
	}

	public Decal getDecal() {
		if (isVisibile) {
			return decal;
		} else {
			return null;
		}
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public Statistics getStatistics() {
        return statistics;
    }
    
    public void getName(String name) {
        this.name = name;
    }
    
    public void setDecal(Decal decal) {
        this.decal = decal;
    }

    public void setName(String name) {
        this.name = name;
    }

    public final void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
 
	public void setVisbility(boolean visibility) {
		this.isVisibile = visibility;
	}
	
	public boolean getVisibility() {
		return isVisibile;
	}

    public static void main(String[] args) {
    	Item[] originals = {
    			new InteractiveItem(),
    			new ObstacleItem(),
    			new OneShotItem(),
    			new TakeableItem()
    	};
    	Item[] restored = new Item[originals.length];
    	
    	for (int i = 0; i < originals.length; ++i) {
    		restored[i] = Item.fromString(originals[i].toString());
    		if (restored[i].toString().equals(originals[i].toString()) == false) {
    			System.out.println("Serialized strings differ");
    		}
    		if (restored[i].getClass() != originals[i].getClass()) {
    			System.out.println("Classes differ");
    		}
    		if (restored[i].getCategory().equals(originals[i].getCategory()) == false) {
    			System.out.println("Categories differ");
    		}
    		if (restored[i].getDecal().toString().equals(originals[i].getDecal().toString()) == false) {
    			System.out.println("Decals Differ");
    		}
    		if (restored[i].getName().equals(originals[i].getName()) == false) {
    			System.out.println("Names differ");
    		}
    		if (restored[i].getStatistics().toString().equals(originals[i].getStatistics().toString()) == false) {
    			System.out.println("Statistics differ");
    		}
    		if (restored[i].isVisibile != originals[i].isVisibile) {
    			System.out.println("Visibilities differ");
    		}
    	}
    }

}
