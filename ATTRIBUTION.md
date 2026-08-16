# Legal Notice — Attribution

This standalone mod is a derivative work of **BetterFurnacesReforged** by **Icaro K. Bomfim**.

## Original Work

- **Project:** BetterFurnacesReforged
- **Repository:** https://github.com/Wilyicaro/BetterFurnacesReforged
- **Author:** Icaro K. Bomfim (GitHub: Wilyicaro)
- **License:** MIT
- **License file:** https://github.com/Wilyicaro/BetterFurnacesReforged/blob/main/LICENSE

## Derivative Work

This mod ("CobblestoneGeneratorStandalone") extracts only the **Cobblestone Generator** feature from
the original BetterFurnacesReforged mod. The following changes were made:

1. **Source code extraction** — Only the Java source files and resource files related to the
   Cobblestone Generator were extracted. All other features (furnaces, forges, upgrades, fuel
   verifier, conductor blocks, etc.) were removed.

2. **Upgrade system removal** — The upgrade slot system (Fuel Efficiency Upgrade, Ore Processing
   Upgrade, and related logic) was removed. The Cobblestone Generator now operates without upgrades.

3. **Namespace replacement** — All references to `betterfurnacesreforged` were changed to
   `cobblegenstandalone` (Java package, mod ID, resource namespace, recipe type, etc.).

4. **API adaptation** — The code was adapted from the multi-version Stonecutter source to target
   Minecraft 1.20.1 Forge specifically. This includes resolving Stonecutter conditional compilation
   directives and adjusting API calls for 1.20.1.

5. **Dependency** — This mod depends on **FactoryAPI** (also by Icaro K. Bomfim, MIT-licensed),
   which provides the base classes for inventory management, networking, and GUI rendering used by
   the original mod.

## License Compliance

Both the original BetterFurnacesReforged and this derivative work are distributed under the MIT
License, which permits modification, redistribution, and sublicensing. The original copyright notice
and license are preserved in the [LICENSE](./LICENSE) file.

## Credits

All original code, textures, models, and game design by **Icaro K. Bomfim**.
