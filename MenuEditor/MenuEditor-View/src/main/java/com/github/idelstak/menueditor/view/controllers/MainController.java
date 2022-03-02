/*
 * The MIT License
 *
 * Copyright 2022 Hiram K..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.github.idelstak.menueditor.view.controllers;

import com.github.idelstak.menueditor.model.DesktopEntryFiles;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javafx.fxml.FXML;

/** @author Hiram K. */
public class MainController {
  private static final Logger LOG = Logger.getLogger(MainController.class.getName());

  @FXML
  void initialize() {
    var entryFiles =
        new DesktopEntryFiles(System.getProperty("user.home") + "/.local/share/applications");
    //        new DesktopEntryFiles("/usr/share/applications/");
    //        new DesktopEntryFiles("/usr/local/share/applications/");

    StreamSupport.stream(entryFiles.spliterator(), false)
        .forEachOrdered(
            f -> {
              LOG.log(Level.INFO, "Desktop file: {0}", f);

              try (Stream<String> lines = Files.lines(f.toPath())) {
                lines.forEachOrdered(System.out::println);
              } catch (IOException ex) {
                LOG.log(Level.SEVERE, null, ex);
              }
            });
  }
}
