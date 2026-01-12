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
import pl.theyurii.entity.custom.MizoreEntity;
import pl.theyurii.entity.custom.NikoEntity;

public class MizoreModel<T extends MizoreEntity> extends SinglePartEntityModel<T> {

    public static final EntityModelLayer MIZORE = new EntityModelLayer(Identifier.of(SealParadise.MOD_ID, "mizore"),"main");

    private final ModelPart MizoreRoot;
    private final ModelPart gowa;
    private final ModelPart pysczek;
    private final ModelPart tulow;
    private final ModelPart przedogon;
    private final ModelPart ogon;
    private final ModelPart lapa1;
    private final ModelPart lapa2;
    private final ModelPart tylniepletwy;
    private final ModelPart tylniepletwy2;
    public MizoreModel(ModelPart root) {
        this.MizoreRoot = root.getChild("MizoreRoot");
        this.gowa = this.MizoreRoot.getChild("gowa");
        this.pysczek = this.gowa.getChild("pysczek");
        this.tulow = this.MizoreRoot.getChild("tulow");
        this.przedogon = this.MizoreRoot.getChild("przedogon");
        this.ogon = this.MizoreRoot.getChild("ogon");
        this.lapa1 = this.MizoreRoot.getChild("lapa1");
        this.lapa2 = this.MizoreRoot.getChild("lapa2");
        this.tylniepletwy = this.MizoreRoot.getChild("tylniepletwy");
        this.tylniepletwy2 = this.tylniepletwy.getChild("tylniepletwy2");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData MizoreRoot = modelPartData.addChild("MizoreRoot", ModelPartBuilder.create(), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData gowa = MizoreRoot.addChild("gowa", ModelPartBuilder.create().uv(16, 10).cuboid(3.0F, -3.0F, -3.0F, 3.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData pysczek = gowa.addChild("pysczek", ModelPartBuilder.create().uv(18, 21).cuboid(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, 0.0F, 0.0F));

        ModelPartData tulow = MizoreRoot.addChild("tulow", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -4.0F, -4.0F, 7.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData przedogon = MizoreRoot.addChild("przedogon", ModelPartBuilder.create().uv(0, 10).cuboid(-6.0F, -3.0F, -3.0F, 4.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData ogon = MizoreRoot.addChild("ogon", ModelPartBuilder.create().uv(10, 21).cuboid(-8.0F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData lapa1 = MizoreRoot.addChild("lapa1", ModelPartBuilder.create().uv(0, 17).cuboid(1.0F, -1.0F, -7.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData lapa2 = MizoreRoot.addChild("lapa2", ModelPartBuilder.create().uv(10, 17).cuboid(1.0F, -1.0F, -7.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 9.0F));

        ModelPartData tylniepletwy = MizoreRoot.addChild("tylniepletwy", ModelPartBuilder.create(), ModelTransform.of(-8.0F, 0.0F, -3.0F, 0.0F, -0.5236F, 0.0F));

        ModelPartData cube_r1 = tylniepletwy.addChild("cube_r1", ModelPartBuilder.create().uv(20, 17).cuboid(0.0F, -1.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, 3.0F, 0.0F, -0.1745F, 0.0F));

        ModelPartData tylniepletwy2 = tylniepletwy.addChild("tylniepletwy2", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, 0.0F));

        ModelPartData cube_r2 = tylniepletwy2.addChild("cube_r2", ModelPartBuilder.create().uv(0, 21).cuboid(0.0F, -1.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -1.0F, 0.0F, -1.2217F, 0.0F));
        return TexturedModelData.of(modelData, 34, 34);
    }

    @Override
    public void setAngles(MizoreEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);


        this.animateMovement(MizoreAnimations.ANIM_MIZORE_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        //this.updateAnimation(entity.idleAnimationState, MizoreAnimations.ANIM_MIZORE_IDLE, ageInTicks, 1f);
    }
    private void setHeadAngles(float headYaw, float headPitch){
        headYaw = MathHelper.clamp(headYaw, -30.0F, 30.0F);
        headPitch = MathHelper.clamp(headPitch, -25.0F, 45.0F);

        this.gowa.yaw = headYaw * 0.017453292F;
        this.gowa.pitch = headPitch * 0.017453292F;

    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        MizoreRoot.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return MizoreRoot;
    }
}
