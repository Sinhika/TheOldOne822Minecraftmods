 package theoldone822.NetherrocksFusion.Items;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import theoldone822.NetherrocksFusion.Content;
import theoldone822.NetherrocksFusion.Settings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
 
public class SimpleBow extends ItemBow
{	
	
	/**
	 * The EnumToolMaterial for the tool. This is used to set what item can be used to repair it.
	 */
    private final ToolMaterial material;
	
    /**
     * Creating the icons for the bows. As you draw the bows back, the icon changes, which is why there are 4 icons for each bow here.
     */ 
	public static IIcon dragonbezoarBow;
	public static IIcon dragonbezoarBow1;
	public static IIcon dragonbezoarBow2;
	public static IIcon dragonbezoarBow3;
		
	/**
	 * Constructor for the bows. Worth noting are the following:
	 * 
	 * "this.maxStackSize = 1;" This basically just sets it so that you can only have one per stack.
	 * "this.bFull3D = true;" This allows it to be rendered in proper 3D when held in your hand. Tools are rendered like this, while items such as sugar are not. 
	 */
	public SimpleBow(int dam, ToolMaterial enumtoolmaterial)
	{
		super();
		this.maxStackSize = 1;	
		material = enumtoolmaterial;
		this.canRepair = true;
    	
	    this.bFull3D = true;
	    this.setMaxDamage(dam);
	}
	
	public SimpleBow setUnlocalizedName(String unlocalizedName)
	{
		super.setUnlocalizedName(unlocalizedName);
		GameRegistry.registerItem(this, unlocalizedName);
		return this;
	}

	/**
	 * Registers the icons for the bows. All icons are registered, and the default icons (when you haven't drawn it back at all) are set.
	 * 
	 * The default image for the thyrium bow is set as thyriumBow.
	 * The default image for the sinisite bow is set as sinisiteBow.
	 */
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{			
		if(this == Content.dragonbezoarBow)
		{
			this.itemIcon = iconRegister.registerIcon("netherrocksfusion:" + "dragonbezoarBow");
		}
		
		dragonbezoarBow = iconRegister.registerIcon("netherrocksfusion:" + "dragonbezoarBow");
		dragonbezoarBow1 = iconRegister.registerIcon("netherrocksfusion:" + "dragonbezoarBow1");
		dragonbezoarBow2 = iconRegister.registerIcon("netherrocksfusion:" + "dragonbezoarBow2");
		dragonbezoarBow3 = iconRegister.registerIcon("netherrocksfusion:" + "dragonbezoarBow3");
	}
	
	/**
	 * Gets the icon based on draw back time. As you draw back for longer, the image changes. 
	 * 
	 * The GL11.glTranslate/Rotate sets how the item is held in third person, so it is held like a bow rather than a tool
	 * (ie. hand on the grip in the middle, not at the bottom.
	 */
	public IIcon getIcon(ItemStack itemStack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) 
	{	
		if(Minecraft.getMinecraft().gameSettings.thirdPersonView != 0)
		{
            GL11.glTranslatef(0.0F, -0.6F, -0.025F);
	        GL11.glRotatef(-17.0F, 0.0F, 0.0F, 1.0F);
	        GL11.glRotatef(14.0F, 1.0F, 0.0F, 0.0F);
	        GL11.glRotatef(4.5F, 0.0F, 1.0F, 0.0F);
		}
		
		if (player.getItemInUse() == null) return this.itemIcon;
		int var8 = itemStack.getMaxItemUseDuration() - useRemaining;
		if (var8 >= 18)
		{						
			if(this == Content.dragonbezoarBow)
			{
				return dragonbezoarBow3;
			}
			
		}
		if (var8 > 13)
		{			
			if(this == Content.dragonbezoarBow)
			{
				return dragonbezoarBow2;
			}
			
		}		
		if (var8 > 0)
		{			
			if(this == Content.dragonbezoarBow)
			{
				return dragonbezoarBow1;
			}
			
		}
		return this.itemIcon;
	}
	
	/**
	 * Sets the rarity of the item. This gives it a different coloured name (like with the golden apple).
	 * This is not in use though, it is simply here for reference so I can remember how to do it if I choose to do so later.
	 */
	/*
	public EnumRarity getRarity(ItemStack itemstack) 
	{
		if(itemID == SimpleOres.silverBow.itemID)
		{
			return EnumRarity.uncommon;
		}
		
		if(itemID == SimpleOres.mythrilBow.itemID)
		{
			return EnumRarity.rare;
		}
		
		if(itemID == SimpleOres.onyxBow.itemID)
		{
			return EnumRarity.epic;
		}
		
		else return EnumRarity.common;				
	}
	*/
	
