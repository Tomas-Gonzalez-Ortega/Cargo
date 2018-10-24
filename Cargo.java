import java.util.*;

/** A cargo module for holding Items. Items can be retrieved in various ways.
    @author Jeremy Hilliker @ Langara
    @author Tomas Gonzalez Ortega
    @version 2017-05-16 11h17
    @see <a href="https://d2l.langara.bc.ca/d2l/le/content/88736/viewContent/1313159/View">a 03: Cargo</a>
*/
public class Cargo
{
   private final int maxWeight;
   private final HashSet<Item> cargo;

   /** Create a new cargo module with a given weight capacity.
      @param aMaxWeight the maximum weight capacity of the cargo module before it becomes overweight.
   */
   public Cargo(int aMaxWeight)
   {
      maxWeight = aMaxWeight;
      cargo = new HashSet<Item>();
   }

   /** Gets the maximum weight capacity of the cargo module.
      @return the maximum weight capacity of the cargo module.
   */
   public int getMaxWeight()
   {
      return maxWeight;
   }

   /** Gets the number of items currently stored.
      @return the number of items currently stored
   */
   public int getItemCount()
   {
      return cargo.size();
   }

   /** Get the sum of the weights of all of the items currently stored.
      @return the sum of the weights of all of the items currently stored
   */
   public int getTotalWeight()
   {
      int totalWeight = 0;
      for (Item index : cargo)
      {
         totalWeight += index.weight;
      }
      return totalWeight;
   }

   /** Determines if the total weight of all of the stored items exceeds the weight capacity of the cargo module.
      @return true if the total weight of all of the stored items exceeds the weight capacity of the cargo module.
   */
   public boolean isOverWeight()
   {
      return (getTotalWeight() > maxWeight);
   }

   /** Adds an item to the cargo module.
      @param item the item to add. It must not already be in the cargo module.
   */
   public void add(Item item)
   {
      cargo.add(item);
   }

   /** Determines if an item is stored in the cargo module.
      @param tracking the tracking number of the item to search for
      @return true if an item with the given tracking umber is stored in the cargo module
   */
   public boolean contains(long tracking)
   {
      for (Item index : cargo)
      {
         if (tracking == index.tracking)
         {
            return true;
         }
      }
      return false;
   }

   /** Removes an item from the cargo hold.
      @param tracking the tracking number of the item to remove from the cargo module
      @return true if an item was removed
   */
   public boolean remove(long tracking)
   {
      Iterator<Item> it = cargo.iterator();
      while (it.hasNext())
      {
         Item index = it.next();
         if (tracking == index.tracking)
         {
            it.remove();
            return true;
         }
      }
      return false;
   }
   
   /** Gets all items with the given name.
      @param name the name to filter items by
      @return the set of all items with the given name.
   */
   public HashSet<Item> getItems(String name)
   {
      Iterator<Item> it = cargo.iterator();
      HashSet<Item> items = new HashSet<Item>();
      while (it.hasNext())
      {
         Item index = it.next();
         if (index.name == name)
         {
            items.add(index);
         }
      }
      return items;
   }
   
   /** Gets the heaviest item in the cargo module.
      @return the heaviest item in the cargo module; null if empty
   */
   public Item getHeaviest()
   {
      Item heaviestItem;
      int heaviestWeight = 0;
      Iterator<Item> it = cargo.iterator();
      while (it.hasNext())
      {
         Item index = it.next();
         if (getItemCount() == 1)
         {
            return index;
         }
         if (index.weight > heaviestWeight)
         {
            heaviestItem = index;
            heaviestWeight = index.weight;
            return heaviestItem;
         }
      }
      return null;
   }

   /** Gets the heaviest item with the given name.
      @param name the name with which to filter
      @return the heaviest item with the given name; null if no item matches the name
   */
   public Item getHeaviest(String name)
   {
      Item heaviestItem;
      int heaviestWeight = 0;
      Iterator<Item> it = cargo.iterator();
      while (it.hasNext())
      {
         Item index = it.next();
         if (index.name == name && index.weight > heaviestWeight)
         {
            heaviestItem = index;
            heaviestWeight = index.weight;
            return heaviestItem;
         }
      }
      return null;
   }

   private Item getHeaviest(HashSet<Item> sub)
   {
      return null;
   }

   /** Gets the average weight of the items in the cargo module.
      @return the average weight of the items in the cargo module; NaN if empty
   */
   public double getAverageWeight()
   {
      if (getItemCount() == 0)
      {
         return Double.NaN;
      }
      else
      {
         return (double) getTotalWeight() / (double) getItemCount();
      }
   }

   /** Gets the average weight of the items with the given name.
      @param name the name with which to filter
      @return the average weight of the items with the given name; NaN if no item matches the given name
   */
   public double getAverageWeight(String name)
   {
      double sameName = 0.0;
      int sameNameItemsWeight = 0;
      Iterator<Item> it = cargo.iterator();
      while (it.hasNext())
      {
         Item index = it.next();
         if (index.name == name)
         {
            sameNameItemsWeight += index.weight;
            sameName++;
         }
      }
      if (sameName == 0)
      {
         return Double.NaN;
      }
      else
      {
         return sameNameItemsWeight / sameName;
      }
   }

   private double getAverageWeight(HashSet<Item> sub)
   {
      return Double.NaN;
   }

   /** Retrieves the items with the given tracking numbers.
      @param tracking the tracking numbers of the items to retrieve
      @return the items with the given tracking numbers
   */
   public HashSet<Item> getItems(long... tracking)
   {
      HashSet<Item> items = new HashSet<Item>();
      Iterator<Item> it = cargo.iterator();
      if (tracking == null)
      {
         return null;
      }
      while (it.hasNext())
      {
         Item index = it.next();
         for (int i = 0; i < tracking.length; i++)
         {
            if (index.tracking == tracking[i])
            {
               items.add(index);
            }
         }
      }
      if (items == null)
      {
         return null;
      }
      else
      {
         return items;
      }
   }

   /** Retrieves the items with the given names.
      @param names the names of the items to retrieve
      @return a map from names to sets of items with that name. Names that have no items will get an empty set
   */
   public HashMap<String, HashSet<Item>> getItemsMap(String... names)
   {
      HashMap<String, HashSet<Item>> map = new HashMap<String, HashSet<Item>>();
      if (names == null)
      {
         return null;
      }
      for (int i = 0; i < names.length; i++)
      {
         HashSet<Item> items = new HashSet<Item>();
         Iterator<Item> it = cargo.iterator();
         while (it.hasNext())
         {
            Item index = it.next();
            if (index.name == names[i])
            {
               items.add(index);
            }
         }
         if (items != null)
         {
            map.put(names[i], items);
         }
      }
      if (map == null)
      {
         return null;
      }
      else
      {
         return map;
      }
   }

   /** An item in the cargo module. Items are immutable.
   */
   public static class Item
   {
      public final long tracking;
      public final String name;
      public final int weight;
      private static long nextTrack = 101;

      /** An item for transport.
         @param aName the name of the item
         @param aWeight the weight of the item
      */
      public Item(String aName, int aWeight)
      {
         tracking = nextTrack++;
         name = aName;
         weight = aWeight;
      }
   }
}
