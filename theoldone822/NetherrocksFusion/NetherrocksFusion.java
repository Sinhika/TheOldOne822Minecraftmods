package theoldone822.NetherrocksFusion;


import java.io.File;
import java.util.Arrays;

import theoldone822.NetherrocksFusion.Furnaces.SuperFurnace;
import theoldone822.NetherrocksFusion.Furnaces.SuperFurnaceTileEntity;
import theoldone822.NetherrocksFusion.Furnaces.SuperFusionFurnace;
import theoldone822.NetherrocksFusion.Furnaces.SuperFusionFurnaceTileEntity;
import theoldone822.NetherrocksFusion.HandlerEvent;
import net.minecraft.block.Block;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "netherrocksfusion", name = "Netherrocks Fusion", version = "2.0", dependencies = "required-after:fusion; required-after:netherrocks")
public class NetherrocksFusion {

	@SidedProxy(clientSide = "theoldone822.NetherrocksFusion.ProxyClient", serverSide = "theoldone822.NetherrocksFusion.ProxyCommon")	
	public static ProxyCommon proxy;

	public static ToolMaterial toolCinderstone;
	public static ToolMaterial toolThraka;
	public static ToolMaterial toolPyralis;
	public static ToolMaterial toolDragonBezoar;

	public static ArmorMaterial armorCinderstone;
	public static ArmorMaterial armorThraka;
	public static ArmorMaterial armorPyralis;
	public static ArmorMaterial armorPyralisChain;
	public static ArmorMaterial armorDragonBezoar;
	public static ArmorMaterial armorDragonBezoarChain;

	public static int rendererCinderstone;
	public static int rendererThraka;
	public static int rendererPyralis;
	public static int rendererPyralisChain;
	public static int rendererDragonBezoar;
	public static int rendererDragonBezoarChain;
	
	@Instance("NetherrocksFusion")
	public static NetherrocksFusion instance = new NetherrocksFusion();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new HandlerEvent());
		ModMetadata metadata = event.getModMetadata();
		metadata.autogenerated = false;

		metadata.authorList = Arrays.asList("theoldone822");
		metadata.description = "Adds Nerherrocks Fusions.";
		proxy.registerClientTickHandler();

		Settings.doSettings(event);

		toolCinderstone = EnumHelper.addToolMaterial("FYRITEIL", Settings.cinderstoneMiningLevel, Settings.cinderstoneUsesNum, Settings.cinderstoneMiningSpeed, Settings.cinderstoneDamageVsEntity, Settings.cinderstoneEnchantability);
    	toolThraka = EnumHelper.addToolMaterial("ASHMAL", Settings.thrakaMiningLevel, Settings.thrakaUsesNum, Settings.thrakaMiningSpeed, Settings.thrakaDamageVsEntity, Settings.thrakaEnchantability);
    	toolPyralis = EnumHelper.addToolMaterial("DRAGONPLUS", Settings.pyralisMiningLevel, Settings.pyralisUsesNum, Settings.pyralisMiningSpeed, Settings.pyralisDamageVsEntity, Settings.pyralisEnchantability);
    	toolDragonBezoar = EnumHelper.addToolMaterial("SUPER", Settings.dragonbezoarMiningLevel, Settings.dragonbezoarUsesNum, Settings.dragonbezoarMiningSpeed, Settings.dragonbezoarDamageVsEntity, Settings.dragonbezoarEnchantability);
    	
    	armorCinderstone = EnumHelper.addArmorMaterial("FYRITEIL", Settings.cinderstoneArmorDurability, Settings.cinderstoneArmorDamageReduction, Settings.cinderstoneArmorEnchantability);
    	armorThraka = EnumHelper.addArmorMaterial("ASHMAL", Settings.thrakaArmorDurability, Settings.thrakaArmorDamageReduction, Settings.thrakaArmorEnchantability);
    	armorPyralis = EnumHelper.addArmorMaterial("DRAGONPLUS", Settings.pyralisArmorDurability, Settings.pyralisArmorDamageReduction, Settings.pyralisArmorEnchantability);
    	armorPyralisChain = EnumHelper.addArmorMaterial("DRAGONPLUS", Settings.pyralischainArmorDurability, Settings.pyralischainArmorDamageReduction, Settings.pyralischainArmorEnchantability);
    	armorDragonBezoar = EnumHelper.addArmorMaterial("SUPER", Settings.dragonbezoarArmorDurability, Settings.dragonbezoarArmorDamageReduction, Settings.dragonbezoarArmorEnchantability);
    	armorDragonBezoarChain = EnumHelper.addArmorMaterial("SUPER", Settings.dragonbezoarchainArmorDurability, Settings.dragonbezoarchainArmorDamageReduction, Settings.dragonbezoarchainArmorEnchantability);

		Content.doArmor();
    	Content.doBlocks();
    	Content.doItems();
    	Content.doTools();
    	Recipes.doRecipes();
	}

	@EventHandler
	public void InitiateNetherrocksFusion(FMLInitializationEvent initEvent) {
		instance = this;
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		GameRegistry.registerTileEntity(SuperFusionFurnaceTileEntity.class, "dragonbezoarFusionFurnace");
		GameRegistry.registerTileEntity(SuperFurnaceTileEntity.class, "dragonbezoarFurnace");

        toolCinderstone.customCraftingMaterial = Content.cinderstoneIngot;
        toolPyralis.customCraftingMaterial = Content.pyralisIngot;
        toolThraka.customCraftingMaterial = Content.thrakaIngot;
        toolDragonBezoar.customCraftingMaterial = Content.dragonbezoarIngot;

        armorCinderstone.customCraftingMaterial = Content.cinderstoneIngot;
        armorPyralis.customCraftingMaterial = Content.pyralisIngot;
        armorPyralisChain.customCraftingMaterial = Content.pyralisIngot;
        armorThraka.customCraftingMaterial = Content.thrakaIngot;
        armorDragonBezoar.customCraftingMaterial = Content.dragonbezoarIngot;
        armorDragonBezoarChain.customCraftingMaterial = Content.dragonbezoarIngot;

		proxy.registerRenderInformation();
	}
}