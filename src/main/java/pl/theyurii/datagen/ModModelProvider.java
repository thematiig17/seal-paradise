package pl.theyurii.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;
import pl.theyurii.block.ModBlocks;
import pl.theyurii.item.ModItems;

import java.util.Optional;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.BLOCK_SEAL_NIKO);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SNOWY_COBBLESTONE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.FISH_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.PORTABLE_SEAL_NIKO, Models.GENERATED);
        itemModelGenerator.register(ModItems.TORT_BLOCK, Models.GENERATED);

        Model spawnEggModel = new Model(Optional.of(Identifier.ofVanilla("item/template_spawn_egg")), Optional.empty());

        itemModelGenerator.register(ModItems.NIKO_SPAWN_EGG, spawnEggModel);
        itemModelGenerator.register(ModItems.KYORO_SPAWN_EGG, spawnEggModel);
        itemModelGenerator.register(ModItems.MIZORE_SPAWN_EGG, spawnEggModel);
        itemModelGenerator.register(ModItems.YO_SPAWN_EGG, spawnEggModel);
    }
}
