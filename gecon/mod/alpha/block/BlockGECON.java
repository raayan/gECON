package gecon.mod.alpha.block;

import gecon.mod.alpha.gECON;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.Icon;

public class BlockGECON extends Block
{

	public BlockGECON(int id, Material par2Material) 
	{
		super(id, par2Material);
		
		//Makes the Bank Block go into the creative tab for "Blocks"
		this.setCreativeTab(gECON.tabGECON); //Change to CreativeTabs.tabGECON
		
		
	}
	
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons = new Icon[3];
		
		for(int i = 0; i < icons.length; i++)
		{
			icons[i] = par1IconRegister.registerIcon(gECON.modid + ":" + (this.getUnlocalizedName2()) + i);
			
		}
		
		
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
			//Neglect par2 as it is metadata
			switch(par1)
			{
				case 0: //Bottom
					return icons[0];
				case 1: //Top
					return icons[1];
				default: //Sides (can be programmed to 2,3,4,5
					return icons[2];
			}

	}
	
	

}
