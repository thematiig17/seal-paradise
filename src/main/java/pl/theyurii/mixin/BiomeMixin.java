package pl.theyurii.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Biome.class)
public class BiomeMixin {
    // Wstrzykujemy się na początek (HEAD) metody canSetIce
    @Inject(method = "canSetIce", at = @At("HEAD"), cancellable = true)
    public void preventFreezingInSealWorld(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {

        // Sprawdzamy, czy "świat" jest pełnym światem gry (a nie np. tylko podglądem)
        if (world instanceof World realWorld) {

            // Sprawdzamy, czy aktualny wymiar to "seal_world"
            // Używamy getValue().getPath(), żeby sprawdzić samą nazwę "seal_world"
            if (realWorld.getRegistryKey().getValue().getPath().equals("seal_world")) {

                // Jeśli tak -> Zwróć FAŁSZ (nie zamrażaj) i przerwij resztę metody
                cir.setReturnValue(false);
            }
        }
    }
}
