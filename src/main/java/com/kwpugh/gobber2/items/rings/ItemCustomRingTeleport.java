package com.kwpugh.gobber2.items.rings;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomRingTeleport extends Item
{
	public ItemCustomRingTeleport(Properties properties)
	{
		super(properties);
	}
    
	//Set the location in the ring
	public InteractionResult useOn(UseOnContext context)
	{
		 Level world = context.getLevel();
		 BlockPos pos = context.getClickedPos();
		 Player player = context.getPlayer();
		 Direction direction = context.getClickedFace();
		 ItemStack stackRing = context.getPlayer().getMainHandItem();	 
		 
		 if(getPosition(stackRing) == null && player.isCrouching())
		 {
			 setPosition(stackRing, world, pos.relative(direction), player);
			 player.sendMessage(new TranslatableComponent("item.gobber2.ring_teleport.line1").withStyle(ChatFormatting.YELLOW), null);
			 
			 return InteractionResult.SUCCESS;
		 }
		 
		 if(getPosition(stackRing) != null)
		 {
			 player.sendMessage(new TranslatableComponent("item.gobber2.ring_teleport.line2").withStyle(ChatFormatting.YELLOW), null);
			 
			 return InteractionResult.SUCCESS;
		 }

		 return InteractionResult.PASS;
	}
	
	//Use the teleport function or clear it
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
	  
		if(getPosition(stack) != null && !player.isCrouching())
		{
			teleport(player, world, stack);
			world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CHORUS_FRUIT_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
		}
	 
		if(getPosition(stack) != null && player.isCrouching())
		{
			setPosition(stack, world, null, player);
			player.sendMessage(new TranslatableComponent("item.gobber2.ring_teleport.line3").withStyle(ChatFormatting.YELLOW), null);
		}
    	
		return new InteractionResultHolder<>(InteractionResult.PASS, player.getItemInHand(hand)); 
	}

	//Capture player position
	public static BlockPos getPosition(ItemStack stack)
	{
		if (!stack.hasTag())
		{
			return null;
		}		 

		CompoundTag tags = stack.getTag();
	 
		if (tags.contains("pos"))
		{
			return NbtUtils.readBlockPos((CompoundTag) tags.get("pos"));
		}
		return null;
	}

	//Capture player dimension
	public static int getDimension(ItemStack stack)
	{
		if (!stack.hasTag())
		{
			return Integer.MAX_VALUE;
		}
	 
		CompoundTag tags = stack.getTag();
	 
		if (tags.contains("dim"))
		{
			return tags.getInt("dim");
		}	
		return Integer.MAX_VALUE;
	}
	
	//Set position and dimension in the NBT
	public static void setPosition(ItemStack stack, Level world, BlockPos pos, Player player)
	{
		if(world.isClientSide)
		{
			return;
		}
	 
		CompoundTag tags;
	 
		if (!stack.hasTag())
		{
			tags = new CompoundTag();
		}
		else
		{
			tags = stack.getTag();
		}
	 
		if (pos == null)
		{
			tags.remove("pos");
			tags.remove("dim");
		}
		else
		{
			tags.put("pos", NbtUtils.writeBlockPos(pos));
			
			if(world.dimension().equals(Level.OVERWORLD)) tags.putInt("dim", 0); //OVERWORLD
			if(world.dimension().equals(Level.NETHER)) tags.putInt("dim", -1); //NETHER
			if(world.dimension().equals(Level.END)) tags.putInt("dim", 1); //END
		}
		stack.setTag(tags);
	}

	//Teleport
	public void teleport(Player player, Level world, ItemStack stack)
	{
		if(world.isClientSide)
		{
			return;
		}

		BlockPos pos = getPosition(stack);
		int dim = getDimension(stack);

		if(world.dimension().equals(Level.OVERWORLD) && dim == 0)
		{
			player.teleportTo(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
		}
		else if(world.dimension().equals(Level.NETHER) && dim == -1)
		{
			player.teleportTo(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
		}
		else if(world.dimension().equals(Level.END) && dim == 1)
		{
			player.teleportTo(pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F);
		}
		else
		{
			player.sendMessage(new TranslatableComponent("item.gobber2.ring_teleport.line8").withStyle(ChatFormatting.YELLOW), null);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag)
	{	
		String dimName;
		
		switch(getDimension(stack))
		{
		case 1:
			dimName = "End"
;			break;
		case 0:
			dimName = "OverWorld";
			break;
		case -1:
			dimName = "Nether";
			break;
		default:
			dimName = "Unknown";
			break;
		}
		super.appendHoverText(stack, world, tooltip, flag);
				
		tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line9").withStyle(ChatFormatting.GREEN)));
		tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line10").withStyle(ChatFormatting.AQUA)));
		tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line11").withStyle(ChatFormatting.BLUE)));
		tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line12").withStyle(ChatFormatting.RED)));
		
		if(getPosition(stack) != null)
		{
			BlockPos pos = getPosition(stack);
		 
			tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line13").withStyle(ChatFormatting.GREEN)));
			tooltip.add((new TranslatableComponent("item.gobber2.ring_teleport.line14", dimName, pos.getX(), pos.getY(), pos.getZ()).withStyle(ChatFormatting.LIGHT_PURPLE)));
		}
	}
}
