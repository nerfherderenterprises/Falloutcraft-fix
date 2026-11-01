# Falloutcraft-fix
I liked the mod, so i fixed it so the dev could eventually post it on his curseforge and modrinth, 


Minecraft 1.19.2 Forge mod. Originally created by **TATHAN** https://github.com/TathanDev .

## Building

```bash
./gradlew build
# output: build/libs/
```

Requires JDK 17. First build downloads Forge + mappings (network needed,
subsequent builds are offline).

## Layout

- `src/main/java/fr/tathan/falloutcraft/` — mod source
- `src/main/resources/` — assets, data, `mods.toml`
- `libs/` — bundled mod dependencies (JEI, Terrablender)
- `sources-decompiled/` — *reference only, not built* (original jar,
  decompiled, for comparison during fixes)

## Maintenance fixes (Oct 2026)

<!-- FIX LIST GOES HERE — maintained by panel-me, who has the session history -->

## Configuration

Config file: `config/falloutcraft-common.toml` (generated on first run).

- `radiationRainDamage` — radioactive rain damage toggle
- `itemRadiationDamage` — item radiation toggle
- `usePimpBoy` — Pimp-Boy inventory mechanic master switch
- `pimpBoyUtilisation` — require Pimp-Boy in hand (true) vs. in inventory (false)

## Credits

Original mod by TATHAN. Maintenance fixes by Jimmy (ImperialNerfherder).
