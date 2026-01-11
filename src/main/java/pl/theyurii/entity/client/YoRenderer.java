package pl.theyurii.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import pl.theyurii.SealParadise;
import pl.theyurii.entity.custom.YoEntity;

public class YoRenderer extends MobEntityRenderer<YoEntity, YoModel<YoEntity>> {

    public YoRenderer(EntityRendererFactory.Context context) {
        super(context, new YoModel<>(context.getPart(YoModel.YO)), 0.75f);
    }

    @Override
    public Identifier getTexture(YoEntity entity) {
        return Identifier.of(SealParadise.MOD_ID, "textures/entity/yo/yo.png");
    }

    @Override
    public void render(YoEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(1.4f, 1.4f, 1.4f);
        } else {
            matrixStack.scale(2.8f, 2.8f, 2.8f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
