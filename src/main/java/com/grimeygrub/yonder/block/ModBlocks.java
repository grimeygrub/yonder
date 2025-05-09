package com.grimeygrub.yonder.block;

import com.grimeygrub.yonder.Yonder;
import com.grimeygrub.yonder.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Yonder.MOD_ID);

    public static final DeferredBlock<Block>  MOONDUST_BLOCK = registerBlock("moondust_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(0.3F, 1.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.SAND )
                    .mapColor(MapColor.STONE)
                    .instrument(NoteBlockInstrument.BIT)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
