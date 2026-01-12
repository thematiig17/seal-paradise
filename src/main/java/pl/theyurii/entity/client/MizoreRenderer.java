package pl.theyurii.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import pl.theyurii.SealParadise;
import pl.theyurii.entity.custom.MizoreEntity;

public class MizoreRenderer extends MobEntityRenderer<MizoreEntity, MizoreModel<MizoreEntity>> {

    public MizoreRenderer(EntityRendererFactory.Context context) {
        super(context, new MizoreModel<>(context.getPart(MizoreModel.MIZORE)), 0.75f);
    }

    @Override
    public Identifier getTexture(MizoreEntity entity) {
        return Identifier.of(SealParadise.MOD_ID, "textures/entity/mizore/mizore.png");
    }

    @Override
    public void render(MizoreEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(1.4f, 1.4f, 1.4f);
        } else {
            matrixStack.scale(2.8f, 2.8f, 2.8f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
