package org.patheloper.nms.v1_19_R2;

import net.minecraft.server.level.WorldServer;
import net.minecraft.world.level.block.state.IBlockData;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.DataPaletteBlock;
import org.bukkit.ChunkSnapshot;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_19_R2.CraftChunk;
import org.bukkit.craftbukkit.v1_19_R2.CraftWorld;
import org.patheloper.api.snapshot.NMSInterface;

import java.lang.reflect.Field;

public class OneNineteenTwoNMSInterface implements NMSInterface {

    private static final Field blockIDField;

    static {
        try {
            blockIDField = CraftChunk.class.getDeclaredField("emptyBlockIDs");
            blockIDField.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public ChunkSnapshot getSnapshot(World world, int chunkX, int chunkZ) {
        try {

            WorldServer server = ((CraftWorld) world).getHandle();
            CraftChunk newCraftChunk = new CraftChunk(server, chunkX, chunkZ);

            server.k().a(chunkX, chunkZ, ChunkStatus.o, true);
            DataPaletteBlock<IBlockData> dataDataPaletteBlock = (DataPaletteBlock<IBlockData>) blockIDField.get(newCraftChunk);

            dataDataPaletteBlock.b();
            dataDataPaletteBlock.a();
            ChunkSnapshot chunkSnapshot = newCraftChunk.getChunkSnapshot();
            dataDataPaletteBlock.b();

            return chunkSnapshot;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

}
