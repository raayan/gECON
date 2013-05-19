package gecon.mod.alpha;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class GuiBank extends GuiContainer{
	private World worldMC;
	private EntityPlayer entityP;
	private Minecraft mc;
	private GuiTextField textField;
	private int iID;
	private int switchState = 0;
    private Slot theSlot;


	public GuiBank(World world, EntityPlayer player, Minecraft minecraft){
		super(new ContainerChest(player.inventory, player.inventory));
//		this.xSize = 208;
//		this.ySize = 222;
		worldMC = world;
		entityP = player;
		mc = minecraft;
		
		System.out.println(minecraft.displayWidth +":" + minecraft.displayHeight);
	}
	@Override
	public void initGui(){
		textField = new GuiTextField(fontRenderer, width/2 + 61, height/2 + 10, 32, 16);
		textField.setMaxStringLength(4);
		textField.setFocused(false);
		
		//draw buttons
		buttonList.add(new GuiButton(1, width/2 - 98, height/2 + 2, 44, 20, "Deposit"));
		buttonList.add(new GuiButton(2, width/2 - 53, height/2 + 2, 44, 20, "Withdraw"));
		
		
	}
	
	public void updateScreen(){
		try{ 
		if(textField.getText().length() != 0) 
			iID = Integer.parseInt(textField.getText());
		} catch (NumberFormatException e){
			System.out.println("Invalid Character!");
		}
		
	}
	
	@Override
	public void drawScreen(int i, int j, float f){
//		drawGuiContainerBackgroundLayer(f, i, j);
//		drawGuiContainerForegroundLayer(i, j);
	
		super.drawScreen(i, j, f);
		//TODO

	}
	
	protected void keyTyped(char c, int i)
	{
		super.keyTyped(c, i);
		textField.textboxKeyTyped(c, i);
	}
	
	public void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		textField.mouseClicked(i, j, k);
	}
	
	private boolean isMouseOverSlot(Slot par1Slot, int par2, int par3)
	{
	        return this.isPointInRegion(par1Slot.xDisplayPosition, par1Slot.yDisplayPosition, 16, 16, par2, par3);
	}
	
	@Override
	public boolean doesGuiPauseGame(){
		return false;
	}
	protected void actionPerformed(GuiButton guiButton){
		
			if(guiButton.id == 1){	//Deposit Code
				if(entityP.inventory.hasItem(iID)){
					entityP.inventory.consumeInventoryItem(iID);
//					entityP.inventory.addItemStackToInventory(new ItemStack(iID, 1, 0));
					if (getStackOfItem(iID, 1, gECON.globalBank) != null){
						getStackOfItem(iID, 1, gECON.globalBank).stackSize++;
					}else{
						gECON.globalBank.add(new ItemStack(iID, 1, 0));
					}
				}else{
					switchState = 1;
				}
			}else if(guiButton.id == 2){	//Withdraw Code
				for(int i = gECON.globalBank.size() - 1; i >= 0; i--){
					ItemStack x = gECON.globalBank.get(i);
					if(x.itemID == iID){
						if(x.stackSize > 1){
							entityP.inventory.addItemStackToInventory(new ItemStack(iID, 1, 0));
							x.stackSize--;
						}else if(x.stackSize==1){
							entityP.inventory.addItemStackToInventory(new ItemStack(iID, 1, 0));
							gECON.globalBank.remove(x);
						}
						break;
					}
				}
						

//				if(gECON.globalBank.contains(new ItemStack(iID, 1, 0))){
//				}					
//				System.out.println(gECON.globalBank.toString());

			
			}else if(guiButton.id == 3){
				switchState = 2;
			}else{
				
			}
	}
	
	
	public ItemStack getStackOfItem(int par1, int par2, ArrayList<ItemStack> list){
		for(ItemStack x: gECON.globalBank){
			if(x.itemID == iID)//&& x.stackSize + par2 <= 64
				return x;
		}
		return null;
	}
	
	public void withdrawItems(int par1, int par2){
		
	}
	public ArrayList<String> arrayToListString(ArrayList<ItemStack> list){
		ArrayList outs = new ArrayList<String>();
		if(list.size() != 0){
			for(ItemStack x : list){
				int size = x.stackSize;
				for(ItemStack y: list){
					if(y != x && y.itemID == x.itemID){
						size += y.stackSize;
						list.remove(y);
					}
				}
				outs.add(x.getDisplayName() + ", Quantity: " + size);
			}
		}else{
			outs.add("EMPTY! YOU BROKE!");
		}
		return outs;
	}
	public void drawBankContents(ArrayList<String> list){
		int modulus = 9;
		int mod = modulus;
		for(String x: list){
			drawString(fontRenderer, x, width/2 - 96, height/2 - 91 + mod, 0xffffff);
			mod += modulus;
		}
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {

		textField.drawTextBox();
		drawString(fontRenderer, entityP.username + "'s Bank Account", width/2 - 96, height/2 - 105, 0xffffff);		
		drawString(fontRenderer, "Global Bank List:", width/2 - 96, height/2 - 91, 0xffffff);

		drawString(fontRenderer, "Focus", width/2+ 19, height/2 - 1, 0xffffff);
		drawString(fontRenderer, "Size", width/2+ 67, height/2 -1 , 0xffffff);
		drawBankContents(arrayToListString(gECON.globalBank));
	}
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/mods/woodnet_gECON/gui/bank.png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(width/2 - 208/2, height/2 - 222/2, 0, 0, 208, 222);

	}
	
}
	
