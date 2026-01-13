package pl.theyurii;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import pl.theyurii.entity.ModEntities;
import pl.theyurii.entity.client.*;

public class SealParadiseClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(NikoModel.NIKO, NikoModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.NIKO, NikoRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(YoModel.YO, YoModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.YO, YoRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(MizoreModel.MIZORE, MizoreModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MIZORE, MizoreRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(KyoroModel.KYORO, KyoroModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.KYORO, KyoroRenderer::new);
    }
}
