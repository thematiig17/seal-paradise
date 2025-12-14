package pl.theyurii;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
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

        FabricDefaultAttributeRegistry.register(ModEntities.NIKO, NikoEntity.createNikoAttributes());
	}
}