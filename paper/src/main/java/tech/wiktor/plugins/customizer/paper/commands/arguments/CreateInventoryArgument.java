package tech.wiktor.plugins.customizer.paper.commands.arguments;

import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import eu.okaeri.injector.annotation.Inject;
import panda.std.Option;
import panda.std.Result;
import tech.wiktor.plugins.customizer.paper.modules.inventories.InventoryService;

@ArgumentName("inventory")
public class CreateInventoryArgument implements OneArgument<String> {
    public static final String KEY = "create_inventory";

    @Inject private InventoryService inventoryService;

    @Override
    public Result<String, ?> parse(LiteInvocation liteInvocation, String s) {
        return Option.of(this.inventoryService.getInventoryName(s) == null ? s : null).toResult("To inventory juz istnieje!");
    }
}
