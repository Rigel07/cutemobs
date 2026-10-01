# Cute Mobs - Forge 1.20.1
1. Descarga el MDK de Forge 1.20.1 (47.x) desde files.minecraftforge.net y extráelo en una carpeta vacía.
2. Copia la carpeta src/ de este zip encima de la del MDK (sustituye mods.toml; borra el ExampleMod).
3. En build.gradle del MDK pon mod_id=cutemobs en gradle.properties si usa esa variable.
4. Con Java 17: ./gradlew build  -> el .jar queda en build/libs/
