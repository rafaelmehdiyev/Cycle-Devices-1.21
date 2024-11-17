package net.rafael.cycledevices.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.rafael.cycledevices.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        // Weather Cycle Device Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS,ModItems.WEATHER_CYCLE_DEVICE)
                .pattern("WWW")
                .pattern("PGP")
                .pattern("RRR")
                .input('W', Items.WATER_BUCKET)
                .input('G', Items.GOLD_BLOCK)
                .input('R', Items.BLAZE_ROD)
                .input('P', Items.BLAZE_POWDER)
                .criterion("has_water_bucket", RecipeProvider.conditionsFromItem(Items.WATER_BUCKET))
                .criterion("has_gold_block", RecipeProvider.conditionsFromItem(Items.GOLD_BLOCK))
                .criterion("has_blaze_rod", RecipeProvider.conditionsFromItem(Items.BLAZE_ROD))
                .criterion("has_blaze_powder", RecipeProvider.conditionsFromItem(Items.BLAZE_POWDER))
                .offerTo(recipeExporter, Identifier.of("rafaels-cycle-devices", "weather_cycle_device"));

        // Time Cycle Device Recipe
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS,ModItems.TIME_CYCLE_DEVICE)
                .pattern("GGG")
                .pattern("CNC")
                .pattern("GGG")
                .input('C', Items.CLOCK)
                .input('N', Items.NETHER_STAR)
                .input('G', Items.GOLD_INGOT)
                .criterion("has_clock", RecipeProvider.conditionsFromItem(Items.CLOCK))
                .criterion("has_nether_star", RecipeProvider.conditionsFromItem(Items.NETHER_STAR))
                .criterion("has_gold_ingot", RecipeProvider.conditionsFromItem(Items.GOLD_INGOT))
                .offerTo(recipeExporter, Identifier.of("rafaels-cycle-devices", "time_cycle_device"));
    }

}