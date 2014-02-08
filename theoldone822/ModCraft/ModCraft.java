package theoldone822.ModCraft;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import theoldone822.CraftableChain.CraftableChain;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@NetworkMod(clientSideRequired = true, serverSideRequired = false)
@Mod(
		modid = "modcraft",
		name = "Crafttables Converter",
		version = "1.0")
public class ModCraft {
	@Instance("ModCraft")
	public static ModCraft instance;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModMetadata metadata = event.getModMetadata();
		metadata.autogenerated = false;

		metadata.authorList = Arrays.asList("theoldone822");
		metadata.description = "Allows conversion from mod items to basic ones.";
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Block.workbench, 1), true, new Object[]{"X", Character.valueOf('X'), "crafterWood",}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Block.workbench, 1), true, new Object[]{"X", Character.valueOf('X'), "craftingTableWood",}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Block.planks, 3), true, new Object[]{"XX", "X ", Character.valueOf('X'), "plankWood",}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(Item.stick, 1), true, new Object[]{"X", Character.valueOf('X'), "stickWood",}));
		
	}
	}
