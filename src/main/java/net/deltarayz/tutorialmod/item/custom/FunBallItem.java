package net.deltarayz.tutorialmod.item.custom;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.level.Level;

public class FunBallItem extends SnowballItem {
    public FunBallItem(Properties properties) {
        super(properties);
    }

    @Override
    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        Snowball fun_ball = new Snowball(level, pos.x(), pos.y(), pos.z());
        fun_ball.setItem(stack);
        return fun_ball;
    }


}
