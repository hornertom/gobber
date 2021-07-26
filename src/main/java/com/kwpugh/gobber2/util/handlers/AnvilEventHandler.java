package com.kwpugh.gobber2.util.handlers;

import com.kwpugh.gobber2.Gobber2;
import com.kwpugh.gobber2.init.ItemInit;

import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.network.chat.TextComponent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Gobber2.modid, bus = EventBusSubscriber.Bus.FORGE )
public class AnvilEventHandler
{
	@SubscribeEvent
	public static void upgradeSomeTools(AnvilUpdateEvent event)
	{
		ItemStack left = event.getLeft();
		ItemStack right = event.getRight();
		
		if(left.isEmpty() || right.isEmpty())
		{
			return;
		}

		if(left.getItem().equals(ItemInit.GOBBER2_PICKAXE.get()) && right.getItem().equals(Items.EMERALD))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_PICKAXE.get());
			output.enchant(Enchantments.SILK_TOUCH, 1);
			output.enchant(Enchantments.BLOCK_EFFICIENCY, 3);
			output.setHoverName(new TextComponent("Improved Gobber Pickaxe"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_SWORD_NETHER.get()) && right.getItem().equals(Items.NETHER_STAR))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_SWORD_NETHER.get());
			output.enchant(Enchantments.SHARPNESS, 5);
			output.enchant(Enchantments.KNOCKBACK, 2);
			output.setHoverName(new TextComponent("Deadly Nether Sword"));
			event.setCost(10);
			event.setOutput(output);
		}

		if(left.getItem().equals(ItemInit.GOBBER2_HAMMER_END.get()) && right.getItem().equals(ItemInit.DRAGON_STAR.get()))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_HAMMER_END.get());
			output.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
			output.enchant(Enchantments.BLOCK_FORTUNE, 5);
			output.setHoverName(new TextComponent("Awesome End Hammer of Fortune"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_PICKAXE_END.get()) && right.getItem().equals(ItemInit.DRAGON_STAR.get()))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_PICKAXE_END.get());
			output.enchant(Enchantments.BLOCK_EFFICIENCY, 5);
			output.enchant(Enchantments.BLOCK_FORTUNE, 5);
			output.setHoverName(new TextComponent("Awesome End Pickaxe of Fortune"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_SWORD_SNIPER.get()) && right.getItem().equals(ItemInit.DRAGON_STAR.get()))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_SWORD_SNIPER.get());
			output.enchant(Enchantments.SHARPNESS, 10);
			output.enchant(Enchantments.MOB_LOOTING, 10);
			output.setHoverName(new TextComponent("Devastating Sniper Sword"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_HELMET_DRAGON.get()) && right.getItem().equals(Items.DRAGON_BREATH))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_HELMET_DRAGON.get());
			output.enchant(Enchantments.THORNS, 5);
			output.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 5);
			output.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
			output.setHoverName(new TextComponent("Thorny Dragon Helmet"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_CHESTPLATE_DRAGON.get()) && right.getItem().equals(Items.DRAGON_BREATH))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_CHESTPLATE_DRAGON.get());
			output.enchant(Enchantments.THORNS, 5);
			output.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 5);
			output.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
			output.setHoverName(new TextComponent("Thorny Dragon Chestplate"));
			event.setCost(10);
			event.setOutput(output);
		}
	
		if(left.getItem().equals(ItemInit.GOBBER2_LEGGINGS_DRAGON.get()) && right.getItem().equals(Items.DRAGON_BREATH))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_LEGGINGS_DRAGON.get());
			output.enchant(Enchantments.THORNS, 5);
			output.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 5);
			output.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
			output.setHoverName(new TextComponent("Thorny Dragon Leggings"));
			event.setCost(10);
			event.setOutput(output);
		}
		
		if(left.getItem().equals(ItemInit.GOBBER2_BOOTS_DRAGON.get()) && right.getItem().equals(Items.DRAGON_BREATH))
		{			
			ItemStack output = new ItemStack(ItemInit.GOBBER2_BOOTS_DRAGON.get());
			output.enchant(Enchantments.THORNS, 5);
			output.enchant(Enchantments.ALL_DAMAGE_PROTECTION, 5);
			output.enchant(Enchantments.PROJECTILE_PROTECTION, 5);
			output.setHoverName(new TextComponent("Thorny Dragon Boots"));
			event.setCost(10);
			event.setOutput(output);
		}
	}
}
