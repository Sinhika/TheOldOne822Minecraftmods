package theoldone822.WootzPignGray;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SimpleOre extends Block
{
	private String modName = "simpleores";
	private ItemStack stackDrop = null;
//	private CreativeTabs tab = SimpleOres.tabSimpleBlocks;
	
	/**
	 * Constructor for a basic block.
	 */
	public SimpleOre(Material material) 
	{
		super(material);
//		this.setCreativeTab(TabHelper.getBlocksTab(tab));
	}
	
	/**
	 * Registers the textures for the blocks.
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.blockIcon = iconRegister.registerIcon(modName + ":" + (this.getUnlocalizedName().substring(5)));
	}
	
	/**
	 * The modID of the mod adding the block. Used to find textures. Defaults to "simpleores".
	 */
	public SimpleOre modId(String modId)
	{
		modName = modId;
		return this;
	}
	
	/**
	 * Sets the creative tab for the block to appear in. Defaults to SimpleOres.tabSimpleBlocks.
	 */
	public SimpleOre setTab(CreativeTabs creativetab)
	{
//		tab = creativetab;
//		this.setCreativeTab(TabHelper.getBlocksTab(tab));
		return this;
	}
	
	/**
	 * Sets an itemstack to drop when the block is destroyed. Only used if block drops something other than itself.
	 */
	public SimpleOre setStackToDrop(ItemStack stack)
	{
		this.stackDrop = stack;
		return this;
	}
	
	/**
	 * Registers the block in the GameRegistry, with the name given, and sends the name through to setUnlocalizedName in the super class.
	 */
	public SimpleOre setBlockName(String unlocalizedName)
	{
		super.setBlockName(unlocalizedName);
		GameRegistry.registerBlock(this, unlocalizedName);
		return this;
	}
	
	/**
	 * The ID dropped when the block is broken.
	 */
	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		if(stackDrop != null)
		{
			return stackDrop.getItem();
		}
		else return Item.getItemFromBlock(this);
	}
	
	/**
	 * If the item dropped is not the block itself, then spawns a random amount of XP.
	 */
	@Override
    public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        super.dropBlockAsItemWithChance(par1World, par2, par3, par4, par5, par6, par7);

        if (this.getItemDropped(par5, par1World.rand, par7) != Item.getItemFromBlock(this))
        {
        	int var8 = MathHelper.getRandomIntegerInRange(par1World.rand, 1, 8);

//            this.dropXpOnBlockBreak(par1World, par2, par3, par4, var8);
        }
    }
	
    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random p_149745_1_)
    {
        return 6 + p_149745_1_.nextInt(6);
    }

    /**
	 * Determines how many items are dropped when using a Fortune pickaxe. Only effects blocks that drop items when broken.
	 */
    public int quantityDroppedWithBonus(int par1, Random par2Random)
    {
        if (par1 > 0 && Item.getItemFromBlock(this) != this.getItemDropped(0, par2Random, par1))
        {
            int var3 = par2Random.nextInt(par1 + 2) - 1;

            if (var3 < 0)
            {
                var3 = 0;
            }

            return this.quantityDropped(par2Random) * (var3 + 1);
        }
        else
        {
            return this.quantityDropped(par2Random);
        }
    }
	
	protected boolean canSilkHarvest()
	{
		return true;
	}
}
