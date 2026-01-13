package pl.theyurii.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import pl.theyurii.SealParadise;
import pl.theyurii.entity.custom.KyoroEntity;
import pl.theyurii.entity.custom.NikoEntity;

public class KyoroRenderer extends MobEntityRenderer<KyoroEntity, KyoroModel<KyoroEntity>> {

    public KyoroRenderer(EntityRendererFactory.Context context) {
        super(context, new KyoroModel<>(context.getPart(KyoroModel.KYORO)), 0.75f);
    }

    @Override
    public Identifier getTexture(KyoroEntity entity) {
        return Identifier.of(SealParadise.MOD_ID, "textures/entity/kyoro/kyoro.png");
    }

    @Override
    public void render(KyoroEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(1f, 1f, 1f);
        } else {
            matrixStack.scale(2f, 2f, 2f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}