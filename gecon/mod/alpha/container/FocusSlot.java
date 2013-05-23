package gecon.mod.alpha.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class FocusSlot extends Slot {

	public FocusSlot(IInventory par1iInventory, int par2, int par3, int par4) {
		super(par1iInventory, par2, par3, par4);
		// TODO Auto-generated constructor stub
	}
	public void onSlotChanged(){
		super.onSlotChanged();
		returnItemInSlot(this.getStack());
	}
	public String returnItemInSlot(ItemStack item){
		if(item != null){
			return item.getDisplayName();
		}
		return null;
	}
}
