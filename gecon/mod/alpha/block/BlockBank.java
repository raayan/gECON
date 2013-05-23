package gecon.mod.alpha.block;

import gecon.mod.alpha.container.ContainerBank;
import gecon.mod.alpha.container.TileEntityBank;
import gecon.mod.alpha.gui.GuiBank;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockBank extends BlockGECON {
	private Minecraft mc;
	
	public BlockBank(int id, Material material) {
		super(id, material);
	}

	@SideOnly(Side.CLIENT)
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int f, float a, float b, float c) {		
		if (player instanceof EntityPlayerMP) {
			ModLoader.serverOpenWindow((EntityPlayerMP) player, new ContainerBank(player, world, x, y, z), 30, x, y, z);
		} else {
			ModLoader.openGUI((EntityPlayerSP) player, new GuiBank(player, world, x, y, z));
		}
		
		return true;
	}
	
	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityBank();
	}
}
