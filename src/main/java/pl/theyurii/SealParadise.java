package pl.theyurii;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.theyurii.block.ModBlocks;
import pl.theyurii.entity.ModEntities;
import pl.theyurii.entity.client.NikoModel;
import pl.theyurii.entity.custom.NikoEntity;
import pl.theyurii.item.ModItems;

public class SealParadise implements ModInitializer {
	public static final String MOD_ID = "sealparadise";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModEntities.registerModEntities();
        FabricDefaultAttributeRegistry.register(ModEntities.NIKO, NikoEntity.createNikoAttributes());

        System.out.println("LOG: Rejestruje portal dla modu " + MOD_ID);
        // Rejestracja portalu
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.SANDSTONE) // Blok ramy
                .destDimID(Identifier.of(MOD_ID, "seal_world")) // TWOJA nazwa wymiaru z JSONa
                .tintColor(128, 128, 128) // Kolor tekstury portalu (szary jak bruk)
                .registerPortal();

        //spawnowanie fok w wymiarze fokowym
        BiomeModifications.addSpawn(
                // Selektor: dodaj tylko w biomach, które są w Twoim wymiarze
                context -> context.canGenerateIn(RegistryKey.of(RegistryKeys.DIMENSION, Identifier.of(MOD_ID, "seal_world"))),
                SpawnGroup.CREATURE, // Grupa (CREATURE = zwierzęta, MONSTER = potwory)
                ModEntities.NIKO, // Twoja zmienna z zarejestrowanym EntityType
                500, // WAGA (częstotliwość) - im wyższa, tym częściej się spawnuje
                5,   // Minimalna wielkość grupki
                10    // Maksymalna wielkość grupki
        );
        // Przykład dla zwierzęcia lądowego:
        SpawnRestriction.register(
                ModEntities.NIKO,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                NikoEntity::canSpawn // <--- ZMIANA: Tu podajemy Twoją nową metodę
        );
	}
}