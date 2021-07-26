package com.kwpugh.gobber2.blocks.tileentities;

//import java.util.List;
//
//import com.kwpugh.gobber2.config.GobberConfigBuilder;
//import com.kwpugh.gobber2.init.BlockEntityInit;
//
//import net.minecraft.world.entity.Entity;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.Mob;
//import net.minecraft.world.entity.decoration.ArmorStand;
//import net.minecraft.world.entity.ExperienceOrb;
//import net.minecraft.world.entity.item.ItemEntity;
//import net.minecraft.world.entity.monster.Blaze;
//import net.minecraft.world.entity.monster.Creeper;
//import net.minecraft.world.entity.monster.EnderMan;
//import net.minecraft.world.entity.monster.hoglin.Hoglin;
//import net.minecraft.world.entity.monster.Phantom;
//import net.minecraft.world.entity.monster.Pillager;
//import net.minecraft.world.entity.monster.Ravager;
//import net.minecraft.world.entity.monster.Silverfish;
//import net.minecraft.world.entity.monster.Skeleton;
//import net.minecraft.world.entity.monster.Slime;
//import net.minecraft.world.entity.monster.SpellcasterIllager;
//import net.minecraft.world.entity.monster.Spider;
//import net.minecraft.world.entity.monster.Stray;
//import net.minecraft.world.entity.monster.Vindicator;
//import net.minecraft.world.entity.monster.Witch;
//import net.minecraft.world.entity.monster.WitherSkeleton;
//import net.minecraft.world.entity.monster.Zoglin;
//import net.minecraft.world.entity.monster.Zombie;
//import net.minecraft.world.entity.monster.ZombieVillager;
//import net.minecraft.world.entity.monster.piglin.PiglinBrute;
//import net.minecraft.world.entity.monster.piglin.Piglin;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.level.block.entity.TickableBlockEntity;
//import net.minecraft.world.level.block.entity.BlockEntity;
//import net.minecraft.world.phys.AABB;
//
//public class BlockLooterTile extends BlockEntity implements TickableBlockEntity
//{
//	int radius = GobberConfigBuilder.LOOTER_RADIUS.get();
//	boolean xpOnly = false;
//    public BlockLooterTile()
//	{
//		super(BlockEntityInit.BLOCK_LOOTER.get());
//	}
//
//	@Override
//    public void tick()
//    {
//		if(level != null && !level.isClientSide && !level.hasNeighborSignal(worldPosition))
//		{
//			//Scan the radius for LivingEntity and store in list
//			List<Entity> mobs = level.getEntitiesOfClass(LivingEntity.class, new AABB(worldPosition.getX() - radius, worldPosition.getY() - radius, worldPosition.getZ() - radius, worldPosition.getX() + radius, worldPosition.getY() + radius, worldPosition.getZ() + radius), e -> (e instanceof LivingEntity));
//			for(Entity mob : mobs)
//			{
//				if(mob instanceof ArmorStand)
//				{
//					xpOnly = true;
//				}
//
//				if(mob instanceof Zombie || mob instanceof ZombieVillager || mob instanceof PiglinBrute || mob instanceof Piglin)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.GOLD_INGOT);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof Spider)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.STRING);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof Skeleton || mob instanceof Stray)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.BONE);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof Pillager || mob instanceof Ravager)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					ItemStack drop = new ItemStack(Items.IRON_INGOT);
//					level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof Creeper)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.GUNPOWDER);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof Slime)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.SLIME_BALL);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof Witch || mob instanceof Silverfish)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.EMERALD);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof EnderMan)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.ENDER_PEARL);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof Blaze)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.BLAZE_ROD);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof WitherSkeleton)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.WITHER_SKELETON_SKULL);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof Phantom)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.PHANTOM_MEMBRANE);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof Vindicator || mob instanceof SpellcasterIllager)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.DIAMOND);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//
//				if(mob instanceof Hoglin || mob instanceof Zoglin)
//				{
//					((Mob) mob).spawnAnim();
//					mob.remove(true);
//					if(!xpOnly)
//					{
//						ItemStack drop = new ItemStack(Items.LEATHER);
//						level.addFreshEntity(new ItemEntity(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, drop));
//					}
//
//					level.addFreshEntity(new ExperienceOrb(level, worldPosition.getX()+3, worldPosition.getY(), worldPosition.getZ()+3, 1));
//				}
//		   }
//		}
//    }
//}