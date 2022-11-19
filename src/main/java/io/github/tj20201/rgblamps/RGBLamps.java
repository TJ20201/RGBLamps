package io.github.tj20201.rgblamps;

import io.github.tj20201.rgblamps.block.CustomLampBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RGBLamps implements ModInitializer {

        public static final Block ColorLamp = new CustomLampBlock();

    @Override
    public void onInitialize() {
        Registry.register(Registry.BLOCK, new Identifier("rgblamps", "colorlamp"), ColorLamp);
        Registry.register(Registry.ITEM, new Identifier("rgblamps", "colorlamp"), new BlockItem(ColorLamp, new FabricItemSettings().group(ItemGroup.REDSTONE)));
    }
}
