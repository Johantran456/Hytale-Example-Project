package org.example.plugin.utils;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.math.vector.Vector3f;
import com.hypixel.hytale.math.vector.Vector3i;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.NPCPlugin;

import javax.annotation.Nonnull;

public class EntityUtils {
    public static void spawnNPC(Store<EntityStore> store, World world, Vector3d pos, String npcType, String groupType) {
        world.execute(() -> NPCPlugin.get().spawnNPC(store, npcType, groupType, pos, Vector3f.ZERO));
    }

    public static void spawnNPCToEntity(Store<EntityStore> store, Ref<EntityStore> entityStoreRef, World world, String npcType, String groupType) {
        world.execute(() -> NPCPlugin.get().spawnNPC(store, npcType, groupType, getEntityPos(entityStoreRef).getPosition(), Vector3f.ZERO));
    }

    public static TransformComponent getEntityPos(@Nonnull Ref<EntityStore> entityRef) {
        return entityRef.getStore().getComponent(entityRef, TransformComponent.getComponentType());
    }
}
