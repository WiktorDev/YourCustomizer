package tech.wiktor.plugins.customizer.paper.actions;

import eu.okaeri.injector.Injector;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.injector.annotation.PostConstruct;
import tech.wiktor.plugins.customizer.paper.actions.impl.CloseInventoryAction;
import tech.wiktor.plugins.customizer.paper.actions.impl.ExecuteCommandAction;
import tech.wiktor.plugins.customizer.paper.actions.impl.OpenInventoryAction;
import tech.wiktor.plugins.customizer.paper.actions.impl.SendMessageAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActionService {
    @Inject private Injector injector;
    private final List<Action> actions = new ArrayList<>();

    @PostConstruct
    public void onConstruct() {
        this.register(
                CloseInventoryAction.class,
                SendMessageAction.class,
                OpenInventoryAction.class,
                ExecuteCommandAction.class
        );
    }
    private void register(Class<? extends Action>... classes) {
        Arrays.stream(classes).forEach(aClass -> {
            this.actions.add(this.injector.createInstance(aClass));
        });
    }
    public Action getAction(String identifier) {
        return this.actions.stream()
                .filter(action -> action.getIdentifier().equals(identifier))
                .findAny().orElse(null);
    }
}
