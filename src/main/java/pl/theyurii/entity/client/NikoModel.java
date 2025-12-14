package pl.theyurii.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import pl.theyurii.SealParadise;
import pl.theyurii.entity.custom.NikoEntity;

public class NikoModel<T extends NikoEntity> extends SinglePartEntityModel<T> {

    public static final EntityModelLayer NIKO = new EntityModelLayer(Identifier.of(SealParadise.MOD_ID, "niko"),"main");

    private final ModelPart hitbox;
    private final ModelPart foka;
    private final ModelPart gowa;
    private final ModelPart tuow;
    private final ModelPart apa1;
    private final ModelPart apa2;
    private final ModelPart apatylnia1;
    private final ModelPart apatylnia2;
    private final ModelPart ogonprzytuowiu;
    private final ModelPart ogon;
    private final ModelPart ogonek;
    public NikoModel(ModelPart root) {
        this.hitbox = root.getChild("hitbox");
        this.foka = root.getChild("foka");
        this.gowa = this.foka.getChild("gowa");
        this.tuow = this.foka.getChild("tuow");
        this.apa1 = this.foka.getChild("apa1");
        this.apa2 = this.foka.getChild("apa2");
        this.apatylnia1 = this.foka.getChild("apatylnia1");
        this.apatylnia2 = this.foka.getChild("apatylnia2");
        this.ogonprzytuowiu = this.foka.getChild("ogonprzytuowiu");
        this.ogon = this.foka.getChild("ogon");
        this.ogonek = this.foka.getChild("ogonek");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData hitbox = modelPartData.addChild("hitbox", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData foka = modelPartData.addChild("foka", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData gowa = foka.addChild("gowa", ModelPartBuilder.create().uv(24, 29).cuboid(5.0F, -2.0F, -2.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
                .uv(0, 19).cuboid(2.0F, -3.0F, -3.0F, 4.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, 0.0F, 0.0F));

        ModelPartData tuow = foka.addChild("tuow", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 9.0F, 4.0F, 7.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData apa1 = foka.addChild("apa1", ModelPartBuilder.create().uv(18, 19).cuboid(1.0F, -1.0F, 3.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData apa2 = foka.addChild("apa2", ModelPartBuilder.create().uv(22, 11).cuboid(1.0F, -1.0F, -8.0F, 3.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData apatylnia1 = foka.addChild("apatylnia1", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tylniaapa1_r1 = apatylnia1.addChild("tylniaapa1_r1", ModelPartBuilder.create().uv(0, 27).cuboid(-0.7373F, -1.0F, -2.3244F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-13.0F, 0.0F, 2.0F, 0.0F, -0.7418F, 0.0F));

        ModelPartData apatylnia2 = foka.addChild("apatylnia2", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tylniaapa2_r1 = apatylnia2.addChild("tylniaapa2_r1", ModelPartBuilder.create().uv(12, 29).cuboid(-0.766F, 0.0F, -2.3572F, 2.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-13.0F, -1.0F, -3.0F, 0.0F, -2.2689F, 0.0F));

        ModelPartData ogonprzytuowiu = foka.addChild("ogonprzytuowiu", ModelPartBuilder.create().uv(0, 11).cuboid(-9.0F, -3.0F, -3.0F, 6.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData ogon = foka.addChild("ogon", ModelPartBuilder.create().uv(18, 24).cuboid(-12.0F, -2.0F, -2.0F, 3.0F, 2.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData ogonek = foka.addChild("ogonek", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData ogonek_r1 = ogonek.addChild("ogonek_r1", ModelPartBuilder.create().uv(22, 16).cuboid(-2.1299F, 0.0F, -0.0465F, 3.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-13.0F, -2.0F, 0.0F, 0.0F, -3.098F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }
    @Override
    public void setAngles(NikoEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);
    }
    private void setHeadAngles(float headYaw, float headPitch){
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.gowa.yaw = headYaw * 0.017453292F;
        this.gowa.pitch = headPitch * 0.017453292F;

    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        foka.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return foka;
    }
}
