/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.model.connection;

import org.jkiss.code.NotNull;
import org.jkiss.dbeaver.utils.RuntimeUtils;

import java.util.ArrayList;
import java.util.Collection;

public class NativeClientLocationUtils {
    private NativeClientLocationUtils() {
        // No instances for you!
    }

    @NotNull
    public static Collection<String> unixFoldersToExamine() {
        Collection<String> foldersToExamine = new ArrayList<>();
        foldersToExamine.add("/usr/bin");
        foldersToExamine.add("/usr/local/bin");
        if (RuntimeUtils.isLinux()) {
            foldersToExamine.add("/etc/alternatives");
        } else if (RuntimeUtils.isMacOS()) {
            if (RuntimeUtils.isOSArchAMD64()) {
                foldersToExamine.add("/usr/local/Cellar/"); // homebrew on Intel-based macs
            } else if (RuntimeUtils.isOSArchAArch64()) {
                foldersToExamine.add("/opt/homebrew/bin");
                foldersToExamine.add("/opt/homebrew/Cellar");
                foldersToExamine.add("/opt/homebrew/opt");
            }
        }
        return foldersToExamine;
    }
}
