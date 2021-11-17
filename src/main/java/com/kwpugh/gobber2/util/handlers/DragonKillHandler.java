package com.kwpugh.gobber2.util.handlers;

import com.kwpugh.gobber2.config.GobberConfigBuilder;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/*
 * Credits to Darkere from More Dragon Eggs mod
 * 
 * I suggest you use the More Dragon Eggs
 * mod if you can and support its author
 * 
 * If you cannot, for some reason, this
 * feature can be turned on in the config file
 * 
 */

public class DragonKillHandler
{
    public DragonKillHandler()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void LivingDeathEvent(LivingDeathEvent event)
    {
        if(event.getEntity().getCommandSenderWorld().dimension().equals(Level.END))
    	{
        	if(GobberConfigBuilder.ENABLE_DRAGON_KILL_EVERY_KILL.get())
        	{
                if (event.getEntity() instanceof EnderDragon)
                {
                    EnderDragon d = (EnderDragon) event.getEntity();
                    if (d.getDragonFight() != null && d.getDragonFight().hasPreviouslyKilledDragon())
                    {
                        event.getEntity().getCommandSenderWorld().setBlockAndUpdate(event.getEntity().getCommandSenderWorld().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION), Blocks.DRAGON_EGG.defaultBlockState());
                    }
                }	
        	}
        }
    }
}