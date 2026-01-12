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
import pl.theyurii.entity.custom.MizoreEntity;
import pl.theyurii.entity.custom.NikoEntity;
import pl.theyurii.entity.custom.YoEntity;
import pl.theyurii.item.ModItems;

public class SealParadise implements ModInitializer {
	public static final String MOD_ID = "sealparadise";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

        System.out.println("SEALPARADISE: Rejestruje przedmioty, bloki i zwierzęta dla moda " + MOD_ID);
        //INICJALIZACJA PRZEDMITÓW, BLOKÓW, ZWIERZĄT
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModEntities.registerModEntities();
        FabricDefaultAttributeRegistry.register(ModEntities.NIKO, NikoEntity.createNikoAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.YO, YoEntity.createYoAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.MIZORE, YoEntity.createYoAttributes());

        System.out.println("SEALPARADISE: Rejestruje portal dla moda " + MOD_ID);
        //INICJALIZACJA PORTALU
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.SANDSTONE)
                .destDimID(Identifier.of(MOD_ID, "seal_world"))
                .tintColor(128, 128, 128)
                .registerPortal();

        System.out.println("SEALPARADISE: Rejestruje warunki pojawiania się zwierząt dla moda " + MOD_ID);
        //SPAWN RESTRICITON NOWYCH ZWIERZĄT
        SpawnRestriction.register( //NIKO THE SEAL
                ModEntities.NIKO,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                NikoEntity::canSpawn
        );
        SpawnRestriction.register( //YO THE SEAL
                ModEntities.YO,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                YoEntity::canSpawn
        );
        SpawnRestriction.register( //MIZORE THE SEAL
                ModEntities.MIZORE,
                SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
                MizoreEntity::canSpawn
        );
	}
}