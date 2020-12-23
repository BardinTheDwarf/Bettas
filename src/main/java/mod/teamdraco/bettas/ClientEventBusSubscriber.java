package mod.teamdraco.bettas;


import mod.teamdraco.bettas.client.renderer.BettaFishRenderer;
import mod.teamdraco.bettas.init.BettasBlocks;
import mod.teamdraco.bettas.init.BettasEntities;
import mod.teamdraco.bettas.item.BettasSpawnEggItem;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Bettas.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(BettasEntities.BETTA_FISH.get(), BettaFishRenderer::new);
        RenderTypeLookup.setRenderLayer(BettasBlocks.DRIED_LEAVES.get(), RenderType.getCutout());
    }

    @SubscribeEvent
    public static void itemColors(ColorHandlerEvent.Item event) {
        ItemColors handler = event.getItemColors();
        IItemColor eggColor = (stack, tintIndex) -> ((BettasSpawnEggItem) stack.getItem()).getColor(tintIndex);
        for (BettasSpawnEggItem e : BettasSpawnEggItem.UNADDED_EGGS) handler.register(eggColor, e);
    }
}
