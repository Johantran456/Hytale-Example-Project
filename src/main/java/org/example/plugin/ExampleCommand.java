package org.example.plugin;

import com.hypixel.hytale.component.ComponentAccessor;
import com.hypixel.hytale.component.ComponentType;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.protocol.Transform;
import com.hypixel.hytale.protocol.Vector3i;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;
import com.hypixel.hytale.server.core.entity.LivingEntity;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;
import org.example.plugin.utils.EntityUtils;

import javax.annotation.Nonnull;

/**
 * This is an example command that will simply print the name of the plugin in chat when used.
 */
public class ExampleCommand extends CommandBase {

    private final String pluginName;
    private final String pluginVersion;

    public ExampleCommand(String pluginName, String pluginVersion) {
        super("test", "Prints a test message from the " + pluginName + " plugin.");
        this.setPermissionGroup(GameMode.Adventure); // Allows the command to be used by anyone, not just OP
        this.pluginName = pluginName;
        this.pluginVersion = pluginVersion;
    }

    @Override
    protected void executeSync(@Nonnull CommandContext ctx) {
        ctx.sendMessage(Message.raw("Hello from the " + pluginName + " v" + pluginVersion + " plugin!"));
        World world = Universe.get().getDefaultWorld();
        if (world == null) {
            ctx.sendMessage(Message.raw("Error: Default world not found."));
            return;
        }

        Ref<EntityStore> playerRef = ctx.senderAsPlayerRef();
        EntityUtils.spawnNPCToEntity(playerRef.getStore(), playerRef, world, "Boar", null);
    }
}