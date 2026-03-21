package net.deltarayz.tutorialmod.item;

import net.deltarayz.tutorialmod.TutorialMod;
import net.deltarayz.tutorialmod.blocks.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TutorialMod.MOD_ID);

    public static final Supplier<CreativeModeTab> FIRST_CREATIVE_MODE_TAB = CREATIVE_MODE_TAB.register("first_creative_mode_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FLINT_SHARD.get()))
                    .title(Component.translatable("creativetab.tutorialmod.first_ctab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.FLINT_SHARD);
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                    }).build());

    public static final Supplier<CreativeModeTab> SECOND_CREATIVE_MODE_TAB = CREATIVE_MODE_TAB.register("second_creative_mode_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.FLINT_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "first_creative_mode_tab"))
                    .title(Component.translatable("creativetab.tutorialmod.second_ctab"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.FLINT_BLOCK);
                        output.accept(Blocks.ANVIL);
                        output.accept(Items.FLINT);
                    }).build());

    public static final Supplier<CreativeModeTab> FLINT_STUFF = CREATIVE_MODE_TAB.register("flint_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.FLINT))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "first_creative_mode_tab"))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "second_creative_mode_tab"))
                    .title(Component.translatable("creativetab.tutorialmod.flintstuff"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.FLINT_SHARD);
                        output.accept(Items.FLINT);
                        output.accept(ModBlocks.FLINT_BLOCK);
                        output.accept(Blocks.GRAVEL);
                        output.accept(Items.FLINT_AND_STEEL);
                    }).build());

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("bismuth_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BISMUTH.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "first_creative_mode_tab"))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "second_creative_mode_tab"))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "flint_tab"))
                    .title(Component.translatable("creativetab.tutorialmod.bismuth_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);
                    }).build());

    public static final Supplier<CreativeModeTab> BISMUTH_BLOCK_TAB = CREATIVE_MODE_TAB.register("bismuth_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BISMUTH_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "first_creative_mode_tab"))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "second_creative_mode_tab"))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "flint_tab"))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "bismuth_items_tab"))
                    .title(Component.translatable("creativetab.tutorialmod.bismuth_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
