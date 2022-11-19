package io.github.tj20201.rgblamps;

import io.github.tj20201.rgblamps.block.CustomLampBlock;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Block;

@Environment(EnvType.CLIENT)
public class RGBLampsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            int clr = state.get(CustomLampBlock.COLOR);
            if (clr == 1) { // 1: Red
                return 0xFF0000;
            } else { if (clr == 2) { // 2: Green
                return 0x009900;
            } else { if (clr == 3) { // 3: Purple
                return 0xAA22FF;
            } else { if (clr == 4) { // 4: Cyan
                return 0x9999FF;
            } else { if (clr == 5) { // 5: Light Gray
                return 0x999999;
            } else { if (clr == 6) { // 6: Gray
                return 0x555555;
            } else { if (clr == 7) { // 7: Pink
                return 0xFF9999;
            } else { if (clr == 8) { // 8: Lime
                return 0x99FF99;
            } else { if (clr == 9) { // 9: Yellow
                return 0xFFCC33;
            } else { if (clr == 10) { // 10: Light Blue
                return 0xCCEEFF;
            } else { if (clr == 11) { // 11: Magenta
                return 0xDD88CC;
            } else { if (clr == 12) { // 12: Orange
                return 0x996600;
            } else { if (clr == 13) { // 13: Black
                return 0x222222;
            } else { if (clr == 14) { // 14: Blue
                return 0x116699;
            } else { if (clr == 15) { // 15: Brown
                return 0x552200;
            } else { // 16: White
                return 0xFFFFFF;
            }}}}}}}}}}}}}}}
        }, RGBLamps.ColorLamp);
    }
}
