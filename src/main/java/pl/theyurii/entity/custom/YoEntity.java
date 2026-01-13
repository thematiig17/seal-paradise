package pl.theyurii.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.AquaticMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import pl.theyurii.entity.ModEntities;
import pl.theyurii.item.ModItems;

public class YoEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    protected final SwimNavigation waterNavigation;
    protected final MobNavigation landNavigation;
    protected final MoveControl waterMoveControl;
    protected final MoveControl landMoveControl;

    public YoEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);

        this.waterNavigation = new SwimNavigation(this, world);
        this.landNavigation = new MobNavigation(this, world);

        this.waterMoveControl = new AquaticMoveControl(this, 85, 60, 0.5F, 0.1F, true);
        this.landMoveControl = new MoveControl(this);

        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 0.0F);
    }

    @Override
    protected void initGoals() {

        this.goalSelector.add(1, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(2, new TemptGoal(this, 0.6D, Ingredient.ofItems(Items.TROPICAL_FISH), false));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createYoAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.35D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0D);
    }

    //animacje
    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 80; //ilosc tickow po ktorych animacja sie zresetuje
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void travel(Vec3d movementInput) {
        //sprawdzenie czy foka jest w wodzie
        if (this.isSubmergedInWater() && this.isLogicalSideForUpdatingMovement()) {

            this.updateVelocity(0.2F, movementInput);
            this.move(net.minecraft.entity.MovementType.SELF, this.getVelocity());

            //opor wody
            this.setVelocity(this.getVelocity().multiply(0.9));

            //powolne opadanie jak stoi w miejscu
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0, -0.005, 0.0));
            }
        } else {
            //w przeciwnym wypadku standardowe chodzenie po ladzie.
            super.travel(movementInput);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()){
            this.setupAnimationStates();
        }
        if (this.isSubmergedInWater() || this.isInLava()) {
            //ustawiamy tryb pływania
            this.navigation = this.waterNavigation;
            this.moveControl = this.waterMoveControl;
        } else {
            //ustawiamy tryb chodzenia
            this.navigation = this.landNavigation;
            this.moveControl = this.landMoveControl;
        }
    }

    @Override
    protected int getNextAirUnderwater(int air) {
        return air; //nie tracimy powietrza pod woda
    }
    @Override
    protected int getNextAirOnLand(int air) {
        return this.getMaxAir(); // Natychmiastowe napełnienie płuc po wyjściu
    }
    @Override
    public boolean isPushedByFluids() {
        return false; //foka nie jest spychana przez nurt wody
    }
    @Override
    public int getMaxAir() {
        return 4800; //4 minuty pod wodą
    }


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.TROPICAL_FISH); //czym mozna karmic foke
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.YO.create(world);
    }

    public static boolean canSpawn(EntityType<YoEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isSolidBlock(world, pos.down())
                && world.getLightLevel(pos) > 0;
    }
}
