package cobblegenstandalone;

import cobblegenstandalone.client.screen.CobblestoneGeneratorScreen;
import cobblegenstandalone.init.ModObjects;
import wily.factoryapi.FactoryAPIClient;

public class CobblestoneGeneratorModClient {

    public static void init() {
        FactoryAPIClient.registerMenuScreen(menuScreenRegister -> {
            menuScreenRegister.register(ModObjects.COB_GENERATOR_CONTAINER.get(), CobblestoneGeneratorScreen::new);
        });
    }
}
