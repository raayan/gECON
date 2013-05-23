package gecon.mod.alpha.gui;

import org.lwjgl.opengl.GL11;
import gecon.mod.alpha.gECON;
import gecon.mod.alpha.container.ContainerBank;
import gecon.mod.alpha.container.TileEntityBank;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiBank extends GuiContainer {
	public InventoryPlayer inventoryPlayer;
	public EntityPlayer player;
	public TileEntity tileEntity;
	public ItemStack focusedItem;
	
	public GuiBank(EntityPlayer player, World world, int x, int y, int z) {
		super(new ContainerBank(player, world, x, y, z));

		tileEntity = ContainerBank.bank;
		
		this.player = player;
		this.xSize = 208;
		this.ySize = 222;
	}

	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/" + gECON.modid + "/gui/bank.png");
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	public void updateScreen(){

		if (tileEntity instanceof TileEntityBank){

			if(((TileEntityBank)tileEntity).focusStack != null){
				this.focusedItem = ((TileEntityBank)tileEntity).focusStack;
			}
		}
	}
	
	public String getFocusedSlotInfo(){
		if(this.focusedItem != null){
			return focusedItem.getDisplayName();
		}
		return "No Item";
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(player.getEntityName() +"'s Bank", 8, 6, 0xFFFFFF);

		this.fontRenderer.drawString("Currently Inspecting: " + this.getFocusedSlotInfo(), 8, 26, 0xFFFFFF);

		this.fontRenderer.drawString("Focus", 123, 110, 0xFFFFFF);
		this.fontRenderer.drawString("Size", 170, 110, 0xFFFFFF);
	}
	
}
