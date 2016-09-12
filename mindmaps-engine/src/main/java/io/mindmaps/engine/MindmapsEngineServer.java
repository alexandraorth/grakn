/*
 * MindmapsDB - A Distributed Semantic Database
 * Copyright (C) 2016  Mindmaps Research Ltd
 *
 * MindmapsDB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MindmapsDB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with MindmapsDB. If not, see <http://www.gnu.org/licenses/gpl.txt>.
 */
package io.mindmaps.engine;


import io.mindmaps.engine.controller.*;
import io.mindmaps.engine.util.ConfigProperties;

import static spark.Spark.awaitInitialization;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class MindmapsEngineServer {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        ConfigProperties prop = ConfigProperties.getInstance();

        // Listening port
        port(prop.getPropertyAsInt(ConfigProperties.SERVER_PORT_NUMBER));

        // Set the static files folder
        staticFiles.externalLocation(prop.getPath(ConfigProperties.STATIC_FILES_PATH));

        // ----- APIs --------- //

        new RemoteShellController();
        new VisualiserController();
        new GraphFactoryController();
        new ImportController();
        new CommitLogController();
        new TransactionController();
        new StatusController();

        awaitInitialization(); // Wait for server to be initialized
    }
}
