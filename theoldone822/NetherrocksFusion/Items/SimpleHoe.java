package theoldone822.NetherrocksFusion.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class SimpleHoe extends ItemHoe
{
	/**
	 * The EnumToolMaterial for the tool. This is used to set what item can be used to repair it.
	 */
    private final EnumToolMaterial material;
    private String modName;
    
    /**
     * Main constructor for the hoes added by SimpleOres.
     * 
     * @param i = the items ID
     * @param enumtoolmaterial = the material of the tool. Determines speed, number of uses, etc.
     */
	public SimpleHoe(int i, EnumToolMaterial enumtoolmaterial, String mod, CreativeTabs tab) 
	{
		super(i, enumtoolmaterial);
		material = enumtoolmaterial;
		modName = mod;
		
	}
	
	public SimpleHoe setUnlocalizedName(String unlocalizedName)
	{
		super.setUnlocalizedName(unlocalizedName);
		GameRegistry.registerItem(this, unlocalizedName);
		return this;
	}

	/**
	 * Determines if the tool is repairable, and gets the item which can be used to repair it depending on the material of the tool.
	 * 
	 * For example, a copper hoe can be repaired with a copper ingot, a mythril hoe with a mythril ingot.
	 */
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return this.material.getToolCraftingMaterial() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
}