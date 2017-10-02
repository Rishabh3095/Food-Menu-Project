package application;
import java.util.ArrayList;


public class ItemMenu {
	
	private ArrayList<ItemListed> itemList;
    
    public ItemMenu()
    {
        itemList = new ArrayList();
    }
    
    public void newItem(ItemListed item)
    {
        itemList.add(item);
    }
    
    public ArrayList<ItemListed> getI()
    {
    	return itemList;
    	}
    
}
