package net.deltarayz.tutorialmod.item;

import net.deltarayz.tutorialmod.TutorialMod;
import net.deltarayz.tutorialmod.item.custom.ChiselItem;
import net.deltarayz.tutorialmod.item.custom.FunBallItem;
import net.deltarayz.tutorialmod.item.custom.MagicWandItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SnowballItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TutorialMod.MOD_ID);

    //--------------------------NEW ITEMS--------------------------------------------------------------------//
    public static final DeferredItem<Item> FLINT_SHARD = ITEMS.register("flint_shard",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> FUN_BALL = ITEMS.register("fun_ball",
            () -> new FunBallItem(new Item.Properties()));
    public static final DeferredItem<Item> MAGIC_WAND = ITEMS.register("magic_wand",
            () -> new MagicWandItem(new Item.Properties().durability(16)));
    //--------------------------NEW ITEMS--------------------------------------------------------------------//





    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
