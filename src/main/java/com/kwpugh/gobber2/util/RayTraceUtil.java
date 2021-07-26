package com.kwpugh.gobber2.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

public class RayTraceUtil
{
	public static HitResult getNearestPositionWithAir(Level world, Player player, int reach)
    {
        return getMovingObjectPosWithReachDistance(world, player, reach, false, false, true);
    }

    private static HitResult getMovingObjectPosWithReachDistance(Level world, Player player, double distance, boolean p1, boolean p2, boolean p3)
    {
        float f = player.xRotO;
        float f1 = player.yRotO;
        double d0 = player.getX();
        double d1 = player.getY() + (double) player.getEyeHeight();
        double d2 = player.getZ();
        Vec3 vec3 = new Vec3(d0, d1, d2);
        float f2 = Mth.cos(-f1 * 0.017453292F - (float) Math.PI);
        float f3 = Mth.sin(-f1 * 0.017453292F - (float) Math.PI);
        float f4 = -Mth.cos(-f * 0.017453292F);
        float f5 = Mth.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        Vec3 vec31 = vec3.add((double) f6 * distance, (double) f5 * distance, (double) f7 * distance);
        
        return world.clip(new ClipContext(vec3, vec31, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, player));
    }
}