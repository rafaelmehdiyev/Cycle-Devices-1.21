package net.rafael.cycledevices.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.rafael.cycledevices.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {
            @Override
            public void generate() {
                createShaped(RecipeCategory.TOOLS, ModItems.WEATHER_CYCLE_DEVICE)
                        .pattern("WWW")
                        .pattern("PGP")
                        .pattern("RRR")
                        .input('W', Items.WATER_BUCKET)
                        .input('G', Items.GOLD_BLOCK)
                        .input('R', Items.BLAZE_ROD)
                        .input('P', Items.BLAZE_POWDER)
                        .criterion("has_water_bucket", conditionsFromItem(Items.WATER_BUCKET))
                        .criterion("has_gold_block", conditionsFromItem(Items.GOLD_BLOCK))
                        .criterion("has_blaze_rod", conditionsFromItem(Items.BLAZE_ROD))
                        .criterion("has_blaze_powder", conditionsFromItem(Items.BLAZE_POWDER))
                        .offerTo(recipeExporter);

                // Time Cycle Device Recipe
                createShaped(RecipeCategory.TOOLS, ModItems.TIME_CYCLE_DEVICE)
                        .pattern("GGG")
                        .pattern("CNC")
                        .pattern("GGG")
                        .input('C', Items.CLOCK)
                        .input('N', Items.NETHER_STAR)
                        .input('G', Items.GOLD_INGOT)
                        .criterion("has_clock", conditionsFromItem(Items.CLOCK))
                        .criterion("has_nether_star", conditionsFromItem(Items.NETHER_STAR))
                        .criterion("has_gold_ingot", conditionsFromItem(Items.GOLD_INGOT))
                        .offerTo(recipeExporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Cycle Device Mod Recipes";
    }
}
