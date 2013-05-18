package gecon.mod.alpha;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

public class ItemCoin extends Item
{
	public int value;

	
	public ItemCoin(int value, int id) 
	{
		super(id);
		this.value = value;
		//Makes the Bank Block go into the creative tab for "Blocks"		
		this.setCreativeTab(gECON.tabGECON); //Change to CreativeTabs.tabGECON
	}
	
	public int getValue()
	{
		return value;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
    	//substring(5) is because getUnlocalizedName() returns item.[itemName], this will counteract the 'item.'
		this.itemIcon = par1IconRegister.registerIcon(gECON.modid + ":" + this.getUnlocalizedName().substring(5));

	}
	

}
