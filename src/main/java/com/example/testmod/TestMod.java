package com.example.testmod;

import com.example.testmod.blocks.TutorialBlock;
import com.example.testmod.items.TutorialDust;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

@Mod(Reference.MODID)
public class TestMod {
    private static final Logger LOGGER = LogManager.getLogger();

    public static ItemGroup creativeTab;

    private Block[] blocks;
    private Item[] items;
    private Item[] itemBlocks;

    public TestMod() {
        creativeTab = new ItemGroup(Reference.MODID) {
            @Override
            public ItemStack createIcon() {
                return new ItemStack(new TutorialBlock());
            }
        };

        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Item.class, this::registerItems);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(Block.class, this::registerBlocks);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
            this.getBlocks()
        );
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
            this.getItems()
        );

        event.getRegistry().registerAll(
            this.getItemBlocks()
        );
    }

    private Item[] getItems() {
        if(this.items == null) {
            HashSet<Item> items = new HashSet<>();

            items.add(new TutorialDust());

            this.items = items.toArray(new Item[0]);
        }

        return this.items;
    }

    private Block[] getBlocks() {
        if (this.blocks == null) {
            HashSet<Block> blocks = new HashSet<>();

            blocks.add(new TutorialBlock());

            this.blocks = blocks.toArray(new Block[0]);
        }

        return this.blocks;
    }

    private Item[] getItemBlocks() {
        if (this.itemBlocks == null) {
            HashSet<Item> itemBlocks = new HashSet<>();

            for (Block block : this.getBlocks()) {
                itemBlocks.add(
                    new ItemBlock(block, new Item.Properties().group(TestMod.creativeTab)).setRegistryName(block.getRegistryName())
                );
            }

            this.itemBlocks = itemBlocks.toArray(new Item[0]);
        }

        return this.itemBlocks;
    }

    private void setup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM PREINIT");
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
