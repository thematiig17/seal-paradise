package pl.theyurii.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import pl.theyurii.SealParadise;

public class ModItems {
    //lista custom przedmiotów dodanych przez moda
    public static final Item TORT_BLOCK = registerItem("tort_block", new Item(new Item.Settings()));
    public static final Item PORTABLE_SEAL_NIKO = registerItem("portable_seal_niko", new Item(new Item.Settings()));

    //pomocnicza funkcja rejestrujaca item w rejestrze
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SealParadise.MOD_ID, name), item);
    }

    public static void registerModItems()
    {
        SealParadise.LOGGER.info("Registering ModItems for" + SealParadise.MOD_ID);

        //dodanie przedmiotu do menu creative, pod zakładką Ingredients
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(TORT_BLOCK);
            entries.add(PORTABLE_SEAL_NIKO);
        });
    }
}
