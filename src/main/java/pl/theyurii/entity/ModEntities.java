package pl.theyurii.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import pl.theyurii.SealParadise;
import pl.theyurii.entity.custom.NikoEntity;
import pl.theyurii.entity.custom.YoEntity;

public class ModEntities {

    public static final EntityType<NikoEntity> NIKO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(SealParadise.MOD_ID, "niko"),
            EntityType.Builder.create(NikoEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 0.5f).build());

    public static final EntityType<YoEntity> YO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(SealParadise.MOD_ID, "yo"),
            EntityType.Builder.create(YoEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 0.5f).build());

    public static void registerModEntities(){
        SealParadise.LOGGER.info("Registering Mod Entities for " + SealParadise.MOD_ID);
    }
}
