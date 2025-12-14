package pl.theyurii.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import pl.theyurii.SealParadise;
import pl.theyurii.entity.custom.NikoEntity;

public class NikoRenderer extends MobEntityRenderer<NikoEntity, NikoModel<NikoEntity>> {

    public NikoRenderer(EntityRendererFactory.Context context) {
            super(context, new NikoModel<>(context.getPart(NikoModel.NIKO)), 0.75f);
    }

    @Override
    public Identifier getTexture(NikoEntity entity) {
        return Identifier.of(SealParadise.MOD_ID, "textures/entity/niko/niko.png");
    }

    @Override
    public void render(NikoEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(2f, 2f, 2f);
        } else {
            matrixStack.scale(4f, 4f, 4f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
