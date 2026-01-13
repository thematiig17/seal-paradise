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

public class MizoreEntity extends AnimalEntity {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    protected final SwimNavigation waterNavigation;
    protected final MobNavigation landNavigation;
    protected final MoveControl waterMoveControl;
    protected final MoveControl landMoveControl;

    public MizoreEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.waterNavigation = new SwimNavigation(this, world);
        this.landNavigation = new MobNavigation(this, world);

        this.waterMoveControl = new AquaticMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.landMoveControl = new MoveControl(this);

        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 0.0F);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new AnimalMateGoal(this, 1.15D));
        this.goalSelector.add(2, new TemptGoal(this, 0.6D, Ingredient.ofItems(Items.SALMON), false));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 4.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createMizoreAttributes() {
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
        // Sprawdzamy, czy foka jest w wodzie i czy chce pływać (ma AI)
        if (this.isSubmergedInWater() && this.isLogicalSideForUpdatingMovement()) {

            // Przesuń się w kierunku patrzenia (pływanie 3D)
            this.updateVelocity(0.2F, movementInput);
            this.move(net.minecraft.entity.MovementType.SELF, this.getVelocity());

            // Opór wody (spowalnianie)
            this.setVelocity(this.getVelocity().multiply(0.9));

            // Jeśli nie ma celu, niech powoli opada (symulacja wagi) lub stoi w miejscu
            if (this.getTarget() == null) {
                this.setVelocity(this.getVelocity().add(0.0, -0.005, 0.0));
            }
        } else {
            // Standardowa fizyka lądowa (grawitacja, tarcie bloku)
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
            // JESTEŚMY W WODZIE -> Ustawiamy tryb pływania
            this.navigation = this.waterNavigation;
            this.moveControl = this.waterMoveControl;
        } else {
            // JESTEŚMY NA LĄDZIE -> Ustawiamy tryb chodzenia
            this.navigation = this.landNavigation;
            this.moveControl = this.landMoveControl;
        }
    }

    @Override
    protected int getNextAirUnderwater(int air) {
        return air; // Nie tracimy powietrza pod wodą -> Foka jest nieśmiertelna w wodzie
    }
    @Override
    protected int getNextAirOnLand(int air) {
        return this.getMaxAir(); // Natychmiastowe napełnienie płuc po wyjściu
    }
    @Override
    public boolean isPushedByFluids() {
        return false; // Foka nie jest spychana przez nurt wody (opcjonalne, ułatwia pływanie)
    }
    @Override
    public int getMaxAir() {
        return 4800; // 4800 ticków = 4 minuty pod wodą (domyślnie jest tylko 300 = 15 sekund)
    }


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.SALMON); //czym mozna karmic foke
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.MIZORE.create(world);
    }

    public static boolean canSpawn(EntityType<MizoreEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        // Sprawdzamy tylko, czy blok pod spodem jest pełny/solidny (zamiast wymagać trawy)
        return world.getBlockState(pos.down()).isSolidBlock(world, pos.down())
                && world.getLightLevel(pos) > 0; // Opcjonalnie: musi być minimalnie widno
    }
}
