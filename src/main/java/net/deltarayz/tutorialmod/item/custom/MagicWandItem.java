package net.deltarayz.tutorialmod.item.custom;

import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.Map;

public class MagicWandItem extends Item implements ProjectileItem {

    //Constructor
    public MagicWandItem(Properties properties) {
        super(properties);
    }

    //Magic Wand conversion map
    private static final Map<Block, Block> WAND_MAP =
            Map.ofEntries(
                    Map.entry(Blocks.SAND, Blocks.GRAVEL),
                    Map.entry(Blocks.GRAVEL, Blocks.SAND),

                    Map.entry(Blocks.ANDESITE, Blocks.GRANITE),
                    Map.entry(Blocks.GRANITE, Blocks.DIORITE),
                    Map.entry(Blocks.DIORITE, Blocks.ANDESITE),

                    Map.entry(Blocks.RED_SAND, Blocks.NETHERRACK),
                    Map.entry(Blocks.NETHERRACK, Blocks.RED_SAND),

                    Map.entry(Blocks.OCHRE_FROGLIGHT, Blocks.PEARLESCENT_FROGLIGHT),
                    Map.entry(Blocks.PEARLESCENT_FROGLIGHT, Blocks.VERDANT_FROGLIGHT),
                    Map.entry(Blocks.VERDANT_FROGLIGHT, Blocks.OCHRE_FROGLIGHT)

            );


    @Override
    public InteractionResult useOn(UseOnContext context) {

        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        //Block conversion and wand damage/break
        if(WAND_MAP.containsKey(clickedBlock)) {

            if(!level.isClientSide()) {

                level.setBlockAndUpdate(context.getClickedPos(), WAND_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.ALLAY_HURT, SoundSource.BLOCKS);


            }
        }

        return InteractionResult.SUCCESS;
    }


    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        ItemStack itemstack = player.getItemInHand(hand);

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.VEX_CHARGE,
                SoundSource.NEUTRAL,
                0.5F,
                0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
        );
        if (!level.isClientSide) {
            Snowball snowball = new Snowball(level, player);
            snowball.setItem(itemstack);
            snowball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(snowball);
        }

        player.awardStat(Stats.ITEM_USED.get(this));

        player.getItemInHand(InteractionHand.MAIN_HAND).hurtAndBreak(1,player,EquipmentSlot.MAINHAND);

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }



    public Projectile asProjectile(Level level, Position pos, ItemStack stack, Direction direction) {
        Snowball snowball = new Snowball(level, pos.x(), pos.y(), pos.z());
        snowball.setItem(stack);
        return snowball;
    }


}
