package gecon.mod.alpha.container;

import gecon.mod.alpha.block.BlockBank;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityBank extends TileEntity implements ISidedInventory, net.minecraftforge.common.ISidedInventory {
	public static ItemStack focusStack;
	private String localizedName;
	public int playersCurrentlyUsingBank;
	private String focusStackName;
	
	public int getSizeInventory() {
		return 1;
	}

	public ItemStack getStackInSlot(int i) {
		return this.focusStack;
	}

	public ItemStack decrStackSize(int i, int j) {
		if (this.focusStack != null) {
			ItemStack itemstack;
			
			if (this.focusStack.stackSize <= j) {
				itemstack = this.focusStack;
				
				this.focusStack = null;
				this.onInventoryChanged();
				
				return itemstack;
			} else {
				itemstack = this.focusStack.splitStack(j);
				
				if (this.focusStack.stackSize == 0) {
					this.focusStack = null;
				}
				
				this.onInventoryChanged();
				
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		
		return null;
	}

	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.focusStack = itemstack;
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		NBTTagList nbtFocusItem = new NBTTagList();
		
		if (this.focusStack != null) {
			NBTTagCompound tag = new NBTTagCompound();
			tag.setByte("Slot", (byte) 0);
			
			focusStack.writeToNBT(tag);
			
			nbtFocusItem.appendTag(tag);
		}
		
		nbt.setTag("FocusStack", nbtFocusItem);
		
		if(this.isInvNameLocalized()) {
			nbt.setString("FocusItemStack", this.focusStackName);
		}
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		NBTTagList nbtFocusItem = nbt.getTagList("FocusStack");
		
		int var1 = nbt.getByte("Slot") & 255;
		
		if (var1 >= 0) {
			this.focusStack = ItemStack.loadItemStackFromNBT(nbt);
		}
		
		if (nbt.hasKey("FocusItemStack")) {
			this.focusStackName = nbt.getString("FocusItemStack");
		}
	}

	public String getInvName() {
		return this.isInvNameLocalized() ? this.localizedName : "container.BankBlock";
	}

	public boolean isInvNameLocalized() {
		return this.localizedName != null && this.localizedName.length() > 0;
	}
	
	/**
	 * Setting the localized name variable
	 * @param localizedName
	 */
	public void func_94043_a(String par1Str) {
		this.localizedName = par1Str;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	public void openChest() {
		if (this.playersCurrentlyUsingBank < 0) {
			this.playersCurrentlyUsingBank = 0;
		}
		
		this.playersCurrentlyUsingBank++;
	}

	public void closeChest() {
		if (this.getBlockType() != null && this.getBlockType() instanceof BlockBank) {
			this.playersCurrentlyUsingBank--;
		}
	}

	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Deprecated
	public int getStartInventorySide(ForgeDirection side) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Deprecated
	public int getSizeInventorySide(ForgeDirection side) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
