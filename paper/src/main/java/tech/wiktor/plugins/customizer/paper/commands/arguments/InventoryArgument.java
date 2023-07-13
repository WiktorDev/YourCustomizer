package tech.wiktor.plugins.customizer.paper.commands.arguments;

import dev.rollczi.litecommands.argument.ArgumentName;
import dev.rollczi.litecommands.argument.simple.OneArgument;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.suggestion.Suggestion;
import eu.okaeri.injector.annotation.Inject;
import panda.std.Option;
import panda.std.Result;
import tech.wiktor.plugins.customizer.paper.modules.inventories.InventoryService;

import java.util.List;
import java.util.stream.Collectors;

@ArgumentName("inventory")
public class InventoryArgument implements OneArgument<String> {
    public static final String KEY = "inventory";

    @Inject private InventoryService inventoryService;

    @Override
    public Result<String, ?> parse(LiteInvocation liteInvocation, String s) {
        return Option.of(this.inventoryService.getInventoryName(s)).toResult("To inventory nie istnieje!");
    }

    @Override
    public List<Suggestion> suggest(LiteInvocation invocation) {
        return this.inventoryService.getInventories().keySet().stream()
                .map(String::valueOf)
                .map(Suggestion::of)
                .collect(Collectors.toList());
    }
}
