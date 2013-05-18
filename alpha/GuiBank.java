package gecon.mod.alpha;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

public class GuiBank extends GuiScreen{
	private World worldMC;
	private EntityPlayer entityP;
	private Minecraft mc;
	private GuiTextField textField;
	private int iID;
	private int switchState = 0;

	public GuiBank(World world, EntityPlayer player, Minecraft minecraft){
		worldMC = world;
		entityP = player;
		mc = minecraft;
	}
	public void initGui(){
		textField = new GuiTextField(fontRenderer, width/2, height/2 - 50, 50, 20);
		textField.setMaxStringLength(4);
		textField.setFocused(false);
		
		//draw buttons
		buttonList.add(new GuiButton(1, width/2 - 100, height/2 - 50, 50, 20, "Deposit"));
		buttonList.add(new GuiButton(2, width/2 + 100, height/2 - 50, 50, 20, "Withdraw"));
		buttonList.add(new GuiButton(3, width/2, height/2 + 50, 50, 20, "List"));

		
	}
	
	public void updateScreen(){
		if(textField.getText().length() != 0)
			iID = Integer.parseInt(textField.getText());
	}
	
	
	public void drawScreen(int i, int j, float f){
		textField.drawTextBox();
		drawString(fontRenderer, "You are looking up item " + iID, width/2 - 100, height/2 - 30, 0xffffff);
		switch (switchState){
			case 0:
				drawString(fontRenderer, "Cock Sucker", width/2 - 100, height/2 - 80, 0xffffff);
			case 1:
				drawString(fontRenderer, "You do not have any " + iID, width/2 - 100, height/2 - 70, 0xffffff);
			case 2:
				drawString(fontRenderer, "Global Bank List: " + gECON.globalBank.toString(), width/2 - 100, height/2 - 60, 0xffffff);
		}
		super.drawScreen(i, j, f);
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
						drawString(fontRenderer, "Old Stack Incremented", width/2 - 100, height/2 + 20, 0xffffff);
					}else{
						gECON.globalBank.add(new ItemStack(iID, 1, 0));
						drawString(fontRenderer, "New Stack Created", width/2 - 100, height/2 + 20, 0xffffff);
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
			if(x.itemID == iID && x.stackSize + par2 <= 64)
				return x;
		}
		return null;
	}
	
	public void withdrawItems(int par1, int par2){
		
	}
}
	
