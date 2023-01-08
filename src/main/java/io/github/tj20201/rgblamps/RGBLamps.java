package io.github.tj20201.rgblamps;

import io.github.tj20201.rgblamps.block.CustomLampBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class RGBLamps implements ModInitializer {

    public static final Block ColorLamp = new CustomLampBlock();

    @Override
    public void onInitialize() {
        Registry.register(Registries.BLOCK, new Identifier("rgblamps", "colorlamp"), ColorLamp);
        Registry.register(Registries.ITEM, new Identifier("rgblamps", "colorlamp"), new BlockItem(ColorLamp, new FabricItemSettings()));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            content.add(ColorLamp);
        });
    }
}
