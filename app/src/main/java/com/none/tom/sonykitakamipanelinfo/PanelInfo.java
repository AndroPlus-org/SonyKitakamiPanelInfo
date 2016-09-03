/*
 * Copyright (C) 2016 Tom G.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.none.tom.sonykitakamipanelinfo;

import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Created by Tom G. on 03.09.16.
 */
public class PanelInfo {
    String PanelMan;
    final String SYSFSPATH;

    String readSysfsEntry() {
        String ln = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(SYSFSPATH));

            if ((ln = br.readLine()) != null) {
                br.close();
                return ln;
            }
        } catch (Exception e) {}
        return ln;
    }

    void decodePanelMan(@NonNull String str) {
        if (str.equals("8")) {
            PanelMan = "LG-Display";
        } else if (str.equals("9")) {
            PanelMan = "Japan-Displays Inc.";
        } else {
            PanelMan = "unknown";
        }
    }

    PanelInfo() {
        SYSFSPATH = "/sys/devices/mdss_dsi_panel/panel_id";
        String tmp = readSysfsEntry();
        if (tmp == null) {
            PanelMan = "unknown";
            return;
        }
        decodePanelMan(tmp);
    }
}