	/**
	 * Adds info to the tool tip when you hover over the item. This is just to show the special effects of the bows.
	 */
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{	
		if(this == Content.dragonbezoarBow)
		{
			par3List.add(StatCollector.translateToLocal("tips.damageTooltip"));
			par3List.add(StatCollector.translateToLocal("tips.zoomTooltip"));
			par3List.add(StatCollector.translateToLocal("tips.knockbackTooltip"));
			par3List.add(StatCollector.translateToLocal("tips.efficiencyTooltip"));
			par3List.add(StatCollector.translateToLocal("tips.flameTooltip"));
		}
	}
		
	/**
	 * Determines what happens when you let go of the right mouse button, usually firing an arrow.
	 * Sets the arrows speed etc. depending on drawback time.
	 * 
	 * This is also where the special effects are determined, such as flame, damage and knockback. The Mythril Bows chance to not consume an arrow is determined here too.
	 */
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) 
	{
		int var6 = getMaxItemUseDuration(par1ItemStack) - par4;
		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, var6);
		MinecraftForge.EVENT_BUS.post(event);
		
		if (event.isCanceled()) 
		{
			return;
		}
		
		var6 = event.charge;
		boolean flag = (par3EntityPlayer.capabilities.isCreativeMode) || (EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0);
		
		if ((flag) || (par3EntityPlayer.inventory.hasItem(Items.arrow))) 
		{
			int i = getMaxItemUseDuration(par1ItemStack) - par4;
			float f = i / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;
			
			if (f < 0.1D) 
			{
				return;
			}
			
			if (f > 1.0F) 
			{
				f = 1.0F;
			}
			
			EntityArrow var8 = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);
			
			if (f == 1.0F) 
			{
				var8.setIsCritical(true);
			}
			
			if (f == 1.0F && this == Content.dragonbezoarBow) 
			{
				var8.setIsCritical(true);
				var8.setFire(100);
			}

			int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);			
			
			if (j > 0) 
			{
				var8.setDamage(var8.getDamage() + j * 0.5D + 0.5D);
			}
			
			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);			
			
			if (k > 0) 
			{
				var8.setKnockbackStrength(k);
			}
			
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0) 
			{
				var8.setFire(100);
			}
			
			par1ItemStack.damageItem(1, par3EntityPlayer);
			par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			
            Random generator = new Random();
            
        	int z;
        	int r = generator.nextInt(100);
        	
        	if(r <= 50)
        	{
        		z = 1;
        	}
        	
           	else
        	{
        		z = 0;
        	}
			
			if (!flag) 
			{
				if(z == 1 && this == Content.dragonbezoarBow)
				{
				var8.canBePickedUp = 0;
				}

				else 
				{
				par3EntityPlayer.inventory.consumeInventoryItem(Items.arrow);
				}
			}
						
			else 
			{
				var8.canBePickedUp = 2;
			}
			
			if (!par2World.isRemote) 
			{
				par2World.spawnEntityInWorld(var8);			
				
				if(this == Content.dragonbezoarBow)
				{
					var8.setDamage(var8.getDamage() + Settings.dragonbezoarBowDamageModifier * 0.5D + 0.5D);
					var8.setKnockbackStrength(k + Settings.dragonbezoarBowKnockbackModifier);
				}
			}
		}
	}

	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) 
	{ 
		return par1ItemStack; 		
	}
 
	/**
	 * Determines how long you can use the item for. Not sure if it actually has any effect.
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack) 
	{
		return 72000;
	}
	
	/**
	 * Gets the action for holding right click, in this case the bow action.
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack) 
	{
		return EnumAction.bow;
	}
	
	/**
	 * 
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) 
	{
		if ((par3EntityPlayer.capabilities.isCreativeMode) || (par3EntityPlayer.inventory.hasItem(Items.arrow))) 
		{
			par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
		}

		return par1ItemStack;
	}
	
	/**
	 * Gets the items enchtantability.
	 */
	public int getItemEnchantability() 
	{
		return 1;
	}
	
	/**
	 * Determines if the tool is repairable, and gets the item which can be used to repair it depending on the material of the tool.
	 * 
	 * For example, a mythril bow can be repaired with a mythril ingot, an onyx bow with an onyx gem.
	 */
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return this.material.customCraftingMaterial == par2ItemStack.getItem() ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }
}