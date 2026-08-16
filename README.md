# Cobblestone Generator Standalone

A standalone Minecraft **1.20.1 Forge** mod that extracts the **Cobblestone Generator** feature from
[BetterFurnacesReforged](https://github.com/Wilyicaro/BetterFurnacesReforged) by Icaro K. Bomfim.

## Attribution

This mod is derived from **BetterFurnacesReforged** by **Icaro K. Bomfim** (GitHub: [Wilyicaro](https://github.com/Wilyicaro)),
distributed under the [MIT License](./LICENSE).

- **Original project:** https://github.com/Wilyicaro/BetterFurnacesReforged
- **Original author:** Icaro K. Bomfim
- **License:** MIT (see [LICENSE](./LICENSE))

All source code, textures, models, and recipes were extracted from the original project and adapted
for standalone use. The upgrade slot system has been removed, and the namespace has been changed from
`betterfurnacesreforged` to `cobblegenstandalone`.

This standalone mod also depends on **FactoryAPI** by the same author, which is also MIT-licensed.

## Features

- **Cobblestone Generator block** that generates rocks from lava + water buckets
- **6 rock types** with different generation durations:
  | Rock               | Duration (ticks) |
  |--------------------|------------------|
  | Cobblestone        | 80               |
  | Stone              | 100              |
  | Cobbled Deepslate  | 125              |
  | Blackstone         | 150              |
  | Deepslate          | 150              |
  | Obsidian           | 600              |
- **Cycle through recipes** via a button in the GUI
- **Visual block states** (empty, lava-only, water-only, both fluids)
- **Redstone control** — redstone signal pauses generation
- **Auto-output** removed (was upgrade-gated in the original mod)

## Requirements

- **Minecraft** 1.20.1
- **Forge** 47.3.12 (or later 47.x)
- **FactoryAPI** 1.20.1-2.2.3.2518.0 (or later 2.2.x) — available on [Modrinth](https://modrinth.com/mod/factory-api)

## Building

### Prerequisites

- **JDK 17** (required by Minecraft 1.20.1)
- Internet access (ForgeGradle downloads Minecraft and Forge mappings on first build)

### Steps

1. Ensure JDK 17 is your active Java:
   ```bash
   java -version   # should show 17.x
   ```

2. If `gradlew` is not present, generate the Gradle wrapper:
   ```bash
   # If you have a system Gradle 8.x installed:
   gradle wrapper --gradle-version 8.1.1
   # Otherwise, download and run gradle from https://gradle.org/install/
   ```

3. Build the mod JAR:
   ```bash
   ./gradlew build
   ```

4. The compiled mod JAR will be at:
   ```
   build/libs/cobblegenstandalone-1.0.0.jar
   ```

### Running in dev environment

```bash
./gradlew runClient    # Launches Minecraft with the mod loaded
./gradlew runServer    # Launches a dedicated server with the mod
./gradlew runData      # Generates data files (recipes, blockstates, etc.)
```

## Installation (for players)

1. Install **Minecraft Forge 47.x** for Minecraft 1.20.1
2. Download **FactoryAPI** for 1.20.1 from [Modrinth](https://modrinth.com/mod/factory-api)
3. Download **CobblestoneGeneratorStandalone** JAR
4. Place both JARs in your `.minecraft/mods` folder
5. Launch Minecraft

## Crafting Recipe

```
  C  
I B I
S F S
```

- C = Coal Block
- I = Iron Ingot
- B = Bucket
- S = Smooth Stone
- F = Furnace (vanilla)

## Usage

1. Place the Cobblestone Generator block
2. Right-click with a **Lava Bucket** to insert lava (left slot)
3. Right-click with a **Water Bucket** to insert water (right slot)
4. Right-click the block to open the GUI
5. Use the **arrow button** to cycle through rock types (Cobblestone → Stone → Cobbled Deepslate → Blackstone → Deepslate → Obsidian)
6. Collect the generated rock from the output slot

## Technical Details

- **Mod ID:** `cobblegenstandalone`
- **Main class:** `cobblegenstandalone.CobblestoneGeneratorMod`
- **Recipe type:** `cobblegenstandalone:rock_generating`
- **Custom BlockEntity:** `CobblestoneGeneratorBlockEntity` with 3-slot inventory (lava, water, output)
- **ContainerData:** 3 fields (cobTime, resultType, actualCobTime)
- **Network:** `CobblestoneGeneratorSyncPayload` for recipe cycling

### Changes from the original BetterFurnacesReforged

- **Removed** the entire upgrade slot system (Fuel Efficiency, Ore Processing, etc.)
- **Removed** auto-output (was upgrade-gated)
- **Removed** all other BetterFurnacesReforged blocks/items (furnaces, forges, fuel verifier, conductor blocks)
- **Simplified** ContainerData from 4 to 3 fields
- **Adapted** all code to Minecraft 1.20.1 Forge API (no RecipeHolder, no DataComponents, `use()` not `useWithoutItem()`, etc.)
- **Replaced** `CommonRecipeManager` with vanilla `RecipeManager`
- **Changed** crafting recipe ingredient from `betterfurnacesreforged:fuel_verifier` to `minecraft:furnace`
- **Renamed** all namespaces from `betterfurnacesreforged` to `cobblegenstandalone`
- **Modified** GUI texture to remove upgrade slot visuals

## License

MIT — see [LICENSE](./LICENSE). All credit for the original code and assets goes to Icaro K. Bomfim.
