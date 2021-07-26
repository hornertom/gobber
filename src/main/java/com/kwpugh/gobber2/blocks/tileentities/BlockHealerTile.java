package com.kwpugh.gobber2.blocks.tileentities;

//import java.util.List;
//
//import com.kwpugh.gobber2.config.GobberConfigBuilder;
//import com.kwpugh.gobber2.init.BlockEntityInit;
//import com.kwpugh.gobber2.util.PlayerSpecialAbilities;
//
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.block.entity.TickableBlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.phys.AABB;
//
//public class BlockHealerTile extends BlockEntity implements TickableBlockEntity
//{
//	int radius = GobberConfigBuilder.HEALER_RADIUS.get();
//
//    public BlockHealerTile()
//	{
//		super(BlockEntityInit.BLOCK_HEALER.get());
//	}
//
//	@Override
//    public void tick()
//    {
//		if (level != null && !level.isClientSide)
//		{
//			List<Entity> entities = level.getEntitiesOfClass(Player.class, new AABB(worldPosition.getX() - radius, worldPosition.getY() - radius, worldPosition.getZ() - radius, worldPosition.getX() + radius, worldPosition.getY() + radius, worldPosition.getZ() + radius));
//			for(Entity entity : entities)
//			{
//				Player player = (Player)entity;
//
//				if(entity instanceof Player)
//				{
//					int newfoodlevel = 1;
//					float newsatlevel = 0.025F;
//					PlayerSpecialAbilities.giveRegenEffect(level, player, null, newfoodlevel, newsatlevel);
//				}
//			}
//		}
//    }
//}