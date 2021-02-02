package com.cti.universeawakening.world;

import com.cti.universeawakening.RegistryHandler;
import com.google.common.collect.Lists;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class initGen {
    private static RegistryHandler ModBlocks;

    public static void initGen() {
    Registry.register(
            WorldGenRegistries.field_243653_e /* Feature Registering */,
            ModBlocks.COPPER_ORE.getId() /* Resource Location */,
            Feature.field_236289_V_ /* no_surface_ore */.withConfiguration(
                    new OreFeatureConfig(
                            OreFeatureConfig.FillerBlockType.field_241882_a /* base_stone_overworld */,
                            ModBlocks.COPPER_ORE.get().getDefaultState() ,
                    64
            )
    ).withPlacement(Placement.field_242910_o /* depth */ .configure(
            new DepthAverageConfig(12, 12)
    )).func_242728_a() /* spreadHorizontally */ .func_242731_b(1) /* repeat */
    );
}

    public static void setupGen() {
        for (Map.Entry<RegistryKey<Biome>, Biome> biome : WorldGenRegistries.field_243657_i.func_239659_c_() /* Collection of Biome Entries */) {
            if (!biome.getValue().getCategory().equals(Biome.Category.NETHER) && !biome.getValue().getCategory().equals(Biome.Category.THEEND)) {
                addFeatureToBiome(
                        biome.getValue(),
                        GenerationStage.Decoration.UNDERGROUND_ORES,
                        WorldGenRegistries.field_243653_e.getOrDefault(ModBlocks.COPPER_ORE.getId())
                );
            }
        }
    }

    public static void addFeatureToBiome(Biome biome, GenerationStage.Decoration decoration, ConfiguredFeature<?, ?> configuredFeature) {
        List<Supplier<ConfiguredFeature<?, ?>>> biomeFeatures = new ArrayList<>(
                biome.func_242440_e().func_242498_c() /* List of Configured Features */
        );

        while (biomeFeatures.size() <= decoration.ordinal()) {
            biomeFeatures.add(Lists.newArrayList());
        }

        List<Supplier<ConfiguredFeature<?, ?>>> features = new ArrayList<>(biomeFeatures.get(decoration.ordinal()));
        features.add(() -> configuredFeature);
        biomeFeatures.set(decoration.ordinal(), features);

        /* Change field_242484_f that contains the Configured Features of the Biome*/
        ObfuscationReflectionHelper.setPrivateValue(BiomeGenerationSettings.class, biome.func_242440_e(), biomeFeatures, "field_242484_f");
    }}
}
