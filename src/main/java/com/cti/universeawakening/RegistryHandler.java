package com.cti.universeawakening;
import com.cti.universeawakening.Blocks.BlockItemBase;
import com.cti.universeawakening.Blocks.CopperBlock;
import com.cti.universeawakening.Blocks.CopperOre;
import com.cti.universeawakening.Blocks.RawLightoniumOre;
import com.cti.universeawakening.Items.DarkCurrry;
import com.cti.universeawakening.Items.DeliciousSandwich;
import com.cti.universeawakening.Items.ItemBase;

import com.cti.universeawakening.crafting.lightonian_crafting.lightonian_crafting;
import com.cti.universeawakening.universeawakening;
import com.cti.universeawakening.Tools.LightoniumTier;
import com.cti.universeawakening.Tools.ModItemTier;
//import net.minecraft.block;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.material.Material;

import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.item.SwordItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraft.resources.VanillaPack;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import com.cti.universeawakening.Items.DarkCurrry;

import java.util.function.Supplier;
//import net.minecraft.block.material.Material;


public class RegistryHandler {
    // create DeferredRegister object

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, universeawakening.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, universeawakening.MODID);
    public static final DeferredRegister<TileEntityType<?>> TILES_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, universeawakening.MODID);
    public static void init() {
        // attach DeferredRegister to the event bus
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // register item
    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget", ItemBase::new);
    public static final RegistryObject<DeliciousSandwich> DELICIOUS_SANDWICH = ITEMS.register("delicious_sandwich", DeliciousSandwich::new);
    //Reccords and other goodies
    //public static final Item recordCraft1 = new ItemModRecord(1, .ModSounds.ctiRecord1, .unstackable());
    public static final RegistryObject<DarkCurrry> DARK_CURRRY = ITEMS.register("dark_curry", DarkCurrry::new);
    public static final RegistryObject<Item> LIGHTONIUM_INGOT = ITEMS.register("lightonium_ingot", ItemBase::new);
    public static final RegistryObject<Item> LIGHTONIAN_CRYSTAL = ITEMS.register("lightonium_crystal", ItemBase::new);
    public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", ItemBase::new);
    public static final RegistryObject<Item> RAW_LIGHTONIUM_CRYSTAL = ITEMS.register("raw_lightonium_crystal", ItemBase::new);


//TOOLS

    public static final RegistryObject<SwordItem> COPPER_SWORD = ITEMS.register("copper_sword", () ->
            new SwordItem(ModItemTier.COPPER, 3, -2.5f, new Item.Properties().group(universeawakening.TAB))
            );
    public static final RegistryObject<AxeItem> LIGHONIAN_AXE = ITEMS.register("lightonian_axe", () ->
            new AxeItem(LightoniumTier.LIGHTONIUM, 13, -1.7f, new Item.Properties().group(universeawakening.TAB)));
    public static final RegistryObject<SwordItem> LIGHTONIAN_SWORD = ITEMS.register("lightonian_sword", () ->
            new SwordItem(LightoniumTier.LIGHTONIUM, 98100, -2.5f, new Item.Properties().group(universeawakening.TAB)));
    public static final RegistryObject<PickaxeItem> LIGHTONIAN_PICKAXE = ITEMS.register("lightonian_pickaxe", () ->
            new PickaxeItem(LightoniumTier.LIGHTONIUM, 5, -1, new Item.Properties().group(universeawakening.TAB)));

//BlOCKS
    public static final RegistryObject<Block> FUSION_BLOCK = BLOCKS.register("fusion_block", )
    public static final RegistryObject<Block> COPPER_ORE = BLOCKS.register("copper_ore", CopperOre::new);
    public static final RegistryObject<Block> RAW_LIGHTONIUM_ORE = BLOCKS.register("raw_lightonium_ore", RawLightoniumOre::new);
    public static final RegistryObject<Block> COPPER_BLOCK = BLOCKS.register("copper_block", CopperBlock::new);
    public static final RegistryObject<Block> LIGHTONIAN_CRAFTING_TABLE = BLOCKS.register("lightonian_crafting_table", lightonian_crafting::new);
//BLOCK-ITEMS
public static final RegistryObject<Item> COPPER_BLOCK_ITEM = ITEMS.register("copper_block",() -> new BlockItemBase(COPPER_BLOCK.get()));
public static final RegistryObject<Item> COPPER_ORE_ITEM = ITEMS.register("copper_ore", () -> new BlockItemBase(COPPER_ORE.get()));
public static final RegistryObject<Item> RAW_LIGHTONIUM_ORE_ITEM = ITEMS.register("raw_lightonium_ore", () -> new BlockItemBase(RAW_LIGHTONIUM_ORE.get()));
public static final RegistryObject<Item> LIGHTONIAN_CRAFTING_TABLE_ITEM = ITEMS.register("lightonian_crafting_table", () -> new BlockItemBase(LIGHTONIAN_CRAFTING_TABLE.get()));
public

//random crap I added BC I'm insane

    ResourceLocation location = new ResourceLocation("universeawakening", "why");
    SoundEvent event = new SoundEvent(location);

//TILES


    public static final RegistryObject<TileE>
}








                     //   )


        //    );

