package gecon.mod.alpha;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = gECON.modid, name = "gECON Alpha", version = "0.1a")
public class gECON extends BaseMod
{

	 public static final String modid = "woodnet_gECON";
	 public static CreativeTabs tabGECON;

	 public static ArrayList<ItemStack> globalBank;
	 //Blocks
	 public static Block bankBlock = new BlockBank(501, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("bankBlock");
	 public static Block tradingTableBlock = new BlockTradingTable(502, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("tradingTableBlock");
	 public static Block marketBlock = new BlockMarket(503, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("marketBlock");
	 public static Block marketAnalysisBlock = new BlockMarketAnalysis(504, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("marketAnalysisBlock");

	 
	 //Coins
	 public static Item coinItem1 = new ItemCoin(1, 511).setUnlocalizedName("itemCoin1");
	 public static Item coinItem10 = new ItemCoin(10, 512).setUnlocalizedName("itemCoin10");
	 public static Item coinItem100 = new ItemCoin(100, 513).setUnlocalizedName("itemCoin100");
	 public static Item coinItem1000 = new ItemCoin(1000, 514).setUnlocalizedName("itemCoin1000");
	 
//@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)

	//Creative Tab

		 
	 
	 @Init
	 public void load(FMLInitializationEvent event)
	 {
		
		
		 
		 //Setup the creative tab
		 tabGECON = new CreativeTabGECON(CreativeTabs.getNextID(), bankBlock.blockID, "tabgECON", "gECON Tab");
		 
		 // Add items and blocks to creative tab
		 bankBlock.setCreativeTab(gECON.tabGECON);
		 tradingTableBlock.setCreativeTab(gECON.tabGECON);
		 marketBlock.setCreativeTab(tabGECON);
		 marketAnalysisBlock.setCreativeTab(tabGECON);
		 coinItem1.setCreativeTab(gECON.tabGECON);
		 coinItem10.setCreativeTab(gECON.tabGECON);
		 coinItem100.setCreativeTab(gECON.tabGECON);
		 coinItem1000.setCreativeTab(gECON.tabGECON);
		 
		 
		 
//		 register the bank here
//		 ModLoader.registerContainerID(this, 0);
		 
		 //Resgister TileEntity
		 
//		 Register the bank tile here
//		 GameRegistry.registerTileEntity(TileEntityBank.class, "tileEntityBank");
		 
		 
		 //Adds it to the game registry.
		 GameRegistry.registerBlock(bankBlock, modid + bankBlock.getUnlocalizedName2());
		 GameRegistry.registerBlock(tradingTableBlock, modid + tradingTableBlock.getUnlocalizedName2());
		 GameRegistry.registerBlock(marketBlock, modid + marketBlock.getUnlocalizedName2());
		 GameRegistry.registerBlock(marketAnalysisBlock, modid + marketAnalysisBlock.getUnlocalizedName2());
		 GameRegistry.registerItem(coinItem1, modid + coinItem1.getUnlocalizedName());
		 GameRegistry.registerItem(coinItem10, modid + coinItem10.getUnlocalizedName());
		 GameRegistry.registerItem(coinItem100, modid + coinItem100.getUnlocalizedName());
		 GameRegistry.registerItem(coinItem1000, modid + coinItem1000.getUnlocalizedName());

		 
		 
		 //Add a name to the object
		 LanguageRegistry.addName(bankBlock, "Bank");
		 LanguageRegistry.addName(tradingTableBlock, "Trading Table");
		 LanguageRegistry.addName(marketBlock, "Market");
		 LanguageRegistry.addName(marketAnalysisBlock, "Market Analysis");
		 LanguageRegistry.addName(coinItem1, "1 gCOIN");
		 LanguageRegistry.addName(coinItem10, "10 gCOINs");
		 LanguageRegistry.addName(coinItem100, "100 gCOINs");
		 LanguageRegistry.addName(coinItem1000, "1000 gCOINs");

		 
		 //Add recipes
		 GameRegistry.addRecipe(new ItemStack(bankBlock), new Object[]{"GRG", "GCG", "GRG", Character.valueOf('G'), Item.ingotGold, Character.valueOf('R'), Item.redstone, Character.valueOf('C'), Block.chest});
		 GameRegistry.addRecipe(new ItemStack(tradingTableBlock), new Object[]{"GRG", "GCG", "GRG", Character.valueOf('G'), Item.ingotGold, Character.valueOf('R'), Item.redstone, Character.valueOf('C'), Block.brick});
		 GameRegistry.addRecipe(new ItemStack(marketBlock), new Object[]{"GRG", "GCG", "GRG", Character.valueOf('G'), Item.ingotGold, Character.valueOf('R'), Item.redstone, Character.valueOf('C'), Block.blockDiamond});
		 GameRegistry.addRecipe(new ItemStack(marketAnalysisBlock), new Object[]{"GRG", "GCG", "GRG", Character.valueOf('G'), Item.ingotGold, Character.valueOf('R'), Item.redstone, Character.valueOf('C'), gECON.marketBlock});
		 
		 //Create the bank database
		 globalBank = new ArrayList<ItemStack>();
	 }
	 
	 public GuiContainer getContainerGUI(EntityClientPlayerMP player, int id, int x, int y, int z)
	 {
		 switch(id)
		 {
//		 	case 0:
		 //Enter the bank gui here
		 	default:
		 		return null;
		 }
	 }
	 


	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}


}
