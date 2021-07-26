package com.kwpugh.gobber2.items.tools.paxel;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.kwpugh.gobber2.items.toolbaseclasses.PaxelBase;
import com.kwpugh.gobber2.util.EnableUtil;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
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
import net.minecraftforge.common.util.Constants.WorldEvents;

import net.minecraft.world.item.Item.Properties;

public class ItemCustomPaxelStars extends PaxelBase
{
	public ItemCustomPaxelStars(float attackDamageIn, float attackSpeedIn, Tier tier, Set<Block> effectiveBlocksIn,
			Properties builder)
	{
		super(attackDamageIn, attackSpeedIn, tier, EFFECTIVE_ON, builder);
	}

    @Override
    public InteractionResult useOn(UseOnContext iuc)
    {    	
    	BlockPos torchPos;
    	BlockPos pos = iuc.getClickedPos();
    	Level world = iuc.getLevel();
    	BlockPos blockpos = iuc.getClickedPos();
    	Player player = iuc.getPlayer();
    	ItemStack stack = player.getMainHandItem();
    	BlockState blockstate = world.getBlockState(blockpos);
    	BlockState resultToSet = null;
    	Block strippedResult = BLOCK_STRIPPING_MAP.get(blockstate.getBlock());
          
    	if(!EnableUtil.isEnabled(stack))
    	{
    		if (strippedResult != null)
            {
                world.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
                resultToSet = strippedResult.defaultBlockState().setValue(RotatedPillarBlock.AXIS, blockstate.getValue(RotatedPillarBlock.AXIS));
            }
            else
            {
                if (iuc.getClickedFace() == Direction.DOWN)
                {
                    return InteractionResult.PASS;
                }
                
                BlockState foundResult = SHOVEL_LOOKUP.get(blockstate.getBlock());
                
                if (foundResult != null && world.isEmptyBlock(blockpos.above()))
                {
                    world.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
                    resultToSet = foundResult;
                }
                else if (blockstate.getBlock() instanceof CampfireBlock && blockstate.getValue(CampfireBlock.LIT))
                {
                    world.levelEvent(null, WorldEvents.FIRE_EXTINGUISH_SOUND, blockpos, 0);
                    resultToSet = blockstate.setValue(CampfireBlock.LIT, false);
                }
            }
            if (resultToSet == null)
            {
                return InteractionResult.PASS;
            }
            if (!world.isClientSide)
            {
                world.setBlock(blockpos, resultToSet, 11);
                
                if (player != null)
                {
                    iuc.getItemInHand().hurtAndBreak(0, player, onBroken -> onBroken.broadcastBreakEvent(iuc.getHand()));
                }
            }
            stack.setDamageValue(0);  //no damage
            
            return InteractionResult.SUCCESS;
    	}
    	
    	if(iuc.getLevel().getBlockState(pos).getBlock() == Blocks.TORCH
				|| iuc.getLevel().getBlockState(pos).getBlock() == Blocks.WALL_TORCH)
		{
			return InteractionResult.FAIL;
		}
    	
    	Boolean isWallTorch = false;
    	switch(iuc.getClickedFace())
    	{
    	case DOWN:
    		return InteractionResult.FAIL;
    	case UP:
    		torchPos = new BlockPos(pos.getX(), pos.getY() +1, pos.getZ());
    		break;
    	case NORTH:
    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() -1);
    		isWallTorch = true;
    		break;
    	case SOUTH:
    		torchPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() +1);
    		isWallTorch = true;
    		break;
    	case WEST:
    		torchPos = new BlockPos(pos.getX() -1, pos.getY(), pos.getZ());
    		isWallTorch = true;
    		break;
    	case EAST:
    		torchPos = new BlockPos(pos.getX() +1, pos.getY(), pos.getZ());
    		isWallTorch = true;
    		break;
    	default:
    		return InteractionResult.FAIL;
    	}
    	
    	if(iuc.getLevel().getBlockState(torchPos).isAir() || iuc.getLevel().getBlockState(torchPos).getFluidState().isSource())
    	{
    		if(blockstate.isRedstoneConductor(world, pos))
    		{
    			if (isWallTorch)
        		{
        			iuc.getLevel().setBlockAndUpdate(torchPos, Blocks.WALL_TORCH.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, iuc.getClickedFace()));
        			iuc.getLevel().playSound(null, iuc.getPlayer().blockPosition(), SoundEvents.WOOD_PLACE, SoundSource.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
        		}
        		else
        		{
        			iuc.getLevel().setBlockAndUpdate(torchPos, Blocks.TORCH.defaultBlockState());
        			iuc.getLevel().playSound(null, iuc.getPlayer().blockPosition(), SoundEvents.WOOD_PLACE, SoundSource.NEUTRAL, 8.0F, (float) (0.7F + (Math.random()*0.3D)));
        		}			
    		}
    
    		return InteractionResult.SUCCESS;
    	}
    	return InteractionResult.FAIL;
    }
	
	@Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand)
    {
		ItemStack stack = player.getItemInHand(hand);
		
        if(!world.isClientSide && player.isShiftKeyDown())
        {
            EnableUtil.changeEnabled(player, hand);
            player.displayClientMessage(new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line4", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.RED), true);
            return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, player.getItemInHand(hand));
        }
        return super.use(world, player, hand);
    }

	@Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
		stack.setDamageValue(0);  //no damage
        
        return true;
    }

    public boolean mineBlock(ItemStack stack, Level worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving)
    {
        if (!worldIn.isClientSide && (double)state.getDestroySpeed(worldIn, pos) != 0.0D)
        {
            stack.setDamageValue(0);
        }
        return true;
    }
    
	@Override
	public void onCraftedBy(ItemStack stack, Level worldIn, Player playerIn)
	{
		stack.getOrCreateTag().putBoolean("Unbreakable", true);
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		return true;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn)
	{
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line2").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line3").withStyle(ChatFormatting.YELLOW)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line4", EnableUtil.isEnabled(stack)).withStyle(ChatFormatting.RED)));
		tooltip.add((new TranslatableComponent("item.gobber2.gobber2_paxel_stars.line5").withStyle(ChatFormatting.LIGHT_PURPLE)));
	} 
}
