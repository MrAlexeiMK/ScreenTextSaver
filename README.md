# ScreenTextSaver

Minecraft 1.21.8 Fabric mod.

## Build

`./gradlew build`

The built mod JAR will be in `build/libs`.

## Installation

### Client

1. Place the mod JAR into your `mods` folder.
2. Launch Minecraft with Fabric 1.21.8.
3. Join a server running the `ScreenTextSaver` server mod.

### Server

1. Place the mod JAR into the server `mods` folder.
2. Start the server once to generate the default configuration.
3. Edit the configuration file `screenTextSaver.toml` to set your PostgreSQL connection details:

    ```toml
    databaseUrl = "jdbc:postgresql://localhost:15432/screen_text_saver"
    databaseUser = "postgres"
    databasePassword = "postgres"
    ```

4. Restart server.
5. Clients with the mod can now send messages, which will be stored in the database.
